package proyect;
/**
 * Downloader.java
 */

import java.io.FileWriter;
import java.sql.ResultSet;

/**
 * Clase que crea un archivo de texto donde según al método invocado, luego de la construcción de la clase,
 * escribe en el mismo los mensajes almacenados en la base de datos o lo deja vacío.
 * 
 * @author Odalis Garcia Vino
 */

public class Downloader {
    ResultSet rst;
    FileWriter file;
    BaseDeDatos conn;

    /**
    * Clase constructor, abre la conexión con la base de datos definida en BaseDeDatos.
    */
    Downloader(BaseDeDatos bd){
        conn = bd ; //Abre una conexión con la base de datos.
    }

    
    /**
    * Escribe los mensajes almacenados en la base de datos, definido en BaseDeDatos, en el archivo: "fileChat.txt".
    */
    public void downloadChat(){
        String hour, username, message;
        try {
            file = new FileWriter("fileChat.txt");
            ResultSet rst = conn.getResultSet();
            while(rst.next()){
                hour     = rst.getString("hour");
                username = rst.getString("username");
                message  = rst.getString("message");
                
                file.write(hour + "-" + username + ":" + "\n"); //Escribe la hora y nombre de usuario.
                file.write(message +"\n"); //Escribe el mensaje.
                file.write("\n");
        }
        file.close();
        conn.close();
        }catch(Exception ex){
            System.out.println("No ha sido posible escribir en el fichero: " + ex.getMessage());
	}
    }
    
    /**
    * Para simular la cancelación de descarga, elimina el contenido del archivo: "fileChat.txt".
    */
    public void cancelDownload(){
        try{
            FileWriter file = new FileWriter("fileChat.txt");
            file.write("");
            file.close();
	    conn.close();
        }catch(Exception ex){
            System.out.println("No ha sido posible escribir en el fichero: " + ex.getMessage());
	}
    }
}
