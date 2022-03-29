package DBa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconecction {

    public static Connection DBaConecction () {
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/asz?"+"user=root&password=root");
            System.out.println("Connection to Store DB succesfull!");
        }
        catch (SQLException ex) {
            System.out.println("Connection failed...");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }return con;
    }
}
