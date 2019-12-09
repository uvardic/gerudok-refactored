package gerudok.view;

import gerudok.controller.TreeController;
import gerudok.model.TreeNodeModel;
import gerudok.model.Workspace;
import gerudok.model.observer.Observer;
import gerudok.model.observer.Subject;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class Tree extends JTree implements Subject {

    private static Tree instance;

    public static Tree getInstance() {
        if (instance != null)
            return instance;

        return initializeTree();
    }

    private static Tree initializeTree() {
        instance = new Tree();

        instance.setModel(new DefaultTreeModel(new Workspace()));
        instance.setCellRenderer(new TreeCellRenderer());
        instance.addMouseListener(new TreeController());
        instance.addTreeSelectionListener(new TreeController());

        Observer.addSubject(instance);

        return instance;
    }

    private Tree() {}

    public TreeNodeModel getLastSelectedNode() {
        return (TreeNodeModel) getLastSelectedPathComponent();
    }

    public Workspace getRoot() {
        return (Workspace) getModel().getRoot();
    }

    @Override
    public void update() {
        updateUI();
    }

    private static class TreeCellRenderer extends DefaultTreeCellRenderer {

        @Override
        public Component getTreeCellRendererComponent(
                JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus
        ) {
            super.getTreeCellRendererComponent(
                    tree, ((TreeNodeModel) value).getName(), sel, expanded, leaf, row, hasFocus
            );

            setIcon(((TreeNodeModel) value).getIcon());

            return this;
        }

    }

}
