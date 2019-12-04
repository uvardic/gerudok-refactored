package gerudok.ui.desktop;

import gerudok.model.Slot;
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
        this.stateManager = new SlotPanelStateManager(model);

        initializeSlotPanel();
        Desktop.getInstance().getSelectedPagePanel().addSlotPanel(this);
    }

    private void initializeSlotPanel() {
        add(new Canvas(), BorderLayout.CENTER);
    }

    public Slot getModel() {
        return model;
    }

    public AffineTransform getTransformationMatrix() {
        return transformationMatrix;
    }

    public void zoomIn() {
        double scalingFactor = boundScale(transformationMatrix.getScaleX()) * SCALING_FACTOR;

        Point2D oldPosition = new Point(getWidth() / 2, getHeight() / 2);

    }

    private double boundScale(double scalingFactor) {
        if (scalingFactor < MIN_SCALING_FACTOR)
            return MIN_SCALING_FACTOR;

        return Math.min(scalingFactor, MAX_SCALING_FACTOR);
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

    private class CanvasController extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent event) {
            transformPosition(event.getPoint());
            stateManager.getCurrentState().mousePressed(event);
        }

        private void transformPosition(Point2D position) {
            try {
                transformationMatrix.inverseTransform(position, position);
            } catch (NoninvertibleTransformException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent event) {
            DiagramFrame selectedDiagramFrame = Desktop.getInstance().getSelectedDiagramFrame();

            if (isControlPressed(event))
                mouseWheelZoom(event);
            else if (isShiftPressed(event))
                selectedDiagramFrame.adjustScrollBars(0, event.getWheelRotation());
            else
                selectedDiagramFrame.adjustScrollBars(event.getWheelRotation(), 0);
        }

        private boolean isControlPressed(MouseWheelEvent event) {
            return (event.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0;
        }

        private void mouseWheelZoom(MouseWheelEvent event) {
            double scale = calculateNewScale(event);

            Point2D originalPosition = event.getPoint();
            transformPosition(originalPosition);

            transformationMatrix.setToScale(scale, scale);

            Point2D newPosition = event.getPoint();
            transformPosition(newPosition);

            transformationMatrix.translate(
                    newPosition.getX() - originalPosition.getX(),
                    newPosition.getY() - originalPosition.getY()
            );
        }

        private double calculateNewScale(MouseWheelEvent event) {
            double scale = boundScale(transformationMatrix.getScaleX());

            if (isScrollWheelZoomingOut(event))
                return scale / (event.getWheelRotation() * SCALING_FACTOR);
            else
                return scale * (-event.getWheelRotation() * SCALING_FACTOR);
        }

        private boolean isScrollWheelZoomingOut(MouseWheelEvent event) {
            return event.getWheelRotation() > 0;
        }

        private boolean isShiftPressed(MouseWheelEvent event) {
            return (event.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0;
        }
    }

}
