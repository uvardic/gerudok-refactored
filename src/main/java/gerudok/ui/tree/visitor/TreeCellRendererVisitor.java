package gerudok.ui.tree.visitor;

import gerudok.ui.tree.node.*;
import gerudok.ui.tree.view.TreeCellRenderer;
import gerudok.ui.util.UIIcon;


public class TreeCellRendererVisitor implements TreeVisitor {

    private final TreeCellRenderer treeCellRenderer;

    public TreeCellRendererVisitor(TreeCellRenderer treeCellRenderer) {
        this.treeCellRenderer = treeCellRenderer;
    }

    @Override
    public void visit(WorkspaceNode workspaceNode) {
        treeCellRenderer.setIcon(UIIcon.WORKSPACE_ICON.loadIcon());
    }

    @Override
    public void visit(ProjectNode projectNode) {
        treeCellRenderer.setIcon(UIIcon.PROJECT_ICON.loadIcon());
    }

    @Override
    public void visit(DiagramNode diagramNode) {
        treeCellRenderer.setIcon(UIIcon.DIAGRAM_ICON.loadIcon());
    }

    @Override
    public void visit(PageNode pageNode) {
        treeCellRenderer.setIcon(UIIcon.PAGE_ICON.loadIcon());
    }

    @Override
    public void visit(SlotNode slotNode) {
        treeCellRenderer.setIcon(UIIcon.SLOT_ICON.loadIcon());
    }

    @Override
    public void visit(ElementNode elementNode) {
        treeCellRenderer.setIcon(UIIcon.ELEMENT_ICON.loadIcon());
    }

}
