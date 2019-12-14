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

public class Project implements TreeNodeModel {

    private final Workspace workspace;

    private final String name;

    private final List<Diagram> diagrams = new ArrayList<>();

    public Project(Workspace workspace) {
        this.workspace = workspace;
        this.name = formatName("Project");

        this.workspace.addProject(this);
    }

    public Project(Workspace workspace, String name) {
        this.workspace = workspace;
        this.name = formatName(name);

        this.workspace.addProject(this);
    }

    private String formatName(String name) {
        return String.format("%s - %d", name, workspace.getChildCount() + 1);
    }

    public void addDiagram(Diagram diagram) {
        if (diagram == null)
            throw new IllegalArgumentException("Diagram can't be null!");

        if (diagrams.contains(diagram))
            throw new IllegalArgumentException("Diagram is already present!");

        diagrams.add(diagram);
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
        return IconLoader.PROJECT_ICON.loadIcon();
    }

    @Override
    public Diagram getChildAt(int childIndex) {
        return diagrams.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return diagrams.size();
    }

    @Override
    public Workspace getParent() {
        return workspace;
    }

    @Override
    public int getIndex(TreeNode node) {
        if (!(node instanceof Diagram))
            throw new IllegalArgumentException("Illegal child instance!");

        return diagrams.indexOf(node);
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
    public Enumeration<? extends Diagram> children() {
        return enumeration(diagrams);
    }

    @Override
    public String toString() {
        return String.format("Project{parent='%s', name=%s}", workspace, name);
    }

}
