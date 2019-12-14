package gerudok.controller.action.palette;

import gerudok.controller.action.IconLoader;
import gerudok.view.desktop.Desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SlotPanelRectangleStateAction extends AbstractAction {

    public SlotPanelRectangleStateAction() {
        putValue(SHORT_DESCRIPTION, "Rectangle State");
        putValue(SMALL_ICON, IconLoader.RECTANGLE_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Desktop.getInstance().getSelectedSlotPanel().startRectangleState();
    }

}
