package gerudok.ui.tree.node;

import gerudok.ui.tree.visitor.TreeCellRendererVisitor;
import gerudok.ui.tree.visitor.TreeSelectionVisitor;

import javax.swing.tree.TreeNode;

public interface Node extends TreeNode {

    void acceptTreeCellRendererVisitor(TreeCellRendererVisitor visitor);

    void acceptTreeSelectionVisitor(TreeSelectionVisitor visitor);

    String formatName();

}
