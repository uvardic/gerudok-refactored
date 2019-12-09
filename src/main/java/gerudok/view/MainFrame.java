package gerudok.view;

import gerudok.view.desktop.Desktop;
import gerudok.view.util.SizeCalculator;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private MainFrame() {}

    public static MainFrame getInstance() {
        if (instance != null)
            return instance;

        return initializeMainFrame();
    }

    private static MainFrame initializeMainFrame() {
        instance = new MainFrame();

        instance.initializeFrame();
        instance.initializeComponents();

        return instance;
    }

    private void initializeFrame() {
        instance.setTitle("GeRuDok");
        instance.setDefaultCloseOperation(EXIT_ON_CLOSE);
        instance.setSize(SizeCalculator.calculateMainFrameSize());
        instance.setMinimumSize(SizeCalculator.calculateMainFrameMinSize());
        instance.setMaximumSize(SizeCalculator.calculateMainFrameMaxSize());
        instance.setExtendedState(MAXIMIZED_BOTH);
    }

    private void initializeComponents() {
        instance.setJMenuBar(new MenuBar());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        splitPane.setLeftComponent(new JScrollPane(Tree.getInstance()));
        splitPane.setRightComponent(new JScrollPane(Desktop.getInstance()));
        splitPane.setDividerLocation(SizeCalculator.calculateSplitPaneDividerLocation());

        add(splitPane, BorderLayout.CENTER);
    }

}
