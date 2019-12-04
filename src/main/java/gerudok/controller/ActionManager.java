package gerudok.controller;

public class ActionManager {

    private static ActionManager instance;

    public static ActionManager getInstance() {
        if (instance != null)
            return instance;

        return initializeActionManager();
    }

    private static ActionManager initializeActionManager() {
        instance = new ActionManager();

        return instance;
    }

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

    private final CreateFastSlotAction createFastSlotAction = new CreateFastSlotAction();

    public CreateFastSlotAction getCreateFastSlotAction() {
        return createFastSlotAction;
    }
}
