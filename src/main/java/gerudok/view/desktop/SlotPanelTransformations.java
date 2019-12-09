package gerudok.view.desktop;

import gerudok.model.device.Device;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.List;

class SlotPanelTransformations {

    private static final double SCALING_FACTOR = 1.2;

    private static final double MIN_SCALING_FACTOR = 0.2;

    private static final double MAX_SCALING_FACTOR = 5;

    private final AffineTransform transformationMatrix = new AffineTransform();

    private final SlotPanel slotPanel;

    SlotPanelTransformations(SlotPanel slotPanel) {
        this.slotPanel = slotPanel;
    }

    void transformPosition(Point2D position) {
        try {
            transformationMatrix.inverseTransform(position, position);
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    void regionZoom() {
        regionZoomWorker(slotPanel.getModel().getChildDevices());
    }

    void regionZoom(List<Device<?>> devices) {
        regionZoomWorker(devices);
    }

    private void regionZoomWorker(List<Device<?>> devices) {
        if (devices.size() == 0)
            return;

        double[][] scaledRegion = scaleRegion(createRegion(devices));

        transformationMatrix.translate(
                (-scaledRegion[0][0] + (slotPanel.getWidth() - scaledRegion[1][0]) / 2) / transformationMatrix.getScaleX(),
                (-scaledRegion[0][1] + (slotPanel.getHeight() - scaledRegion[1][1]) / 2) / transformationMatrix.getScaleY()
        );

        // Zoom out once for better view
        centerZoomOut();
    }

    private double[][] createRegion(List<Device<?>> devices) {
        double[][] region = new double[2][2];

        initializeRegion(devices.get(0), region);
        devices.forEach(device -> adjustRegion(device, region));

        return region;
    }

    private void initializeRegion(Device<?> firstDevice, double[][] region) {
        region[0][0] = firstDevice.getPositionX();
        region[0][1] = firstDevice.getPositionY();
        region[1][0] = firstDevice.getPositionX() + firstDevice.getWidth();
        region[1][1] = firstDevice.getPositionY() + firstDevice.getHeight();
    }

    private void adjustRegion(Device<?> device, double[][] region) {
        if (device.getPositionX() < region[0][0])
            region[0][0] = device.getPositionX();

        if (device.getPositionY() < region[0][1])
            region[0][1] = device.getPositionY();

        if (device.getPositionX() + device.getWidth() > region[1][0])
            region[1][0] = device.getPositionX() + device.getWidth();

        if (device.getPositionY() + device.getHeight() > region[1][1])
            region[1][1] = device.getPositionY() + device.getHeight();
    }

    private double[][] scaleRegion(double[][] region) {
        double newScale = boundScale(calculateNewScaleForRegionZoom(region));

        transformationMatrix.setToScale(newScale, newScale);

        return transformRegion(region);
    }

    private double calculateNewScaleForRegionZoom(double[][] region) {
        double[][] transformedRegion = transformRegion(region);

        double scaleX = slotPanel.getWidth() / transformedRegion[1][0];

        double scaleY = slotPanel.getHeight() / transformedRegion[1][1];

        if (scaleX < scaleY)
            return transformationMatrix.getScaleX() * scaleX;

        return transformationMatrix.getScaleY() * scaleY;
    }

    private double[][] transformRegion(double[][] region) {
        double[][] transformedRegion = new double[2][2];

        transformedRegion[0][0] = transformX(region[0][0]);
        transformedRegion[0][1] = transformY(region[0][1]);
        transformedRegion[1][0] = transformX(region[1][0]) - transformX(region[0][0]);
        transformedRegion[1][1] = transformY(region[1][1]) - transformY(region[0][1]);

        return transformedRegion;
    }

    private double transformX(double x) {
        return x * transformationMatrix.getScaleX() + transformationMatrix.getTranslateX();
    }

    private double transformY(double y) {
        return y * transformationMatrix.getScaleY() + transformationMatrix.getTranslateY();
    }

    void centerZoomIn() {
        double newScale = calculateNewScaleForCenterZoomIn();

        transformToNewScaleForCenterZoom(newScale);
    }

    void centerZoomOut() {
        double newScale = calculateNewScaleForCenterZoomOut();

        transformToNewScaleForCenterZoom(newScale);
    }

    private double calculateNewScaleForCenterZoomIn() {
        double scale = boundScale(transformationMatrix.getScaleX());

        return scale * SCALING_FACTOR;
    }

    private double calculateNewScaleForCenterZoomOut() {
        double scale = boundScale(transformationMatrix.getScaleX());

        return scale / SCALING_FACTOR;
    }

    private double boundScale(double scalingFactor) {
        if (scalingFactor < MIN_SCALING_FACTOR)
            return MIN_SCALING_FACTOR;

        return Math.min(scalingFactor, MAX_SCALING_FACTOR);
    }

    private void transformToNewScaleForCenterZoom(double newScale) {
        Point2D originalPosition = new Point(slotPanel.getWidth() / 2, slotPanel.getHeight() / 2);
        transformPosition(originalPosition);

        transformationMatrix.setToScale(newScale, newScale);

        Point2D newPosition = new Point(slotPanel.getWidth() / 2, slotPanel.getHeight() / 2);
        transformPosition(newPosition);

        transformationMatrix.translate(
                newPosition.getX() - originalPosition.getX(),
                newPosition.getY() - originalPosition.getY()
        );
    }

    double calculateNewScaleForMouseWheelEvent(MouseWheelEvent event) {
        double scale = boundScale(transformationMatrix.getScaleX());

        if (isScrollWheelZoomingOut(event))
            return scale / (event.getWheelRotation() * SCALING_FACTOR);
        else
            return scale * (-event.getWheelRotation() * SCALING_FACTOR);
    }

    private boolean isScrollWheelZoomingOut(MouseWheelEvent event) {
        return event.getWheelRotation() > 0;
    }

    void transformToNewScaleForMouseEvent(MouseEvent event, double newScale) {
        Point2D originalPosition = event.getPoint();
        transformPosition(originalPosition);

        transformationMatrix.setToScale(newScale, newScale);

        Point2D newPosition = event.getPoint();
        transformPosition(newPosition);

        transformationMatrix.translate(
                newPosition.getX() - originalPosition.getX(),
                newPosition.getY() - originalPosition.getY()
        );
    }

    AffineTransform getTransformationMatrix() {
        return transformationMatrix;
    }
}
