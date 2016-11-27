import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Narendra Kadali
 *
 */
public class DBConnectionTester
{

   /**
    * @param args
    * @throws Exception
    */
   public static void main(String[] args) throws Exception
   {
      System.out.println("Initializing DBConnectionTester");
      // First read properties file
      Properties props = new Properties();
      props.load(DBConnectionTester.class.getResourceAsStream("db.properties"));
      int counter = 0;

      // Build DataSource
      DataSource ds = DataSource.buildDataSource(props);
      List<Connection> connection = new ArrayList<>();
      int maxConnections = Integer
               .parseInt(props.getProperty(Constants.KEY_DB_MAX_ACTIVE, Constants.KEY_DEFAULT_DB_MAX_ACTIVE));
      System.out.println("Trying to establish " + maxConnections + " to the DB");
      try
      {
         for (int i = 0; i < maxConnections; i++)
         {
            connection.add(ds.getConnection());
            System.out.println("Successfully established connection:: " + ++counter);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         System.out.println("Trying to close established connections " + counter);
         Thread.sleep(5000);
      }
      finally
      {
         System.out.println("Total number of established connections to DB:: " + counter);
         counter = 0;
         for (Connection conn : connection)
         {
            try
            {
               DataSource.closeConnection(conn);
               System.out.println("Successfully closed connection: " + ++counter);
            }
            catch (SQLException e)
            {
               e.printStackTrace();
            }
         }
         System.out.println("Completed execution of DBConnectionTester");
      }

   }

}