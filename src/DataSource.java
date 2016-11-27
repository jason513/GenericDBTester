
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 
 * @author Narendra Kadali
 *
 */
public class DataSource
{
   private BasicDataSource ds;
   private static DataSource datasource;

   /**
    * Private constructor to make this class as a singleton
    * 
    * @throws IOException
    * @throws SQLException
    * @throws PropertyVetoException
    */
   private DataSource() throws IOException, SQLException, PropertyVetoException
   {

   }

   /**
    * Setter for basic datasource
    * 
    * @param ds
    */
   private void setDs(BasicDataSource ds)
   {
      this.ds = ds;
   }

   /**
    * Build DataSource
    * 
    * @param props
    * @throws Exception
    */
   public static synchronized DataSource buildDataSource(Properties props) throws Exception
   {
      if (datasource != null)
      {
         throw new Exception("Datasource already intialized.");
      }
      datasource = new DataSource();
      BasicDataSource ds = new BasicDataSource();
      ds = new BasicDataSource();

      // set JDBC URL
      String url = props.getProperty(Constants.KEY_JDBC_URL, Constants.KEY_DEFAULT_JDBC_URL);
      System.out.println("Establishing connection to:: " + url);
      ds.setUrl(url);

      // Set ClassName
      String className = props.getProperty(Constants.KEY_JDBC_DRIVER_CLASSNAME,
               Constants.KEY_DEFAULT_JDBC_DRIVER_CLASSNAME);
      System.out.println("Using " + className + " as JDBC classname");
      ds.setDriverClassName(className);

      // Set userName
      String userName = props.getProperty(Constants.KEY_DB_USERNAME, Constants.KEY_DEFAULT_DB_USERNAME);
      System.out.println("Got DB UserName as:: " + userName);
      ds.setUsername(userName);

      // Set DB Password
      String passwd = props.getProperty(Constants.KEY_DB_PASSWORD, Constants.KEY_DEFAULT_DB_PASSWORD);
      // System.out.println("Got DB Password as:: " + passwd);
      ds.setPassword(passwd);

      // Set Min Idle
      int minIdle = Integer.parseInt(props.getProperty(Constants.KEY_DB_MIN_IDLE, Constants.KEY_DEFAULT_DB_MIN_IDLE));
      System.out.println("Got Min Idle as:: " + minIdle);
      ds.setMinIdle(minIdle);

      // Set max Idle
      int maxIdle = Integer.parseInt(props.getProperty(Constants.KEY_DB_MAX_IDLE, Constants.KEY_DB_MAX_IDLE));
      System.out.println("Got Max Idle as:: " + maxIdle);
      ds.setMaxIdle(maxIdle);

      // Set max active
      int maxActive = Integer.parseInt(props.getProperty(Constants.KEY_DB_MAX_ACTIVE, Constants.KEY_DB_MAX_ACTIVE));
      System.out.println("Got Max Active as:: " + maxActive);
      ds.setMaxActive(maxActive);

      // Set maxwait time
      long maxWaitTime = Long
               .parseLong(props.getProperty(Constants.KEY_DB_MAX_WAIT_TIME, Constants.KEY_DEFAULT_DB_MAX_WAIT_TIME));
      System.out.println("Got Max Wait Time as:: " + maxActive);
      ds.setMaxWait(maxWaitTime);

      // Set test connection
      String testSqlStmt = props.getProperty(Constants.KEY_TEST_SQL_STMT);
      if (testSqlStmt != null && !testSqlStmt.trim().isEmpty())
      {
         System.out.println("Using SQL Test connection stmt:: " + testSqlStmt);
         ds.setValidationQuery(testSqlStmt);
      }

      // Now set it into internal datasource
      datasource.setDs(ds);

      // Return instance of custom datasource
      return datasource;
   }

   /**
    * Get a instance of DataSource
    * 
    * @return
    * @throws Exception
    */
   public static DataSource getInstance() throws Exception
   {
      if (datasource == null)
      {
         throw new Exception(
                  "Datasource not intialized. Intialize the datasource before trying to access instance of it");
      }
      else
      {
         return datasource;
      }
   }

   /**
    * Get a DB connection
    * 
    * @return
    * @throws SQLException
    */
   public Connection getConnection() throws SQLException
   {
      return this.ds.getConnection();
   }

   /**
    * Util for closing a given DB connection
    * 
    * @param connection
    * @throws SQLException
    */
   public static void closeConnection(Connection connection) throws SQLException
   {
      if (!connection.isClosed())
      {
         connection.close();
      }
   }

}