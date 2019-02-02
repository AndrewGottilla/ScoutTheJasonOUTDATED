import static STJ_UserForm.User.getConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Snippets of code for future reference

/*try
        {
            System.out.println(rs.getString(1));
        }
        catch (SQLException ex)
        {
            System.out.println("Error executing query. Here is some info:");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        //ResultSetMetaData rsmd = resultSet.getMetaData();*/

//stmt = conn.createStatement();
            //rs = stmt.executeQuery("SELECT * FROM useraccounts WHERE id='"
            //        + term + "'");
            /*
            
            // rsmd = rs.getMetaData();
            */


/**
 *
 * @author Andrew Godzilla
 */
public class UserTesting {
    
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    public UserTesting()
    {
        conn = null;
        stmt = null;
        rs = null;
    }
    
    public static void testTableData()
    {
        if (getConnection())
        {
            try
            {
                System.out.println("* - user_accounts - *");
                
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM user_accounts");
                
                // Display all inside table
                while (rs.next())
                {
                    System.out.println("___" + rs.getString(2) + " - " + rs.getString(3) + "___\n"
                    + "Wins: " + rs.getString(4) + '\n'
                    + "Losses: " + rs.getString(5) + '\n'
                    + "Level: " + rs.getString(7) + '\n'
                    + "XP: " + rs.getString(8) + '\n');
                }
                
                System.out.println("* - user_credentials - *");
                
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM user_credentials");
                
                // Display
                while (rs.next())
                {
                    System.out.println( "___" + rs.getString(1) + "___\n"
                    + "email: " + rs.getString(2) + '\n'
                    + "pass: " + rs.getString(3) + '\n'
                    + "username: " + rs.getString(4) + '\n' );
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Error during query execution. Here is some info:");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                System.exit(0);
            }
        }
    }
    
    public static boolean testConnection()
    {
        try
        {
            // TODO: Fill conn with proper credentials
            conn = DriverManager.getConnection("jdbc:mysql://serverIP/schema?"+
                                   "user=root&password=Pa$$w0rd");
            System.out.println("\n* - Connection established - *");
            return true;
        }
        catch (SQLException ex)
        {
            // handle any errors
            System.out.println("Connection error. Here is some info:");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
            return false;
        }
    }
    
    public static boolean addUser(String fn, String ln, String user, String email, String pass)
    {
        // TODO: Encryption for passwords
        if (getConnection())
        {
            try
            {
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO user_accounts"
                        + "(FirstName, LastName, Wins, Losses, GamesPlayed, Level, XP, XPNeeded)"
                        + "VALUES ('" + fn + "', '" + ln + "', 0, 0, 0, 1, 0, 0);");
                stmt.executeUpdate("INSERT INTO user_credentials"
                        + "(email, password, username)"
                        + "VALUES ('" + email + "', '" + pass + "', '" + user + "');");
                
                testTableData();
                
                return true;
            }
            catch (SQLException ex)
            {
                System.out.println("Connection error. Here is some info:");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
                return false;
            }
        }
        else return false;
    }
    
    public static boolean loginUser(String username, String pass)
    {
        if (getConnection())
        {
            try
            {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM user_credentials");
                
                while (rs.next())
                {
                    if (username.equals(rs.getString(4)) && pass.equals(rs.getString(3)))
                        return true;
                }
                
                testTableData();
                
                return false;
            }
            catch (SQLException ex)
            {
                System.out.println("Connection error. Here is some info:");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
                return false;
            }
        }
        else return false;
    }
}
