package gerudok.tree.model;

import gerudok.tree.exception.IllegalTreeChildException;
import gerudok.tree.view.DiagramNode;
import gerudok.tree.view.WorkspaceNode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toList;

public class Project implements Serializable {

    private final String name;

    private final Workspace parent;

    private final Set<Diagram> children = new HashSet<>();

    public Project(String name, Workspace parent) {
        this.name = name;
        this.parent = parent;
        this.parent.addChild(this);
    }

    public Workspace getParent() {
        return parent;
    }

    public WorkspaceNode getParentAsNode() {
        return new WorkspaceNode(parent);
    }

    void addChild(Diagram child) {
        if (child == null)
            throw new IllegalTreeChildException("Child can't be null!");

        children.add(child);
    }

    public void removeChild(Diagram child) {
        children.remove(child);
    }

    public Set<Diagram> getChildren() {
        return unmodifiableSet(children);
    }

    public List<DiagramNode> getChildrenAsNodes() {
        return children.stream()
                .map(DiagramNode::new)
                .collect(toList());
    }

    public String formatName() {
        return String.format("%s - %d", name, parent.getChildren().size());
    }

    @Override
    public String toString() {
        return String.format("Project{name='%s', parent=%s, children=%s}", name, parent, children);
    }

}
