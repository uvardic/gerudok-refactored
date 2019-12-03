package gerudok.controller;

import gerudok.model.Slot;
import gerudok.observer.Observer;
import gerudok.ui.Dialog;
import gerudok.ui.PagePanel;
import gerudok.ui.tree.Tree;
import gerudok.ui.tree.node.Node;
import gerudok.ui.tree.node.PageNode;
import gerudok.ui.util.UIIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateSlotAction extends AbstractAction {

    public CreateSlotAction() {
        putValue(NAME, "Create slot");
        putValue(SHORT_DESCRIPTION, "Create a new slot on the selected page");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, UIIcon.SLOT_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Node lastSelectedNode = Tree.getInstance().getLastSelectedNode();

        if (!(lastSelectedNode instanceof PageNode)) {
            Dialog.errorDialog(
                    "Create slot error!", "Please select a page in order to create a new slot."
            );
            return;
        }

        PageNode pageNode = (PageNode) lastSelectedNode;

        new Slot(pageNode.getModel(), "Slot");

        Observer.updateSubjects(Tree.class, PagePanel.class);
    }
}
