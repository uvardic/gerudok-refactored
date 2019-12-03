package gerudok.ui.tree;

import gerudok.model.Workspace;
import gerudok.observer.Observer;
import gerudok.observer.Subject;
import gerudok.ui.tree.node.Node;
import gerudok.ui.tree.node.WorkspaceNode;
import gerudok.ui.tree.view.TreeCellRenderer;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        instance.addMouseListener(new TreeController());
        instance.addTreeSelectionListener(new TreeController());

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

    private static class TreeController extends MouseAdapter implements TreeSelectionListener {

        @Override
        public void valueChanged(TreeSelectionEvent event) {
            ((Node) event.getPath().getLastPathComponent()).selectionEvent();
        }

        @Override
        public void mouseClicked(MouseEvent event) {

        }

    }

}
