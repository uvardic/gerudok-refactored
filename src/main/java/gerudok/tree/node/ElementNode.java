package gerudok.tree.node;

import gerudok.tree.model.Element;
import gerudok.tree.visitor.TreeCellRendererVisitor;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;

public class ElementNode implements Node {

    private final Element model;

    public ElementNode(Element model) {
        this.model = model;
    }

    @Override
    public void acceptTreeCellRendererVisitor(TreeCellRendererVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String formatName() {
        return model.formatName();
    }

    @Override
    public TreeNode getChildAt(int i) {
        return null;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public TreeNode getParent() {
        return model.getParentAsNode();
    }

    @Override
    public int getIndex(TreeNode treeNode) {
        return 0;
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("ElementNode{model=%s}", model);
    }
}
