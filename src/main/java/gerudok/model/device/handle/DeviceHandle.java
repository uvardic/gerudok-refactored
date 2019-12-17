package gerudok.model.device.handle;

import gerudok.model.device.Device;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public abstract class DeviceHandle {

    private static final int HANDLE_SIZE = 7;

    private final Device<?> device;

    private final Point2D position;

    private final Rectangle2D handleBounds;

    protected DeviceHandle(Device<?> device) {
        this.device = device;
        this.position = definePosition();
        this.handleBounds = defineHandleBounds();
    }

    protected abstract Point2D definePosition();

    private Rectangle2D defineHandleBounds() {
        double rectanglePointX = position.getX() - (double) HANDLE_SIZE / 2;
        double rectanglePointY = position.getY() - (double) HANDLE_SIZE / 2;

        return new Rectangle2D.Double(rectanglePointX, rectanglePointY, HANDLE_SIZE, HANDLE_SIZE);
    }

    protected Device<?> getDevice() {
        return device;
    }

    public void paint(Graphics2D graphics2D) {
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(handleBounds);
    }

    public boolean isHandleAt(Point2D position) {
        return handleBounds.contains(position);
    }

    @Override
    public String toString() {
        return String.format("DeviceHandle{device=%s, position=%s, handleBounds=%s}", device, position, handleBounds);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeviceHandle) {
            DeviceHandle other = (DeviceHandle) o;

            return Objects.equals(this.device, other.device) &&
                    Objects.equals(this.position, other.position);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(device, position);
    }
}
