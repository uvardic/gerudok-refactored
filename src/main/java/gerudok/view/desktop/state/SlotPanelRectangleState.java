package gerudok.view.desktop.state;

import gerudok.model.device.Rectangle;
import gerudok.view.desktop.SlotPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class SlotPanelRectangleState implements SlotPanelState {

    private final SlotPanel slotPanel;

    public SlotPanelRectangleState(SlotPanel slotPanel) {
        this.slotPanel = slotPanel;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getButton() != MouseEvent.BUTTON1)
            return;

        Point2D mousePosition = event.getPoint();

        slotPanel.transformPosition(mousePosition);

        if (slotPanel.getModel().isElementAt(mousePosition))
            return;

        new Rectangle.Builder(slotPanel.getModel())
                .name("Rectangle")
                .position(mousePosition)
                .size(new Dimension(75, 50))
                .build();
    }


}
