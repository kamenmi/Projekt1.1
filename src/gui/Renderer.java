package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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

    /* public void drawLineDDA2(int x1, int y1, int x2, int y2, int color) {
         int dx, dy;
         float k, h = 0, g, x, y;

         dx = x2 - x1;
         dy = y2 - y1;
         k = (float) dy/dx;

         x = x1;
         y = y1;

         if (dx > dy) {
             g = 1;
             h = k;

             for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy));i++) {
                 drawPixel(Math.round(x), Math.round(y), color);
                 x = x + g;
                 y = y + h;
             }
         } else {
             g = 1 / k;
             k = 1;
             for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy));i++) {
                 drawPixel(Math.round(x), Math.round(y), color);
                 x = x + g;
                 y = y + h;
             }
         }
 }
     */
    public void drawLineDDA2(int x1, int y1, int x2, int y2, int color) {
        //Zdrojovy kod (http://tech-algorithm.com/articles/drawing-line-using-bresenham-algorithm/)
        //vubec netusim, co to dela
        try {
            int dx = x2 - x1;
            int dy = y2 - y1;
            int x = 0, y = 0, g = 0, h = 0;

            if (dx < 0) {
                x = -1;
            } else if (dx > 0) {
                x = 1;
            }
            if (dy < 0) {
                y = -1;
            } else if (dy > 0) {
                y = 1;
            }
            if (dx < 0) {
                g = -1;
            } else if (dx > 0) {
                g = 1;
            }
            int longest = Math.abs(dx);
            int shortest = Math.abs(dy);
            if (!(longest > shortest)) {
                longest = Math.abs(dy);
                shortest = Math.abs(dx);
                if (dy < 0) {
                    h = -1;
                } else if (dy > 0) {
                    h = 1;
                }
                g = 0;
            }
            int numerator = longest >> 1;
            for (int i = 0; i <= longest; i++) {
                drawPixel(x1, y1, color);
                numerator += shortest;
                if (!(numerator < longest)) {
                    numerator -= longest;
                    x1 += x;
                    y1 += y;
                } else {
                    x1 += g;
                    y1 += h;
                }
            }
        }
        catch(Exception e) {
            // Zdrojový kod (https://www.mkyong.com/swing/java-swing-how-to-make-a-simple-dialog/)
            JOptionPane.showMessageDialog(null, "Kreslíš mimo okno, debílku!", "Chybička se vloudila", JOptionPane.ERROR_MESSAGE);
        }

     /*   public void drawPolygon(List <Integer> points) {
            clear();
            drawLine(points.get(0), points.get(1), points.get(2), points.get(3));
            i += 2;
            // for cyklus po dvou se správným omezením
            drawLine(points.get(i), points.get(i + 1), points.get(i + 2), points.get(i + 3));
        }*/
    }


}

