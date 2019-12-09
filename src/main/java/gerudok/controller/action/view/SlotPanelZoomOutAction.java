package gerudok.controller.action.view;

import gerudok.controller.action.IconLoader;
import gerudok.view.Dialog;
import gerudok.view.desktop.Desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SlotPanelZoomOutAction extends AbstractAction {

    public SlotPanelZoomOutAction() {
        putValue(NAME, "Zoom out");
        putValue(SHORT_DESCRIPTION, "Zoom out current slot");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, IconLoader.ZOOM_OUT_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.getInstance().isSlotPanelClosed()) {
            Dialog.errorDialog("Zoom out error!", "Please open a slot in order to use Zoom out!");
            return;
        }

        Desktop.getInstance().getSelectedSlotPanel().centerZoomOut();
    }
}
