package gerudok.model.device.handle;

import gerudok.model.device.Device;

import java.awt.*;
import java.awt.geom.Point2D;

public class DeviceSouthWestHandle extends DeviceHandle {

    public DeviceSouthWestHandle(Device<?> device) {
        super(device);
    }

    @Override
    protected Point2D definePosition() {
        return new Point2D.Double(
                getDevice().getPositionX(),
                getDevice().getPositionY() + getDevice().getHeight()
        );
    }

    @Override
    protected Cursor defineCursor() {
        return new Cursor(Cursor.SW_RESIZE_CURSOR);
    }
}
