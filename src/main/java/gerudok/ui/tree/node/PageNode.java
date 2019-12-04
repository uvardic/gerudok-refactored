package gerudok.ui.tree.node;

import gerudok.controller.ActionManager;
import gerudok.model.Page;
import gerudok.ui.desktop.Desktop;
import gerudok.ui.util.UIIcon;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Objects;

import static java.util.Collections.enumeration;

public class PageNode implements Node {

    private final Page model;

    public PageNode(Page model) {
        this.model = model;
    }

    public Page getModel() {
        return model;
    }

    @Override
    public void renderCell(DefaultTreeCellRenderer cellRenderer) {
        cellRenderer.setIcon(UIIcon.PAGE_ICON.loadIcon());
    }

    @Override
    public void selectionEvent() {
        Desktop.getInstance().selectFrame(model.getParent())
                .selectPagePanel(model);
    }

    @Override
    public String formatName() {
        return model.getName();
    }

    @Override
    public JPopupMenu createMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        popupMenu.add(ActionManager.getInstance().getCreateSlotAction());

        return popupMenu;
    }

    @Override
    public SlotNode getChildAt(int i) {
        return model.getChildrenAsNodes().get(i);
    }

    @Override
    public int getChildCount() {
        return model.getChildrenAsNodes().size();
    }

    @Override
    public DiagramNode getParent() {
        return model.getParentAsNode();
    }

    @Override
    public int getIndex(TreeNode treeNode) {
        if (!(treeNode instanceof SlotNode))
            throw new IllegalArgumentException("Illegal child instance!");

        return model.getChildrenAsNodes().indexOf(treeNode);
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
    public Enumeration<? extends SlotNode> children() {
        return enumeration(model.getChildrenAsNodes());
    }

    @Override
    public String toString() {
        return String.format("PageNode{model=%s}", model);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PageNode) {
            PageNode other = (PageNode) o;

            return Objects.equals(this.model, other.model);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
