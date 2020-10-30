import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class conexion
{
    private Connection connection;
    private ResultSet result;
    private static String driver="org.postgresql.Driver";
    private String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/kbtkvadi",
                      user = "kbtkvadi",
                    pass = "1sCFv0Nx_85BOI5dLxuzPzTWgxhRbhSh"; 
   
    public conexion()
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
            
            result = statem.executeQuery(query);
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
      
      public ResultSet getResultSet( )
      {
           
          try 
            {
                Statement statem =   connection.createStatement();
                String query  = "SELECT * FROM chat ORDER BY id ;";
              
                result = statem.executeQuery(query);
                    
            } catch (SQLException ex) {
             ex.printStackTrace();
            }
       return result;      
      }

  public void close()
      {
          try
          {
          connection.close();
          } catch (SQLException ex)
          {
            ex.printStackTrace();
           }
       }
      
}
