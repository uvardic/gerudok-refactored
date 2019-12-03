package gerudok.ui.util;

import javax.swing.*;
import java.net.URL;

public enum UIIcon {

    WORKSPACE_ICON("/icon/WorkspaceIcon.png"),
    PROJECT_ICON("/icon/ProjectIcon.png"),
    DIAGRAM_ICON("/icon/DiagramIcon.png"),
    PAGE_ICON("/icon/PageIcon.png"),
    SLOT_ICON("/icon/SlotIcon.png"),
    ELEMENT_ICON("/icon/ElementIcon.png");

    String iconPath;

    UIIcon(String iconPath) {
        this.iconPath = iconPath;
    }

    public Icon loadIcon() {
        URL iconURL = UIIcon.class.getResource(iconPath);

        return new ImageIcon(iconURL);
    }

}

