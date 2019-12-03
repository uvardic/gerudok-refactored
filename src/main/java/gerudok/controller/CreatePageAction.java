package gerudok.controller;

import gerudok.model.Page;
import gerudok.observer.Observer;
import gerudok.ui.DiagramFrame;
import gerudok.ui.Dialog;
import gerudok.ui.tree.Tree;
import gerudok.ui.tree.node.DiagramNode;
import gerudok.ui.tree.node.Node;
import gerudok.ui.util.UIIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreatePageAction extends AbstractAction {

    public CreatePageAction() {
        putValue(NAME, "Create page");
        putValue(SHORT_DESCRIPTION, "Create a new page on the selected diagram");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, UIIcon.PAGE_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Node lastSelectedNode = Tree.getInstance().getLastSelectedNode();

        if (!(lastSelectedNode instanceof DiagramNode)) {
            Dialog.errorDialog(
                    "Create page error!", "Please select a diagram in order to create a new page."
            );
            return;
        }

        DiagramNode diagramNode = (DiagramNode) lastSelectedNode;

        new Page(diagramNode.getModel(), "Page");

        Observer.updateSubjects(Tree.class, DiagramFrame.class);
    }
}
