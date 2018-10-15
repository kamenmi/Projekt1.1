package App;

import gui.MainFrame_Pravidelny_N;

import javax.swing.*;

public class App_Polygon {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame_Pravidelny_N().setVisible(true); // vypisu tridu na polygon
            }
        });
    }
}
