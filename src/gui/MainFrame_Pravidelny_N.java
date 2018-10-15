package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MainFrame_Pravidelny_N extends JFrame {

    private BufferedImage img;
    private Canvas canvas;
    private Renderer renderer;
    private List<Integer> cislaPole = new ArrayList<>();
    private int x1, y1, x2, y2;
    private int vrcholy = 3 ;
    private int stav = 1;

    public MainFrame_Pravidelny_N() {
        init();

        img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();

        add(canvas);
        setVisible(true);
        renderer = new Renderer(img, canvas);

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
               // if(e.getKeyCode() == KeyEvent.VK_LEFT)
            }
        });


        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                    polygon(x1,y1,x2,y2,vrcholy);

            }
        });

        canvas.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (stav == 1) {
                    x1 = e.getX();
                    y1 = e.getY();
                    stav ++;
                } else {
                    x2 = e.getX();
                    y2 = e.getY();
                    polygon(x1,y1,x2,y2,vrcholy);
                    stav--;
                }



            }
        });
        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (stav == 2) {
                    renderer.clear();
                    polygon(x1,y1,e.getX(),e.getY(),vrcholy);
                }
            }
        });
    }

    public void init() {
        setTitle("Grafika Projekt");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

   public void polygon(int x1, int y1, int x2, int y2, int count) {
       double x0 = x2 - x1;
       double y0 = y2 - y1;
       double circleRadius = 2 * Math.PI;
       double step = circleRadius / (double) count;
       for (double i = 0; i < circleRadius; i += step) {
           // dle rotační matice
           double x = x0 * Math.cos(step) + y0 * Math.sin(step);
           double y = y0 * Math.cos(step) - x0 * Math.sin(step);
           renderer.drawLineDDA2((int) x0 + x1, (int) y0 + y1,
                   (int) x + x1, (int) y + y1, 0xFF4488);
           x0 = x;
           y0 = y;
       }
   }
}


