package gerudok.ui;

import gerudok.model.Page;
import gerudok.observer.Observer;
import gerudok.observer.Subject;

import javax.swing.*;
import java.awt.*;

public class PagePanel extends JPanel implements Subject {

    private final Page model;

    private final JTabbedPane slotTabbedPane = new JTabbedPane();

    public PagePanel(Page model) {
        this.model = model;

        initializePagePanel();

        Desktop.getInstance().getSelectedDiagramFrame().addPagePanel(this);
    }

    private void initializePagePanel() {
        Observer.observerSubject(this);

        setLayout(new BorderLayout());
        add(slotTabbedPane, BorderLayout.CENTER);
    }

    public Page getModel() {
        return model;
    }

    public void addSlotPanel(SlotPanel slotPanel) {
        if (slotPanel == null)
            throw new IllegalArgumentException("Slot panel can't be null!");

        slotTabbedPane.addTab(slotPanel.getModel().getName(), slotPanel);
    }

    public void removeSlotPanel(SlotPanel slotPanel) {
        slotTabbedPane.remove(slotPanel);
    }

    public SlotPanel getSelectedSlotPanel() {
        return (SlotPanel) slotTabbedPane.getSelectedComponent();
    }

    @Override
    public void update() {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
