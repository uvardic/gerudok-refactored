package gerudok.controller.action.file;

import gerudok.controller.action.IconLoader;
import gerudok.model.Diagram;
import gerudok.model.Project;
import gerudok.model.TreeNodeModel;
import gerudok.view.Dialog;
import gerudok.view.Tree;
import gerudok.view.desktop.DiagramFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateDiagramAction extends AbstractAction {

    public CreateDiagramAction() {
        putValue(NAME, "Create diagram");
        putValue(SHORT_DESCRIPTION, "Create a new diagram on the selected project");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, IconLoader.DIAGRAM_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        TreeNodeModel lastSelectedNode = Tree.getInstance().getLastSelectedNode();

        if (!(lastSelectedNode instanceof Project)) {
            Dialog.errorDialog(
                    "Create diagram error!", "Please select a project in order to create a new diagram."
            );
            return;
        }

        Diagram diagram = new Diagram((Project) lastSelectedNode);

        new DiagramFrame(diagram);
    }

}
