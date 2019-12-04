package gerudok.model;

import gerudok.ui.tree.node.DiagramNode;
import gerudok.ui.tree.node.ProjectNode;
import gerudok.ui.tree.node.WorkspaceNode;

import java.io.Serializable;
import java.util.*;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toList;

public class Project implements Serializable {

    private final Workspace parent;

    private final String name;

    private final Set<Diagram> children = new LinkedHashSet<>();

    public Project(Workspace parent, String name) {
        this.parent = parent;
        this.name = formatName(name);
        this.parent.addChild(this);

        new ProjectNode(this);
    }

    private String formatName(String name) {
        return String.format("%s - %d", name, parent.getChildren().size() + 1);
    }

    public Workspace getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public WorkspaceNode getParentAsNode() {
        return new WorkspaceNode(parent);
    }

    void addChild(Diagram child) {
        if (child == null)
            throw new IllegalArgumentException("Child can't be null!");

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

    @Override
    public String toString() {
        return String.format("Project{name='%s', parent=%s}", name, parent);
    }

}
