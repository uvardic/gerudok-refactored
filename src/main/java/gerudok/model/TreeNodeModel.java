package gerudok.model;

import gerudok.model.visitor.TreeNodeModelVisitor;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.io.Serializable;

public interface TreeNodeModel extends TreeNode, Serializable {

    void acceptModelVisitor(TreeNodeModelVisitor visitor);

    String getName();

    Icon getIcon();

}
