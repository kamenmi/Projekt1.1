package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class MainFrame_cara extends JFrame {

    private BufferedImage img;
    private Canvas canvas;
    private Renderer renderer;
    private int prvniX, prvniY;

    public MainFrame_cara() { //v konstruktoru si vytvorime dulezite prvky pro kresleni a pote pridame listenery pro praci s mysi pro nasledne kresleni
        init(); // volame metodu init()

        img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB); // vytvarime okno
        canvas = new Canvas();

        add(canvas);
        setVisible(true);
        renderer = new Renderer(img, canvas);

        canvas.addMouseMotionListener(new MouseAdapter() { // zavolame listener pro pohyb - abychom videli caru pri pohybu
        @Override
        public void mouseDragged(MouseEvent e) {
            renderer.clear();
            if (e.isControlDown()) { // pri podrzeni control vyuzivame trivialni algoritmus
                renderer.drawLine(prvniX, prvniY, e.getX(), e.getY(), 0xFFFF44);
            } else { // jinak vyuzivame DDA2 algoritmu - jeste barevne odliseno
                renderer.drawLineDDA2(prvniX, prvniY, e.getX(), e.getY(), 0x55AACD);
            }
        }
    });
        canvas.addMouseListener(new MouseAdapter() { // zavolame mouse listener pro kliknuti - zavedeni pocatecniho bodu, od ktereho budeme kreslit caru
            @Override
            public void mousePressed(MouseEvent e) {
                prvniX = e.getX();
                prvniY = e.getY();
            }
        });
}

    public void init() { // metoda dulezita pro okno aplikace
        setTitle("Grafika Projekt");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

