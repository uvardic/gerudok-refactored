package gerudok.ui.tree.node;

import gerudok.model.Workspace;
import gerudok.ui.tree.view.TreeCellRenderer;
import gerudok.ui.util.UIIcon;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Objects;

import static java.util.Collections.enumeration;

public class WorkspaceNode implements Node {

    private final Workspace model;

    public WorkspaceNode(Workspace model) {
        this.model = model;
    }

    public Workspace getModel() {
        return model;
    }

    @Override
    public void renderCell(TreeCellRenderer cellRenderer) {
        cellRenderer.setIcon(UIIcon.WORKSPACE_ICON.loadIcon());
    }

    @Override
    public void openMenu() {

    }

    @Override
    public void selectionEvent() {}

    @Override
    public String formatName() {
        return model.getName();
    }

    @Override
    public ProjectNode getChildAt(int i) {
        return model.getChildrenAsNodes().get(i);
    }

    @Override
    public int getChildCount() {
        return model.getChildrenAsNodes().size();
    }

    @Override
    public TreeNode getParent() {
        return null;
    }

    @Override
    public int getIndex(TreeNode treeNode) {
        if (!(treeNode instanceof ProjectNode))
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
    public Enumeration<? extends ProjectNode> children() {
        return enumeration(model.getChildrenAsNodes());
    }

    @Override
    public String toString() {
        return String.format("WorkspaceNode{model=%s}", model);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof WorkspaceNode) {
            WorkspaceNode other = (WorkspaceNode) o;

            return Objects.equals(this.model, other.model);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

}
