package gerudok.controller.action.palette;

import gerudok.controller.action.IconLoader;
import gerudok.view.Dialog;
import gerudok.view.desktop.Desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SlotPanelTriangleStateAction extends AbstractAction {

    public SlotPanelTriangleStateAction() {
        putValue(SHORT_DESCRIPTION, "Triangle State");
        putValue(SMALL_ICON, IconLoader.TRIANGLE_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.getInstance().isSlotPanelClosed()) {
            Dialog.errorDialog("Triangle state error!", "Please open a slot in order to use Triangle state!");
            return;
        }

        Desktop.getInstance().getSelectedSlotPanel().startTriangleState();
    }
}
