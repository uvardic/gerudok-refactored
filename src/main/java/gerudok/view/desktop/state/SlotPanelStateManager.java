package gerudok.view.desktop.state;

import gerudok.view.desktop.SlotPanel;

public class SlotPanelStateManager {

    private final SlotPanelCircleState circleState;

    private SlotPanelState currentState;

    public SlotPanelStateManager(SlotPanel slotPanel) {
        this.circleState = new SlotPanelCircleState(slotPanel);

        currentState = circleState;
    }

    public SlotPanelState getCurrentState() {
        return currentState;
    }

    public void startCircleState() {
        currentState = circleState;
    }

}
