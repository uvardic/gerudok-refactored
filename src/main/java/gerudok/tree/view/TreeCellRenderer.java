package gerudok.tree.view;

import gerudok.tree.node.Node;
import gerudok.tree.visitor.TreeCellRendererVisitor;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class TreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus
    ) {
        super.getTreeCellRendererComponent(tree, ((Node) value).formatName(), sel, expanded, leaf, row, hasFocus);

        Node node = (Node) value;

        node.acceptTreeCellRendererVisitor(new TreeCellRendererVisitor(this));

        return this;
    }

}
