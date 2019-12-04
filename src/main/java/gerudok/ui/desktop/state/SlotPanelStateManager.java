package gerudok.ui.desktop.state;

import gerudok.model.Slot;

public class SlotPanelStateManager {

    private final SlotPanelCircleState circleState;

    private SlotPanelState currentState;

    public SlotPanelStateManager(Slot model) {
        this.circleState = new SlotPanelCircleState(model);

        currentState = circleState;
    }

    public SlotPanelState getCurrentState() {
        return currentState;
    }

    public void startCircleState() {
        currentState = circleState;
    }

}
