package gerudok.ui.tree.visitor;

import gerudok.ui.tree.node.*;

public interface TreeVisitor {

    void visit(WorkspaceNode workspaceNode);

    void visit(ProjectNode projectNode);

    void visit(DiagramNode diagramNode);

    void visit(PageNode pageNode);

    void visit(SlotNode slotNode);

    void visit(ElementNode elementNode);

}
