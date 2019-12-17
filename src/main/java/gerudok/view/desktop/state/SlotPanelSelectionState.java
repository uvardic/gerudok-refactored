package gerudok.view.desktop.state;

import gerudok.model.Element;
import gerudok.view.desktop.SlotPanel;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class SlotPanelSelectionState implements SlotPanelState {

    private final SlotPanel slotPanel;

    private int pressedMouseButton = -1;

    public SlotPanelSelectionState(SlotPanel slotPanel) {
        this.slotPanel = slotPanel;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        pressedMouseButton = event.getButton();

        if (pressedMouseButton != MouseEvent.BUTTON1)
            return;

        Point2D mousePosition = slotPanel.transformPosition(event.getPoint());

        if (notElementAt(mousePosition)) {
            slotPanel.getModel().deselectAllElements();
            return;
        }

        Element element = slotPanel.getModel().getElementAt(mousePosition);

        if (event.isControlDown()) {
            if (slotPanel.getModel().isElementSelected(element))
                slotPanel.getModel().deselectElement(element);
            else
                slotPanel.getModel().selectElement(element);
        } else
            selectOnly(element);
    }

    private boolean notElementAt(Point2D mousePosition) {
        return !slotPanel.getModel().isElementAt(mousePosition);
    }

    private void selectOnly(Element element) {
        slotPanel.getModel().deselectAllElements();
        slotPanel.getModel().selectElement(element);
    }

    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseDragged(MouseEvent event) {
        if (pressedMouseButton != MouseEvent.BUTTON1)
            return;

        Point2D mousePosition = slotPanel.transformPosition(event.getPoint());

        if (slotPanel.getModel().isElementAt(mousePosition))
            slotPanel.startMoveState();
    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }

}
