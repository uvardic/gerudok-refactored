package gerudok.view.util;

import java.awt.*;

public class SizeCalculator {

    private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private SizeCalculator() {}

    public static Dimension calculateMainFrameSize() {
        return new Dimension(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
    }

    public static Dimension calculateMainFrameMinSize() {
        return new Dimension(200, 50);
    }

    public static Dimension calculateMainFrameMaxSize() {
        return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public static int calculateSplitPaneDividerLocation() {
        return SCREEN_WIDTH / 6;
    }

    public static Dimension calculateDiagramFrameSize() {
        return new Dimension(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
    }

    public static Dimension calculateDiagramFrameMinSize() {
        return new Dimension(100, 100);
    }

    public static Dimension calculateDiagramFrameMaxSize() {
        return new Dimension(SCREEN_WIDTH - calculateSplitPaneDividerLocation(), SCREEN_HEIGHT);
    }

}
