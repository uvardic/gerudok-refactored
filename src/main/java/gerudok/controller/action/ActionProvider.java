package gerudok.controller.action;

import gerudok.controller.action.file.*;
import gerudok.controller.action.palette.SlotPanelCircleStateAction;
import gerudok.controller.action.palette.SlotPanelLinkStateAction;
import gerudok.controller.action.palette.SlotPanelRectangleStateAction;
import gerudok.controller.action.palette.SlotPanelTriangleStateAction;
import gerudok.controller.action.view.*;

public class ActionProvider {

    private static ActionProvider instance;

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

    private final CreateProjectAction createProjectAction = new CreateProjectAction();

    public CreateProjectAction getCreateProjectAction() {
        return createProjectAction;
    }

    private final CreateDiagramAction createDiagramAction = new CreateDiagramAction();

    public CreateDiagramAction getCreateDiagramAction() {
        return createDiagramAction;
    }

    private final CreatePageAction createPageAction = new CreatePageAction();

    public CreatePageAction getCreatePageAction() {
        return createPageAction;
    }

    private final CreateSlotAction createSlotAction = new CreateSlotAction();

    public CreateSlotAction getCreateSlotAction() {
        return createSlotAction;
    }

    private final SlotPanelCircleStateAction slotPanelCircleStateAction = new SlotPanelCircleStateAction();

    public SlotPanelCircleStateAction getSlotPanelCircleStateAction() {
        return slotPanelCircleStateAction;
    }

    private final SlotPanelLinkStateAction slotPanelLinkStateAction = new SlotPanelLinkStateAction();

    public SlotPanelLinkStateAction getSlotPanelLinkStateAction() {
        return slotPanelLinkStateAction;
    }

    private final SlotPanelRectangleStateAction slotPanelRectangleStateAction = new SlotPanelRectangleStateAction();

    public SlotPanelRectangleStateAction getSlotPanelRectangleStateAction() {
        return slotPanelRectangleStateAction;
    }

    private final SlotPanelTriangleStateAction slotPanelTriangleStateAction = new SlotPanelTriangleStateAction();

    public SlotPanelTriangleStateAction getSlotPanelTriangleStateAction() {
        return slotPanelTriangleStateAction;
    }

    private final CreateFastSlotAction createFastSlotAction = new CreateFastSlotAction();

    public CreateFastSlotAction getCreateFastSlotAction() {
        return createFastSlotAction;
    }

    private final CascadeFramesAction cascadeFramesAction = new CascadeFramesAction();

    public CascadeFramesAction getCascadeFramesAction() {
        return cascadeFramesAction;
    }

    private final SlotPanelRegionZoomAction slotPanelRegionZoomAction = new SlotPanelRegionZoomAction();

    public SlotPanelRegionZoomAction getSlotPanelRegionZoomAction() {
        return slotPanelRegionZoomAction;
    }

    private final SlotPanelZoomInAction slotPanelZoomInAction = new SlotPanelZoomInAction();

    public SlotPanelZoomInAction getSlotPanelZoomInAction() {
        return slotPanelZoomInAction;
    }

    public final SlotPanelZoomOutAction slotPanelZoomOutAction = new SlotPanelZoomOutAction();

    public SlotPanelZoomOutAction getSlotPanelZoomOutAction() {
        return slotPanelZoomOutAction;
    }

    private final TileFramesHorizontallyAction tileFramesHorizontallyAction = new TileFramesHorizontallyAction();

    public TileFramesHorizontallyAction getTileFramesHorizontallyAction() {
        return tileFramesHorizontallyAction;
    }

    private final TileFramesVerticallyAction tileFramesVerticallyAction = new TileFramesVerticallyAction();

    public TileFramesVerticallyAction getTileFramesVerticallyAction() {
        return tileFramesVerticallyAction;
    }

}
