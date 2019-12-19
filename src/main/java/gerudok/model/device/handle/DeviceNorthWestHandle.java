package gerudok.model.device.handle;

import gerudok.model.device.Device;

import java.awt.*;
import java.awt.geom.Point2D;

public class DeviceNorthWestHandle extends DeviceHandle {

    public DeviceNorthWestHandle(Device<?> device) {
        super(device);
    }

    @Override
    protected Point2D definePosition() {
        return new Point2D.Double(
                getDevice().getPositionX(), getDevice().getPositionY()
        );
    }

    @Override
    protected Cursor defineCursor() {
        return new Cursor(Cursor.NW_RESIZE_CURSOR);
    }
}
