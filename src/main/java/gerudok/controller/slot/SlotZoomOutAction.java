package gerudok.controller.slot;

import gerudok.ui.Dialog;
import gerudok.ui.desktop.Desktop;
import gerudok.ui.util.UIIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SlotZoomOutAction extends AbstractAction {

    public SlotZoomOutAction() {
        putValue(NAME, "Zoom out");
        putValue(SHORT_DESCRIPTION, "Zoom out current slot");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, UIIcon.ZOOM_OUT_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.getInstance().isSlotPaneClosed()) {
            Dialog.errorDialog("Zoom out error!", "Please open a slot in order to use Zoom out!");
            return;
        }

        Desktop.getInstance().getSelectedSlotPanel().centerZoomOut();
    }
}
