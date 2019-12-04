package gerudok.ui.desktop.state;

import gerudok.model.Slot;
import gerudok.model.device.Circle;

import java.awt.event.MouseEvent;

public class SlotPanelCircleState implements SlotPanelState {

    private final Slot model;

    public SlotPanelCircleState(Slot model) {
        this.model = model;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getButton() != MouseEvent.BUTTON1)
            return;

        if (model.isElementAt(event.getPoint()))
            return;

        new Circle.CircleBuilder(model)
                .name("Circle")
                .position(event.getPoint())
                .build();
    }
}
