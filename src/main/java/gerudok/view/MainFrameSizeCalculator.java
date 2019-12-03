package gerudok.view;

import java.awt.*;

public class MainFrameSizeCalculator {

    private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private MainFrameSizeCalculator() {}

    static Dimension calculateFrameSize() {
        return new Dimension(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
    }

    static Dimension calculateFrameMinSize() {
        return new Dimension(200, 50);
    }

    static Dimension calculateFrameMaxSize() {
        return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

}
