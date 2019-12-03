package gerudok.ui.desktop;

import gerudok.observer.Observer;
import gerudok.observer.Subject;
import gerudok.ui.DiagramFrame;

import javax.swing.*;
import java.beans.PropertyVetoException;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

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

    public void selectFrame(DiagramFrame frame) {
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public void removeFrame(DiagramFrame frame) {
        frames.remove(frame);
        removeFrameFromDesktop(frame);
    }

    private void removeFrameFromDesktop(DiagramFrame frame) {
        remove(frame);
    }

    public Set<DiagramFrame> getFrames() {
        return unmodifiableSet(frames);
    }

    @Override
    public void update() {
        SwingUtilities.updateComponentTreeUI(instance);
    }
}
