package gerudok.tree.model;

import gerudok.tree.view.SlotNode;

import java.io.Serializable;

public class Element implements Serializable {

    private final String name;

    private final Slot parent;

    public Element(String name, Slot parent) {
        this.name = name;
        this.parent = parent;
        this.parent.addChild(this);
    }

    public Slot getParent() {
        return parent;
    }

    public SlotNode getParentAsNode() {
        return new SlotNode(parent);
    }

    public String formatName() {
        return String.format("%s - %d", name, parent.getChildren().size());
    }

    @Override
    public String toString() {
        return String.format("Element{name='%s', parent=%s}", name, parent);
    }

}
