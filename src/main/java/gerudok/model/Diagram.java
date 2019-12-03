package gerudok.model;

import gerudok.ui.DiagramFrame;
import gerudok.ui.tree.node.DiagramNode;
import gerudok.ui.tree.node.PageNode;
import gerudok.ui.tree.node.ProjectNode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toList;

public class Diagram implements Serializable {

    private final Project parent;

    private final String name;

    private final Set<Page> children = new LinkedHashSet<>();

    public Diagram(Project parent, String name) {
        this.parent = parent;
        this.name = formatName(name);
        this.parent.addChild(this);

        new DiagramNode(this);
        new DiagramFrame(this);
    }

    private String formatName(String name) {
        return String.format("%s - %d", name, parent.getChildren().size() + 1);
    }

    public Project getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public ProjectNode getParentAsNode() {
        return new ProjectNode(parent);
    }

    void addChild(Page child) {
        if (child == null)
            throw new IllegalArgumentException("Child can't be null!");

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

    @Override
    public String toString() {
        return String.format("Diagram{name='%s', parent=%s, children=%s}", name, parent, children);
    }
}
