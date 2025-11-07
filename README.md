# Connecting Java and Snowflake with ADBC

## Instructions for running this demo

1. [Install dbc](https://docs.columnar.tech/dbc/getting_started/installation/)

2. Use dbc to install the Snowflake ADBC driver:

   ```sh
   dbc install snowflake
   ```

3. Edit the file `Demo.java` to change:
   - The connection parameters (lines 21-35)
     - See the [supported features of the Snowflake driver](https://arrow.apache.org/adbc/current/driver/snowflake.html#supported-features) for details.
   - The SQL SELECT statement (line 40)

4. In this directory, run `mvn compile exec:exec`
