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
        setTimer(); // volame metodu pro vykresleni za cas
    }

    private void setTimer() { // Nastavujeme automaticke vykresleni za urcity cas - snimky za sekundu
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                canvas.getGraphics().drawImage(img, 0, 0, null);
            }
        }, 0, FPS);
    }

    public void clear() { // Metoda pro vycisteni okna - nastavime novou plochu - nechame ji vykreslit zakladni barvou - v mem pripade cernou
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);
    }

    public void drawPixel(int x, int y, int color) { // Metoda nakresli pixel
        img.setRGB(x, y, color);
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color) { // Metoda vytvorena na bazi Trivialniho algoritmu
        // Zdrojový kod (https://beginnersbook.com/2013/04/try-catch-in-java/)
        // Try a catch - pro pripad, ze uzivatel budu chtit vykreslit mimo kreslime platno
        // Algoritmus psan na cviceni
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
            // Zdrojový kod (https://beginnersbook.com/2013/04/try-catch-in-java/)
            // Zachytime pokus o vykresleni mimo pole a nahlasime chybove hlaseni uzivateli, aby vedel co je spatne
            JOptionPane.showMessageDialog(null, "Maluješ mimo okno", "Chybové hlášení", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void drawLineDDA2(int x1, int y1, int x2, int y2, int color) { // Metoda, ktera implementuje algoritmus DDA2 pro vykresleni usecky
        try { // Opet try a catch pro zachyceni pokusu o vykresleni mimo okno platna
            // Algoritmus psan na cviceni a dodelavan za ukol
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

                for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy)); i++) {
                    drawPixel(Math.round(x), Math.round(y), color);
                    x = x + g;
                    y = y + h;
                }
            }
        } catch (Exception e) {
            //https://beginnersbook.com/2013/04/try-catch-in-java/
            // Zachytime pokus o vykresleni mimo pole a nahlasime chybove hlaseni uzivateli, aby vedel co je spatne
            JOptionPane.showMessageDialog(null, "Maluješ mimo okno", "Chybové hlášení", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void drawPolygon(List<Integer> points) { // Metoda pro hledani bodu polygonu - vstupni hodnota pole, kde jsou ulozeny jednotlive souradnice
        clear(); // Vycistime si latno nez zacneme cokoliv vykreslovat
        if (points.size() >= 6) { // Zkontrolujeme zdali jiz mame dostatecny pocet bodu pro vykresleni alespon zakladniho polygonu - ten je tvoren tremi useckami - tudiz tremi body neboli sesti hodnotami
            for (int i = 0; i < points.size() - 2; i += 2) { // For cyklus, ktery nam prochazi pole a bere si souradnice -- points.size() - 2 -- kontrolujeme zdali jiz nebereme body, ktere jeste nejsou v poli
                drawLine(points.get(i), points.get(i + 1), points.get(i + 2), points.get(i + 3), 0xFF4488); // kreslime caru podle hodnot z pole - bereme podle indexu x1,y1,x2,y2
            }
            drawLine(points.get(0), points.get(1), points.get(points.size() - 2), points.get(points.size() - 1), 0xFF4488); // Kreslime finalni caru mezi poslednim bodeme a prvnim bodem
            // bereme posleni bod z pole - bereme dve posledni hodnoty -- points.size() - 2) (pro x2) -- points.get(points.size() - 1) (pro y2)
        }
    }
}

