package gerudok.controller.action.view;

import gerudok.controller.action.IconLoader;
import gerudok.view.Dialog;
import gerudok.view.desktop.Desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SlotPanelRegionZoomAction extends AbstractAction {

    public SlotPanelRegionZoomAction() {
        putValue(NAME, "Region zoom");
        putValue(SHORT_DESCRIPTION, "Zoom to see the whole device region");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, IconLoader.REGION_ZOOM_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.getInstance().isSlotPanelClosed()) {
            Dialog.errorDialog("Region zoom error!", "Please open a slot in order to use region zoom!");
            return;
        }

        Desktop.getInstance().getSelectedSlotPanel().regionZoom();
    }
}
