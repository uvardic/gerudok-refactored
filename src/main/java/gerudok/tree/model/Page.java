package gerudok.tree.model;

import gerudok.tree.exception.IllegalTreeChildException;
import gerudok.tree.view.DiagramNode;
import gerudok.tree.view.SlotNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toList;

public class Page implements TreeModel {

    private final String name;

    private final Diagram parent;

    private final Set<Slot> children = new HashSet<>();

    public Page(String name, Diagram parent) {
        this.name = name;
        this.parent = parent;
        this.parent.addChild(this);
    }

    public Diagram getParent() {
        return parent;
    }

    public DiagramNode getParentAsNode() {
        return new DiagramNode(parent);
    }

    void addChild(Slot child) {
        if (child == null)
            throw new IllegalTreeChildException("Child can't be null");

        children.add(child);
    }

    public void removeChild(Slot child) {
        children.remove(child);
    }

    public Set<Slot> getChildren() {
        return unmodifiableSet(children);
    }

    public List<SlotNode> getChildrenAsNodes() {
        return children.stream()
                .map(SlotNode::new)
                .collect(toList());
    }

    @Override
    public String formatName() {
        return String.format("%s - %d", name, parent.getChildren().size());
    }

    @Override
    public String toString() {
        return String.format("Page{name='%s', parent=%s, children=%s}", name, parent, children);
    }
}
