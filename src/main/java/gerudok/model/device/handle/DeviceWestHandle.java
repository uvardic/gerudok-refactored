package gerudok.model.device.handle;

import gerudok.model.device.Device;

import java.awt.geom.Point2D;

public class DeviceWestHandle extends DeviceHandle {

    public DeviceWestHandle(Device<?> device) {
        super(device);
    }

    @Override
    protected Point2D definePosition() {
        return new Point2D.Double(
                getDevice().getPositionX(),
                getDevice().getPositionY() + getDevice().getHeight() / 2
        );
    }

}
