package gerudok.controller.action.file;

import gerudok.controller.action.IconLoader;
import gerudok.model.Diagram;
import gerudok.model.Page;
import gerudok.model.TreeNodeModel;
import gerudok.view.Dialog;
import gerudok.view.Tree;
import gerudok.view.desktop.PagePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreatePageAction extends AbstractAction {

    public CreatePageAction() {
        putValue(NAME, "Create page");
        putValue(SHORT_DESCRIPTION, "Create a new page on the selected diagram");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, IconLoader.PAGE_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        TreeNodeModel lastSelectedNode = Tree.getInstance().getLastSelectedNode();

        if (!(lastSelectedNode instanceof Diagram)) {
            Dialog.errorDialog(
                    "Create page error!", "Please select a diagram in order to create a new page."
            );
            return;
        }

        Page page = new Page((Diagram) lastSelectedNode);
        new PagePanel(page);
    }

}
