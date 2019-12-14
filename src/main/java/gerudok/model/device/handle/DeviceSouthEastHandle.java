package gerudok.model.device.handle;

import gerudok.model.device.Device;

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


}
