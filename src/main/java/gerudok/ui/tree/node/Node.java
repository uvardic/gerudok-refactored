package gerudok.ui.tree.node;

import gerudok.ui.tree.view.TreeCellRenderer;

import javax.swing.tree.TreeNode;

public interface Node extends TreeNode {

    void renderCell(TreeCellRenderer cellRenderer);

    void openMenu();

    void selectionEvent();

    String formatName();

}
