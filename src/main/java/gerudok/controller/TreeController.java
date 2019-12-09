package gerudok.controller;

import gerudok.model.visitor.TreePopupMenuVisitor;
import gerudok.model.visitor.TreeSelectionEventVisitor;
import gerudok.view.Tree;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TreeController extends MouseAdapter implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent event) {
        Tree.getInstance()
                .getLastSelectedNode()
                .acceptModelVisitor(new TreeSelectionEventVisitor());
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getButton() != MouseEvent.BUTTON3)
            return;

        selectClickedNode(event);
        showPopupMenu(event);
    }

    private void selectClickedNode(MouseEvent event) {
        Tree tree = Tree.getInstance();

        tree.setSelectionPath(tree.getClosestPathForLocation(event.getX(), event.getY()));
    }

    private void showPopupMenu(MouseEvent event) {
        TreePopupMenuVisitor treePopupMenuVisitor = new TreePopupMenuVisitor();

        Tree.getInstance()
                .getLastSelectedNode()
                .acceptModelVisitor(treePopupMenuVisitor);

        treePopupMenuVisitor.showMenu(event);
    }

}
