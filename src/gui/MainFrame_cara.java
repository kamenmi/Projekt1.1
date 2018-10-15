package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MainFrame_cara extends JFrame {
    private BufferedImage img;
    private Canvas canvas;
    private Renderer renderer;
    private List<Integer> cislaPole = new ArrayList<>();
    private int x1, y1, x2, y2;
    private int prvniX, prvniY;
    private int vrcholy = 3;

    public MainFrame_cara() {
        init();

        img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();

        add(canvas);
        setVisible(true);
        renderer = new Renderer(img, canvas);

        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //renderer.clear();
                renderer.clear();
                if(e.isControlDown()){
                    renderer.drawLine(prvniX, prvniY, e.getX(), e.getY(), 0x445544);
                }
                else{
                    renderer.drawLineDDA2(prvniX, prvniY, e.getX(), e.getY(), 0xFFFF11);
                }
            }
        });

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                prvniX = e.getX();
                prvniY = e.getY();
                x1 = e.getX();
                y1 = e.getY();
            }
        });
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                // renderer.drawLineDDA2(x1, y1, x2, y2, 0xFF00FF);
            }
        });
    }

    public void init() {
        setTitle("Grafika Projekt");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

