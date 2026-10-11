import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Paint extends JFrame {
    JComboBox<String> comboColores;
    JPanel panelDibujo, panelSuperior;
    JButton btnLimpiar;
    
    BufferedImage imagen;
    BufferedImage imagenOriginal; 
    Graphics2D g2; 
    
    int xAnterior, yAnterior;
    
    public Paint() {
        setTitle("Paint");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        comboColores = new JComboBox<>();
        comboColores.addItem("Rojo");
        comboColores.addItem("Verde");
        comboColores.addItem("Azul");  
        
        btnLimpiar = new JButton("Limpiar");
                
        panelSuperior = new JPanel();
        panelSuperior.add(comboColores);
        panelSuperior.add(btnLimpiar);
        
        add(panelSuperior, BorderLayout.NORTH);
        
        try {
            imagenOriginal = ImageIO.read(new File("fondo.jpg")); 
            imagen = new BufferedImage(imagenOriginal.getWidth(), imagenOriginal.getHeight(), BufferedImage.TYPE_INT_RGB);
            g2 = imagen.createGraphics();
            g2.drawImage(imagenOriginal, 0, 0, null);
            g2.setColor(Color.red); 
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se encontró 'fondo.jpg'. Asegúrate de ponerla en la carpeta principal del proyecto.");
            imagen = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
            g2 = imagen.createGraphics();
            g2.setColor(Color.white);
            g2.fillRect(0, 0, 400, 400);
            g2.setColor(Color.red);
        }

        panelDibujo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                g.drawImage(imagen, 0, 0, null);            
            }
        };  
        panelDibujo.setBackground(Color.yellow); 
        
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagenOriginal != null) {
                    g2.drawImage(imagenOriginal, 0, 0, null); 
                } else {
                    Color colorActual = g2.getColor();
                    g2.setColor(Color.white);
                    g2.fillRect(0, 0, imagen.getWidth(), imagen.getHeight());
                    g2.setColor(colorActual);
                }
                panelDibujo.repaint();
            }
        });
        
        comboColores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int colorSeleccionado = comboColores.getSelectedIndex();
               switch(colorSeleccionado) {
                   case 0:  g2.setColor(Color.red); break;
                   case 1:  g2.setColor(Color.green); break;                                
                   case 2:  g2.setColor(Color.blue); break;
               }            
            }
        });
        
        panelDibujo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xAnterior = e.getX();
                yAnterior = e.getY();
            }
        });
        
        panelDibujo.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();                            
                g2.drawLine(xAnterior, yAnterior, x, y);  
                
                xAnterior = x;
                yAnterior = y;
                
                panelDibujo.repaint();
            }
        });
        
        add(panelDibujo, BorderLayout.CENTER);        
    }
    
    public static void main(String[] args) {
        Paint d = new Paint();
        d.setVisible(true); 
    }
}