package gerudok.view.desktop;

import gerudok.model.Slot;
import gerudok.model.device.Device;
import gerudok.model.observer.Observer;
import gerudok.model.observer.Subject;
import gerudok.view.desktop.state.SlotPanelState;
import gerudok.view.desktop.state.SlotPanelStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Objects;

public class SlotPanel extends JPanel implements Subject {

    private final Slot model;

    private final SlotPanelCanvas canvas = new SlotPanelCanvas(this);

    private final SlotPanelTransformations transformations = new SlotPanelTransformations(this);

    private final SlotPanelStateManager stateManager = new SlotPanelStateManager(this);

    public SlotPanel(Slot model) {
        this.model = model;

        initializeSlotPanel();

        Desktop.getInstance().getSelectedPagePanel().addSlotPanel(this);
    }

    private void initializeSlotPanel() {
        setLayout(new BorderLayout());

        add(canvas, BorderLayout.CENTER);
        add(new SlotPanelPalette(), BorderLayout.EAST);

        Observer.addSubject(this);
    }

    AffineTransform getTransformationMatrix() {
        return transformations.getTransformationMatrix();
    }

    public Slot getModel() {
        return model;
    }

    public Point2D transformPosition(Point2D position) {
        return transformations.transformPosition(position);
    }

    public void regionZoom() {
        transformations.regionZoom();
    }

    public void regionZoom(List<Device<?>> devices) {
        transformations.regionZoom(devices);
    }

    public void centerZoomIn() {
        transformations.centerZoomIn();
    }

    public void centerZoomOut() {
        transformations.centerZoomOut();
    }

    public double calculateNewScaleForMouseWheelEvent(MouseWheelEvent event) {
        return transformations.calculateNewScaleForMouseWheelEvent(event);
    }

    public void transformToNewScaleForMouseEvent(MouseEvent event, double newScale) {
        transformations.transformToNewScaleForMouseEvent(event, newScale);
    }

    public SlotPanelState getCurrentState() {
        return stateManager.getCurrentState();
    }

    public void startCircleState() {
        stateManager.startCircleState();
    }

    public void startRectangleState() {
        stateManager.startRectangleState();
    }

    public void startTriangleState() {
        stateManager.startTriangleState();
    }

    public void startLinkState() {
        stateManager.startLinkState();
    }

    public void startSelectionState() {
        stateManager.startSelectionState();
    }

    public void startMoveState() {
        stateManager.startMoveState();
    }

    public void startSelectionRectangleState() {
        stateManager.startSelectionRectangleState();
    }

    public void setCanvasCursor(Cursor cursor) {
        canvas.setCursor(cursor);
    }

    public void paintSelectionRectangle(Rectangle2D selectionRectangle) {
        canvas.setSelectionRectangle(selectionRectangle);
    }

    @Override
    public void update() {
        canvas.updateUI();
        updateUI();
    }

    @Override
    public String toString() {
        return String.format("SlotPanel{model=%s}", model);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SlotPanel) {
            SlotPanel other = (SlotPanel) o;

            return Objects.equals(this.model, other.model);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

}
