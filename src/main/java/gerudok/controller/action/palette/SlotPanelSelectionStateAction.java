package gerudok.controller.action.palette;

import gerudok.controller.action.IconLoader;
import gerudok.view.desktop.Desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SlotPanelSelectionStateAction extends AbstractAction {

    public SlotPanelSelectionStateAction() {
        putValue(SHORT_DESCRIPTION, "Selection State");
        putValue(SMALL_ICON, IconLoader.SELECT_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Desktop.getInstance().getSelectedSlotPanel().startSelectionState();
    }
}
