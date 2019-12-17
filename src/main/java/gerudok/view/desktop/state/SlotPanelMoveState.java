package gerudok.view.desktop.state;

import gerudok.model.device.Device;
import gerudok.model.link.Link;
import gerudok.view.desktop.SlotPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class SlotPanelMoveState implements SlotPanelState {

    private final SlotPanel slotPanel;

    private boolean movingStarted;

    private Point2D previousMousePosition;

    public SlotPanelMoveState(SlotPanel slotPanel) {
        this.slotPanel = slotPanel;
    }

    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseReleased(MouseEvent event) {
        slotPanel.setCanvasCursor(new Cursor(Cursor.HAND_CURSOR));
        slotPanel.startSelectionState();
        movingStarted = false;
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        slotPanel.setCanvasCursor(new Cursor(Cursor.MOVE_CURSOR));

        Point2D mousePosition = slotPanel.transformPosition(event.getPoint());

        initializePreviousMousePosition(mousePosition);

        moveElements(mousePosition);

        previousMousePosition = mousePosition;
    }

    private void initializePreviousMousePosition(Point2D mousePosition) {
        if (!movingStarted) {
            previousMousePosition = mousePosition;
            movingStarted = true;
        }
    }

    private void moveElements(Point2D mousePosition) {
        slotPanel.getModel().getSelectedDevices().forEach(device -> moveDevice(device, mousePosition));
        slotPanel.getModel().getSelectedLinks().forEach(link -> moveLink(link, mousePosition));
    }

    private void moveDevice(Device<?> device, Point2D mousePosition) {
        device.setPosition(
                device.getPositionX() + calculateMoveAmountX(mousePosition),
                device.getPositionY() + calculateMoveAmountY(mousePosition)
        );
    }

    private void moveLink(Link link, Point2D mousePosition) {
        Point2D linkPoint = link.getPointClosestTo(mousePosition);

        linkPoint.setLocation(
                linkPoint.getX() + calculateMoveAmountX(mousePosition),
                linkPoint.getY() + calculateMoveAmountY(mousePosition)
        );
    }

    private double calculateMoveAmountX(Point2D mousePosition) {
        return mousePosition.getX() - previousMousePosition.getX();
    }

    private double calculateMoveAmountY(Point2D mousePosition) {
        return mousePosition.getY() - previousMousePosition.getY();
    }

    @Override
    public void mouseMoved(MouseEvent event) {}

}
