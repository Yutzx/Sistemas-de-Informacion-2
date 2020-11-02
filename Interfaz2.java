import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Muestra la interfaz gr?fica para descargar el chat o cancelar dicha descarga
 * @author Guillermo
 */
public class Interfaz2 implements ActionListener{
    private Downloader loader;
    private JFrame theframe;
    private JPanel thepanel;
    private JButton thebutton;
    private JButton thebutton2;
    private JLabel thelabel;
    
    /**
     * Ingresa la base de Datos que deber?a contener el chat
     * @param bd 
     */
    public Interfaz2(BaseDeDatos bd ){
        loader = new Downloader(bd);
        theframe= new JFrame();
        
        thebutton= new JButton("Descargar conversación");
        thebutton.setBounds(30,80,180,25);
        thebutton.addActionListener(this);
        
        thebutton2= new JButton("Cancelar descarga");
        thebutton2.setBounds(230,80,180,25);
        thebutton2.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent a)
          {
		loader.cancelDownload();              
		JOptionPane.showMessageDialog(null," Se ha cancelado la descarga" );
          }
        });
        
        thelabel= new JLabel("Seleccione una opción");
        thelabel.setBounds(140,30,180,35);
        theframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        thepanel= new JPanel();
        thepanel.setLayout(null);
        
        
        thepanel.add(thebutton);
        thepanel.add(thebutton2);
        thepanel.add(thelabel);
        
        theframe.setLocation(500,500);
        theframe.setSize(450,200);
        theframe.add(thepanel);
   
        theframe.setTitle("Historia del chat");
        theframe.setVisible(true);
        
        
    }
    
  /**
   * Activa la acci?n deseada para cada bot?n
   * @param e 
   */

    @Override
    public void actionPerformed(ActionEvent e) {
        loader.downloadChat();
	JOptionPane.showMessageDialog(null," La descarga se ha iniciado" );
    }
    
}
