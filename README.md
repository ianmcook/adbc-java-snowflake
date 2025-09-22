# Connecting Java and Snowflake with ADBC

## Instructions for running this demo

1. Install the [Snowflake ADBC driver](https://arrow.apache.org/adbc/main/driver/snowflake.html) for C/C++ and locate the Snowflake shared library file:
   - On Linux, this file is typically named `libadbc_driver_snowflake.so`.
   - On macOS, this file is typically named `libadbc_driver_snowflake.dylib`.
   - On Windows, this file is typically named `libadbc_driver_snowflake.dll`.

2. Edit the file `Demo.java` to change:
   - The path to your copy of the Snowflake ADBC driver shared library file (line 20)
   - The connection parameters (lines 21-35)
     - See the [supported features of the Snowflake driver](https://arrow.apache.org/adbc/current/driver/snowflake.html#supported-features) for details.
   - The SQL SELECT statement (line 40)

3. In this directory, run `mvn compile exec:exec`
