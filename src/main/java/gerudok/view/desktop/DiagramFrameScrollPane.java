package gerudok.view.desktop;

import gerudok.model.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;

class DiagramFrameScrollPane {

    private static final int INITIAL_SCROLL_BAR_VALUE = 150;

    private static final int MIN_SCROLL_BAR_VALUE = 0;

    private static final int MAX_SCROLL_BAR_VALUE = 300;

    private static final int SCROLL_BAR_EXTENT = 40;

    private static final int SCROLL_BAR_TRANSLATE_FACTOR = -10;

    private final JScrollBar verticalScrollBar = new JScrollBar(
            Adjustable.VERTICAL, INITIAL_SCROLL_BAR_VALUE, SCROLL_BAR_EXTENT,
            MIN_SCROLL_BAR_VALUE, MAX_SCROLL_BAR_VALUE
    );

    private final JScrollBar horizontalScrollBar = new JScrollBar(
            Adjustable.HORIZONTAL, INITIAL_SCROLL_BAR_VALUE, SCROLL_BAR_EXTENT,
            MIN_SCROLL_BAR_VALUE, MAX_SCROLL_BAR_VALUE
    );

    private int verticalScrollBarValue = INITIAL_SCROLL_BAR_VALUE;

    private int horizontalScrollBarValue = INITIAL_SCROLL_BAR_VALUE;

    DiagramFrameScrollPane() {
        verticalScrollBar.addAdjustmentListener(this::verticalScrollBarAdjustmentListener);
        horizontalScrollBar.addAdjustmentListener(this::horizontalScrollBarAdjustmentListener);
    }

    private void verticalScrollBarAdjustmentListener(AdjustmentEvent event) {
        getSelectedSlotPanel().getTransformationMatrix().translate(
                0, (event.getValue() - verticalScrollBarValue) * SCROLL_BAR_TRANSLATE_FACTOR
        );
        verticalScrollBarValue = event.getValue();

        Observer.updateSubject(DiagramFrame.class);
    }

    private void horizontalScrollBarAdjustmentListener(AdjustmentEvent event) {
        getSelectedSlotPanel().getTransformationMatrix().translate(
                (event.getValue() - horizontalScrollBarValue) * SCROLL_BAR_TRANSLATE_FACTOR, 0
        );
        horizontalScrollBarValue = event.getValue();

        Observer.updateSubject(DiagramFrame.class);
    }

    private SlotPanel getSelectedSlotPanel() {
        return Desktop.getInstance().getSelectedSlotPanel();
    }

    JScrollBar getVerticalScrollBar() {
        return verticalScrollBar;
    }

    JScrollBar getHorizontalScrollBar() {
        return horizontalScrollBar;
    }

    void adjustScrollBarsValue(int verticalFactor, int horizontalFactor) {
        adjustVerticalScrollBarValue(verticalFactor);
        adjustHorizontalScrollBarValue(horizontalFactor);
    }

    private void adjustVerticalScrollBarValue(int verticalFactor) {
        if (isVerticalScrollBarValueInBounds(verticalFactor))
            verticalScrollBar.setValue(
                    verticalScrollBar.getValue() + verticalScrollBar.getUnitIncrement() * verticalFactor
            );
    }

    private boolean isVerticalScrollBarValueInBounds(int verticalFactor) {
        return (verticalScrollBar.getValue() >= MIN_SCROLL_BAR_VALUE && verticalFactor > 0)
                || (verticalScrollBar.getValue() <= MAX_SCROLL_BAR_VALUE && verticalFactor < 0);
    }

    private void adjustHorizontalScrollBarValue(int horizontalFactor) {
        if (isHorizontalScrollBarValueInBounds(horizontalFactor))
            horizontalScrollBar.setValue(
                    horizontalScrollBar.getValue() + horizontalScrollBar.getUnitIncrement() * horizontalFactor
            );
    }

    private boolean isHorizontalScrollBarValueInBounds(int horizontalFactor) {
        return (horizontalScrollBar.getValue() >= MIN_SCROLL_BAR_VALUE && horizontalFactor > 0)
                || (horizontalScrollBar.getValue() <= MAX_SCROLL_BAR_VALUE && horizontalFactor < 0);
    }

}
