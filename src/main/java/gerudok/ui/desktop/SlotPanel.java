package gerudok.ui.desktop;

import gerudok.model.Slot;
import gerudok.model.device.Device;
import gerudok.ui.desktop.state.SlotPanelStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.List;

public class SlotPanel extends JPanel {

    private static final double SCALING_FACTOR = 1.2;

    private static final double MIN_SCALING_FACTOR = 0.2;

    private static final double MAX_SCALING_FACTOR = 5;

    private final Slot model;

    private final AffineTransform transformationMatrix = new AffineTransform();

    private final SlotPanelStateManager stateManager;

    public SlotPanel(Slot model) {
        super(new BorderLayout());

        this.model = model;
        this.stateManager = new SlotPanelStateManager(this);

        initializeSlotPanel();
        Desktop.getInstance().getSelectedPagePanel().addSlotPanel(this);
    }

    private void initializeSlotPanel() {
        add(new Canvas(), BorderLayout.CENTER);
    }

    public void transformPosition(Point2D position) {
        try {
            transformationMatrix.inverseTransform(position, position);
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    public void regionZoom() {
        regionZoomWorker(model.getChildDevices());
    }

    public void regionZoom(List<Device<?>> devices) {
        regionZoomWorker(devices);
    }

    private void regionZoomWorker(List<Device<?>> devices) {
        if (devices.size() == 0)
            return;

        double[][] scaledRegion = scaleRegion(createRegion(devices));

        transformationMatrix.translate(
                (-scaledRegion[0][0] + (getWidth() - scaledRegion[1][0]) / 2) / transformationMatrix.getScaleX(),
                (-scaledRegion[0][1] + (getHeight() - scaledRegion[1][1]) / 2) / transformationMatrix.getScaleY()
        );

        // Zoom out once for better view
        centerZoomOut();
    }

    private double[][] createRegion(List<Device<?>> devices) {
        double[][] region = new double[2][2];

        initializeRegion(devices.get(0), region);
        devices.forEach(device -> adjustRegion(device, region));

        return region;
    }

    private void initializeRegion(Device<?> firstDevice, double[][] region) {
        region[0][0] = firstDevice.getPositionX();
        region[0][1] = firstDevice.getPositionY();
        region[1][0] = firstDevice.getPositionX() + firstDevice.getWidth();
        region[1][1] = firstDevice.getPositionY() + firstDevice.getHeight();
    }

    private void adjustRegion(Device<?> device, double[][] region) {
        if (device.getPositionX() < region[0][0])
            region[0][0] = device.getPositionX();

        if (device.getPositionY() < region[0][1])
            region[0][1] = device.getPositionY();

        if (device.getPositionX() + device.getWidth() > region[1][0])
            region[1][0] = device.getPositionX() + device.getWidth();

        if (device.getPositionY() + device.getHeight() > region[1][1])
            region[1][1] = device.getPositionY() + device.getHeight();
    }

    private double[][] scaleRegion(double[][] region) {
        double newScale = boundScale(calculateNewScaleForRegionZoom(region));

        transformationMatrix.setToScale(newScale, newScale);

        return transformRegion(region);
    }

    private double calculateNewScaleForRegionZoom(double[][] region) {
        double[][] transformedRegion = transformRegion(region);

        double scaleX = getWidth() / transformedRegion[1][0];

        double scaleY = getHeight() / transformedRegion[1][1];

        if (scaleX < scaleY)
            return transformationMatrix.getScaleX() * scaleX;

        return transformationMatrix.getScaleY() * scaleY;
    }

    private double[][] transformRegion(double[][] region) {
        double[][] transformedRegion = new double[2][2];

        transformedRegion[0][0] = transformX(region[0][0]);
        transformedRegion[0][1] = transformY(region[0][1]);
        transformedRegion[1][0] = transformX(region[1][0]) - transformX(region[0][0]);
        transformedRegion[1][1] = transformY(region[1][1]) - transformY(region[0][1]);

        return transformedRegion;
    }

    private double transformX(double x) {
        return x * transformationMatrix.getScaleX() + transformationMatrix.getTranslateX();
    }

    private double transformY(double y) {
        return y * transformationMatrix.getScaleY() + transformationMatrix.getTranslateY();
    }

    public void centerZoomIn() {
        double newScale = calculateNewScaleForCenterZoomIn();

        transformToNewScaleForCenterZoom(newScale);
    }

    public void centerZoomOut() {
        double newScale = calculateNewScaleForCenterZoomOut();

        transformToNewScaleForCenterZoom(newScale);
    }

    private double calculateNewScaleForCenterZoomIn() {
        double scale = boundScale(transformationMatrix.getScaleX());

        return scale * SCALING_FACTOR;
    }

    private double calculateNewScaleForCenterZoomOut() {
        double scale = boundScale(transformationMatrix.getScaleX());

        return scale / SCALING_FACTOR;
    }

    private double boundScale(double scalingFactor) {
        if (scalingFactor < MIN_SCALING_FACTOR)
            return MIN_SCALING_FACTOR;

        return Math.min(scalingFactor, MAX_SCALING_FACTOR);
    }

    private void transformToNewScaleForCenterZoom(double newScale) {
        Point2D originalPosition = new Point(getWidth() / 2, getHeight() / 2);
        transformPosition(originalPosition);

        transformationMatrix.setToScale(newScale, newScale);

        Point2D newPosition = new Point(getWidth() / 2, getHeight() / 2);
        transformPosition(newPosition);

        transformationMatrix.translate(
                newPosition.getX() - originalPosition.getX(),
                newPosition.getY() - originalPosition.getY()
        );
    }

    public Slot getModel() {
        return model;
    }

    public AffineTransform getTransformationMatrix() {
        return transformationMatrix;
    }

    private class CanvasController extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent event) {
            stateManager.getCurrentState().mousePressed(event);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent event) {
            DiagramFrame selectedDiagramFrame = Desktop.getInstance().getSelectedDiagramFrame();

            if (isControlPressed(event))
                mouseWheelZoom(event);
            else if (isShiftPressed(event))
                selectedDiagramFrame.adjustScrollBarsValue(0, event.getWheelRotation());
            else
                selectedDiagramFrame.adjustScrollBarsValue(event.getWheelRotation(), 0);
        }

        private boolean isControlPressed(MouseWheelEvent event) {
            return (event.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0;
        }

        private boolean isShiftPressed(MouseWheelEvent event) {
            return (event.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0;
        }

        private void mouseWheelZoom(MouseWheelEvent event) {
            double newScale = calculateNewScaleForMouseWheelEvent(event);

            transformToNewScaleForMouseEvent(event, newScale);
        }

        private double calculateNewScaleForMouseWheelEvent(MouseWheelEvent event) {
            double scale = boundScale(transformationMatrix.getScaleX());

            if (isScrollWheelZoomingOut(event))
                return scale / (event.getWheelRotation() * SCALING_FACTOR);
            else
                return scale * (-event.getWheelRotation() * SCALING_FACTOR);
        }

        private boolean isScrollWheelZoomingOut(MouseWheelEvent event) {
            return event.getWheelRotation() > 0;
        }

        private void transformToNewScaleForMouseEvent(MouseEvent event, double newScale) {
            Point2D originalPosition = event.getPoint();
            transformPosition(originalPosition);

            transformationMatrix.setToScale(newScale, newScale);

            Point2D newPosition = event.getPoint();
            transformPosition(newPosition);

            transformationMatrix.translate(
                    newPosition.getX() - originalPosition.getX(),
                    newPosition.getY() - originalPosition.getY()
            );
        }
    }

    private class Canvas extends JPanel {

        private Canvas() {
            initializeCanvas();
            initializeCanvasController();
        }

        private void initializeCanvas() {
            setBackground(Color.WHITE);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        private void initializeCanvasController() {
            CanvasController controller = new CanvasController();

            addMouseListener(controller);
            addMouseWheelListener(controller);
            addMouseMotionListener(controller);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            Graphics2D graphics2D = initializeGraphics(graphics);

            paintElements(graphics2D);
        }

        private Graphics2D initializeGraphics(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics;

            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.transform(transformationMatrix);

            return graphics2D;
        }

        private void paintElements(Graphics2D graphics2D) {
            model.getChildren().forEach(element -> element.paint(graphics2D));
            repaint();
        }

    }

}
