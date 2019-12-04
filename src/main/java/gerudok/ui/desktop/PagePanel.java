package gerudok.ui.desktop;

import gerudok.model.Page;
import gerudok.model.Slot;
import gerudok.observer.Observer;
import gerudok.observer.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class PagePanel extends JPanel implements Subject {

    private final Page model;

    private final JTabbedPane slotTabbedPane = new JTabbedPane();

    private final Set<SlotPanel> slotPanels = new LinkedHashSet<>();

    public PagePanel(Page model) {
        this.model = model;

        initializePagePanel();
        Observer.observerSubject(this);
        Desktop.getInstance().getSelectedDiagramFrame().addPagePanel(this);
    }

    private void initializePagePanel() {
        setLayout(new BorderLayout());
        add(slotTabbedPane, BorderLayout.CENTER);
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

    public void removeSlotPanel(SlotPanel slotPanel) {
        slotPanel.remove(slotPanel);
        slotTabbedPane.remove(slotPanel);
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
        SwingUtilities.updateComponentTreeUI(this);
    }
}
