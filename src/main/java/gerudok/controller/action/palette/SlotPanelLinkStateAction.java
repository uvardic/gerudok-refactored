package gerudok.controller.action.palette;

import gerudok.controller.action.IconLoader;
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
        Desktop.getInstance().getSelectedSlotPanel().startLinkState();
    }
}
