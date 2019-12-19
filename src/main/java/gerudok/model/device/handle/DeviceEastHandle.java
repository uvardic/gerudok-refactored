package gerudok.model.device.handle;

import gerudok.model.device.Device;

import java.awt.*;
import java.awt.geom.Point2D;

public class DeviceEastHandle extends DeviceHandle {

    public DeviceEastHandle(Device<?> device) {
        super(device);
    }

    @Override
    protected Point2D definePosition() {
        return new Point2D.Double(
                getDevice().getPositionX() + getDevice().getWidth(),
                getDevice().getPositionY() + getDevice().getHeight() / 2
        );
    }

    @Override
    protected Cursor defineCursor() {
        return new Cursor(Cursor.E_RESIZE_CURSOR);
    }
}
