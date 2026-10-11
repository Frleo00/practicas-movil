import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer; 

public class Contador extends JFrame {
    JButton btnIniciar;
    JButton btnLimpiar;
    JLabel lblContador;
    int contador = 10;
    Timer temporizador;
    
    public Contador() {
        setTitle("Contador Regresivo");
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        lblContador = new JLabel("10", SwingConstants.CENTER);
     
        btnIniciar = new JButton("Iniciar");
        btnLimpiar = new JButton("Reiniciar");
        
        JPanel panelBotones =  new JPanel();
        panelBotones.add(btnIniciar);
        panelBotones.add(btnLimpiar);
        
        temporizador = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contador--;
                lblContador.setText(String.valueOf(contador));
                
                if(contador == 0) {
                    temporizador.stop();
                }
            }
        });
        
        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Iniciando cuenta regresiva...");
                if (!temporizador.isRunning()) {
                    temporizador.start(); // Arranca el reloj
                }
            }
        });
        
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                temporizador.stop();
                contador = 10;
                lblContador.setText(String.valueOf(contador)); 
                System.out.println("Contador reiniciado");
            }
        });
        
        add(panelBotones, BorderLayout.NORTH);
        add(lblContador, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        Contador c = new Contador();
        c.setVisible(true); 
    }
}