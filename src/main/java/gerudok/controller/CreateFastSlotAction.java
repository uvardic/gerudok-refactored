package gerudok.controller;

import gerudok.model.Diagram;
import gerudok.model.Page;
import gerudok.model.Project;
import gerudok.model.Slot;
import gerudok.observer.Observer;
import gerudok.ui.Desktop;
import gerudok.ui.DiagramFrame;
import gerudok.ui.PagePanel;
import gerudok.ui.tree.Tree;
import gerudok.ui.util.UIIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateFastSlotAction extends AbstractAction {

    public CreateFastSlotAction() {
        putValue(NAME, "Create fast slot");
        putValue(SHORT_DESCRIPTION, "Create a new slot and its parents");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, UIIcon.SLOT_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Project project = new Project(Tree.getInstance().getRoot().getModel(), "Project");

        Diagram diagram = new Diagram(project, "Diagram");

        Page page = new Page(diagram, "Page");

        new Slot(page, "Slot");

        Observer.updateSubjects(Tree.class, Desktop.class, DiagramFrame.class, PagePanel.class);
    }
}
