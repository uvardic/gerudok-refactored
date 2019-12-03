package gerudok.ui.tree.node;

import gerudok.model.Slot;
import gerudok.ui.tree.visitor.TreeCellRendererVisitor;
import gerudok.ui.tree.visitor.TreeSelectionVisitor;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Objects;

import static java.util.Collections.enumeration;

public class SlotNode implements Node {

    private final Slot model;

    public SlotNode(Slot model) {
        this.model = model;
    }

    public Slot getModel() {
        return model;
    }

    @Override
    public void acceptTreeCellRendererVisitor(TreeCellRendererVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void acceptTreeSelectionVisitor(TreeSelectionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String formatName() {
        return model.getName();
    }

    @Override
    public ElementNode getChildAt(int i) {
        return model.getChildrenAsNodes().get(i);
    }

    @Override
    public int getChildCount() {
        return model.getChildrenAsNodes().size();
    }

    @Override
    public PageNode getParent() {
        return model.getParentAsNode();
    }

    @Override
    public int getIndex(TreeNode treeNode) {
        if (!(treeNode instanceof ElementNode))
            throw new IllegalArgumentException("Illegal child instance!");

        return model.getChildrenAsNodes().indexOf(treeNode);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration<? extends ElementNode> children() {
        return enumeration(model.getChildrenAsNodes());
    }

    @Override
    public String toString() {
        return String.format("SlotNode{model=%s}", model);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SlotNode) {
            SlotNode other = (SlotNode) o;

            return Objects.equals(this.model, other.model);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
