package gerudok.ui;

import gerudok.model.Diagram;
import gerudok.model.Page;
import gerudok.observer.Observer;
import gerudok.observer.Subject;
import gerudok.ui.util.UISizeCalculator;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class DiagramFrame extends JInternalFrame implements Subject {

    private final Diagram model;

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
        setSize(UISizeCalculator.calculateDiagramFrameSize());
        setMinimumSize(UISizeCalculator.calculateDiagramFrameMinSize());
        setMaximumSize(UISizeCalculator.calculateDiagramFrameMaxSize());

        add(pageTabbedPane, BorderLayout.CENTER);

        Observer.observerSubject(this);

        // Needs to be the last method call
        setVisible(true);
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
