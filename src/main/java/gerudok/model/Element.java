package gerudok.model;

import gerudok.controller.action.IconLoader;
import gerudok.model.visitor.TreeNodeModelVisitor;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Enumeration;

public abstract class Element implements TreeNodeModel {

    private final Slot parent;

    private final String name;

    protected Element(Slot parent, String name) {
        this.parent = parent;
        this.name = formatName(name);

        this.parent.addElement(this);
    }

    private String formatName(String name) {
        return String.format("%s - %d", name, numberOfSameNames(name) + 1);
    }

    private long numberOfSameNames(String name) {
        return parent.getElements()
                .stream()
                .filter(element -> plainName(element).equals(name))
                .count();
    }

    private String plainName(Element element) {
        return element.getName().split(" ")[0];
    }

    public abstract void paint(Graphics2D graphics2D);

    public abstract void paintSelection(Graphics2D graphics2D);

    public abstract boolean isElementAt(Point2D position);

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
        return IconLoader.ELEMENT_ICON.loadIcon();
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return 0;
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public Enumeration<?> children() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("Element{parent='%s', name=%s}", parent, name);
    }
}
