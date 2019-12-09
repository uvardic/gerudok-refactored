package gerudok.controller.action.file;

import gerudok.controller.action.IconLoader;
import gerudok.model.Project;
import gerudok.model.Workspace;
import gerudok.view.Dialog;
import gerudok.view.Tree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateProjectAction extends AbstractAction {

    public CreateProjectAction() {
        putValue(NAME, "Create project");
        putValue(SHORT_DESCRIPTION, "Create a new project on the existing workspace");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, IconLoader.PROJECT_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Workspace root = Tree.getInstance().getRoot();

        if (root == null) {
            Dialog.errorDialog("Create project error!", "Workspace not found!");
            return;
        }

        new Project(root);
    }

}
