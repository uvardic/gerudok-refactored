package gerudok.model.visitor;

import gerudok.model.*;
import gerudok.view.desktop.Desktop;

public class TreeSelectionEventVisitor implements TreeNodeModelVisitor {

    @Override
    public void visit(Workspace workspace) {}

    @Override
    public void visit(Project project) {}

    @Override
    public void visit(Diagram diagram) {
        Desktop.getInstance().selectFrame(diagram);
    }

    @Override
    public void visit(Page page) {
        Desktop.getInstance()
                .selectFrame(page.getParent())
                .selectPagePanel(page);
    }

    @Override
    public void visit(Slot slot) {
        Desktop.getInstance()
                .selectFrame(slot.getParent().getParent())
                .selectPagePanel(slot.getParent())
                .selectSlotPanel(slot);
    }

    @Override
    public void visit(Element element) {

    }

}
