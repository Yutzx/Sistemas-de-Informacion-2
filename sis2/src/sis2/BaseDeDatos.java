
package sis2;
import java.sql.*;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Clase encargada de la conexión con la base de datos y todas las consultas correspondientes.
 * @author 
 */
public class BaseDeDatos {
    Connection BaseDatos = null;    
    //Donde se localiza la base de datos
    String url="jdbc:postgresql://tuffi.db.elephantsql.com:5432/kbtkvadi";   
    //Credenciales de la base de datos
    String usuario="kbtkvadi";
    String contrasena="1sCFv0Nx_85BOI5dLxuzPzTWgxhRbhSh";
    private int pid;
    /**
     * metodo constructor encargado de iniciar la conexion con la base de datos
     */
    public BaseDeDatos()  {  
    try {
        BaseDatos = DriverManager.getConnection(url, usuario, contrasena);
    } catch (Exception e) {
        System.err.println( e.getMessage() );
        }
    pid = obtenerPid();
    }
    /**
     * Metodo que se encarga de comprobar que existe el usuario y password ingresado son correctos mediante sql
     * @param txt - nombre de usuario a comprobar
     * @param txtPass - contraseña del usuario a comprobar
     * @param est - boolean que controla si esta ingresando o cerrando sesion
     * @return 
     */
     
    public boolean comprobarExiste(String txt, String txtPass,boolean est){
        boolean res=false;
        Statement st = null;
        try {    
        st = BaseDatos.createStatement();
        ResultSet rs = st.executeQuery( ""
            + "SELECT *"
            + "FROM \"usuario\" "
            + "where login = '"+txt+"'" 
            );  
        if(rs.next()) {  
            int id_usuario = rs.getInt(1);
            String password = rs.getString(3);
            if(txtPass.equals(password)){                
                System.out.println("Contraseña correcta");
                registrarLogin(id_usuario,est);
                res = true;
            }else{
                JOptionPane.showMessageDialog(new JPanel(), "La contraseña es incorrecta", "Contraseña Erronea", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(new JPanel(), "El USUARIO NO EXISTE", "USUARIO INCORRECTO", JOptionPane.INFORMATION_MESSAGE);
        }        
        rs.close();
        st.close();
        } catch (Exception f) {
        System.err.println( f.getMessage() );
        }
        return res;
    }
    /**
     * Metodo encargado de insertar en la base de datos los datos del usuario que logro iniciar sesion con exito.
     * @param id_usuario - nombre de usuario a registrar 
     * @param act - estado que verifica si es inicio de seion o cierre de sesion
     */
    private void registrarLogin(int id_usuario,boolean act) {
        Statement st = null;
        try {    
        st = BaseDatos.createStatement();
        java.util.Date fechaActual = new java.util.Date();
        boolean ban = act;       

        st.execute("INSERT INTO \"sesion\"\n"
                + "VALUES (default,"+id_usuario+","+pid+",'"+LocalTime.now()+"','"+fechaActual+"',"+ban+",NULL,NULL);");
        st.close();        
        }catch (SQLException j) {
        System.err.println( j.getMessage() );
        }
        
    }
    /**
     * Metodo para conseguir el valor de PID de la base de datos
     * @return retorna un entero 
     */
    private int obtenerPid() {
        int res = 0;
        Statement st = null;
        try {    
        st = BaseDatos.createStatement();
        ResultSet rs = st.executeQuery("SELECT pg_backend_pid();");
        if(rs.next()) {
            res = rs.getInt(1);
        }        
        rs.close();
        st.close();        
        }catch (SQLException j) {
        System.err.println( j.getMessage() );
        }
        return res;
    }
    /**
     * Metodo encargado de actualizar un inicio de sesion a cierre de sesion.
     */
    public void actualizarTabla(){
       Statement st = null;
        try {    
        st = BaseDatos.createStatement();
        java.util.Date fechaActual = new java.util.Date();
        boolean ban = false;       
        st.execute("UPDATE \"sesion\"\n " + "SET estado = '"+ban+"',horasalida = '"+LocalTime.now()+"', fechasalida = '"+fechaActual+
            "' WHERE pid ="+pid+"AND estado = "+true+";");
        
        st.close();        
        }catch (SQLException j) {
        System.err.println( j.getMessage() );
        }
    }
    /**
     * Metodo encargado de crear la reserva de la reunion en la base de datos.
     * @param id1 - id del usuario que creo la reuion
     * @param hora - hora fijada para la reunion
     * @param fecha - fecha fijada para la reunion
     */
    public void crearReunion(int id1,String hora, java.util.Date fecha){
        Statement st = null;
        try {    
        st = BaseDatos.createStatement();
        
        st.execute("INSERT INTO \"reunion\"\n"
              + "VALUES (default,"+id1+",'"+fecha+"','"+hora+"');");
        st.close();        
        }catch (SQLException j) {
        System.err.println( j.getMessage() );
        }
    }
    /**
     * Metodo que retorna el id del usuario en sesion
     * @return retorna un entero
     */
    public int getIdusr(){
        int res = 0;
        Statement st = null;
        try {    
        st = BaseDatos.createStatement();
        ResultSet rs = st.executeQuery("SELECT id_usr from sesion WHERE pid ="+pid+"AND estado = "+true+";");
        if(rs.next()) {
            res = rs.getInt(1);
        }        
        rs.close();
        st.close();        
        }catch (SQLException j) {
        System.err.println( j.getMessage() );
        }
        return res;
    }
    /**
     * Este metodo se encarga de retorna una consulta de la tabla mensajes
     * @return retorna una consulta.
     */
     public ResultSet getResultSet( )
      {
           ResultSet result=null;
          try 
            {
                Statement statem =   BaseDatos.createStatement();
                String query  = "SELECT * FROM chat ORDER BY id ;";
              
                result = statem.executeQuery(query);
                    
            } catch (SQLException ex) {
             ex.printStackTrace();
            }
       return result;      
      }
     /**
      * Metodo que cierra la conexion con la base de datos  
      */
  public void close()
      {
          try
          {
          BaseDatos.close();
          } catch (SQLException ex)
          {
            ex.printStackTrace();
           }
       }
    
  
}