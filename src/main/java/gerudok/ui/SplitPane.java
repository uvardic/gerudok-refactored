package gerudok.ui;

import gerudok.ui.tree.Tree;
import gerudok.ui.util.UISizeCalculator;

import javax.swing.*;

public class SplitPane extends JSplitPane {

    private static SplitPane instance;

    private SplitPane() {
        super(HORIZONTAL_SPLIT);
    }

    public static SplitPane getInstance() {
        if (instance != null)
            return instance;

        return initializeSplitPane();
    }

    private static SplitPane initializeSplitPane() {
        instance = new SplitPane();

        instance.setLeftComponent(new JScrollPane(Tree.getInstance()));
        instance.setRightComponent(new JScrollPane(Desktop.getInstance()));
        instance.setDividerLocation(UISizeCalculator.calculateSplitPaneDividerLocation());

        return instance;
    }

}
