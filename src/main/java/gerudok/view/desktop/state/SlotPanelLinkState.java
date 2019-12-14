package gerudok.view.desktop.state;

import gerudok.model.device.DeviceIO;
import gerudok.model.link.Link;
import gerudok.view.desktop.SlotPanel;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class SlotPanelLinkState implements SlotPanelState {

    private final SlotPanel slotPanel;

    public SlotPanelLinkState(SlotPanel slotPanel) {
        this.slotPanel = slotPanel;
    }

    private Link link;

    @Override
    public void mousePressed(MouseEvent event) {
        Point2D mousePosition = slotPanel.transformPosition(event.getPoint());

        if (event.getButton() == MouseEvent.BUTTON3 && isLinkCreated()) {
            deleteLink();
            return;
        }

        if (event.getButton() != MouseEvent.BUTTON1)
            return;

        boolean isDeviceAtPoint = slotPanel.getModel().isDeviceAt(mousePosition);

        if (isDeviceAtPoint && !isLinkCreated())
            startLink(mousePosition);
        else if (!isDeviceAtPoint && isLinkCreated())
            addLinkPoint(mousePosition);
        else if (isDeviceAtPoint)
            endLink(mousePosition);
    }

    private void startLink(Point2D mousePosition) {
        DeviceIO output = slotPanel.getModel()
                .getDeviceAt(mousePosition)
                .getClosestOutputTo(mousePosition);

        link = new Link.Builder(slotPanel.getModel(), output).build();
    }

    private void addLinkPoint(Point2D mousePosition) {
        link.addPoint(mousePosition);
    }

    private void endLink(Point2D mousePosition) {
        DeviceIO input = slotPanel.getModel()
                .getDeviceAt(mousePosition)
                .getClosestInputTo(mousePosition);

        link.setInput(input);
        link = null;
    }

    private void deleteLink() {
        slotPanel.getModel().removeElement(link);
        link.removeAllPoints();
        link = null;
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        moveLastLinkPoint(event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        moveLastLinkPoint(event);
    }

    private void moveLastLinkPoint(MouseEvent event) {
        if (!isLinkCreated())
            return;

        link.getLastPoint().setLocation(slotPanel.transformPosition(event.getPoint()));
    }

    private boolean isLinkCreated() {
        return link != null;
    }

}
