package gerudok.view;

import gerudok.controller.action.ActionProvider;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public MenuBar() {
        add(createFileMenu());
        add(createViewMenu());
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(ActionProvider.getInstance().getCreateProjectAction());
        fileMenu.add(ActionProvider.getInstance().getCreateDiagramAction());
        fileMenu.add(ActionProvider.getInstance().getCreatePageAction());
        fileMenu.add(ActionProvider.getInstance().getCreateSlotAction());
        fileMenu.add(ActionProvider.getInstance().getCreateFastSlotAction());

        return fileMenu;
    }

    private JMenu createViewMenu() {
        JMenu viewMenu = new JMenu("View");

        viewMenu.add(ActionProvider.getInstance().getCascadeFramesAction());
        viewMenu.add(ActionProvider.getInstance().getTileFramesHorizontallyAction());
        viewMenu.add(ActionProvider.getInstance().getTileFramesVerticallyAction());
        viewMenu.addSeparator();
        viewMenu.add(ActionProvider.getInstance().getSlotPanelZoomInAction());
        viewMenu.add(ActionProvider.getInstance().getSlotPanelZoomOutAction());
        viewMenu.add(ActionProvider.getInstance().getSlotPanelRegionZoomAction());

        return viewMenu;
    }

}
