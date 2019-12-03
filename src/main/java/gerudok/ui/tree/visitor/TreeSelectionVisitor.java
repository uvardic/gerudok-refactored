package gerudok.ui.tree.visitor;

import gerudok.model.Page;
import gerudok.model.Slot;
import gerudok.ui.Desktop;
import gerudok.ui.tree.node.*;

public class TreeSelectionVisitor implements TreeVisitor {

    @Override
    public void visit(WorkspaceNode workspaceNode) {}

    @Override
    public void visit(ProjectNode projectNode) {}

    @Override
    public void visit(DiagramNode diagramNode) {
        Desktop.getInstance().selectFrame(diagramNode.getModel());
    }

    @Override
    public void visit(PageNode pageNode) {
        Page page = pageNode.getModel();

        Desktop.getInstance().selectFrame(page.getParent())
                             .selectPagePanel(page);
    }

    @Override
    public void visit(SlotNode slotNode) {
        Slot slot = slotNode.getModel();

        Desktop.getInstance().selectFrame(slot.getParent().getParent())
                             .selectPagePanel(slot.getParent())
                             .selectSlotPanel(slot);
    }

    @Override
    public void visit(ElementNode elementNode) {

    }

}
