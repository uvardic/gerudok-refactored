package gerudok.tree.model;

import gerudok.tree.exception.IllegalTreeChildException;
import gerudok.tree.view.ProjectNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toList;

public class Workspace implements TreeModel {

    private final String name;

    private final Set<Project> children = new HashSet<>();

    public Workspace(String name) {
        this.name = name;
    }

    void addChild(Project child) {
        if (child == null)
            throw new IllegalTreeChildException("Child can't be null!");

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
    public String formatName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Workspace{name='%s', children=%s}", name, children);
    }

}
