package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainFrame_Pravidelny_N extends JFrame {

    private BufferedImage img;
    private Canvas canvas;
    private Renderer renderer;
    private int x1, y1, x2, y2;
    private int vrcholy = 3; // inicializujeme pocatecni hodnotu - podminka - nemuzeme mit mene nez tri vrcholy
    private int stav = 1; // inicializujeme pocatecni hodnotu

    public MainFrame_Pravidelny_N() {
        init();

        img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();

        add(canvas);
        setVisible(true);
        renderer = new Renderer(img, canvas);

        canvas.addKeyListener(new KeyAdapter() { // zavolame KeyListener, abychom mohli ovladat pocet vrcholu pomoci sipek na klavesni
            @Override
            public void keyPressed(KeyEvent e) {
                renderer.clear(); // vycistime si platno, abychom mohli nastavit novy pocet vrcholu
                if (e.getKeyCode() == 37/*KeyEvent.VK_LEFT*/) { // pokud se zmacne leva sipka
                    if (vrcholy > 3) { // tak se zjisti pokud je vrcholu vice nez tri - pokud by bylo mene - nesel by vykreslit polygon - 3 je minimum
                        vrcholy = vrcholy - 1; // odectu jeden vrchol od promene - co klik - to jeden vrchol
                    }
                }
                if (e.getKeyCode() == 39/*KeyEvent.VK_RIGHT*/) { // pokud se zmackne prava sipka
                    if (vrcholy >= 3) { // zkonrolujeme pokud je vice nez tri vrcholy - nadbytecne - ale kod vypada lepe citelne - jinak to nema hlubsi vyznam
                        vrcholy = vrcholy + 1; // pricteme jednicku do promene - co klik - to jeden vrchol navic
                    }
                }
                polygon(x1, y1, x2, y2, vrcholy); // vykreslime polygon znovu s novym poctem vrcholu
            }
        });


        canvas.addMouseMotionListener(new MouseAdapter() { // pridame Motion Listener, abychom videli animovany vykreslovany polygon pri tvorbe
            @Override
            public void mouseDragged(MouseEvent e) {
                polygon(x1, y1, x2, y2, vrcholy);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (stav == 2) { // kontrolujeme jestli jsme kliknuli po druhe
                    renderer.clear(); // vycistime platno
                    polygon(x1, y1, e.getX(), e.getY(), vrcholy); // a vykreslime finalni polygon
                }
            }
        });

        canvas.addMouseListener(new MouseAdapter() { // pridame Mouse Listener, abychom kontrolovali kliknuti mysi
            @Override
            public void mouseClicked(MouseEvent e) {
                if (stav == 1) { // kontrola prvniho kliknuti
                    x1 = e.getX(); // zjistime souradnice pocatecniho bodu
                    y1 = e.getY();
                    stav = stav + 1; // pricteme jednicku, abychom pri druhem kliku skocili do vetve else
                } else {
                    x2 = e.getX(); // zjistime souradnice koncoveho bodu
                    y2 = e.getY();
                    polygon(x1, y1, x2, y2, vrcholy); // vykreslime
                    stav = stav - 1; // odecteme jednicku, abychom se mohli opet dostat do vetve if a mohli opet zavest novy pocatecni bod - kdyz chceme novy polygon
                }
            }
        });
    }

    public void init() {// metoda dulezita pro okno aplikace
        setTitle("Grafika Projekt");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void polygon(int x1, int y1, int x2, int y2, int vrcholy) { // metoda pro vytvoreni Pravidelneho N Uhelniku
        // Zdrojovy kod (https://stackoverflow.com/questions/22491178/how-to-rotate-a-point-around-another-point) - upraveny a doplneni podle prezentace
        // Zdrojovy kod (https://stackoverflow.com/questions/28688716/finding-the-radius-diameter-area-and-circumference-of-a-circle)
        double x0 = x2 - x1;
        double y0 = y2 - y1;
        double circleRadius = 2 * Math.PI;
        double step = circleRadius / (double) vrcholy;
        for (double i = 0; i < circleRadius; i += step) { //for cyklus pro vykresleni a hledani vrcholu
            // rotační matice - prednaska prezentace
            double x = x0 * Math.cos(step) + y0 * Math.sin(step);
            double y = y0 * Math.cos(step) - x0 * Math.sin(step);
            renderer.drawLineDDA2((int) x0 + x1, (int) y0 + y1, // musime (int) pretypovat hodnoty z double do int kvuli nastaveni metody drawLineDDA2
                    (int) x + x1, (int) y + y1, 0xFF4488);
            x0 = x;
            y0 = y;
        }
    }
}


