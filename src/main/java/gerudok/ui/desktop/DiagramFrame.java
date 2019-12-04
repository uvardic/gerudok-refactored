package gerudok.ui.desktop;

import gerudok.model.Diagram;
import gerudok.model.Page;
import gerudok.observer.Observer;
import gerudok.observer.Subject;
import gerudok.ui.util.UISizeCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class DiagramFrame extends JInternalFrame implements Subject {

    private static final int INITIAL_SCROLL_BAR_VALUE = 150;

    private static final int MIN_SCROLL_BAR_VALUE = 0;

    private static final int MAX_SCROLL_BAR_VALUE = 300;

    private static final int SCROLL_BAR_EXTENT = 20;

    private static final int SCROLL_BAR_TRANSLATE_FACTOR = -10;

    private final Diagram model;

    private final JTabbedPane pageTabbedPane = new JTabbedPane();

    private final JScrollBar verticalScrollBar = new JScrollBar(
            Adjustable.VERTICAL, INITIAL_SCROLL_BAR_VALUE, SCROLL_BAR_EXTENT,
            MIN_SCROLL_BAR_VALUE, MAX_SCROLL_BAR_VALUE
    );

    private final JScrollBar horizontalScrollBar = new JScrollBar(
            Adjustable.HORIZONTAL, INITIAL_SCROLL_BAR_VALUE, SCROLL_BAR_EXTENT,
            MIN_SCROLL_BAR_VALUE, MAX_SCROLL_BAR_VALUE
    );

    private final Set<PagePanel> pagePanels = new LinkedHashSet<>();

    public DiagramFrame(Diagram model) {
        super(formatName(model), true, true, true, true);

        this.model = model;

        initializeDiagramFrame();
        Observer.observerSubject(this);
        Desktop.getInstance().addFrame(this);
    }

    private static String formatName(Diagram model) {
        return String.format("%s - %s", model.getParent().getName(), model.getName());
    }

    private void initializeDiagramFrame() {
        initializeDiagramFrameSize();
        initializeDiagramFrameComponents();
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setVisible(true);
    }

    private void initializeDiagramFrameSize() {
        setSize(UISizeCalculator.calculateDiagramFrameSize());
        setMinimumSize(UISizeCalculator.calculateDiagramFrameMinSize());
        setMaximumSize(UISizeCalculator.calculateDiagramFrameMaxSize());
    }

    private void initializeDiagramFrameComponents() {
        verticalScrollBar.addAdjustmentListener(this::verticalScrollBarAdjustmentListener);
        horizontalScrollBar.addAdjustmentListener(this::horizontalScrollBarAdjustmentListener);

        add(pageTabbedPane, BorderLayout.CENTER);
        add(verticalScrollBar, BorderLayout.EAST);
        add(horizontalScrollBar, BorderLayout.SOUTH);
    }

    private int verticalScrollBarValue = INITIAL_SCROLL_BAR_VALUE;

    private void verticalScrollBarAdjustmentListener(AdjustmentEvent event) {
        SlotPanel selectedSlotPanel = Desktop.getInstance().getSelectedSlotPanel();

        selectedSlotPanel.getTransformationMatrix().translate(
                0, (event.getValue() - verticalScrollBarValue) * SCROLL_BAR_TRANSLATE_FACTOR
        );
        verticalScrollBarValue = event.getValue();

        repaint();
    }

    private int horizontalScrollBarValue = INITIAL_SCROLL_BAR_VALUE;

    private void horizontalScrollBarAdjustmentListener(AdjustmentEvent event) {
        SlotPanel selectedSlotPanel = Desktop.getInstance().getSelectedSlotPanel();

        selectedSlotPanel.getTransformationMatrix().translate(
                (event.getValue() - horizontalScrollBarValue) * SCROLL_BAR_TRANSLATE_FACTOR, 0
        );
        horizontalScrollBarValue = event.getValue();

        repaint();
    }

    public void adjustScrollBars(int verticalFactor, int horizontalFactor) {
        adjustVerticalScrollBar(verticalFactor);
        adjustHorizontalScrollBar(horizontalFactor);
    }

    private void adjustVerticalScrollBar(int verticalFactor) {
        if (isVerticalScrollBarInBounds(verticalFactor))
            verticalScrollBar.setValue(
                    verticalScrollBar.getValue() + verticalScrollBar.getUnitIncrement() * verticalFactor
            );
    }

    private boolean isVerticalScrollBarInBounds(int verticalFactor) {
        return (verticalScrollBar.getValue() >= MIN_SCROLL_BAR_VALUE && verticalFactor > 0)
                || (verticalScrollBar.getValue() <= MAX_SCROLL_BAR_VALUE && verticalFactor < 0);
    }

    private void adjustHorizontalScrollBar(int horizontalFactor) {
        if (isHorizontalScrollBarInBounds(horizontalFactor))
            horizontalScrollBar.setValue(
                    horizontalScrollBar.getValue() + horizontalScrollBar.getUnitIncrement() * horizontalFactor
            );
    }

    private boolean isHorizontalScrollBarInBounds(int horizontalFactor) {
        return (horizontalScrollBar.getValue() >= MIN_SCROLL_BAR_VALUE && horizontalFactor > 0)
                || (horizontalScrollBar.getValue() <= MAX_SCROLL_BAR_VALUE && horizontalFactor < 0);
    }

    public Diagram getModel() {
        return model;
    }

    public void addPagePanel(PagePanel pagePanel) {
        if (pagePanel == null)
            throw new IllegalArgumentException("Page panel can't be null!");

        pagePanels.add(pagePanel);
        pageTabbedPane.addTab(pagePanel.getModel().getName(), pagePanel);
    }

    public void removePagePanel(PagePanel pagePanel) {
        pagePanels.remove(pagePanel);
        pageTabbedPane.remove(pagePanel);
    }

    public PagePanel getSelectedPagePanel() {
        return (PagePanel) pageTabbedPane.getSelectedComponent();
    }

    public PagePanel selectPagePanel(Page model) {
        PagePanel pagePanelToSelect = pagePanels.stream()
                .filter(pagePanel -> pagePanel.getModel().equals(model))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Page panel not found!"));

        pageTabbedPane.setSelectedComponent(pagePanelToSelect);

        return pagePanelToSelect;
    }

    @Override
    public void update() {
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public String toString() {
        return String.format("DiagramFrame{model=%s}", model);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DiagramFrame) {
            DiagramFrame other = (DiagramFrame) o;

            return Objects.equals(this.model, other.model);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

}
