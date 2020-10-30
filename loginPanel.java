import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.toedter.calendar.*;
import java.time.LocalTime;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import lu.tudor.santec.jtimechooser.JTimeChooser;
public class creaReunionPanel extends JFrame implements ActionListener {

    private JPanel main = new JPanel();
    private JButton start;
    private JButton close;


    private BaseDeDatos bd;
    private JLabel jLabel1;
    private JLabel jLabel2;
    
    private JDateChooser jCalendar1;
    private JTimeChooser timeButton;
    /**
     * Inicia las variable(metodo constructor)
     */
    public creaReunionPanel(BaseDeDatos bd1){
        setTitle("CREAR REUNION");
        setSize(270, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        jCalendar1 = new JDateChooser();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel1.setText("Ingrese la hora para la reunion");
        jLabel2.setText("Ingrese la fecha para la reunion");
        timeButton = new JTimeChooser();
        main.setLayout(new FlowLayout(FlowLayout.CENTER));
        bd = bd1;     
        start = new JButton("Crear Reunion");
        start.setName("Crear Reunion");
        start.addActionListener(this);
        start.setForeground(Color.green);
        close = new JButton("Cancelar");
        close.setName("Cancelar");
        close.addActionListener(this);
        close.setForeground(Color.red);
        main.add(jLabel1);
        main.add(timeButton);
        main.add(jLabel2);
        main.add(jCalendar1);       
        main.add(start);
        main.add(close);
        add(main);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){

        JButton button = (JButton)e.getSource();
        if(button.getName().equals("Crear Reunion")){
            String horaAsignada = timeButton.getFormatedTime();
            java.util.Date fechaAsignada = jCalendar1.getDate();
            java.util.Date fechaActual = new java.util.Date();
            fechaActual.setHours(0);
            fechaActual.setMinutes(0);
            fechaActual.setSeconds(0);
            System.out.println(fechaActual);
            int idusr = bd.getIdusr();
            if(!fechaAsignada.after(fechaActual)){
                        String s,v;
                        s = fechaAsignada.toString(); 
                        v = fechaActual.toString();
                if(s.compareTo(v) == 0){
                        System.out.println(fechaAsignada.compareTo(fechaActual)+" diferencia de fechas");
                        System.out.println("se logro agregar");
                        LocalTime t = LocalTime.parse( horaAsignada ) ;
                        if(t.compareTo(LocalTime.now()) > 0){
                        bd.crearReunion(idusr,horaAsignada, fechaAsignada);
                        mostrarAlerta("Reunion programada con exito");
                        }else{
                            mostrarAlerta("La hora es menor a la hora actual");
                        }  
                }else{
                mostrarAlerta("Fecha asignada esta antes de la fecha actual");
                }
            }else{
                    bd.crearReunion(idusr,horaAsignada, fechaAsignada);
                    mostrarAlerta("Reunion programada con exito");
            }
            System.out.println(fechaAsignada);
            System.err.println(horaAsignada);
            System.out.println(idusr);
            
            
        }
        if(button.getName().equals("Cancelar")){  
            this.dispose();
        }
    }
    
    public void mostrarAlerta(String msg){     
            JOptionPane.showMessageDialog(new JPanel(), msg, "ALERTA!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
