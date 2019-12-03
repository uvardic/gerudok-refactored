package gerudok.ui;

import javax.swing.*;

public class Dialog {

    private Dialog() {}

    public static void errorDialog(String title, String message) {
        JOptionPane.showMessageDialog(MainFrame.getInstance(), message, title, JOptionPane.ERROR_MESSAGE);
    }

}
