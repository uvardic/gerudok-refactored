package gerudok.tree.model;

import gerudok.tree.exception.IllegalTreeChildException;
import gerudok.tree.view.ElementNode;
import gerudok.tree.view.PageNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toList;

public class Slot implements TreeModel {

    private final String name;

    private final Page parent;

    private final Set<Element> children = new HashSet<>();

    public Slot(String name, Page parent) {
        this.name = name;
        this.parent = parent;
        this.parent.addChild(this);
    }

    public Page getParent() {
        return parent;
    }

    public PageNode getParentAsNode() {
        return new PageNode(parent);
    }

    void addChild(Element child) {
        if (child == null)
            throw new IllegalTreeChildException("Child can't be null!");

        children.add(child);
    }

    public void removeChild(Element child) {
        children.remove(child);
    }

    public Set<Element> getChildren() {
        return unmodifiableSet(children);
    }

    public List<ElementNode> getChildrenAsNodes() {
        return children.stream()
                .map(ElementNode::new)
                .collect(toList());
    }

    @Override
    public String formatName() {
        return String.format("%s - %d", name, parent.getChildren().size());
    }

    @Override
    public String toString() {
        return String.format("Slot{name='%s', parent=%s}", name, parent);
    }

}
