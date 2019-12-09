package gerudok.view.desktop;

import gerudok.model.Diagram;
import gerudok.model.observer.Observer;
import gerudok.model.observer.Subject;
import gerudok.view.util.SizeCalculator;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Desktop extends JDesktopPane implements Subject {

    private static final int FRAME_X_OFFSET = 30;

    private static final int FRAME_Y_OFFSET = 30;

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

        Observer.addSubject(instance);

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

    public void cascadeFrames() {
        frames.forEach(this::cascadeFrame);
    }

    private void cascadeFrame(DiagramFrame frame) {
        // First frame has an initial offset
        int frameIndex = frames.indexOf(frame) + 1;

        frame.setLocation(frameIndex * FRAME_X_OFFSET, frameIndex * FRAME_Y_OFFSET);
        frame.setSize(SizeCalculator.calculateDiagramFrameSize());
        selectFrame(frame);
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

    public List<DiagramFrame> getFrames() {
        return unmodifiableList(frames);
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

    public boolean isSlotPanelClosed() {
        return getSelectedDiagramFrame() == null
                || getSelectedPagePanel() == null
                || getSelectedSlotPanel() == null;
    }

    @Override
    public void update() {
        updateUI();
    }
}
