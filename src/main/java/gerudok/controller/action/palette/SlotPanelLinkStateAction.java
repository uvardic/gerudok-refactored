package gerudok.controller.action.palette;

import gerudok.controller.action.IconLoader;
import gerudok.view.Dialog;
import gerudok.view.desktop.Desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SlotPanelLinkStateAction extends AbstractAction {

    public SlotPanelLinkStateAction() {
        putValue(SHORT_DESCRIPTION, "Link State");
        putValue(SMALL_ICON, IconLoader.LINK_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.getInstance().isSlotPanelClosed()) {
            Dialog.errorDialog("Link state error!", "Please open a slot in order to use Link state!");
            return;
        }

        Desktop.getInstance().getSelectedSlotPanel().startLinkState();
    }
}
