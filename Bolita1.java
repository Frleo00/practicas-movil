import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Cambiamos el nombre de la clase a Bolita1 para que coincida con tu archivo
public class Bolita1 extends JPanel {

    // Variables para Batman
    int x1 = 50, y1 = 50;
    Image img1;
    int ancho1, alto1;
    int offsetX1, offsetY1;
    boolean arrastrando1 = false;

    // Variables para Wonder Woman
    int x2 = 250, y2 = 50; 
    Image img2;
    int ancho2, alto2;
    int offsetX2, offsetY2;
    boolean arrastrando2 = false;

    // El constructor también debe llamarse Bolita1
    public Bolita1() {
        setSize(500, 400); 
        setBackground(Color.green);

        // Cargamos imagen de Batman
        ImageIcon icon1 = new ImageIcon("batman.jpg"); 
        img1 = icon1.getImage();
        ancho1 = icon1.getIconWidth();
        alto1 = icon1.getIconHeight();

        // Cargamos imagen de Wonder Woman
        ImageIcon icon2 = new ImageIcon("wonderwoman.jpg"); 
        img2 = icon2.getImage();
        ancho2 = icon2.getIconWidth();
        alto2 = icon2.getIconHeight();

        MouseAdapter mouse = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getX() >= x2 && e.getX() <= (x2 + ancho2) && e.getY() >= y2 && e.getY() <= (y2 + alto2)) {
                        arrastrando2 = true;
                        offsetX2 = e.getX() - x2;
                        offsetY2 = e.getY() - y2;
                    } 
                    else if (e.getX() >= x1 && e.getX() <= (x1 + ancho1) && e.getY() >= y1 && e.getY() <= (y1 + alto1)) {
                        arrastrando1 = true;
                        offsetX1 = e.getX() - x1;
                        offsetY1 = e.getY() - y1;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                arrastrando1 = false;
                arrastrando2 = false; 
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (arrastrando2 && (e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0) {
                    x2 = e.getX() - offsetX2;
                    y2 = e.getY() - offsetY2;
                    repaint();
                } else if (arrastrando1 && (e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0) {
                    x1 = e.getX() - offsetX1;
                    y1 = e.getY() - offsetY1;
                    repaint();
                }
            }
        };

        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img1, x1, y1, this);
        g.drawImage(img2, x2, y2, this);
    }
}

class Ventana extends JFrame {

    public Ventana() {
        setSize(550, 450); 
        setTitle("Mover Batman y Wonder Woman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Instanciamos la clase Bolita1
        Bolita1 bolita = new Bolita1();
        add(bolita);
    }

    public static void main(String[] args) {
        Ventana v = new Ventana();
        v.setVisible(true);
    }
}