package gerudok.tree;

import gerudok.tree.controller.TreeCellRenderer;
import gerudok.tree.controller.TreeMouseAdapter;
import gerudok.tree.controller.TreeSelectionListenerImpl;
import gerudok.tree.model.Workspace;
import gerudok.tree.view.WorkspaceNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class Tree extends JTree {

    private static Tree instance;

    private Tree() {}

    public static Tree getInstance() {
        if (instance != null)
            return instance;

        return initializeTree();
    }

    private static Tree initializeTree() {
        instance = new Tree();

        instance.setModel(new TreeModel());
        instance.setCellRenderer(new TreeCellRenderer());
        instance.addMouseListener(new TreeMouseAdapter());
        instance.addTreeSelectionListener(new TreeSelectionListenerImpl());

        return instance;
    }

    private static class TreeModel extends DefaultTreeModel {

        private TreeModel() {
            super(new WorkspaceNode(new Workspace("Workspace")));
        }

    }

}