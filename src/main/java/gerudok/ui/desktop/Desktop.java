package gerudok.ui.desktop;

import gerudok.model.Diagram;
import gerudok.observer.Observer;
import gerudok.observer.Subject;
import gerudok.ui.util.UISizeCalculator;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

public class Desktop extends JDesktopPane implements Subject {

    public static final int FRAME_X_OFFSET = 30;

    public static final int FRAME_Y_OFFSET = 30;

    private static Desktop instance;

    private final List<DiagramFrame> frames = new ArrayList<>();

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

        if (frames.contains(frame))
            throw new IllegalArgumentException("Frame is already present!");

        frames.add(frame);
        addFrameToDesktop(frame);
    }

    private void addFrameToDesktop(DiagramFrame frame) {
        add(frame);
        cascadeFrame(frame);
    }

    public DiagramFrame selectFrame(Diagram model) {
        DiagramFrame frameToSelect = frames.stream()
                .filter(frame -> frame.getModel().equals(model))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Diagram frame not found!"));

        selectFrame(frameToSelect);

        return frameToSelect;
    }

    private void selectFrame(DiagramFrame frame) {
        try {
            frame.setSelected(true);
            frame.setVisible(true);
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

    public void cascadeFrames() {
        frames.forEach(this::cascadeFrame);
    }

    private void cascadeFrame(DiagramFrame frame) {
        // First frame has an initial offset
        int frameIndex = frames.indexOf(frame) + 1;

        frame.setLocation(frameIndex * FRAME_X_OFFSET, frameIndex * FRAME_Y_OFFSET);
        frame.setSize(UISizeCalculator.calculateDiagramFrameSize());
        selectFrame(frame);
    }

    public void tileFramesHorizontally() {
        tileFramesWorker(makeHorizontalGrid());
    }

    public void tileFramesVertically() {
        tileFramesWorker(makeVerticalGrid());
    }

    private GridLayout makeHorizontalGrid() {
        return new GridLayout(
                (int) Math.sqrt(frames.size()),
                (int) Math.ceil(frames.size() / Math.floor(Math.sqrt(frames.size())))
        );
    }

    private GridLayout makeVerticalGrid() {
        return new GridLayout(
                (int) Math.ceil(frames.size() / Math.floor(Math.sqrt(frames.size()))),
                (int) Math.sqrt(frames.size())
        );
    }

    private void tileFramesWorker(GridLayout grid) {
        int frameWidth = (int) getSize().getWidth() / grid.getRows();

        int frameHeight = (int) getSize().getHeight() / grid.getColumns();

        int frameCounter = 0;

        for (int row = 0; row < grid.getRows(); row++) {
            for (int column = 0; column < grid.getColumns(); column++) {
                if (frameCounter < frames.size()) {
                    frames.get(frameCounter).setSize(frameWidth, frameHeight);
                    frames.get(frameCounter++).setLocation(frameWidth * row, frameHeight * column);
                }
            }
        }
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
