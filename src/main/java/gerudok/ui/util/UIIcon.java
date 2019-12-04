package gerudok.ui.util;

import javax.swing.*;
import java.net.URL;

public enum UIIcon {

    CASCADE_ICON("/icon/CascadeIcon.png"),
    COPY_ICON("/icon/CopyIcon.png"),
    CUT_ICON("/icon/CutIcon.png"),
    DELETE_ICON("/icon/DeleteIcon.png"),
    DIAGRAM_ICON("/icon/DiagramIcon.png"),
    ELEMENT_ICON("/icon/ElementIcon.png"),
    OPEN_ICON("/icon/OpenIcon.png"),
    PAGE_ICON("/icon/PageIcon.png"),
    PASTE_ICON("/icon/PasteIcon.png"),
    PROJECT_ICON("/icon/ProjectIcon.png"),
    REDO_ICON("/icon/RedoIcon.png"),
    SAVE_ICON("/icon/SaveIcon.png"),
    SLOT_ICON("/icon/SlotIcon.png"),
    TILE_H_ICON("/icon/TileHIcon.png"),
    TILE_V_ICON("/icon/TileVIcon.png"),
    UNDO_ICON("/icon/UndoIcon.png"),
    WORKSPACE_ICON("/icon/WorkspaceIcon.png"),
    ZOOM_IN_ICON("/icon/ZoomInIcon.png"),
    ZOOM_OUT_ICON("/icon/ZoomOutIcon.png"),
    ZOOM_TO_FIT_ICON("/icon/ZoomToFitIcon.png");

    final String iconPath;

    UIIcon(String iconPath) {
        this.iconPath = iconPath;
    }

    public Icon loadIcon() {
        URL iconURL = UIIcon.class.getResource(iconPath);

        return new ImageIcon(iconURL);
    }

}

