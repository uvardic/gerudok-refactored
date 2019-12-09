package gerudok.controller.action.file;

import gerudok.controller.action.IconLoader;
import gerudok.model.*;
import gerudok.view.Dialog;
import gerudok.view.Tree;
import gerudok.view.desktop.DiagramFrame;
import gerudok.view.desktop.PagePanel;
import gerudok.view.desktop.SlotPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateFastSlotAction extends AbstractAction {

    public CreateFastSlotAction() {
        putValue(NAME, "Create fast slot");
        putValue(SHORT_DESCRIPTION, "Create a new slot and its parents");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, IconLoader.SLOT_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Workspace root = Tree.getInstance().getRoot();

        if (root == null) {
            Dialog.errorDialog("Create fast slot error!", "Workspace not found!");
            return;
        }

        Project project = new Project(root);

        Diagram diagram = new Diagram(project);
        new DiagramFrame(diagram);

        Page page = new Page(diagram);
        new PagePanel(page);

        Slot slot = new Slot(page);
        new SlotPanel(slot);
    }
}
