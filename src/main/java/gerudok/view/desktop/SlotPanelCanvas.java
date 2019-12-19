package gerudok.view.desktop;

import gerudok.controller.SlotPanelCanvasController;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

class SlotPanelCanvas extends JPanel  {

    private final SlotPanel slotPanel;

    private Rectangle2D selectionRectangle = new Rectangle2D.Double();

    SlotPanelCanvas(SlotPanel slotPanel) {
        this.slotPanel = slotPanel;

        initializeSlotPanelCanvas();
        initializeSlotPanelController();
    }

    private void initializeSlotPanelCanvas() {
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void initializeSlotPanelController() {
        SlotPanelCanvasController controller = new SlotPanelCanvasController();

        addMouseListener(controller);
        addMouseWheelListener(controller);
        addMouseMotionListener(controller);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = initializeGraphics(graphics);

        slotPanel.getModel().paintElements(graphics2D);
        paintSelectionRectangle(graphics2D);
    }

    private Graphics2D initializeGraphics(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.transform(slotPanel.getTransformationMatrix());

        return graphics2D;
    }

    private void paintSelectionRectangle(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(
                1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1f, new float[] { 3, 6 }, 0
        ));

        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(selectionRectangle);
    }

    void setSelectionRectangle(Rectangle2D selectionRectangle) {
        this.selectionRectangle = selectionRectangle;
    }
}
