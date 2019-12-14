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

    private final List<Project> projects = new ArrayList<>();

    public Workspace() {
        this.name = "Workspace";
    }

    public Workspace(String name) {
        this.name = name;
    }

    public void addProject(Project project) {
        if (project == null)
            throw new IllegalArgumentException("Project can't be null!");

        if (projects.contains(project))
            throw new IllegalArgumentException("Project is already present!");

        projects.add(project);
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
        return projects.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return projects.size();
    }

    @Override
    public TreeNode getParent() {
        return null;
    }

    @Override
    public int getIndex(TreeNode node) {
        if (!(node instanceof Project))
            throw new IllegalArgumentException("Illegal child instance!");

        return projects.indexOf(node);
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
        return enumeration(projects);
    }

    @Override
    public String toString() {
        return String.format("Workspace{name='%s'}", name);
    }

}
