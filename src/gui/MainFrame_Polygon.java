package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MainFrame_Polygon extends JFrame {

    private BufferedImage img;
    private Canvas canvas;
    private Renderer renderer;
    private List<Integer> cislaPole = new ArrayList<>();

    public MainFrame_Polygon() { //v konstruktoru si vytvorime dulezite prvky pro kresleni a pote pridame listenery pro praci s mysi pro nasledne kresleni
        init(); // volame metodu init()

        img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB); // vytvarime okno
        canvas = new Canvas();

        add(canvas);
        setVisible(true);
        renderer = new Renderer(img, canvas);

        canvas.addMouseListener(new MouseAdapter() { // vytvorime mouse Listener pro kliknuti mysi
            @Override
            public void mouseClicked(MouseEvent e) {
                cislaPole.add(e.getX()); // zavadime hodnoty X a Y do tabulky cislaPole
                cislaPole.add(e.getY());
                renderer.drawPolygon(cislaPole); // vykreslujeme polygon a hodnoty si bereme z tabulky cislaPole
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
