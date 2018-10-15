package App;

import gui.MainFrame_Polygon;

import javax.swing.*;

public class App_Polygon {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame_Polygon().setVisible(true); //otevre okno, ktere lze videt, z tridy MainFrame_Pravidelny_N - pro vykresleni cary
            }
        });
    }
}
