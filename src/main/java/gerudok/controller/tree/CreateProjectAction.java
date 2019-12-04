package gerudok.controller.tree;

import gerudok.model.Project;
import gerudok.observer.Observer;
import gerudok.ui.tree.Tree;
import gerudok.ui.util.UIIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateProjectAction extends AbstractAction {

    public CreateProjectAction() {
        putValue(NAME, "Create project");
        putValue(SHORT_DESCRIPTION, "Create a new project on the existing workspace");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, UIIcon.PROJECT_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new Project(Tree.getInstance().getRoot().getModel(), "Project");

        Observer.updateSubject(Tree.class);
    }
}
