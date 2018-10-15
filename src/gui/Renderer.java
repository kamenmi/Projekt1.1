package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Renderer {

    private BufferedImage img;
    private Canvas canvas;
    private static final int FPS = 1000 / 30;

    public Renderer(BufferedImage img, Canvas canvas) {
        this.canvas = canvas;
        this.img = img;
        setTimer();
    }

    private void setTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                canvas.getGraphics().drawImage(img, 0, 0, null);
            }
        }, 0, FPS);
    }

    public void clear() {
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);
    }

    public void drawPixel(int x, int y, int color) {
        img.setRGB(x, y, color);
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color) {
        //Zdrojový kod (https://beginnersbook.com/2013/04/try-catch-in-java/)
        try {
            float k = (y2 - y1) / (float) (x2 - x1);
            float q = y1 - k * x1;

            if (Math.abs(k) < 1) {
                if (x1 > x2) {
                    int a = x1;
                    x1 = x2;
                    x2 = a;
                }
                for (int x = x1; x <= x2; x++) {
                    int y = Math.round(k * x + q);
                    drawPixel(x, y, color);
                }
            } else {
                if (y1 > y2) {
                    int a = y1;
                    y1 = y2;
                    y2 = a;
                }

                for (int y = y1; y <= y2; y++) {
                    int x = Math.round((y - q) / k);
                    drawPixel(x, y, color);

                }
            }
        } catch (Exception e) {
            // Zdrojový kod (https://www.mkyong.com/swing/java-swing-how-to-make-a-simple-dialog/)
            JOptionPane.showMessageDialog(null, "Kreslíš mimo okno, debílku!", "Chybička se vloudila", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void drawLineDDA2(int x1, int y1, int x2, int y2, int color) {
        try{
            int dx, dy;
            float k, h, g, x, y;

            dx = x2 - x1;
            dy = y2 - y1;
            k = (float) dy / dx;

            x = x1;
            y = y1;

            if (Math.abs(dx) > Math.abs(dy)) {
                g = 1;
                h = k;

                if (x1 > x2) {
                    int a = x1;
                    x1 = x2;
                    x2 = a;
                    int b = y1;
                    y1 = y2;
                    y2 = b;
                }
                y = y1;
                x = x1;
                for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy)); i++) {
                    drawPixel(Math.round(x), Math.round(y), color);
                    x = x + g;
                    y = y + h;
                }

            } else {
                g = 1 / k;
                h = 1;
                if (y1 > y2) {
                    int a = y1;
                    y1 = y2;
                    y2 = a;
                    int b = x1;
                    x1 = x2;
                    x2 = b;
                }
                y = y1;
                x = x1;
                //jak otočit bod okolo budu - java - anglicky
                for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy)); i++) {
                    drawPixel(Math.round(x), Math.round(y), color);
                    x = x + g;
                    y = y + h;
                }
            }
        }
        catch(Exception e){
            //https://beginnersbook.com/2013/04/try-catch-in-java/
            JOptionPane.showMessageDialog(null, "Maluješ mimo okno", "Chyba",JOptionPane.ERROR_MESSAGE);
        }

    }

    public void drawPolygon(List<Integer> points) {
        clear();
        //drawLine(points.get(0), points.get(1), points.get(2), points.get(3));
        if (points.size() >= 6) {

            for (int i = 0; i < points.size() - 2; i += 2) {
                drawLine(points.get(i), points.get(i + 1), points.get(i + 2), points.get(i + 3), 0xFF4488);
            }
            drawLine(points.get(0), points.get(1), points.get(points.size() - 2), points.get(points.size() - 1), 0xFF4488);
        }
    }


   /* public void drawNuhelnik(int x0, int y0, int konecX, int konecY, int color) {
        double x1 = konecX - x0;
        double y1 = konecY - y0;

        int n = 1;
        int angle = 360/n;

        double x2 = x1 * Math.cos(angle) - y1 * Math.sin(angle));
        double y2 = x1 * Math.sin(angle) + y1 * Math.cos(angle));

        konecX = (int) (x2 + x0);
        konecY = (int) (y2 + y0);

        for(int i =0; angle<=360; i++){
            drawLine(x1,y1,x2,y2,0x222244);
        }
    }*/
}

