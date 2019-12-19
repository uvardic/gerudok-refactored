package gerudok.model.device.handle;

import gerudok.model.device.Device;

import java.awt.*;
import java.awt.geom.Point2D;

public class DeviceNorthEastHandle extends DeviceHandle {

    public DeviceNorthEastHandle(Device<?> device) {
        super(device);
    }

    @Override
    protected Point2D definePosition() {
        return new Point2D.Double(
                getDevice().getPositionX() + getDevice().getWidth(),
                getDevice().getPositionY()
        );
    }

    @Override
    protected Cursor defineCursor() {
        return new Cursor(Cursor.NE_RESIZE_CURSOR);
    }
}
