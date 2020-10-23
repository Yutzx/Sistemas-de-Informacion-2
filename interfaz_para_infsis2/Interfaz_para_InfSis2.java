/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_para_infsis2;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author Guillermo
 */
public class Interfaz_para_InfSis2 implements ActionListener{

    private JFrame theframe;
    private JPanel thepanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel ingreso;
    private JButton boton;
    private JButton boton1;
    private JTextField Textbox = new JTextField(20);
    private JTextField Textbox1 = new JTextField(10);
    
    private String rece;
    private String difi;
    
    public Interfaz_para_InfSis2(){
        
        
        
        theframe= new JFrame();
        
        
        boton= new JButton("Ingresar");
        boton.setBounds(30,80,100,25);
        boton.addActionListener(this);
        
        boton1= new JButton("Siguiente");
        boton1.setBounds(30,110,100,25);
        boton1.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent a)
          {
           rece="";
           difi="";
           Textbox.setText("");
           Textbox1.setText("");
           ingreso.setText("");
          }
        });
        
        
        label1= new JLabel("Ingrese la receta");
        label2= new JLabel("Ingrese la dificultad");
        ingreso= new JLabel("");
        label1.setBounds(30,20,150,25);
        label2.setBounds(30,50,150,25);
        ingreso.setBounds(150,80,180,25);
        
        thepanel= new JPanel();
        thepanel.setLayout(null);
        
        Textbox.setBounds(150,20,165,25);
        Textbox1.setBounds(150,50,165,25);
        
        
        
        
        thepanel.add(Textbox);
        thepanel.add(Textbox1);
        thepanel.add(boton);
        thepanel.add(boton1);
        thepanel.add(label1);
        thepanel.add(label2);
        thepanel.add(ingreso);
        
        theframe.setSize(400,200);
        theframe.add(thepanel);
        theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theframe.setTitle("El Recetario");
        theframe.setVisible(true);
        
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Interfaz_para_InfSis2 inter= new Interfaz_para_InfSis2();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Aquí es donde se coloca todo lo relacionado con los Textboxes y su contenido
        rece= Textbox.getText();
        difi= Textbox1.getText();
        ingreso.setText("La receta ha sido ingresada");
        
    }
  
}
