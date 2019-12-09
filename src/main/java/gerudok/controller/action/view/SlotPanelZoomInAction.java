package gerudok.controller.action.view;

import gerudok.controller.action.IconLoader;
import gerudok.view.Dialog;
import gerudok.view.desktop.Desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SlotPanelZoomInAction extends AbstractAction {

    public SlotPanelZoomInAction() {
        putValue(NAME, "Zoom in");
        putValue(SHORT_DESCRIPTION, "Zoom in current slot");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, IconLoader.ZOOM_IN_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.getInstance().isSlotPanelClosed()) {
            Dialog.errorDialog("Zoom in error!", "Please open a slot in order to use Zoom in!");
            return;
        }

        Desktop.getInstance().getSelectedSlotPanel().centerZoomIn();
    }
}
