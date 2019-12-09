package gerudok.view.desktop;

import gerudok.model.Page;
import gerudok.model.Slot;
import gerudok.model.observer.Observer;
import gerudok.model.observer.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class PagePanel extends JPanel implements Subject {

    private final Page model;

    private final JTabbedPane slotTabbedPane = new JTabbedPane();

    private final Set<SlotPanel> slotPanels = new LinkedHashSet<>();

    public PagePanel(Page model) {
        this.model = model;

        initializePagePanel();
        Desktop.getInstance().getSelectedDiagramFrame().addPagePanel(this);
    }

    private void initializePagePanel() {
        setLayout(new BorderLayout());
        add(slotTabbedPane, BorderLayout.CENTER);

        Observer.addSubject(this);
    }

    public Page getModel() {
        return model;
    }

    public void addSlotPanel(SlotPanel slotPanel) {
        if (slotPanel == null)
            throw new IllegalArgumentException("Slot panel can't be null!");

        slotPanels.add(slotPanel);
        slotTabbedPane.addTab(slotPanel.getModel().getName(), slotPanel);
    }

    public SlotPanel getSelectedSlotPanel() {
        return (SlotPanel) slotTabbedPane.getSelectedComponent();
    }

    public SlotPanel selectSlotPanel(Slot model) {
        SlotPanel slotPanelToSelect = slotPanels.stream()
                .filter(slotPanel -> slotPanel.getModel().equals(model))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Slot panel not found!"));

        slotTabbedPane.setSelectedComponent(slotPanelToSelect);

        return slotPanelToSelect;
    }

    @Override
    public void update() {
        updateUI();
    }

    @Override
    public String toString() {
        return String.format("PagePanel{model=%s}", model);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PagePanel) {
            PagePanel other = (PagePanel) o;

            return Objects.equals(this.model, other.model);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

}
