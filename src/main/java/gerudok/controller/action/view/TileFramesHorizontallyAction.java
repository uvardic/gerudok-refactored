package gerudok.controller.action.view;

import gerudok.controller.action.IconLoader;
import gerudok.view.Dialog;
import gerudok.view.desktop.Desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class TileFramesHorizontallyAction extends AbstractAction {

    public TileFramesHorizontallyAction() {
        putValue(NAME, "Tile frames horizontally");
        putValue(SHORT_DESCRIPTION, "Tile open frames horizontally");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, IconLoader.TILE_H_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (Desktop.getInstance().getFrames().size() == 0) {
            Dialog.errorDialog(
                    "Tile frames horizontally error!",
                    "Please open a frame in order to use Tile frames horizontally!"
            );

            return;
        }

        Desktop.getInstance().tileFramesHorizontally();
    }
}
