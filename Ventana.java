package ProyectoU2;

/**
 *
 * @author Aldair Torres
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ventana extends JPanel implements ActionListener,
        MouseListener, MouseWheelListener, KeyListener {

    // ventana
    private JFrame ventana;

    // contenedor
    private Container contenedor;

    //declarar la figura
    private Punto figura[], resta[];

    //Barra Menu
    public JMenuBar barra;
    public JMenu menu;
    public JMenuItem ma1, ma2, ma3, ma4, ma5, ma6, ma7, ma8, ma9, mar, mas;

    //Botones
    public JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, br, bs;

    /**
     * Crear una ventana para representar la figura
     *
     * @param titulo Titulo de la ventana
     * @param figura Figura representada por puntos
     */
    public Ventana(String titulo, Punto figura[]) {

        // inicializar la ventana
        ventana = new JFrame(titulo);

        // definir tamaño a ventana
        ventana.setSize(670, 660);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(true);

        contenedor = ventana.getContentPane();
        contenedor.setSize(670, 660);


        // agregar la ventana en el contenedor
        contenedor.add(this, BorderLayout.CENTER);
        this.figura = figura;
        resta = figura;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        //Añadiendo acciones con el mouse
        addMouseListener(this);
        addMouseWheelListener(this);

        //Añadiendo acciones con el teclado
        contenedor.setFocusable(true);
        ventana.setFocusable(true);
        contenedor.addKeyListener(this);
        ventana.addKeyListener(this);
        barra();
        botones();
    }

    /**
     * Metodo inicializa la barra
     */
    public void barra() {
        //agregar la barra en el contenedor
        barra = new JMenuBar();
        menu = new JMenu("Menu");
        menu.setBorder(BorderFactory.createLineBorder(Color.black));
        ma1 = new JMenuItem("Traslacion");
        ma2 = new JMenuItem("Rotación Derecha");
        ma3 = new JMenuItem("Rotación Izquierda");
        ma4 = new JMenuItem("Escalar Matriz");
        ma5 = new JMenuItem("Escalar Vector");
        ma6 = new JMenuItem("Reflexión Horizontal");
        ma7 = new JMenuItem("Reflexión Vertical");
        ma8 = new JMenuItem("Reflexión Horizontal y Vertical");
        ma9 = new JMenuItem("Deformación");
        mar = new JMenuItem("Restablecer Figura");
        mas = new JMenuItem("Salir");
        add(barra);
        barra.add(menu);

        //Agregar las opciones al menu
        menu.add(ma1);
        menu.add(ma2);
        menu.add(ma3);
        menu.add(ma4);
        menu.add(ma5);
        menu.add(ma6);
        menu.add(ma7);
        menu.add(ma8);
        menu.add(ma9);
        menu.add(mar);
        menu.add(mas);
        ma1.addActionListener(this);
        ma2.addActionListener(this);
        ma3.addActionListener(this);
        ma4.addActionListener(this);
        ma5.addActionListener(this);
        ma6.addActionListener(this);
        ma7.addActionListener(this);
        ma8.addActionListener(this);
        ma9.addActionListener(this);
        mar.addActionListener(this);
        mas.addActionListener(this);
    }

    /**
     * Metodo que inicializa los botones
     */
    public void botones() {
        //Agregar botones al contenedor
        b1 = new JButton("Traslación");
        add(b1);
        b1.addActionListener(this);
        b2 = new JButton("Rotación Derecha");
        add(b2);
        b2.addActionListener(this);
        b3 = new JButton("Rotación Izquierda");
        add(b3);
        b3.addActionListener(this);
        b4 = new JButton("Escalar Matriz");
        add(b4);
        b4.addActionListener(this);
        b5 = new JButton("Escalar Vector");
        add(b5);
        b5.addActionListener(this);
        b6 = new JButton("Reflexión Horizontal");
        add(b6);
        b6.addActionListener(this);
        b7 = new JButton("Reflexión Vertical");
        add(b7);
        b7.addActionListener(this);
        b8 = new JButton("Reflexión Horizontal y Vertical");
        add(b8);
        b8.addActionListener(this);
        b9 = new JButton("Deformación");
        add(b9);
        b9.addActionListener(this);
        br = new JButton("Restablecer");
        add(br);
        br.addActionListener(this);
        bs = new JButton("Salir");
        add(bs);
        bs.addActionListener(this);
    }

    /**
     * Metodo que pinta la figura
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Dibujar la figura original
        g.setColor(Color.decode("#a22322"));
        this.dibujar(g);

    }

    /**
     * Este metodo captura los eventos tomados ya sea por el menu o por el boton
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ma1) {
            int x = 0, y = 0;

            try {
                x = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Ingrese el valor de traslado en X",
                        "Traslación", JOptionPane.INFORMATION_MESSAGE));
                y = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Ingrese el valor de traslado en Y",
                        "Traslación", JOptionPane.INFORMATION_MESSAGE));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese numeros enteros",
                        "Traslación", JOptionPane.INFORMATION_MESSAGE);
            }
            this.traslacion(x, y);
            ventana.repaint();
        }

        if (e.getSource() == ma2) {
            double angulo = 0;
            try {
                JOptionPane.showMessageDialog(null, "Ingrese en Decimales",
                        "Rotación Derecha", JOptionPane.INFORMATION_MESSAGE);
                angulo = Double.parseDouble(JOptionPane.showInputDialog(null,
                        "Ingrese el valor del angulo",
                        "Rotación Derecha", JOptionPane.INFORMATION_MESSAGE));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese numeros decimales",
                        "Rotacion Derecha", JOptionPane.INFORMATION_MESSAGE);
            }
            this.rotacionder(angulo);
            repaint();
        }

        if (e.getSource() == ma3) {
            double angulo = 0;

            try {
                JOptionPane.showMessageDialog(null, "Ingrese en Decimales",
                        "Rotación Izquierda", JOptionPane.INFORMATION_MESSAGE);
                angulo = Double.parseDouble(JOptionPane.showInputDialog(null,
                        "Ingrese el valor del angulo",
                        "Rotación Izquierda", JOptionPane.INFORMATION_MESSAGE));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese numeros decimales",
                        "Rotacion Izquierda", JOptionPane.INFORMATION_MESSAGE);
            }

            this.rotacionizq(angulo);
            repaint();
        }

        if (e.getSource() == ma4) {
            float x = 0, y = 0;

            try {
                JOptionPane.showMessageDialog(null, "Ingrese en Decimales",
                        "Escalar Matriz", JOptionPane.INFORMATION_MESSAGE);
                x = Float.parseFloat(JOptionPane.showInputDialog(null,
                        "Ingrese el valor de escalado en X",
                        "Escalar Matriz", JOptionPane.INFORMATION_MESSAGE));
                y = Float.parseFloat(JOptionPane.showInputDialog(null,
                        "Ingrese el valor de escalado en Y",
                        "Escalar Matriz", JOptionPane.INFORMATION_MESSAGE));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese numeros decimales",
                        "Escalar Matriz", JOptionPane.INFORMATION_MESSAGE);
            }
            this.escalar(x, y);
            repaint();
        }

        if (e.getSource() == ma5) {
            float v = 0;

            try {
                JOptionPane.showMessageDialog(null, "Ingrese en Decimales",
                        "Escalar Vector", JOptionPane.INFORMATION_MESSAGE);
                v = Float.parseFloat(JOptionPane.showInputDialog(null,
                        "Ingrese el valor de escalado",
                        "Escalar Vector", JOptionPane.INFORMATION_MESSAGE));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese numeros decimales",
                        "Escalar Vector", JOptionPane.INFORMATION_MESSAGE);
            }

            this.escalar(v);
            repaint();
        }

        if (e.getSource() == ma6 || e.getSource() == b6) {
            this.reflexionx();
            repaint();
        }

        if (e.getSource() == ma7 || e.getSource() == b7) {
            this.reflexiony();
            repaint();
        }

        if (e.getSource() == ma8 || e.getSource() == b8) {
            this.reflexionxy();
            repaint();
        }

        if (e.getSource() == ma9) {
            double dx=0, dy=0;

            try {
                JOptionPane.showMessageDialog(null, "Ingrese en Decimales",
                        "Deformación", JOptionPane.INFORMATION_MESSAGE);
                dx = Float.parseFloat(JOptionPane.showInputDialog(null,
                        "Ingrese el valor de deformacion en x",
                        "Deformación", JOptionPane.INFORMATION_MESSAGE));
                dy = Float.parseFloat(JOptionPane.showInputDialog(null,
                        "Ingrese el valor de deformacion en y",
                        "Deformación", JOptionPane.INFORMATION_MESSAGE));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese en Decimales",
                        "Deformación", JOptionPane.INFORMATION_MESSAGE);
            }

            this.deformacion(dx, dy);
            repaint();
        }

        if (e.getSource() == mar || e.getSource() == br) { //restablecer figura
            this.restablcer();
            repaint();
        }

        if (e.getSource() == mas || e.getSource() == bs) {
            System.exit(0);
        }

        if (e.getSource() == b1) {
            int x = 100, y = 100;

            this.traslacion(x, y);
            ventana.repaint();
        }

        if (e.getSource() == b2) {
            double angulo = 30;

            this.rotacionder(angulo);
            repaint();
        }

        if (e.getSource() == b3) {
            double angulo = 30;

            this.rotacionizq(angulo);
            repaint();
        }

        if (e.getSource() == b4) {
            float x = .3f, y = .3f;

            this.escalar(x, y);
            repaint();
        }

        if (e.getSource() == b5) {
            float v = .3f;

            this.escalar(v);
            repaint();
        }

        if (e.getSource() == b9) {
            double dx = 50, dy = 40;

            this.deformacion(dx, dy);
            repaint();
        }
    }

    /**
     * Este metodo captura los eventos por el mouse
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            float v = .9f;

            this.escalar(v);
            repaint();
        }
        if (e.getButton() == 3) {
            this.reflexiony();
            repaint();
        }
    }

    /**
     * Este metodo captura los eventos por el mouse
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Este metodo captura los eventos por el mouse
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Este metodo captura los eventos por el mouse
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Este metodo captura los eventos por el mouse
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Este metodo captura los eventos por el mouse(scroll)
     *
     * @param e
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        e.getWheelRotation();
        if (e.getWheelRotation() == -1) {
            int x = -20, y = -20;

            this.traslacion(x, y);
            ventana.repaint();
        } else {
            int x = 20, y = 20;

            this.traslacion(x, y);
            ventana.repaint();
        }
    }

    /**
     * Este metodo es el de traslacion el cual recibe dos datos de tipo entero
     * para su nueva ubicacion
     *
     * @param xf Posicion en x
     * @param yf Posicion en y
     */
    public void traslacion(int xf, int yf) {
        for (Punto punto : figura) {
            //punto x:((x - xf) 
            //punto y: ((y - yf)
            punto.setX((int) (punto.getX() + xf));
            punto.setY((int) (punto.getY() + yf));
        }
    }

    /**
     * Metodo que rota la figura hacia la derecha
     *
     * @param angulo Dato de tipo entero
     */
    public void rotacionder(double angulo) {
        int tx = figura[0].getX(), ty = figura[0].getY();
        for (Punto punto : figura) {
            //punto x:(x - tx) * cos – (b – ty) * sin + tx
            //punto y: (x - ty) * sin + (b – ty) * cos + ty
            punto.setX((int) ((punto.getX() - tx) * Math.cos(Math.toRadians(angulo)) - (punto.getY() - ty) * Math.sin(Math.toRadians(angulo)) + tx));
            //punto y
            punto.setY((int) ((punto.getX() - ty) * Math.sin(Math.toRadians(angulo)) + (punto.getY() - ty) * Math.cos(Math.toRadians(angulo)) + ty));
        }
    }

    /**
     * Metodo que rota la figura hacia la izquierda
     *
     * @param angulo Dato de tipo Entero
     */
    public void rotacionizq(double angulo) {
        int tx = figura[0].getX(), ty = figura[0].getY();

        for (Punto punto : figura) {
            //punto x:((a-tx)cos + (b-ty)(sen)) +tx
            //punto y: (-(a-tx)(sen) + (b-ty)(cos))+ty
            punto.setX((int) ((punto.getX() - tx) * Math.cos(Math.toRadians(angulo)) + (punto.getY() - ty) * Math.sin(Math.toRadians(angulo)) + tx));
            //punto y
            punto.setY((int) (-(punto.getX() - ty) * Math.sin(Math.toRadians(angulo)) + (punto.getY() - ty) * Math.cos(Math.toRadians(angulo)) + ty));
        }
    }

    /**
     * Metodo que escala la figura por matriz
     *
     * @param factorx Dato de tipo flotante
     * @param factory Dato de tipo flotante
     */
    public void escalar(float factorx, float factory) {
        for (Punto punto : figura) {
            punto.setX((int) (punto.getX() * factorx));
            punto.setY((int) (punto.getY() * factory));
        }
    }

    /**
     * Metodo que escala la figura por vector
     *
     * @param factor Dato de tipo flotante
     */
    public void escalar(float factor) {
        for (Punto punto : figura) {
            punto.setX((int) (punto.getX() * factor));
            punto.setY((int) (punto.getY() * factor));
        }
    }

    /**
     * Metodo que refleja la figura de manera horizontal
     */
    public void reflexionx() {
        int tx = figura[0].getX(), ty = figura[0].getY();

        for (Punto punto : figura) {
            //punto x:-(a – tx) + tx
            //punto y: (b – ty) + ty
            punto.setX((int) -(punto.getX() - tx) + tx);
            punto.setY((int) (punto.getY() - ty) + ty);
        }
    }

    /**
     * Metodo que refleja la figura de manera vertical
     */
    public void reflexiony() {
        int tx = figura[0].getX(), ty = figura[0].getY();

        for (Punto punto : figura) {
            //punto x:-(a – tx) + tx
            //punto y: (b – ty) + ty
            punto.setX((int) (punto.getX() - tx) + tx);
            punto.setY((int) -(punto.getY() - ty) + ty);
        }
    }

    /**
     * Metodo que refleja la figura de manera vertical y horizontal
     */
    public void reflexionxy() {
        int tx = figura[0].getX(), ty = figura[0].getY();

        for (Punto punto : figura) {
            //punto x:-(a – tx) + tx
            //punto y: (b – ty) + ty
            punto.setX((int) -(punto.getX() - tx) + tx);
            punto.setY((int) -(punto.getY() - ty) + ty);
        }
    }

    /**
     * Metodo que deforma la figura
     *
     * @param dx Dato de tipo entero
     * @param dy Dato de tipo entero
     */
    public void deformacion(double dx, double dy) {
        int tx = figura[0].getX(), ty = figura[0].getY();

        for (Punto punto : figura) {
            //punto x:((x - tx) + (b - ty) * dx) + tx
            //punto y: ((y - ty) * dy + (b – ty )) + ty
            punto.setX((int) (((punto.getX() - tx) + (punto.getY() - ty) * dx + tx)));
            punto.setY((int) (((punto.getY() - ty) * dy + (punto.getY() - ty) + ty)));
        }
    }

    /**
     * Metodo que se encarga de capturar las teclas tecleadas
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metodo que se encarga de capturar la teclas oprimidas
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_T) {
            this.traslacion(200, 50);
            repaint();
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_E) {
            this.escalar(.6f);
            repaint();
        }

        if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_E) {
            this.escalar(.2f, .2f);
            repaint();
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_R) {
            this.rotacionder(10);
            repaint();
        }

        if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_R) {
            this.rotacionizq(10);
            repaint();
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_X) {
            this.reflexionx();
            repaint();
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
            this.reflexiony();
            repaint();
        }

        if (e.isControlDown() && (e.getKeyCode() == KeyEvent.VK_X && e.getKeyCode() == KeyEvent.VK_Y)) {
            this.reflexionxy();
            repaint();
        }

        if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_E) {
            this.restablcer();
            repaint();
        }
        
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_D){
            this.deformacion(1.3, 1.3);
            repaint();
        }
    }

    /**
     * Metodo que se encarga de capturar las teclas soltadas
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Metodo que se encarga de guardar la figura
     *
     * @param figura
     */
    public void setDibujar(Punto figura[]) {
        this.figura = figura;
    }

    /**
     * Metodo que restablece la figura a como estaba originalmente
     */
    public void restablcer() {
        Punto figura[] = {
            new Punto(230, 480),//1
            new Punto(230, 440),//2
            new Punto(195, 440),//3
            new Punto(185, 430),//4
            new Punto(195, 420),//5
            new Punto(220, 420),//6
            new Punto(220, 410),//7
            new Punto(215, 405),//8
            new Punto(220, 400),//9
            new Punto(215, 395),//10
            new Punto(220, 390),//11
            new Punto(215, 385),//12
            new Punto(220, 380),//13
            new Punto(215, 375),//14
            new Punto(220, 370),//15
            new Punto(215, 365),//16
            new Punto(220, 360),//17
            new Punto(215, 355),//18
            new Punto(220, 350),//19
            new Punto(215, 345),//20
            new Punto(220, 340),//21
            new Punto(215, 335),//22
            new Punto(220, 330),//23
            new Punto(220, 310),//24
            new Punto(235, 275),//25
            new Punto(250, 310),//26
            new Punto(250, 330),//27
            new Punto(255, 335),//28
            new Punto(250, 340),//29
            new Punto(255, 345),//30
            new Punto(250, 350),//31
            new Punto(255, 355),//32
            new Punto(250, 360),//33
            new Punto(255, 365),//34
            new Punto(250, 370),//35
            new Punto(255, 375),//36
            new Punto(250, 380),//37
            new Punto(255, 385),//38
            new Punto(250, 390),//39
            new Punto(255, 395),//40
            new Punto(250, 400),//41
            new Punto(255, 405),//42
            new Punto(250, 410),//43
            new Punto(250, 420),//44
            new Punto(275, 420),//45
            new Punto(285, 430),//46
            new Punto(275, 440),//47
            new Punto(240, 440),//48
            new Punto(240, 480),//49
            new Punto(247, 487),//50
            new Punto(247, 494),//51
            new Punto(240, 501),//52
            new Punto(230, 501),//53
            new Punto(223, 494),//54
            new Punto(223, 487),//55
            new Punto(230, 480),//56
            new Punto(240, 480),//57
        };
        setDibujar(figura);
        repaint();
    }

    /**
     * Metodo que se encarga de dibujar la figura
     *
     * @param g
     */
    private void dibujar(Graphics g) {
        for (int i = 0; i < figura.length - 1; i++) {
            g.drawLine(
                    this.figura[i].getX(),//punto A
                    this.figura[i].getY(),
                    this.figura[i + 1].getX(),//punto B
                    this.figura[i + 1].getY()
            );
        }
    }
}
