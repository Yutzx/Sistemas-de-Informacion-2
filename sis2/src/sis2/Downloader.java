/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sis2;


import java.io.FileWriter;
import java.sql.ResultSet;

/**
  * @author Odalis Garcia Vino
 */
public class Downloader {
    ResultSet rst;
    FileWriter file;
    BaseDeDatos conn;
    Downloader(BaseDeDatos bd){
        conn = bd;
    }
    
    public void downloadChat(){
        String hour, username, message;
        try {
            file = new FileWriter("fileChat.txt");
            ResultSet rst = conn.getResultSet();
            while(rst.next()){
                hour     = rst.getString("hour");
                username = rst.getString("username");
                message  = rst.getString("message");
                
                file.write(hour + "-" + username + ":" + "\n");
                file.write(message +"\n");
                file.write("\n");
        }
                    
        file.close();

        }catch(Exception ex){
            System.out.println("No ha sido posible escribir en el fichero: " + ex.getMessage());
	}
        
    }
    public void cancelDownload(){
        try{
            FileWriter file = new FileWriter("fileChat.txt");
            file.write("");
            file.close();
        }catch(Exception ex){
            System.out.println("No ha sido posible escribir en el fichero: " + ex.getMessage());
	}
    }
}