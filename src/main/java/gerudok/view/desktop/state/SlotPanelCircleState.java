package gerudok.view.desktop.state;

import gerudok.model.device.Circle;
import gerudok.view.desktop.SlotPanel;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class SlotPanelCircleState implements SlotPanelState {

    private final SlotPanel slotPanel;

    public SlotPanelCircleState(SlotPanel slotPanel) {
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

        new Circle.Builder(slotPanel.getModel())
                .name("Circle")
                .position(mousePosition)
                .build();
    }
}
