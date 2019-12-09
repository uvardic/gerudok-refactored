package gerudok.controller.action.view;

import gerudok.controller.action.IconLoader;
import gerudok.view.Dialog;
import gerudok.view.desktop.Desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class TileFramesVerticallyAction extends AbstractAction {

    public TileFramesVerticallyAction() {
        putValue(NAME, "Tile frames vertically");
        putValue(SHORT_DESCRIPTION, "Tile open frames vertically");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, IconLoader.TILE_V_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (Desktop.getInstance().getFrames().size() == 0) {
            Dialog.errorDialog(
                    "Tile frames vertically error!",
                    "Please open a frame in order to use Tile frames vertically!"
            );

            return;
        }

        Desktop.getInstance().tileFramesVertically();
    }
}
