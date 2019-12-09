package gerudok.view.desktop;

import gerudok.model.Diagram;
import gerudok.model.Page;
import gerudok.model.observer.Observer;
import gerudok.model.observer.Subject;
import gerudok.view.util.SizeCalculator;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class DiagramFrame extends JInternalFrame implements Subject {

    private final Diagram model;

    private final DiagramFrameScrollPane scrollPane = new DiagramFrameScrollPane();

    private final JTabbedPane pageTabbedPane = new JTabbedPane();

    private final Set<PagePanel> pagePanels = new LinkedHashSet<>();

    public DiagramFrame(Diagram model) {
        super(formatName(model), true, true, true, true);

        this.model = model;

        initializeDiagramFrame();
        Desktop.getInstance().addFrame(this);
    }

    private static String formatName(Diagram model) {
        return String.format("%s - %s", model.getParent().getName(), model.getName());
    }

    private void initializeDiagramFrame() {
        initializeDiagramFrameSize();
        initializeDiagramFrameComponents();
        Observer.addSubject(this);
    }

    private void initializeDiagramFrameSize() {
        setSize(SizeCalculator.calculateDiagramFrameSize());
        setMinimumSize(SizeCalculator.calculateDiagramFrameMinSize());
        setMaximumSize(SizeCalculator.calculateDiagramFrameMaxSize());
    }

    private void initializeDiagramFrameComponents() {
        add(pageTabbedPane, BorderLayout.CENTER);
        add(new DiagramFramePalette(), BorderLayout.NORTH);
        add(scrollPane.getVerticalScrollBar(), BorderLayout.EAST);
        add(scrollPane.getHorizontalScrollBar(), BorderLayout.SOUTH);
    }

    public void addPagePanel(PagePanel pagePanel) {
        if (pagePanel == null)
            throw new IllegalArgumentException("Page panel can't be null!");

        pagePanels.add(pagePanel);
        pageTabbedPane.addTab(pagePanel.getModel().getName(), pagePanel);
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

    public void adjustScrollBarsValue(int verticalFactor, int horizontalFactor) {
        scrollPane.adjustScrollBarsValue(verticalFactor, horizontalFactor);
    }

    public Diagram getModel() {
        return model;
    }

    @Override
    public void update() {
        updateUI();
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
