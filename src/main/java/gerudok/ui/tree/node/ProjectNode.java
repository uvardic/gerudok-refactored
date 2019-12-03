package gerudok.ui.tree.node;

import gerudok.model.Project;
import gerudok.ui.tree.view.TreeCellRenderer;
import gerudok.ui.util.UIIcon;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Objects;

import static java.util.Collections.enumeration;

public class ProjectNode implements Node {

    private final Project model;

    public ProjectNode(Project model) {
        this.model = model;
    }

    public Project getModel() {
        return model;
    }

    @Override
    public void renderCell(TreeCellRenderer cellRenderer) {
        cellRenderer.setIcon(UIIcon.PROJECT_ICON.loadIcon());
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
    public DiagramNode getChildAt(int i) {
        return model.getChildrenAsNodes().get(i);
    }

    @Override
    public int getChildCount() {
        return model.getChildrenAsNodes().size();
    }

    @Override
    public WorkspaceNode getParent() {
        return model.getParentAsNode();
    }

    @Override
    public int getIndex(TreeNode treeNode) {
        if (!(treeNode instanceof DiagramNode))
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
    public Enumeration<? extends DiagramNode> children() {
        return enumeration(model.getChildrenAsNodes());
    }

    @Override
    public String toString() {
        return String.format("ProjectNode{model=%s}", model);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ProjectNode) {
            ProjectNode other = (ProjectNode) o;

            return Objects.equals(this.model, other.model);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

}
