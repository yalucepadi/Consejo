
package MethodConnection;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionUtil {

public Connection connection;

public Connection getConnection(){
   String dbName="dataConsejo";
   String userName="root";
   String password="";
   
    try {
           Class.forName("com.mysql.jdbc.Driver");
           Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
    } catch (Exception e) 
    {e.printStackTrace();}
           
    return  connection;
    }
    
    
   

   
      
        

}



