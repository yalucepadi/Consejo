package MethodConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionUtil {

    public Connection connection;

    public Connection getConnection() {
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        String serverUrl = "jdbc:mysql://localhost/?useUnicode=true&amp;amp;amp;useJDBCCompliantTimezoneShift=true&amp;amp;amp;useLegacyDatetimeCode=false&amp;amp;amp;serverTimezone=UTC";

        //String jdbcUrl = "jdbc:mysql://localhost/dataConsejo";
        String dbName = "dataConsejo";
        String userName = "root";
        String password = "";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean dbFound = false;

       try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/dataConsejo", "root", "");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("ConnectionUtil : "+ex.getMessage());
         
        }

        return conn;
    }

    public static void main(String[] args) {
        ConnectionUtil a = new ConnectionUtil();
        a.getConnection();

    }

}
