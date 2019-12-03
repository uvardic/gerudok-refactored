package gerudok.tree.model;

import gerudok.tree.exception.IllegalTreeChildException;
import gerudok.tree.node.PageNode;
import gerudok.tree.node.ProjectNode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toList;

public class Diagram implements Serializable {

    private final String name;

    private final Project parent;

    private final Set<Page> children = new HashSet<>();

    public Diagram(String name, Project parent) {
        this.name = name;
        this.parent = parent;
        this.parent.addChild(this);
    }

    public Project getParent() {
        return parent;
    }

    public ProjectNode getParentAsNode() {
        return new ProjectNode(parent);
    }

    void addChild(Page child) {
        if (child == null)
            throw new IllegalTreeChildException("Child can't be null!");

        children.add(child);
    }

    public void removeChild(Page child) {
        children.remove(child);
    }

    public Set<Page> getChildren() {
        return unmodifiableSet(children);
    }

    public List<PageNode> getChildrenAsNodes() {
        return children.stream()
                .map(PageNode::new)
                .collect(toList());
    }

    public String formatName() {
        return String.format("%s - %d", name, parent.getChildren().size());
    }

    @Override
    public String toString() {
        return String.format("Diagram{name='%s', parent=%s, children=%s}", name, parent, children);
    }
}
