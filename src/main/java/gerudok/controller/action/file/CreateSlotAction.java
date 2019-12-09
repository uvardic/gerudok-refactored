package gerudok.controller.action.file;

import gerudok.controller.action.IconLoader;
import gerudok.model.Page;
import gerudok.model.Slot;
import gerudok.model.TreeNodeModel;
import gerudok.view.Dialog;
import gerudok.view.Tree;
import gerudok.view.desktop.SlotPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateSlotAction extends AbstractAction {

    public CreateSlotAction() {
        putValue(NAME, "Create slot");
        putValue(SHORT_DESCRIPTION, "Create a new slot on the existing page");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, IconLoader.SLOT_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        TreeNodeModel lastSelectedNode = Tree.getInstance().getLastSelectedNode();

        if (!(lastSelectedNode instanceof Page)) {
            Dialog.errorDialog(
                    "Create slot error!", "Please select a page in order to create a new slot."
            );
            return;
        }

        Slot slot = new Slot((Page) lastSelectedNode);
        new SlotPanel(slot);
    }

}
