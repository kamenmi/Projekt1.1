package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainFrame extends JFrame {

    private BufferedImage img;
    private Canvas canvas;
    private Renderer renderer;

    public MainFrame(){
        init();

        img = new BufferedImage(500,500, BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();

        add(canvas);
        setVisible(true);
        renderer = new Renderer(img, canvas);

        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                  renderer.clear();
                  renderer.drawLineDDA2(250,250,e.getX(),e.getY(),0xFF00FF);
            }
        });

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                renderer.drawPixel(e.getX(),e.getY(),0xFF44FF);
            }
        });

    }
    public void init(){
        setTitle("Grafika Projekt");
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}
