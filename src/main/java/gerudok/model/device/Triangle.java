package gerudok.model.device;

import gerudok.model.Slot;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class Triangle extends Device<Triangle> {

    protected Triangle(DeviceBuilder<Triangle> builder) {
        super(builder);
    }

    @Override
    protected Shape defineShape() {
        GeneralPath shape = new GeneralPath();

        shape.moveTo(0, 0);
        shape.lineTo(0, getHeight());
        shape.lineTo(getWidth(), getHeight() / 2);
        shape.closePath();

        return shape;
    }

    public static class Builder extends DeviceBuilder<Triangle> {

        public Builder(Slot parent) {
            super(parent);
        }

        @Override
        public Triangle build() {
            return new Triangle(this);
        }

    }
}
