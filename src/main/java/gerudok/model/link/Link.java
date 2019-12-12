package gerudok.model.link;

import gerudok.model.Element;
import gerudok.model.Slot;
import gerudok.model.device.DeviceIO;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Link extends Element {

    private final DeviceIO output;

    private final Stroke lineStroke;

    private final Color lineStrokeColor;

    private final Stroke arrowStroke;

    private final Color arrowStrokeColor;

    private final int pointSize;

    private final List<Point2D> points = new ArrayList<>();

    private DeviceIO input;

    private Link(Builder builder) {
        super(builder.parent, builder.name);

        this.output           = builder.output;
        this.lineStroke       = builder.lineStroke;
        this.lineStrokeColor  = builder.lineStrokeColor;
        this.arrowStroke      = builder.arrowStroke;
        this.arrowStrokeColor = builder.arrowStrokeColor;
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

    @Override
    public void paint(Graphics2D graphics2D) {
        paintPoints(graphics2D);
    }

    private Point2D previousPoint;

    private void paintPoints(Graphics2D graphics2D) {
        graphics2D.setStroke(lineStroke);
        graphics2D.setPaint(lineStrokeColor);

        previousPoint = output.getPosition();
        points.forEach(point -> paintPoint(point, graphics2D));
        paintInput(graphics2D);
    }

    private void paintPoint(Point2D point, Graphics2D graphics2D) {
        graphics2D.drawLine(
                (int) previousPoint.getX(), (int) previousPoint.getY(),
                (int) point.getX(), (int) point.getY()
        );

        paintSelectionRectangle(point, graphics2D);

        previousPoint = point;
    }

    private void paintSelectionRectangle(Point2D point, Graphics2D graphics2D) {
        graphics2D.fillRect((int) point.getX(), (int) point.getY(), pointSize, pointSize);
    }

    private void paintInput(Graphics2D graphics2D) {
        if (input != null)
            graphics2D.drawLine(
                    (int) previousPoint.getX(), (int) previousPoint.getY(),
                    (int) input.getPositionX(), (int) input.getPositionY()
            );
    }

    @Override
    public boolean isElementAt(Point2D position) {
        return false;
    }

    public static class Builder {

        private final Slot parent;

        private final DeviceIO output;

        private String name = "Link";

        private Stroke lineStroke = new BasicStroke((2), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);

        private Color lineStrokeColor = Color.BLACK;

        private Stroke arrowStroke = new BasicStroke((2), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);

        private Color arrowStrokeColor = Color.BLACK;

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

        public Builder arrowStroke(Stroke arrowStroke) {
            this.arrowStroke = arrowStroke;

            return this;
        }

        public Builder arrowStrokeColor(Color arrowStrokeColor) {
            this.arrowStrokeColor = arrowStrokeColor;

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
