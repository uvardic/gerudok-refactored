package gerudok.model.device;

import gerudok.model.Slot;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle extends Device<Circle> {

    public Circle(DeviceBuilder<Circle> builder) {
        super(builder);
    }

    @Override
    protected Shape defineShape() {
        return new Ellipse2D.Double(getPositionX(), getPositionY(), getWidth(), getHeight());
    }

    public static class CircleBuilder extends Device.DeviceBuilder<Circle> {

        public CircleBuilder(Slot parent) {
            super(parent);
        }

        @Override
        public Circle build() {
            return new Circle(this);
        }
    }

}
