package gerudok.controller;

import gerudok.view.desktop.Desktop;
import gerudok.view.desktop.DiagramFrame;
import gerudok.view.desktop.SlotPanel;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class SlotPanelCanvasController extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent event) {
        Desktop.getInstance().getSelectedSlotPanel().getCurrentState().mousePressed(event);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        Desktop.getInstance().getSelectedSlotPanel().getCurrentState().mouseDragged(event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        Desktop.getInstance().getSelectedSlotPanel().getCurrentState().mouseMoved(event);
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
        SlotPanel slotPane = Desktop.getInstance().getSelectedSlotPanel();

        double newScale = slotPane.calculateNewScaleForMouseWheelEvent(event);

        slotPane.transformToNewScaleForMouseEvent(event, newScale);
    }


}
