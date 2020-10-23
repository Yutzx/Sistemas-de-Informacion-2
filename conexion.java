import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url="jdbc:sqlserver://25.7.49.78:1433;databaseName=tallerbase";
    private static String user="sis2";
    private static String pass="1234";
    public static void main (String args[]){      
        try {
           // Class.forName(driver);
            DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
          System.out.println(e); 
        }
    }  
