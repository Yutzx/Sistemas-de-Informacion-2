import java.sql.*;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class BaseDeDatos {
    Connection baseDatos = null;    
    //Donde se localiza la base de datos
    String url="jdbc:postgresql://tuffi.db.elephantsql.com:5432/kbtkvadi";   
    //Credenciales de la base de datos
    String usuario="kbtkvadi";
    String contrasena="1sCFv0Nx_85BOI5dLxuzPzTWgxhRbhSh";
    private int pid;

    public BaseDeDatos()  {  
    try {
        baseDatos = DriverManager.getConnection(url, usuario, contrasena);
    } catch (Exception e) {
        System.err.println( e.getMessage() );
        }
    pid = obtenerPid();
    }
    public boolean comprobarExiste(String txt, String txtPass,boolean est){
        boolean res=false;
        Statement st = null;
        try {    
        st = baseDatos.createStatement();
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
                registrarLogin(id_usuario,txt,txtPass,est);
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

    private void registrarLogin(int id_usuario, String txt, String txtPass,boolean act) {
        Statement st = null;
        try {    
        st = baseDatos.createStatement();
        java.util.Date fechaActual = new java.util.Date();
        boolean ban = act;       

        st.execute("INSERT INTO \"sesion\"\n"
                + "VALUES (default,"+id_usuario+","+pid+",'"+LocalTime.now()+"','"+fechaActual+"',"+ban+",NULL,NULL);");
        st.close();        
        }catch (SQLException j) {
        System.err.println( j.getMessage() );
        }
        
    }

    private int obtenerPid() {
        int res = 0;
        Statement st = null;
        try {    
        st = baseDatos.createStatement();
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
    public void actualizarTabla(){
       Statement st = null;
        try {    
        st = baseDatos.createStatement();
        java.util.Date fechaActual = new java.util.Date();
        boolean ban = false;       
        st.execute("UPDATE \"sesion\"\n " + "SET estado = '"+ban+"',horasalida = '"+LocalTime.now()+"', fechasalida = '"+fechaActual+
            "' WHERE pid ="+pid+"AND estado = "+true+";");
        
        st.close();        
        }catch (SQLException j) {
        System.err.println( j.getMessage() );
        }
    
    }
    public void crearReunion(int id1,String hora, java.util.Date fecha){
        Statement st = null;
        try {    
        st = baseDatos.createStatement();
        
        st.execute("INSERT INTO \"reunion\"\n"
              + "VALUES (default,"+id1+",'"+fecha+"','"+hora+"');");
        st.close();        
        }catch (SQLException j) {
        System.err.println( j.getMessage() );
        }
    }
    public int getIdusr(){
        int res = 0;
        Statement st = null;
        try {    
        st = baseDatos.createStatement();
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
    
    
       public ResultSet getResultSet( )
      {
           ResultSet rs = null;
          try 
            {
                Statement statem =   baseDatos.createStatement();
                String query  = "SELECT * FROM chat ORDER BY id ;";
              
                rs = statem.executeQuery(query);
                    
            } catch (SQLException ex) {
             ex.printStackTrace();
            }
       return rs;      
      }

       
        public void close()
      {
          try
          {
          baseDatos.close();
          } catch (SQLException ex)
          {
            ex.printStackTrace();
           }
       }
  
        
        public Connection getConnection()
        {
            return baseDatos;
        }
}