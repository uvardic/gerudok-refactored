package gerudok.tree.node;

import gerudok.tree.visitor.TreeCellRendererVisitor;

import javax.swing.tree.TreeNode;

public interface Node extends TreeNode {

    void acceptTreeCellRendererVisitor(TreeCellRendererVisitor visitor);

    String formatName();

}
