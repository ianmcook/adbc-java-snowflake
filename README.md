# Connecting Java and Snowflake with ADBC

## Instructions

1. Wait until after the ADBC libraries and drivers release 0.20.0 (expected the week of September 8, 2025).
2. Install the [Snowflake ADBC driver](https://arrow.apache.org/adbc/main/driver/snowflake.html) for C/C++ and locate the Snowflake shared library file:
  - On Linux, this file is typically named `libadbc_driver_snowflake.so`.
  - On macOS, this file is typically named `libadbc_driver_snowflake.dylib`.
  - On Windows, this file is typically named `libadbc_driver_snowflake.dll`.
3. Edit the file `Demo.java` to change:
  - The path to your copy of the Snowflake ADBC driver shared library file (line 20)
  - The connection parameters (lines 21-35)
  - The SQL SELECT statement (line 40)
4. In this directory, run `mvn compile exec:exec`
