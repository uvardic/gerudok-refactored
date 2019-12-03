package gerudok.view;

import gerudok.tree.Tree;

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
        instance.setRightComponent(new JPanel());
        instance.setDividerLocation(UISizeCalculator.calculateSplitPaneDividerLocation());

        return instance;
    }

}
