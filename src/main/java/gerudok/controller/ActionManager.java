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
}
