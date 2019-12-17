package gerudok.view.desktop.state;

import java.awt.event.MouseEvent;

public interface SlotPanelState {

    void mousePressed(MouseEvent event);

    void mouseReleased(MouseEvent event);

    void mouseDragged(MouseEvent event);

    void mouseMoved(MouseEvent event);

}
