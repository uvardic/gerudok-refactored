package gerudok.tree.node;

import gerudok.tree.exception.IllegalTreeChildException;
import gerudok.tree.model.Diagram;
import gerudok.tree.visitor.TreeCellRendererVisitor;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Objects;

import static java.util.Collections.enumeration;

public class DiagramNode implements Node {

    private final Diagram model;

    public DiagramNode(Diagram model) {
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
        return model.getChildrenAsNodes().get(i);
    }

    @Override
    public int getChildCount() {
        return model.getChildrenAsNodes().size();
    }

    @Override
    public TreeNode getParent() {
        return model.getParentAsNode();
    }

    @Override
    public int getIndex(TreeNode treeNode) {
        if (!(treeNode instanceof PageNode))
            throw new IllegalTreeChildException("Illegal child instance!");

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
    public Enumeration<? extends TreeNode> children() {
        return enumeration(model.getChildrenAsNodes());
    }

    @Override
    public String toString() {
        return String.format("DiagramNode{model=%s}", model);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DiagramNode) {
            DiagramNode other = (DiagramNode) o;

            return Objects.equals(this.model, other.model);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
