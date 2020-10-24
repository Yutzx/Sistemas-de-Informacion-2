import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion
{
    private Connection connection;
    private static String driver="org.postgresql.Driver";
    private static String url="jdbc:postgresql://25.7.49.78:1433;databaseName=tallerbase";
    private static String user="sis2";
    private static String pass="1234";
   
    public Conexion()
    {      
        try
        {
           // Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
          System.out.println(e); 
        }
    }
    
    
    //busca en la base de datos , si no se encuantra lo añade
      public void search(String recipe, int difficulty)
    {
        try 
        {
            Statement statem =   connection.createStatement();
            String query  = "SELECT nombre,puntaje  FROM receta WHERE nombre = '" +recipe+ "'    ;";
            
            ResultSet result = statem.executeQuery(query);
            if(result.next())
            {
              
                query = "UPDATE receta set puntaje = puntaje+1 where nombre = '"+ recipe + "'";
                statem.executeUpdate(query);
              //  connection.commit();
            }
            else
            { 
                query = "INSERT INTO receta (nombre,puntaje) VALUES ('"+ recipe + ',' +difficulty+");" ;
                statem.executeQuery(query);
                connection.commit();
               
            }
            
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        
    }
    
}