
/**
 * @author Narendra Kadali
 *
 */
public interface Constants
{

   /*******************************************/
   /*********** Properties keys ***************/
   /*******************************************/
   String KEY_JDBC_URL = "jdbcUrl";
   String KEY_JDBC_DRIVER_CLASSNAME = "jdbcClassName";
   String KEY_DB_USERNAME = "dbUserName";
   String KEY_DB_PASSWORD = "dbPassword";
   String KEY_DB_MIN_IDLE = "minIdle";
   String KEY_DB_MAX_IDLE = "maxIdle";
   String KEY_DB_MAX_ACTIVE = "maxActive";
   String KEY_DB_MAX_WAIT_TIME = "maxWaitTime";
   String KEY_TEST_SQL_STMT = "testSqlStmt";

   /*******************************************/
   /************* Default Values **************/
   /*******************************************/
   String KEY_DEFAULT_JDBC_URL = "jdbc:mysql://localhost/test";
   String KEY_DEFAULT_JDBC_DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";
   String KEY_DEFAULT_DB_USERNAME = "root";
   String KEY_DEFAULT_DB_PASSWORD = "";
   String KEY_DEFAULT_DB_MIN_IDLE = "1";
   String KEY_DEFAULT_DB_MAX_IDLE = "1";
   String KEY_DEFAULT_DB_MAX_ACTIVE = "100";
   String KEY_DEFAULT_DB_MAX_WAIT_TIME = "10000";
}
