package gerudok.model;

import gerudok.ui.tree.node.ElementNode;
import gerudok.ui.tree.node.SlotNode;

import java.io.Serializable;

public class Element implements Serializable {

    private final Slot parent;

    private final String name;

    public Element(Slot parent, String name) {
        this.parent = parent;
        this.name = formatName(name);
        this.parent.addChild(this);

        new ElementNode(this);
    }

    private String formatName(String name) {
        return String.format("%s - %d", name, parent.getChildren().size() + 1);
    }

    public Slot getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public SlotNode getParentAsNode() {
        return new SlotNode(parent);
    }

    @Override
    public String toString() {
        return String.format("Element{name='%s', parent=%s}", name, parent);
    }

}
