package gerudok.model.device;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class DeviceIO implements Serializable {

    enum Type {

        INPUT(-5), OUTPUT(5);

        private final int shapeSize;

        Type(int shapeSize) {
            this.shapeSize = shapeSize;
        }

    }

    private final Device<?> device;

    private final Type type;

    private final int index;

    private final Shape shape;

    public DeviceIO(Device<?> device, Type type, int index) {
        this.device = device;
        this.type = type;
        this.index = index;
        this.shape = initializeShape();
    }

    private Shape initializeShape() {
        GeneralPath shape = new GeneralPath();

        shape.moveTo(0, 0);
        shape.lineTo(type.shapeSize, 0);

        return shape;
    }

    void paint(Graphics2D graphics2D) {
        AffineTransform oldTransform = graphics2D.getTransform();

        initializeGraphics(graphics2D);
        graphics2D.draw(shape);
        graphics2D.fill(shape);

        graphics2D.setTransform(oldTransform);
    }

    private void initializeGraphics(Graphics2D graphics2D) {
        graphics2D.translate(getPositionX(), getPositionY());
        graphics2D.setPaint(Color.BLACK);
        graphics2D.setPaint(Color.BLACK);
    }

    public double getPositionX() {
        return getPosition().getX();
    }

    public double getPositionY() {
        return getPosition().getY();
    }

    public Point2D getPosition() {
        switch (type) {
            case INPUT:
                return calculateInputPosition();
            case OUTPUT:
                return calculateOutputPosition();
            default:
                throw new IllegalStateException("Not implemented value found!");
        }
    }

    private Point2D calculateInputPosition() {
        return new Point2D.Double(
                device.getPositionX(),
                device.getPositionY() + (device.getHeight() / (device.getNumberOfInputs() + 1)) * (index + 1)
        );
    }

    private Point2D calculateOutputPosition() {
        return new Point2D.Double(
                device.getPositionX() + device.getWidth(),
                device.getPositionY() + (device.getHeight() / (device.getNumberOfOutputs() + 1)) * (index + 1)
        );
    }

    @Override
    public String toString() {
        return String.format("DeviceIO{device=%s, type=%s, shape=%s}", device, type, shape);
    }


}
