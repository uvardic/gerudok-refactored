package gerudok.tree.visitor;

import gerudok.tree.node.*;
import gerudok.tree.view.TreeCellRenderer;
import gerudok.view.UIIcon;


public class TreeCellRendererVisitor implements TreeVisitor {

    private final TreeCellRenderer treeCellRenderer;

    public TreeCellRendererVisitor(TreeCellRenderer treeCellRenderer) {
        this.treeCellRenderer = treeCellRenderer;
    }

    @Override
    public void visit(WorkspaceNode workspaceNode) {
        treeCellRenderer.setIcon(UIIcon.TREE_WORKSPACE_ICON.loadIcon());
    }

    @Override
    public void visit(ProjectNode projectNode) {
        treeCellRenderer.setIcon(UIIcon.TREE_PROJECT_ICON.loadIcon());
    }

    @Override
    public void visit(DiagramNode diagramNode) {
        treeCellRenderer.setIcon(UIIcon.TREE_DIAGRAM_ICON.loadIcon());
    }

    @Override
    public void visit(PageNode pageNode) {
        treeCellRenderer.setIcon(UIIcon.TREE_PAGE_ICON.loadIcon());
    }

    @Override
    public void visit(SlotNode slotNode) {
        treeCellRenderer.setIcon(UIIcon.TREE_SLOT_ICON.loadIcon());
    }

    @Override
    public void visit(ElementNode elementNode) {
        treeCellRenderer.setIcon(UIIcon.TREE_ELEMENT_ICON.loadIcon());
    }

}
