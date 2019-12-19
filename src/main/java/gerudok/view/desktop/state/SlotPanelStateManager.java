package gerudok.view.desktop.state;

import gerudok.view.desktop.SlotPanel;

public class SlotPanelStateManager {

    private final SlotPanelCircleState circleState;

    private final SlotPanelRectangleState rectangleState;

    private final SlotPanelTriangleState triangleState;

    private final SlotPanelLinkState linkState;

    private final SlotPanelSelectionState selectionState;

    private final SlotPanelMoveState moveState;

    private final SlotPanelSelectionRectangleState selectionRectangleState;

    private SlotPanelState currentState;

    public SlotPanelStateManager(SlotPanel slotPanel) {
        this.circleState             = new SlotPanelCircleState(slotPanel);
        this.rectangleState          = new SlotPanelRectangleState(slotPanel);
        this.triangleState           = new SlotPanelTriangleState(slotPanel);
        this.linkState               = new SlotPanelLinkState(slotPanel);
        this.selectionState          = new SlotPanelSelectionState(slotPanel);
        this.moveState               = new SlotPanelMoveState(slotPanel);
        this.selectionRectangleState = new SlotPanelSelectionRectangleState(slotPanel);

        currentState = linkState;
    }

    public SlotPanelState getCurrentState() {
        return currentState;
    }

    public void startCircleState() {
        currentState = circleState;
    }

    public void startRectangleState() {
        currentState = rectangleState;
    }

    public void startTriangleState() {
        currentState = triangleState;
    }

    public void startLinkState() {
        currentState = linkState;
    }

    public void startSelectionState() {
        currentState = selectionState;
    }

    public void startMoveState() {
        currentState = moveState;
    }

    public void startSelectionRectangleState() {
        currentState = selectionRectangleState;
    }

}
