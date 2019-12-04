package gerudok.ui.desktop;

import gerudok.model.Slot;

import javax.swing.*;

public class SlotPanel extends JPanel {

    private final Slot model;

    public SlotPanel(Slot model) {
        this.model = model;

        Desktop.getInstance().getSelectedPagePanel().addSlotPanel(this);
    }

    public Slot getModel() {
        return model;
    }
}
