package gerudok.ui.desktop;

import gerudok.model.Diagram;
import gerudok.observer.Observer;
import gerudok.observer.Subject;

import javax.swing.*;
import java.beans.PropertyVetoException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Desktop extends JDesktopPane implements Subject {

    private static final int FRAME_X_OFFSET = 30;

    private static final int FRAME_Y_OFFSET = 30;

    private static Desktop instance;

    private final Set<DiagramFrame> frames = new LinkedHashSet<>();

    private Desktop() {}

    public static Desktop getInstance() {
        if (instance != null)
            return instance;

        return initializeDesktop();
    }

    private static Desktop initializeDesktop() {
        instance = new Desktop();

        Observer.observerSubject(instance);

        return instance;
    }

    public void addFrame(DiagramFrame frame) {
        if (frame == null)
            throw new IllegalArgumentException("Frame can't be null!");

        frames.add(frame);

        add(frame);
        cascadeNewFrame(frame);
        selectFrame(frame);
    }

    private void cascadeNewFrame(DiagramFrame frame) {
        frame.setLocation(frames.size() * FRAME_X_OFFSET, frames.size() * FRAME_Y_OFFSET);
    }

    private void selectFrame(DiagramFrame frame) {
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public DiagramFrame selectFrame(Diagram model) {
        DiagramFrame frameToSelect = frames.stream()
                .filter(frame -> frame.getModel().equals(model))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Diagram frame not found!"));

        selectFrame(frameToSelect);

        return frameToSelect;
    }

    public void removeFrame(DiagramFrame frame) {
        frames.remove(frame);
        removeFrameFromDesktop(frame);
    }

    private void removeFrameFromDesktop(DiagramFrame frame) {
        remove(frame);
    }

    public DiagramFrame getSelectedDiagramFrame() {
        return (DiagramFrame) getSelectedFrame();
    }

    public PagePanel getSelectedPagePanel() {
        return getSelectedDiagramFrame().getSelectedPagePanel();
    }

    public SlotPanel getSelectedSlotPanel() {
        return getSelectedPagePanel().getSelectedSlotPanel();
    }

    @Override
    public void update() {
        SwingUtilities.updateComponentTreeUI(instance);
    }
}
