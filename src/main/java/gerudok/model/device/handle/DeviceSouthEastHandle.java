package gerudok.model.device.handle;

import gerudok.model.device.Device;

import java.awt.*;
import java.awt.geom.Point2D;

public class DeviceSouthEastHandle extends DeviceHandle {

    public DeviceSouthEastHandle(Device<?> device) {
        super(device);
    }

    @Override
    protected Point2D definePosition() {
        return new Point2D.Double(
                getDevice().getPositionX() + getDevice().getWidth(),
                getDevice().getPositionY() + getDevice().getHeight()
        );
    }

    @Override
    protected Cursor defineCursor() {
        return new Cursor(Cursor.SE_RESIZE_CURSOR);
    }
}
