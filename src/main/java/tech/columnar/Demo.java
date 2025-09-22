package tech.columnar;

import org.adbcdrivers.driver.jni.JniDriver;
import org.apache.arrow.adbc.core.AdbcConnection;
import org.apache.arrow.adbc.core.AdbcDatabase;
import org.apache.arrow.adbc.core.AdbcStatement;
import org.apache.arrow.adbc.drivermanager.AdbcDriverManager;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.ipc.ArrowReader;

import java.util.HashMap;
import java.util.Map;

public class Demo {
    private static final String DRIVER_FACTORY = "org.adbcdrivers.driver.jni.JniDriverFactory";

    public static void main(String[] args) throws Exception {
        Map<String, Object> params = new HashMap<>();
        JniDriver.PARAM_DRIVER.set(params, "/path/to/libadbc_driver_snowflake.so");
        params.put("username", "USER");

        // for username/password authentication
        params.put("adbc.snowflake.sql.auth_type", "auth_snowflake");
        params.put("password", "PASS");

        // for JWT authentication
        //params.put("adbc.snowflake.sql.auth_type", "auth_jwt");
        //params.put("adbc.snowflake.sql.client_option.jwt_private_key", "/path/to/rsa_key.p8");

        params.put("adbc.snowflake.sql.account", "ACCOUNT-IDENT");
        params.put("adbc.snowflake.sql.db", "SNOWFLAKE_SAMPLE_DATA");
        params.put("adbc.snowflake.sql.schema", "TPCH_SF1");
        params.put("adbc.snowflake.sql.warehouse", "MY_WAREHOUSE");
        params.put("adbc.snowflake.sql.role", "MY_ROLE");
        try (BufferAllocator allocator = new RootAllocator();
             AdbcDatabase db = AdbcDriverManager.getInstance().connect(DRIVER_FACTORY, allocator, params);
             AdbcConnection conn = db.connect();
             AdbcStatement stmt = conn.createStatement()) {
            stmt.setSqlQuery("SELECT C_CUSTKEY, C_NAME, C_ADDRESS FROM CUSTOMER LIMIT 5");
            try (AdbcStatement.QueryResult result = stmt.executeQuery()) {
                ArrowReader reader = result.getReader();
                while (reader.loadNextBatch()) {
                    System.out.println(reader.getVectorSchemaRoot().contentToTSVString());
                }
            }
        }
    }
}
