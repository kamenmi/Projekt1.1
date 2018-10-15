package App;

import gui.MainFrame_cara;

import javax.swing.*;

public class App_Cara {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame_cara().setVisible(true); //prepisu na jinou tridu
            }
        });
    }
}
