@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  com.smartservice.nomina startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and COM_SMARTSERVICE_NOMINA_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\smartservicenomina.jar;%APP_HOME%\lib\spring-boot-starter-actuator-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-boot-actuator-docs-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-batch-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-data-jpa-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-integration-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-jersey-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-jooq-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-web-1.5.9.RELEASE.jar;%APP_HOME%\lib\javaslang-2.0.0.jar;%APP_HOME%\lib\commons-lang3-3.4.jar;%APP_HOME%\lib\opencsv-3.9.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-boot-devtools-1.5.9.RELEASE.jar;%APP_HOME%\lib\postgresql-9.4.1212.jre7.jar;%APP_HOME%\lib\spring-boot-starter-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-boot-actuator-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-hateoas-0.23.0.RELEASE.jar;%APP_HOME%\lib\spring-core-4.3.13.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-batch-core-3.0.8.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-aop-1.5.9.RELEASE.jar;%APP_HOME%\lib\hibernate-core-5.0.12.Final.jar;%APP_HOME%\lib\hibernate-entitymanager-5.0.12.Final.jar;%APP_HOME%\lib\javax.transaction-api-1.2.jar;%APP_HOME%\lib\spring-data-jpa-1.11.9.RELEASE.jar;%APP_HOME%\lib\spring-aspects-4.3.13.RELEASE.jar;%APP_HOME%\lib\spring-integration-core-4.3.12.RELEASE.jar;%APP_HOME%\lib\spring-integration-java-dsl-1.2.3.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-validation-1.5.9.RELEASE.jar;%APP_HOME%\lib\jackson-databind-2.8.10.jar;%APP_HOME%\lib\spring-web-4.3.13.RELEASE.jar;%APP_HOME%\lib\jersey-server-2.25.1.jar;%APP_HOME%\lib\jersey-container-servlet-core-2.25.1.jar;%APP_HOME%\lib\jersey-container-servlet-2.25.1.jar;%APP_HOME%\lib\jersey-bean-validation-2.25.1.jar;%APP_HOME%\lib\jersey-spring3-2.25.1.jar;%APP_HOME%\lib\jersey-media-json-jackson-2.25.1.jar;%APP_HOME%\lib\spring-tx-4.3.13.RELEASE.jar;%APP_HOME%\lib\jooq-3.9.6.jar;%APP_HOME%\lib\hibernate-validator-5.3.6.Final.jar;%APP_HOME%\lib\spring-webmvc-4.3.13.RELEASE.jar;%APP_HOME%\lib\javaslang-match-2.0.0.jar;%APP_HOME%\lib\commons-beanutils-1.9.3.jar;%APP_HOME%\lib\tomcat-embed-core-8.5.23.jar;%APP_HOME%\lib\tomcat-embed-el-8.5.23.jar;%APP_HOME%\lib\tomcat-embed-websocket-8.5.23.jar;%APP_HOME%\lib\spring-boot-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-1.5.9.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-logging-1.5.9.RELEASE.jar;%APP_HOME%\lib\snakeyaml-1.17.jar;%APP_HOME%\lib\spring-context-4.3.13.RELEASE.jar;%APP_HOME%\lib\spring-aop-4.3.13.RELEASE.jar;%APP_HOME%\lib\spring-beans-4.3.13.RELEASE.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\tomcat-jdbc-8.5.23.jar;%APP_HOME%\lib\spring-jdbc-4.3.13.RELEASE.jar;%APP_HOME%\lib\com.ibm.jbatch-tck-spi-1.0.jar;%APP_HOME%\lib\xstream-1.4.9.jar;%APP_HOME%\lib\jettison-1.2.jar;%APP_HOME%\lib\spring-batch-infrastructure-3.0.8.RELEASE.jar;%APP_HOME%\lib\aspectjweaver-1.8.13.jar;%APP_HOME%\lib\jboss-logging-3.3.1.Final.jar;%APP_HOME%\lib\hibernate-jpa-2.1-api-1.0.0.Final.jar;%APP_HOME%\lib\javassist-3.21.0-GA.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\jandex-2.0.0.Final.jar;%APP_HOME%\lib\dom4j-1.6.1.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.0.1.Final.jar;%APP_HOME%\lib\spring-data-commons-1.13.9.RELEASE.jar;%APP_HOME%\lib\spring-orm-4.3.13.RELEASE.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.25.jar;%APP_HOME%\lib\spring-messaging-4.3.13.RELEASE.jar;%APP_HOME%\lib\spring-retry-1.2.1.RELEASE.jar;%APP_HOME%\lib\reactive-streams-1.0.0.jar;%APP_HOME%\lib\jackson-annotations-2.8.0.jar;%APP_HOME%\lib\jackson-core-2.8.10.jar;%APP_HOME%\lib\jersey-common-2.25.1.jar;%APP_HOME%\lib\jersey-client-2.25.1.jar;%APP_HOME%\lib\javax.ws.rs-api-2.0.1.jar;%APP_HOME%\lib\jersey-media-jaxb-2.25.1.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\hk2-api-2.5.0-b32.jar;%APP_HOME%\lib\javax.inject-2.5.0-b32.jar;%APP_HOME%\lib\hk2-locator-2.5.0-b32.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\hk2-2.5.0-b32.jar;%APP_HOME%\lib\spring-bridge-2.5.0-b32.jar;%APP_HOME%\lib\jersey-entity-filtering-2.25.1.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.8.10.jar;%APP_HOME%\lib\jackson-jaxrs-json-provider-2.8.10.jar;%APP_HOME%\lib\classmate-1.3.4.jar;%APP_HOME%\lib\spring-expression-4.3.13.RELEASE.jar;%APP_HOME%\lib\commons-collections-3.2.2.jar;%APP_HOME%\lib\tomcat-annotations-api-8.5.23.jar;%APP_HOME%\lib\logback-classic-1.1.11.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.25.jar;%APP_HOME%\lib\log4j-over-slf4j-1.7.25.jar;%APP_HOME%\lib\tomcat-juli-8.5.23.jar;%APP_HOME%\lib\javax.batch-api-1.0.jar;%APP_HOME%\lib\xmlpull-1.1.3.1.jar;%APP_HOME%\lib\xpp3_min-1.1.4c.jar;%APP_HOME%\lib\jersey-guava-2.25.1.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.1.jar;%APP_HOME%\lib\hk2-utils-2.5.0-b32.jar;%APP_HOME%\lib\aopalliance-repackaged-2.5.0-b32.jar;%APP_HOME%\lib\config-types-2.5.0-b32.jar;%APP_HOME%\lib\hk2-core-2.5.0-b32.jar;%APP_HOME%\lib\hk2-config-2.5.0-b32.jar;%APP_HOME%\lib\hk2-runlevel-2.5.0-b32.jar;%APP_HOME%\lib\class-model-2.5.0-b32.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.8.10.jar;%APP_HOME%\lib\logback-core-1.1.11.jar;%APP_HOME%\lib\asm-all-repackaged-2.5.0-b32.jar

@rem Execute com.smartservice.nomina
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %COM_SMARTSERVICE_NOMINA_OPTS%  -classpath "%CLASSPATH%" com.smartservice.nomina.SmartServiceNominaApplication %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable COM_SMARTSERVICE_NOMINA_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%COM_SMARTSERVICE_NOMINA_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
