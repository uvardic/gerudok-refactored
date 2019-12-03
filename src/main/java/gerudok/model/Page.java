package gerudok.model;

import gerudok.ui.PagePanel;
import gerudok.ui.tree.node.DiagramNode;
import gerudok.ui.tree.node.PageNode;
import gerudok.ui.tree.node.SlotNode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toList;

public class Page implements Serializable {

    private final Diagram parent;

    private final String name;

    private final Set<Slot> children = new LinkedHashSet<>();

    public Page(Diagram parent, String name) {
        this.parent = parent;
        this.name = formatName(name);
        this.parent.addChild(this);

        new PageNode(this);
        new PagePanel(this);
    }

    private String formatName(String name) {
        return String.format("%s - %d", name, parent.getChildren().size() + 1);
    }

    public Diagram getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public DiagramNode getParentAsNode() {
        return new DiagramNode(parent);
    }

    void addChild(Slot child) {
        if (child == null)
            throw new IllegalArgumentException("Child can't be null");

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
    public String toString() {
        return String.format("Page{name='%s', parent=%s, children=%s}", name, parent, children);
    }
}
