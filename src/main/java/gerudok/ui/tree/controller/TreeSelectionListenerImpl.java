package gerudok.ui.tree.controller;

import gerudok.ui.tree.node.Node;
import gerudok.ui.tree.visitor.TreeSelectionVisitor;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class TreeSelectionListenerImpl implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent event) {
        Node lastSelectedNode = (Node) event.getPath().getLastPathComponent();

        lastSelectedNode.acceptTreeSelectionVisitor(new TreeSelectionVisitor());
    }

}
