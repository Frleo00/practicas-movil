import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Galeria extends JFrame{
    JButton btnAtras, btnAdelante, btnPlay;
    JPanel panelBotones, panelImagen;
    ImageIcon [] imagenes;
    String [] titulos; // Nuevo arreglo para los títulos
    int imagenActual;
    JLabel etiquetaImagen;
    JLabel etiquetaTitulo; // Nueva etiqueta para mostrar el texto
    
    Timer timer;
    boolean reproduciendo;
    
    public Galeria(){
        setTitle("Galeria Imagenes");
        setSize(450, 550); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            
        
        panelBotones = new JPanel();        
        panelImagen = new JPanel();        
        btnAtras = new JButton("Atras");
        btnAdelante = new JButton("Adelante");
        btnPlay = new JButton("Reproducir");
        
        // 1. Ampliar arreglos a 10 elementos
        imagenes = new ImageIcon[10];
        titulos = new String[10];
        
        // 2. Cargar imágenes y asignar títulos (Reemplaza los .jpg con los tuyos)
        imagenes[0] = new ImageIcon("batman.jpg");        
        titulos[0] = "1. Batman";
        
        imagenes[1] = new ImageIcon("superman.jpg");
        titulos[1] = "2. Superman";
        
        imagenes[2] = new ImageIcon("flash.jpg");
        titulos[2] = "3. Flash";
        
        imagenes[3] = new ImageIcon("wonderwoman.jpg");
        titulos[3] = "4. Wonder Woman";
        
        imagenes[4] = new ImageIcon("aquaman.jpg");
        titulos[4] = "5. Aquaman";
        
        imagenes[5] = new ImageIcon("cyborg.jpg");
        titulos[5] = "6. Cyborg";
        
        imagenes[6] = new ImageIcon("greenlantern.jpg");
        titulos[6] = "7. Green Lantern";
        
        imagenes[7] = new ImageIcon("shazam.jpg");
        titulos[7] = "8. Shazam";
        
        imagenes[8] = new ImageIcon("nightwing.jpg");
        titulos[8] = "9. Nightwing";
        
        imagenes[9] = new ImageIcon("joker.jpg");
        titulos[9] = "10. The Joker";
        
        // Configurar etiqueta del título
        etiquetaTitulo = new JLabel("", SwingConstants.CENTER);
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 24)); // Texto más grande
        
        etiquetaImagen = new JLabel();
        panelImagen.add(etiquetaImagen);
        
        imagenActual = 0;
        mostrarImagen();
        
        // 3. Reemplazar el "2" estático por límites dinámicos
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(imagenActual == 0){
                        imagenActual = imagenes.length - 1; // Manda al final
                    }else{
                        imagenActual--;
                    }
                    mostrarImagen();
            }
        });
        
        btnAdelante.addActionListener(e->{
             if(imagenActual == imagenes.length - 1){ // Detecta el final automáticamente
                        imagenActual = 0;
                    }else{
                        imagenActual++;
                    }
                    mostrarImagen();
        });
        
        btnPlay.addActionListener(e->{
            if(!reproduciendo){
                btnPlay.setText("Detener");
                reproduciendo = true;
                timer.start();
            }else{                
                btnPlay.setText("Reproducir");
                reproduciendo = false;
                timer.stop();
            }            
        });
        
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(imagenActual == imagenes.length - 1){
                        imagenActual = 0;
                    }else{
                        imagenActual++;
                    }
                    mostrarImagen();
            }
        });
        
        panelBotones.add(btnAtras);
        panelBotones.add(btnAdelante);        
        panelBotones.add(btnPlay);

        // Agregamos el título en la parte superior (NORTH)
        add(etiquetaTitulo, BorderLayout.NORTH);
        add(panelImagen, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);        
    }

    public static void main(String[] args) {
        Galeria g = new Galeria();
        g.setVisible(true); 
    }
    
     public void mostrarImagen() {
        // Actualizar el texto del título
        etiquetaTitulo.setText(titulos[imagenActual]);
         
        ImageIcon icono = imagenes[imagenActual];
        // Prevención de error por si la imagen no se encuentra en la carpeta
        if (icono.getIconWidth() == -1) {
             etiquetaImagen.setIcon(null);
             etiquetaImagen.setText("Imagen no encontrada");
             return;
        } else {
             etiquetaImagen.setText(""); // Limpiar texto de error si existe
        }

        Image imagen = icono.getImage();
        Image imagenEscalada = imagen.getScaledInstance(
                400,
                400,
                Image.SCALE_SMOOTH
        );

        etiquetaImagen.setIcon(new ImageIcon(imagenEscalada));
    }
}