package gerudok.view.desktop.state;

import gerudok.model.device.Triangle;
import gerudok.view.desktop.SlotPanel;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class SlotPanelTriangleState implements SlotPanelState {

    private final SlotPanel slotPanel;

    public SlotPanelTriangleState(SlotPanel slotPanel) {
        this.slotPanel = slotPanel;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getButton() != MouseEvent.BUTTON1)
            return;

        Point2D mousePosition = slotPanel.transformPosition(event.getPoint());

        if (slotPanel.getModel().isElementAt(mousePosition))
            return;

        new Triangle.Builder(slotPanel.getModel())
                .name("Triangle")
                .position(mousePosition)
                .build();
    }

    @Override
    public void mouseDragged(MouseEvent event) {}

    @Override
    public void mouseMoved(MouseEvent event) {}

}
