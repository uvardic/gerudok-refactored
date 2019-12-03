package gerudok.model;

import gerudok.ui.SlotPanel;
import gerudok.ui.tree.node.ElementNode;
import gerudok.ui.tree.node.PageNode;
import gerudok.ui.tree.node.SlotNode;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toList;

public class Slot implements Serializable {

    private final Page parent;

    private final String name;

    private final Set<Element> children = new LinkedHashSet<>();

    public Slot(Page parent, String name) {
        this.parent = parent;
        this.name = formatName(name);
        this.parent.addChild(this);

        new SlotNode(this);
        new SlotPanel(this);
    }

    private String formatName(String name) {
        return String.format("%s - %d", name, parent.getChildren().size() + 1);
    }

    public Page getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public PageNode getParentAsNode() {
        return new PageNode(parent);
    }

    void addChild(Element child) {
        if (child == null)
            throw new IllegalArgumentException("Child can't be null!");

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
    public String toString() {
        return String.format("Slot{name='%s', parent=%s}", name, parent);
    }

}
