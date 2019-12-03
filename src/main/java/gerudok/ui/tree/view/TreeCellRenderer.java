package gerudok.ui.tree.view;

import gerudok.ui.tree.node.Node;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class TreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus
    ) {
        super.getTreeCellRendererComponent(tree, ((Node) value).formatName(), sel, expanded, leaf, row, hasFocus);

        ((Node) value).renderCell(this);

        return this;
    }

}
