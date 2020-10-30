import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
public class LoginPanel extends JFrame implements ActionListener {

    private JPanel main = new JPanel();
    private JButton start;
    private JButton close;

    private static String usuario = "";
    private static String contraseña;
    
    private BaseDeDatos bd;

    private JTextField playerfield = new JTextField(20);
    private JTextField playerfield1 = new JTextField(20);
    /**
     * Inicia las variable(metodo constructor)
     */
    public LoginPanel(){
        setTitle("INICIAR SESION");
        setSize(270, 120);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        main.setLayout(new FlowLayout(FlowLayout.CENTER));
        bd = new BaseDeDatos();
        playerfield.setText("Usuario");
        playerfield.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    playerfield.setText("");
                }
            });
        playerfield1.setText("Contraseña");
        playerfield1.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    playerfield1.setText("");
                }
            });

        start = new JButton("Ingresar");
        start.setName("Ingresar");
        start.addActionListener(this);
        start.setForeground(Color.green);
        close = new JButton("Cerrar Sesion");
        close.setName("Cerrar Sesion");
        close.addActionListener(this);
        close.setForeground(Color.red);
        main.add(playerfield);
        main.add(playerfield1);
        main.add(start);
        main.add(close);
        add(main);
        setVisible(true);
        close.setEnabled(false);
        close.setFocusable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e){

        JButton button = (JButton)e.getSource();
        if(button.getName().equals("Ingresar")){
            usuario = playerfield.getText();
            contraseña = playerfield1.getText();            
            boolean ban = bd.comprobarExiste(usuario, contraseña,true);
            if(ban){
                MainPanel mP = new MainPanel(bd);
            }
            sesionIniciada();
        }
        if(button.getName().equals("Cerrar Sesion")){
            bd.actualizarTabla();
            sesionCerrada();
        }
    }
    private void sesionIniciada(){
            start.setEnabled(false);
            start.setFocusable(false);
            close.setEnabled(true);
            close.setFocusable(true);
            playerfield.setEnabled(false);
            playerfield1.setEnabled(false);
            playerfield.setFocusable(false);
            playerfield1.setFocusable(false);
    }
    private void sesionCerrada(){
            start.setEnabled(true);
            start.setFocusable(true);            
            close.setEnabled(false);
            close.setFocusable(false);
            playerfield.setEnabled(true);
            playerfield1.setEnabled(true);
            playerfield.setFocusable(true);
            playerfield1.setFocusable(true);
    }
    public static void main(String args[]){  
            LoginPanel sc = new LoginPanel();            
    }
    
}
