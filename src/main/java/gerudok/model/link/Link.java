package gerudok.model.link;

import gerudok.model.Element;
import gerudok.model.Slot;
import gerudok.model.device.DeviceIO;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Link extends Element {

    private static final int HANDLE_SIZE = 7;

    private final DeviceIO output;

    private final Stroke lineStroke;

    private final Color lineStrokeColor;

    private final int pointSize;

    private final List<Point2D> points = new ArrayList<>();

    private DeviceIO input;

    private Link(Builder builder) {
        super(builder.parent, builder.name);

        this.output           = builder.output;
        this.lineStroke       = builder.lineStroke;
        this.lineStrokeColor  = builder.lineStrokeColor;
        this.pointSize        = builder.pointSize;

        addPoint(output.getPosition());
    }

    public void addPoint(Point2D point) {
        if (point == null)
            throw new NullPointerException("Point can't be null!");

        points.add(point);
    }

    public Point2D getLastPoint() {
        return points.get(points.size() - 1);
    }

    public void setInput(DeviceIO input) {
        this.input = input;
        // input becomes the last point
        points.remove(getLastPoint());
    }

    public void removeAllPoints() {
        points.clear();
    }

    private Point2D closestPoint;

    public Point2D getPointClosestTo(Point2D position) {
        closestPoint = points.get(0);

        points.stream()
                .filter(point -> isClosestPoint(position, point))
                .forEach(point -> closestPoint = point);

        return closestPoint;
    }

    private boolean isClosestPoint(Point2D position, Point2D point) {
        return position.distance(point) < position.distance(closestPoint);
    }

    private Point2D previousPoint;

    @Override
    public void paint(Graphics2D graphics2D) {
        graphics2D.setStroke(lineStroke);
        graphics2D.setPaint(lineStrokeColor);

        previousPoint = output.getPosition();
        points.forEach(point -> paintLink(point, graphics2D));

        if (input != null)
            paintInput(graphics2D);
    }

    private void paintLink(Point2D point, Graphics2D graphics2D) {
        graphics2D.drawLine(
                (int) previousPoint.getX(), (int) previousPoint.getY(),
                (int) point.getX(), (int) point.getY()
        );

        paintPoint(point, graphics2D);

        previousPoint = point;
    }

    private void paintPoint(Point2D point, Graphics2D graphics2D) {
        graphics2D.fillRect(
                (int) point.getX() - pointSize / 2, (int) point.getY() - pointSize / 2, pointSize, pointSize
        );
    }

    private void paintInput(Graphics2D graphics2D) {
        graphics2D.drawLine(
                (int) previousPoint.getX(), (int) previousPoint.getY(),
                (int) input.getPositionX(), (int) input.getPositionY()
        );
    }

    @Override
    public void paintSelection(Graphics2D graphics2D) {
        graphics2D.setPaint(Color.BLACK);

        points.forEach(point -> paintPointHandle(point, graphics2D));
    }

    private void paintPointHandle(Point2D point, Graphics2D graphics2D) {
        graphics2D.fillRect(
                (int) point.getX() - HANDLE_SIZE / 2,
                (int) point.getY() - HANDLE_SIZE / 2,
                HANDLE_SIZE, HANDLE_SIZE
        );
    }

    @Override
    public boolean isElementAt(Point2D position) {
        if (input == null)
            return false;

        return points.stream().anyMatch(point -> isPositionInPointBounds(position, point));
    }

    private boolean isPositionInPointBounds(Point2D position, Point2D point) {
        Rectangle2D pointBounds = new Rectangle2D.Double(
                point.getX() - (double) pointSize / 2,
                point.getY() - (double) pointSize / 2,
                pointSize, pointSize
        );

        return pointBounds.contains(position);
    }

    public static class Builder {

        private final Slot parent;

        private final DeviceIO output;

        private String name = "Link";

        private Stroke lineStroke = new BasicStroke((2), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);

        private Color lineStrokeColor = Color.BLACK;

        private int pointSize = 5;

        public Builder(Slot parent, DeviceIO output) {
            this.parent = parent;
            this.output = output;
        }

        public Builder name(String name) {
            this.name = name;

            return this;
        }

        public Builder lineStroke(Stroke lineStroke) {
            this.lineStroke = lineStroke;

            return this;
        }

        public Builder lineStrokeColor(Color lineStrokeColor) {
            this.lineStrokeColor = lineStrokeColor;

            return this;
        }

        public Builder pointSize(int pointSize) {
            this.pointSize = pointSize;

            return this;
        }

        public Link build() {
            return new Link(this);
        }

    }

}
