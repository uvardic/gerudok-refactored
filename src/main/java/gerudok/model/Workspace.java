package gerudok.model;

import gerudok.ui.tree.node.ProjectNode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toList;

public class Workspace implements Serializable {

    private final String name;

    private final Set<Project> children = new LinkedHashSet<>();

    public Workspace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void addChild(Project child) {
        if (child == null)
            throw new IllegalArgumentException("Child can't be null!");

        children.add(child);
    }

    public void removeChild(Project child) {
        children.remove(child);
    }

    public Set<Project> getChildren() {
        return unmodifiableSet(children);
    }

    public List<ProjectNode> getChildrenAsNodes() {
        return children.stream()
                .map(ProjectNode::new)
                .collect(toList());
    }

    @Override
    public String toString() {
        return String.format("Workspace{name='%s', children=%s}", name, children);
    }

}
