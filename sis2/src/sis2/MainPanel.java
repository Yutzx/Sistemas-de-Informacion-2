package sis2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *  Clase que ejecutara todas las funcionalidades del programa
 * @author Camila
 */
public class MainPanel extends JFrame implements ActionListener
{
    
    private JPanel main = new JPanel ();
    private JButton boton1, boton2, boton3, boton4;
    private BaseDeDatos bd;
    /**
     * Inicializa todas las variable (metodo constructor) y asigna funciones a los botones.
     * @param baseD - conexion con la base de datos
     */
    public MainPanel(BaseDeDatos baseD){
        setSize(270, 270);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);
	setResizable(false);
        setTitle("Panel Principal");
        bd = baseD;  

        
        
                boton1 = new JButton("Programar una reunion");                
		boton2 = new JButton("Descagar conversacion");
                boton3 = new JButton("Entrar a una Reunion");
                boton4= new JButton("Compartir pantalla");
                                              	
		ButtonGroup group = new ButtonGroup();
		group.add(boton1);
		group.add(boton2);
		group.add(boton3);
                group.add(boton4);
               
                main.add(boton1);
		main.add(boton2);
		main.add(boton3);
                main.add(boton4);
                
                add(main);
                setVisible(true);
                
    boton1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
            CreaReunionPanel b1 = new CreaReunionPanel(bd);
      } });
    boton2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
            Interfaz2 theinter= new Interfaz2(bd);
      } });
     boton3.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
            JOptionPane.showMessageDialog(null, "aqui va una accion");
      } });
      boton4.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
            JOptionPane.showMessageDialog(null, "aqui va una accion");
      } });
                
    }               

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}