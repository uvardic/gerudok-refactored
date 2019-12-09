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

public class Workspace implements TreeNodeModel {

    private final String name;

    private final List<Project> children = new ArrayList<>();

    public Workspace() {
        this.name = "Workspace";
    }

    public Workspace(String name) {
        this.name = name;
    }

    public void addChild(Project child) {
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
        return IconLoader.WORKSPACE_ICON.loadIcon();
    }

    @Override
    public Project getChildAt(int childIndex) {
        return children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public TreeNode getParent() {
        return null;
    }

    @Override
    public int getIndex(TreeNode node) {
        if (!(node instanceof Project))
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
    public Enumeration<? extends Project> children() {
        return enumeration(children);
    }

    @Override
    public String toString() {
        return String.format("Workspace{name='%s'}", name);
    }

}
