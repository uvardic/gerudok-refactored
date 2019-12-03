package gerudok.view;

import javax.swing.*;
import java.net.URL;

public enum UIIcon {

    TREE_WORKSPACE_ICON("/icon/tree/WorkspaceIcon.png"),
    TREE_PROJECT_ICON("/icon/tree/ProjectIcon.png"),
    TREE_DIAGRAM_ICON("/icon/tree/DiagramIcon.png"),
    TREE_PAGE_ICON("/icon/tree/PageIcon.png"),
    TREE_SLOT_ICON("/icon/tree/SlotIcon.png"),
    TREE_ELEMENT_ICON("/icon/tree/ElementIcon.png");

    String iconPath;

    UIIcon(String iconPath) {
        this.iconPath = iconPath;
    }

    public Icon loadIcon() {
        URL iconURL = UIIcon.class.getResource(iconPath);

        return new ImageIcon(iconURL);
    }

}

