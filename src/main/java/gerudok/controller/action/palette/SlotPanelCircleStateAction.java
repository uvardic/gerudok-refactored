package gerudok.controller.action.palette;

import gerudok.controller.action.IconLoader;
import gerudok.view.desktop.Desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SlotPanelCircleStateAction extends AbstractAction {

    public SlotPanelCircleStateAction() {
        putValue(SHORT_DESCRIPTION, "Circle State");
        putValue(SMALL_ICON, IconLoader.CIRCLE_ICON.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Desktop.getInstance().getSelectedSlotPanel().startCircleState();
    }

}
