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

public class Diagram implements TreeNodeModel {

    private final Project parent;

    private final String name;

    private final List<Page> children = new ArrayList<>();

    public Diagram(Project parent) {
        this.parent = parent;
        this.name = formatName("Diagram");

        this.parent.addChild(this);
    }

    public Diagram(Project parent, String name) {
        this.parent = parent;
        this.name = formatName(name);

        this.parent.addChild(this);
    }

    private String formatName(String name) {
        return String.format("%s - %d", name, parent.getChildCount() + 1);
    }

    public void addChild(Page child) {
        if (child == null)
            throw new IllegalArgumentException("Child can't be null!");

        if (children.contains(child))
            throw new IllegalArgumentException("Child is already present!");

        children.add(child);
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
        return IconLoader.DIAGRAM_ICON.loadIcon();
    }

    @Override
    public Page getChildAt(int childIndex) {
        return children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public Project getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        if (!(node instanceof Page))
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
    public Enumeration<? extends Page> children() {
        return enumeration(children);
    }

    @Override
    public String toString() {
        return String.format("Diagram{parent='%s', name=%s}", parent, name);
    }

}
