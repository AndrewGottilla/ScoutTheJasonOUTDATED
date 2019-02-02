package STJ_UserForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
// import java.io.Console; for pass later

/**
 *
 * @author Andrew Gottilla
 */
public class User {
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    public User()
    {
        conn = null;
        stmt = null;
        rs = null;
    }
    
    @Override
    public void finalize()
    {
        // it is a good idea to release resources in a finally{} block
        // in reverse-order of their creation if they are no-longer needed
        // This method will work as a destructor
        
        if (rs != null)
        {
            try
            { rs.close(); }
            catch (SQLException sqlEx) { } // ignore
            rs = null;
        }

        if (stmt != null)
        {
            try
            { stmt.close(); }
            catch (SQLException sqlEx) { } // ignore
            stmt = null;
        }
        
        if (conn != null)
        {
            try
            { conn.close(); }
            catch (SQLException sqlEx) { } // ignore
            conn = null;
        }
        try
        {
            super.finalize();
        }
        catch (Throwable ex) { }
    }
    
    public static boolean getConnection()
    {
        try
        {
            // ----------------------------------------------------------------
            // TODO: Get Connection String from File
            // ----------------------------------------------------------------
            conn = DriverManager.getConnection("jdbc:mysql://serverIP/schema?"+
                                   "user=root&password=Pa$$w0rd");
            System.out.println("\n* - Connection established - *");
            return true;
        }
        catch (SQLException ex)
        {
            //TODO: change these to print to log file instead of console
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
                
                return true;
            }
            catch (SQLException ex)
            {
                //TODO: change these to print to log file instead of console
                // error in execution
                System.out.println("Connection error. Here is some info:");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
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
                
                return false;
            }
            catch (SQLException ex)
            {
                //TODO: change these to print to log file instead of console
                // error in execution
                ex.printStackTrace();
                return false;
            }
        }
        else return false;
    }
}
