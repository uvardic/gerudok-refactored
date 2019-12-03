package gerudok.ui;

import gerudok.model.Diagram;
import gerudok.ui.desktop.Desktop;
import gerudok.ui.util.UISizeCalculator;

import javax.swing.*;
import java.util.Objects;

public class DiagramFrame extends JInternalFrame {

    private final Diagram model;

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

        // Needs to be the last method call
        setVisible(true);
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
