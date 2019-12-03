package gerudok.ui;

import gerudok.model.Diagram;
import gerudok.observer.Observer;
import gerudok.observer.Subject;
import gerudok.ui.util.UISizeCalculator;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DiagramFrame extends JInternalFrame implements Subject {

    private final Diagram model;

    private final JTabbedPane pageTabbedPane = new JTabbedPane();

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

    public void addPagePanel(PagePanel pagePanel) {
        if (pagePanel == null)
            throw new IllegalArgumentException("Page panel can't be null!");

        pageTabbedPane.addTab(pagePanel.getModel().getName(), pagePanel);
    }

    public void removePagePanel(PagePanel pagePanel) {
        pageTabbedPane.remove(pagePanel);
    }

    public PagePanel getSelectedPagePanel() {
        return (PagePanel) pageTabbedPane.getSelectedComponent();
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
