package gerudok.controller.action;

import gerudok.controller.action.file.*;
import gerudok.controller.action.palette.*;
import gerudok.controller.action.view.*;

public class ActionProvider {

    private static ActionProvider instance;

    private final CreateProjectAction createProjectAction = new CreateProjectAction();

    private final CreateDiagramAction createDiagramAction = new CreateDiagramAction();

    private final CreatePageAction createPageAction = new CreatePageAction();

    private final CreateSlotAction createSlotAction = new CreateSlotAction();

    private final SlotPanelCircleStateAction slotPanelCircleStateAction = new SlotPanelCircleStateAction();

    private final SlotPanelLinkStateAction slotPanelLinkStateAction = new SlotPanelLinkStateAction();

    private final SlotPanelRectangleStateAction slotPanelRectangleStateAction = new SlotPanelRectangleStateAction();

    private final SlotPanelSelectionStateAction slotPanelSelectionStateAction = new SlotPanelSelectionStateAction();

    private final SlotPanelTriangleStateAction slotPanelTriangleStateAction = new SlotPanelTriangleStateAction();

    private final CreateFastSlotAction createFastSlotAction = new CreateFastSlotAction();

    private final CascadeFramesAction cascadeFramesAction = new CascadeFramesAction();

    private final SlotPanelRegionZoomAction slotPanelRegionZoomAction = new SlotPanelRegionZoomAction();

    private final SlotPanelZoomInAction slotPanelZoomInAction = new SlotPanelZoomInAction();

    public final SlotPanelZoomOutAction slotPanelZoomOutAction = new SlotPanelZoomOutAction();

    private final TileFramesHorizontallyAction tileFramesHorizontallyAction = new TileFramesHorizontallyAction();

    private final TileFramesVerticallyAction tileFramesVerticallyAction = new TileFramesVerticallyAction();

    public static ActionProvider getInstance() {
        if (instance != null)
            return instance;

        return initializeActionProvider();
    }

    private static ActionProvider initializeActionProvider() {
        instance = new ActionProvider();

        return instance;
    }

    private ActionProvider() {}

    public CreateProjectAction getCreateProjectAction() {
        return createProjectAction;
    }

    public CreateDiagramAction getCreateDiagramAction() {
        return createDiagramAction;
    }

    public CreatePageAction getCreatePageAction() {
        return createPageAction;
    }

    public CreateSlotAction getCreateSlotAction() {
        return createSlotAction;
    }

    public SlotPanelCircleStateAction getSlotPanelCircleStateAction() {
        return slotPanelCircleStateAction;
    }

    public SlotPanelLinkStateAction getSlotPanelLinkStateAction() {
        return slotPanelLinkStateAction;
    }

    public SlotPanelRectangleStateAction getSlotPanelRectangleStateAction() {
        return slotPanelRectangleStateAction;
    }

    public SlotPanelSelectionStateAction getSlotPanelSelectionStateAction() {
        return slotPanelSelectionStateAction;
    }

    public SlotPanelTriangleStateAction getSlotPanelTriangleStateAction() {
        return slotPanelTriangleStateAction;
    }

    public CreateFastSlotAction getCreateFastSlotAction() {
        return createFastSlotAction;
    }

    public CascadeFramesAction getCascadeFramesAction() {
        return cascadeFramesAction;
    }

    public SlotPanelRegionZoomAction getSlotPanelRegionZoomAction() {
        return slotPanelRegionZoomAction;
    }

    public SlotPanelZoomInAction getSlotPanelZoomInAction() {
        return slotPanelZoomInAction;
    }

    public SlotPanelZoomOutAction getSlotPanelZoomOutAction() {
        return slotPanelZoomOutAction;
    }

    public TileFramesHorizontallyAction getTileFramesHorizontallyAction() {
        return tileFramesHorizontallyAction;
    }

    public TileFramesVerticallyAction getTileFramesVerticallyAction() {
        return tileFramesVerticallyAction;
    }

}
