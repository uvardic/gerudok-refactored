package gerudok.ui;

import gerudok.controller.ActionManager;

import javax.swing.*;

public class Menu extends JMenuBar {

    // Singleton??
    public Menu() {
        add(createFileMenu());
        add(createViewMenu());
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(ActionManager.getInstance().getCreateProjectAction());
        fileMenu.add(ActionManager.getInstance().getCreateDiagramAction());
        fileMenu.add(ActionManager.getInstance().getCreatePageAction());
        fileMenu.add(ActionManager.getInstance().getCreateSlotAction());
        fileMenu.add(ActionManager.getInstance().getCreateFastSlotAction());

        return fileMenu;
    }

    private JMenu createViewMenu() {
        JMenu viewMenu = new JMenu("View");

        viewMenu.add(ActionManager.getInstance().getCascadeFramesAction());
        viewMenu.add(ActionManager.getInstance().getTileFramesHorizontallyAction());
        viewMenu.add(ActionManager.getInstance().getTileFramesVerticallyAction());
        viewMenu.add(ActionManager.getInstance().getSlotZoomInAction());
        viewMenu.add(ActionManager.getInstance().getSlotZoomOutAction());
        viewMenu.add(ActionManager.getInstance().getSlotRegionZoomAction());

        return viewMenu;
    }

}
