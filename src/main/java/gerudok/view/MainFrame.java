package gerudok.view;

import gerudok.tree.Tree;

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
        instance.setSize(MainFrameSizeCalculator.calculateFrameSize());
        instance.setMinimumSize(MainFrameSizeCalculator.calculateFrameMinSize());
        instance.setMaximumSize(MainFrameSizeCalculator.calculateFrameMaxSize());
        instance.setExtendedState(MAXIMIZED_BOTH);
    }

    private static void setupMainFrameUI() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        splitPane.setLeftComponent(new JScrollPane(Tree.getInstance()));
        splitPane.setRightComponent(new JPanel());

        instance.add(splitPane, BorderLayout.CENTER);
    }

}
