package gerudok.model.device;

import gerudok.model.Slot;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Device<Rectangle> {

    public Rectangle(DeviceBuilder<Rectangle> builder) {
        super(builder);
    }

    @Override
    protected Shape defineShape() {
        // Affine transformation matrix sets the position for every shape
        return new Rectangle2D.Double(0, 0, getWidth(), getHeight());
    }

    public static class Builder extends DeviceBuilder<Rectangle> {

        public Builder(Slot parent) {
            super(parent);
        }

        @Override
        public Rectangle build() {
            return new Rectangle(this);
        }

    }
}
