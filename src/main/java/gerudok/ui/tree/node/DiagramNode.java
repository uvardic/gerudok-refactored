package gerudok.ui.tree.node;

import gerudok.model.Diagram;
import gerudok.ui.tree.visitor.TreeCellRendererVisitor;
import gerudok.ui.tree.visitor.TreeSelectionVisitor;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Objects;

import static java.util.Collections.enumeration;

public class DiagramNode implements Node {

    private final Diagram model;

    public DiagramNode(Diagram model) {
        this.model = model;
    }

    public Diagram getModel() {
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
    public PageNode getChildAt(int i) {
        return model.getChildrenAsNodes().get(i);
    }

    @Override
    public int getChildCount() {
        return model.getChildrenAsNodes().size();
    }

    @Override
    public ProjectNode getParent() {
        return model.getParentAsNode();
    }

    @Override
    public int getIndex(TreeNode treeNode) {
        if (!(treeNode instanceof PageNode))
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
    public Enumeration<? extends PageNode> children() {
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
