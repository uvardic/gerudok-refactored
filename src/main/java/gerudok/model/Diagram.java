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

    private final Project project;

    private final String name;

    private final List<Page> pages = new ArrayList<>();

    public Diagram(Project project) {
        this.project = project;
        this.name = formatName("Diagram");

        this.project.addDiagram(this);
    }

    public Diagram(Project project, String name) {
        this.project = project;
        this.name = formatName(name);

        this.project.addDiagram(this);
    }

    private String formatName(String name) {
        return String.format("%s - %d", name, project.getChildCount() + 1);
    }

    public void addPage(Page page) {
        if (page == null)
            throw new IllegalArgumentException("Page can't be null!");

        if (pages.contains(page))
            throw new IllegalArgumentException("Page is already present!");

        pages.add(page);
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
        return pages.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return pages.size();
    }

    @Override
    public Project getParent() {
        return project;
    }

    @Override
    public int getIndex(TreeNode node) {
        if (!(node instanceof Page))
            throw new IllegalArgumentException("Illegal child instance!");

        return pages.indexOf(node);
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
        return enumeration(pages);
    }

    @Override
    public String toString() {
        return String.format("Diagram{parent='%s', name=%s}", project, name);
    }

}
