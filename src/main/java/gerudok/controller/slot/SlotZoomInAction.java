package gerudok.controller.slot;

import gerudok.ui.Dialog;
import gerudok.ui.desktop.Desktop;
import gerudok.ui.util.UIIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SlotZoomInAction extends AbstractAction {

    public SlotZoomInAction() {
        putValue(NAME, "Zoom in");
        putValue(SHORT_DESCRIPTION, "Zoom in current slot");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, UIIcon.ZOOM_IN_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.getInstance().isSlotPaneClosed()) {
            Dialog.errorDialog("Zoom in error!", "Please open a slot in order to use Zoom in!");
            return;
        }

        Desktop.getInstance().getSelectedSlotPanel().centerZoomIn();
    }
}
