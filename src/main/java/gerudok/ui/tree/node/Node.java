package gerudok.ui.tree.node;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

public interface Node extends TreeNode {

    void renderCell(DefaultTreeCellRenderer cellRenderer);

    void selectionEvent();

    String formatName();

    JPopupMenu createMenu();

}
