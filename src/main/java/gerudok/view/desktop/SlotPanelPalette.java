package gerudok.view.desktop;

import gerudok.controller.action.ActionProvider;

import javax.swing.*;

public class SlotPanelPalette extends JToolBar {

    private final ButtonGroup buttonGroup = new ButtonGroup();

    public SlotPanelPalette() {
        super(VERTICAL);

        initializePalette();
    }

    private void initializePalette() {
        setFloatable(false);
        initializeButton(ActionProvider.getInstance().getSlotPanelCircleStateAction());
        initializeButton(ActionProvider.getInstance().getSlotPanelRectangleStateAction());
        initializeButton(ActionProvider.getInstance().getSlotPanelTriangleStateAction());
        initializeButton(ActionProvider.getInstance().getSlotPanelLinkStateAction());
        initializeButton(ActionProvider.getInstance().getSlotPanelSelectionStateAction());
    }

    private void initializeButton(AbstractAction action) {
        JToggleButton toggleButton = new JToggleButton(action);

        toggleButton.setHideActionText(true);

        buttonGroup.add(toggleButton);
        add(toggleButton);
    }

}
