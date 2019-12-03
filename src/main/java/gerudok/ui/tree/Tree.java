package gerudok.ui.tree;

import gerudok.observer.Observer;
import gerudok.observer.Subject;
import gerudok.ui.tree.controller.TreeMouseAdapter;
import gerudok.ui.tree.controller.TreeSelectionListenerImpl;
import gerudok.model.Workspace;
import gerudok.ui.tree.node.Node;
import gerudok.ui.tree.node.WorkspaceNode;
import gerudok.ui.tree.view.TreeCellRenderer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class Tree extends JTree implements Subject {

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

        Observer.observerSubject(instance);

        return instance;
    }

    public WorkspaceNode getRoot() {
        return (WorkspaceNode) getModel().getRoot();
    }

    public Node getLastSelectedNode() {
        return (Node) getLastSelectedPathComponent();
    }

    @Override
    public void update() {
        SwingUtilities.updateComponentTreeUI(instance);
    }

    // Extract when implementing workspace dialog?
    private static class TreeModel extends DefaultTreeModel {

        private TreeModel() {
            super(new WorkspaceNode(new Workspace("Workspace")));
        }

    }

}
