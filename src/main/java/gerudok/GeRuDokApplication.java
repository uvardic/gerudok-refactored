package gerudok;

import gerudok.view.MainFrame;

import java.awt.*;

public class GeRuDokApplication {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GeRuDokApplication().start());
    }

    private void start() {
        MainFrame.getInstance().setVisible(true);
    }

}
