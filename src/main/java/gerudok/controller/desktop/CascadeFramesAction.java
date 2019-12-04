package gerudok.controller.desktop;

import gerudok.ui.desktop.Desktop;
import gerudok.ui.util.UIIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CascadeFramesAction extends AbstractAction {

    public CascadeFramesAction() {
        putValue(NAME, "Cascade frames");
        putValue(SHORT_DESCRIPTION, "Cascade open frames");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, UIIcon.CASCADE_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Desktop.getInstance().cascadeFrames();
    }

}
