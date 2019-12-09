package gerudok.model;

import gerudok.controller.action.IconLoader;
import gerudok.model.device.Device;
import gerudok.model.observer.Observer;
import gerudok.model.visitor.TreeNodeModelVisitor;
import gerudok.view.Tree;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static java.util.Collections.enumeration;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class Slot implements TreeNodeModel {

    private final Page parent;

    private final String name;

    private final List<Element> children = new ArrayList<>();

    public Slot(Page parent) {
        this.parent = parent;
        this.name = formatName("Slot");

        this.parent.addChild(this);
    }

    public Slot(Page parent, String name) {
        this.parent = parent;
        this.name = formatName(name);

        this.parent.addChild(this);
    }

    private String formatName(String name) {
        return String.format("%s - %d", name, parent.getChildCount() + 1);
    }

    public void addChild(Element child) {
        if (child == null)
            throw new IllegalArgumentException("Child can't be null!");

        if (children.contains(child))
            throw new IllegalArgumentException("Child is already present!");

        children.add(child);
        Observer.updateSubject(Tree.class);
    }

    public List<Element> getChildren() {
        return unmodifiableList(children);
    }

    public List<Device<?>> getChildDevices() {
        return children.stream()
                .filter(child -> child instanceof Device)
                .map(child -> (Device<?>) child)
                .collect(toList());
    }

    public boolean isElementAt(Point2D position) {
        return children.stream().anyMatch(element -> element.isElementAt(position));
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
        return IconLoader.SLOT_ICON.loadIcon();
    }

    @Override
    public Element getChildAt(int childIndex) {
        return children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public Page getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        if (!(node instanceof Element))
            throw new IllegalArgumentException("Illegal child instance!");

        return children.indexOf(node);
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
    public Enumeration<? extends Element> children() {
        return enumeration(children);
    }

    @Override
    public String toString() {
        return String.format("Slot{parent='%s', name=%s}", parent, name);
    }

}
