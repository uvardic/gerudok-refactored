package gerudok.tree.visitor;

import gerudok.tree.node.*;

public interface TreeVisitor {

    void visit(WorkspaceNode workspaceNode);

    void visit(ProjectNode projectNode);

    void visit(DiagramNode diagramNode);

    void visit(PageNode pageNode);

    void visit(SlotNode slotNode);

    void visit(ElementNode elementNode);

}
