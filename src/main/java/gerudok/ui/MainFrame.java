package gerudok.ui;

import gerudok.ui.util.UISizeCalculator;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private MainFrame() {
        super("GeRuDok");
    }

    public static MainFrame getInstance() {
        if (instance != null)
            return instance;

        return initializeMainFrame();
    }

    private static MainFrame initializeMainFrame() {
        instance = new MainFrame();

        setupMainFrame();
        setupMainFrameUI();

        // Needs to be the last method called
        instance.setVisible(true);

        return instance;
    }

    private static void setupMainFrame() {
        instance.setDefaultCloseOperation(EXIT_ON_CLOSE);
        instance.setSize(UISizeCalculator.calculateMainFrameSize());
        instance.setMinimumSize(UISizeCalculator.calculateMainFrameMinSize());
        instance.setMaximumSize(UISizeCalculator.calculateMainFrameMaxSize());
        instance.setExtendedState(MAXIMIZED_BOTH);
    }

    private static void setupMainFrameUI() {
        instance.setJMenuBar(new Menu());
        instance.add(SplitPane.getInstance(), BorderLayout.CENTER);
    }

}
