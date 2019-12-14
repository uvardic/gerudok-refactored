package gerudok.model;

import gerudok.controller.action.IconLoader;
import gerudok.model.observer.Observer;
import gerudok.model.visitor.TreeNodeModelVisitor;
import gerudok.view.Tree;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static java.util.Collections.enumeration;

public class Page implements TreeNodeModel {

    private final Diagram diagram;

    private final String name;

    private final List<Slot> slots = new ArrayList<>();

    public Page(Diagram diagram) {
        this.diagram = diagram;
        this.name = formatName("Page");

        this.diagram.addPage(this);
    }

    public Page(Diagram diagram, String name) {
        this.diagram = diagram;
        this.name = formatName(name);

        this.diagram.addPage(this);
    }

    private String formatName(String name) {
        return String.format("%s - %d", name, diagram.getChildCount() + 1);
    }

    public void addSlot(Slot slot) {
        if (slot == null)
            throw new IllegalArgumentException("Slot can't be null!");

        if (slots.contains(slot))
            throw new IllegalArgumentException("Slot is already present!");

        slots.add(slot);
        Observer.updateSubject(Tree.class);
    }

    @Override
    public void acceptModelVisitor(TreeNodeModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Icon getIcon() {
        return IconLoader.PAGE_ICON.loadIcon();
    }

    @Override
    public Slot getChildAt(int childIndex) {
        return slots.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return slots.size();
    }

    @Override
    public Diagram getParent() {
        return diagram;
    }

    @Override
    public int getIndex(TreeNode node) {
        if (!(node instanceof Slot))
            throw new IllegalArgumentException("Illegal child instance!");

        return slots.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration<? extends Slot> children() {
        return enumeration(slots);
    }

    @Override
    public String toString() {
        return String.format("Page{parent='%s', name=%s}", diagram, name);
    }

}
