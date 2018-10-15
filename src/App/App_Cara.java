package App;

import gui.MainFrame_cara;

import javax.swing.*;

public class App_Cara {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame_cara().setVisible(true); //otevre okno, ktere lze videt, z tridy MainFrame_cara - pro vykresleni cary
            }
        });
    }
}
