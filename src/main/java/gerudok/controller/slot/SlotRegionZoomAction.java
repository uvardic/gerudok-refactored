package gerudok.controller.slot;

import gerudok.ui.Dialog;
import gerudok.ui.desktop.Desktop;
import gerudok.ui.util.UIIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SlotRegionZoomAction extends AbstractAction {

    public SlotRegionZoomAction() {
        putValue(NAME, "Region zoom");
        putValue(SHORT_DESCRIPTION, "Zoom to see the whole device region");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, UIIcon.REGION_ZOOM_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.getInstance().isSlotPaneClosed()) {
            Dialog.errorDialog("Region zoom error!", "Please open a slot in order to use region zoom!");
            return;
        }

        Desktop.getInstance().getSelectedSlotPanel().regionZoom();
    }
}
