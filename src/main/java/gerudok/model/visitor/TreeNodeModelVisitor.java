package gerudok.model.visitor;

import gerudok.model.*;

public interface TreeNodeModelVisitor {

    void visit(Workspace workspace);

    void visit(Project project);

    void visit(Diagram diagram);

    void visit(Page page);

    void visit(Slot slot);

    void visit(Element element);

}
