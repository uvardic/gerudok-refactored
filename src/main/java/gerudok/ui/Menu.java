package gerudok.ui;

import gerudok.controller.ActionManager;

import javax.swing.*;

public class Menu extends JMenuBar {

    // Singleton??
    public Menu() {
        add(createFileMenu());
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(ActionManager.getInstance().getCreateProjectAction());
        fileMenu.add(ActionManager.getInstance().getCreateDiagramAction());
        fileMenu.add(ActionManager.getInstance().getCreatePageAction());
        fileMenu.add(ActionManager.getInstance().getCreateSlotAction());

        return fileMenu;
    }

}
