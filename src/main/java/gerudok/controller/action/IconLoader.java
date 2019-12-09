package gerudok.controller.action;

import javax.swing.*;
import java.net.URL;

public enum IconLoader {

    CASCADE_ICON("/icon/CascadeIcon.png"),
    CIRCLE_ICON("/icon/CircleIcon.png"),
    COPY_ICON("/icon/CopyIcon.png"),
    CUT_ICON("/icon/CutIcon.png"),
    DELETE_ICON("/icon/DeleteIcon.png"),
    DIAGRAM_ICON("/icon/DiagramIcon.png"),
    ELEMENT_ICON("/icon/ElementIcon.png"),
    LINK_ICON("/icon/LinkIcon.png"),
    OPEN_ICON("/icon/OpenIcon.png"),
    PAGE_ICON("/icon/PageIcon.png"),
    PASTE_ICON("/icon/PasteIcon.png"),
    PROJECT_ICON("/icon/ProjectIcon.png"),
    RECTANGLE_ICON("/icon/RectangleIcon.png"),
    REDO_ICON("/icon/RedoIcon.png"),
    REGION_ZOOM_ICON("/icon/RegionZoomIcon.png"),
    SAVE_ICON("/icon/SaveIcon.png"),
    SELECT_ICON("/icon/SelectIcon.png"),
    SLOT_ICON("/icon/SlotIcon.png"),
    TILE_H_ICON("/icon/TileHIcon.png"),
    TILE_V_ICON("/icon/TileVIcon.png"),
    TRIANGLE_ICON("/icon/TriangleIcon.png"),
    UNDO_ICON("/icon/UndoIcon.png"),
    WORKSPACE_ICON("/icon/WorkspaceIcon.png"),
    ZOOM_IN_ICON("/icon/ZoomInIcon.png"),
    ZOOM_OUT_ICON("/icon/ZoomOutIcon.png");

    final String iconPath;

    IconLoader(String iconPath) {
        this.iconPath = iconPath;
    }

    public Icon loadIcon() {
        URL iconURL = IconLoader.class.getResource(iconPath);

        return new ImageIcon(iconURL);
    }

}

