package gerudok.ui;

import gerudok.controller.ActionManager;

import javax.swing.*;

public class Menu extends JMenuBar {

    public Menu() {
        add(createFileMenu());
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(ActionManager.getInstance().getCreateProjectAction());
        fileMenu.add(ActionManager.getInstance().getCreateDiagramAction());

        return fileMenu;
    }

}
