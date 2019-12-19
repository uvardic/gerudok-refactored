package gerudok.view.desktop.state;

import gerudok.view.desktop.SlotPanel;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class SlotPanelSelectionRectangleState implements SlotPanelState {

    private final SlotPanel slotPanel;

    private Point2D initialMousePosition;

    private boolean lassoStarted;

    public SlotPanelSelectionRectangleState(SlotPanel slotPanel) {
        this.slotPanel = slotPanel;
    }

    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseReleased(MouseEvent event) {
        slotPanel.paintSelectionRectangle(new Rectangle2D.Double());
        slotPanel.startSelectionState();
        lassoStarted = false;
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        Point2D mousePosition = slotPanel.transformPosition(event.getPoint());

        initializeState(mousePosition);

        Rectangle2D selectionRectangle = defineSelectionRectangle(mousePosition);

        slotPanel.paintSelectionRectangle(selectionRectangle);
        slotPanel.getModel().selectElementsIn(selectionRectangle);
    }

    private void initializeState(Point2D mousePosition) {
        if (!lassoStarted) {
            initialMousePosition = mousePosition;
            lassoStarted = true;
        }
    }

    private Rectangle2D defineSelectionRectangle(Point2D mousePosition) {
        double width = mousePosition.getX() - initialMousePosition.getX();

        double height = mousePosition.getY() - initialMousePosition.getY();

        if (width < 0 && height < 0)
            return new Rectangle2D.Double(
                    mousePosition.getX(), mousePosition.getY(), Math.abs(width), Math.abs(height)
            );
        else if (width < 0 && height >= 0)
            return new Rectangle2D.Double(
                    mousePosition.getX(), initialMousePosition.getY(), Math.abs(width), Math.abs(height)
            );
        else if (width > 0 && height < 0)
            return new Rectangle2D.Double(
                    initialMousePosition.getX(), mousePosition.getY(), Math.abs(width), Math.abs(height)
            );
        return new Rectangle2D.Double(
                initialMousePosition.getX(), initialMousePosition.getY(), Math.abs(width), Math.abs(height)
        );
    }

    @Override
    public void mouseMoved(MouseEvent event) {}
}
