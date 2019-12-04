package gerudok.ui.desktop;

import gerudok.model.Slot;
import gerudok.ui.desktop.state.SlotPanelStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SlotPanel extends JPanel {

    private final Slot model;

    private final SlotPanelStateManager stateManager;

    public SlotPanel(Slot model) {
        this.model = model;
        this.stateManager = new SlotPanelStateManager(model);

        initializeSlotPanel();
        Desktop.getInstance().getSelectedPagePanel().addSlotPanel(this);
    }

    private void initializeSlotPanel() {
        setLayout(new BorderLayout());

        add(new SlotPanelCanvas(), BorderLayout.CENTER);
    }

    public Slot getModel() {
        return model;
    }

    private class SlotPanelCanvas extends JPanel {

        private SlotPanelCanvas() {
            initializeSlotPanelCanvas();
            initializeSlotPanelCanvasController();
        }

        private void initializeSlotPanelCanvas() {
            setBackground(Color.WHITE);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        private void initializeSlotPanelCanvasController() {
            SlotPanelCanvasController controller = new SlotPanelCanvasController();

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

            return graphics2D;
        }

        private void paintElements(Graphics2D graphics2D) {
            model.getChildren().forEach(element -> element.paint(graphics2D));
            repaint();
        }

    }

    private class SlotPanelCanvasController extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent event) {
            stateManager.getCurrentState().mousePressed(event);
        }

    }

}
