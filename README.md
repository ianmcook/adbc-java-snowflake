# Connecting Java and Snowflake with ADBC

## Instructions for running this demo

1. Wait until after the ADBC libraries and drivers release 0.20.0 (expected the week of September 8, 2025).
   - If you cannot wait, see the instructions below to build the ADBC libraries locally.

2. Install the [Snowflake ADBC driver](https://arrow.apache.org/adbc/main/driver/snowflake.html) for C/C++ and locate the Snowflake shared library file:
   - On Linux, this file is typically named `libadbc_driver_snowflake.so`.
   - On macOS, this file is typically named `libadbc_driver_snowflake.dylib`.
   - On Windows, this file is typically named `libadbc_driver_snowflake.dll`.

3. Edit the file `Demo.java` to change:
   - The path to your copy of the Snowflake ADBC driver shared library file (line 20)
   - The connection parameters (lines 21-35)
     - See the [supported features of the Snowflake driver](https://arrow.apache.org/adbc/current/driver/snowflake.html#supported-features) for details.
   - The SQL SELECT statement (line 40)

4. In this directory, run `mvn compile exec:exec`

### Instructions for building the ADBC libraries locally

1. Follow the instructions in [Contributing to ADBC](https://github.com/apache/arrow-adbc/blob/main/CONTRIBUTING.md) to set up your development environment. You will need CMake installed. Using conda or mamba is usually the easiest way to install everything you need.

2. Clone the `apache/arrow-adbc` repository and build the C++ driver manager library:
```sh
git clone https://github.com/apache/arrow-adbc.git
cd arrow-adbc
export ADBC_BUILD_STATIC=ON
export ADBC_BUILD_TESTS=OFF
export ADBC_USE_ASAN=OFF
export ADBC_USE_UBSAN=OFF
export BUILD_ALL=OFF
export BUILD_DRIVER_MANAGER=ON
./ci/scripts/cpp_build.sh $(pwd) $(pwd)/build $(pwd)/local
```
3. Ensure `JAVA_HOME` is set. If it's not set, you can find it with:
```sh
java -XshowSettings:properties -version 2>&1 >/dev/null | grep java.home
```

4. Build the JNI libraries:
```sh
./ci/scripts/java_jni_build.sh $(pwd) $(pwd)/java/build $(pwd)/local
```

5. Install the locally built Java libraries:
```sh
pushd java
mvn install -Pjni -DskipTests
popd
```
