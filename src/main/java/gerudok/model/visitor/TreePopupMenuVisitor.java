package gerudok.model.visitor;

import gerudok.controller.action.ActionProvider;
import gerudok.model.*;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class TreePopupMenuVisitor implements TreeNodeModelVisitor {

    private final JPopupMenu popupMenu = new JPopupMenu();

    public void showMenu(MouseEvent event) {
        popupMenu.show(event.getComponent(), event.getX(), event.getY());
    }

    @Override
    public void visit(Workspace workspace) {
        popupMenu.removeAll();

        popupMenu.add(ActionProvider.getInstance().getCreateProjectAction());
        popupMenu.add(ActionProvider.getInstance().getCreateFastSlotAction());
    }

    @Override
    public void visit(Project project) {
        popupMenu.removeAll();

        popupMenu.add(ActionProvider.getInstance().getCreateDiagramAction());
    }

    @Override
    public void visit(Diagram diagram) {
        popupMenu.removeAll();

        popupMenu.add(ActionProvider.getInstance().getCreatePageAction());
    }

    @Override
    public void visit(Page page) {
        popupMenu.removeAll();

        popupMenu.add(ActionProvider.getInstance().getCreateSlotAction());
    }

    @Override
    public void visit(Slot slot) {

    }

    @Override
    public void visit(Element element) {

    }
}
