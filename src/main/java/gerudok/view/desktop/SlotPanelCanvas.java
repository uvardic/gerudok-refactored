package gerudok.view.desktop;

import gerudok.controller.SlotPanelCanvasController;

import javax.swing.*;
import java.awt.*;

class SlotPanelCanvas extends JPanel  {

    private final SlotPanel slotPanel;

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
    }

    private Graphics2D initializeGraphics(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.transform(slotPanel.getTransformationMatrix());

        return graphics2D;
    }

}
