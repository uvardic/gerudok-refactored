package gerudok.controller.tree;

import gerudok.model.Diagram;
import gerudok.observer.Observer;
import gerudok.ui.Dialog;
import gerudok.ui.desktop.Desktop;
import gerudok.ui.tree.Tree;
import gerudok.ui.tree.node.Node;
import gerudok.ui.tree.node.ProjectNode;
import gerudok.ui.util.UIIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateDiagramAction extends AbstractAction {

    public CreateDiagramAction() {
        putValue(NAME, "Create diagram");
        putValue(SHORT_DESCRIPTION, "Create a new diagram on the selected project");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, UIIcon.DIAGRAM_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Node lastSelectedNode = Tree.getInstance().getLastSelectedNode();

        if (!(lastSelectedNode instanceof ProjectNode)) {
            Dialog.errorDialog(
                    "Create diagram error!", "Please select a project in order to create a new diagram."
            );
            return;
        }

        ProjectNode projectNode = (ProjectNode) lastSelectedNode;

        new Diagram(projectNode.getModel(), "Diagram");

        Observer.updateSubjects(Tree.class, Desktop.class);
    }
}
