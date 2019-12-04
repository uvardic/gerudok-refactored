package gerudok.model.device;

import gerudok.model.Element;
import gerudok.model.Slot;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Device<T extends Device<T>> extends Element {

    private static final double MIN_SCALING = 0.2;

    private static final double MAX_SCALING = 3;

    private final Stroke stroke;

    private final Color strokeColor;

    private final Color fillColor;

    private final Dimension size;

    private final Point2D position
            ;
    private final Shape shape;

    private final double scale;

    private final double rotation;

    protected Device(DeviceBuilder<T> builder) {
        super(builder.parent, builder.name);

        this.stroke = builder.stroke;
        this.strokeColor = builder.strokeColor;
        this.fillColor = builder.fillColor;
        this.size = builder.size;
        this.position = builder.position;
        this.shape = defineShape();
        this.scale = builder.scale;
        this.rotation = builder.rotation;

        centerPosition();
    }

    private void centerPosition() {
        position.setLocation(position.getX() - size.getWidth() / 2, position.getY() - size.getHeight() / 2);
    }

    protected abstract Shape defineShape();

    @Override
    public void paint(Graphics2D graphics2D) {
        initializeGraphics(graphics2D);
        paintShape(graphics2D);
        fillShape(graphics2D);
    }

    private void initializeGraphics(Graphics2D graphics2D) {
        graphics2D.scale(scale, scale);
        graphics2D.rotate(rotation);
    }

    private void paintShape(Graphics2D graphics2D) {
        graphics2D.setPaint(strokeColor);
        graphics2D.setStroke(stroke);
        graphics2D.draw(shape);
    }

    private void fillShape(Graphics2D graphics2D) {
        graphics2D.setPaint(fillColor);
        graphics2D.fill(shape);
    }

    @Override
    public boolean isElementAt(Point2D position) {
        Rectangle2D deviceBounds = new Rectangle2D.Double(getPositionX(), getPositionY(), getWidth(), getHeight());

        return deviceBounds.contains(position);
    }

    public double getWidth() {
        return size.getWidth();
    }

    public double getHeight() {
        return size.getHeight();
    }

    public double getPositionX() {
        return position.getX();
    }

    public double getPositionY() {
        return position.getY();
    }

    public static abstract class DeviceBuilder<T> {

        private final Slot parent;

        private Stroke stroke = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);

        private Color strokeColor = Color.BLACK;

        private Color fillColor = Color.BLUE;

        private String name = "Device";

        private Dimension size = new Dimension(50, 50);

        private Point2D position = new Point2D.Double(0, 0);

        private double scale = 1;

        private double rotation = 0;

        public DeviceBuilder(Slot parent) {
            this.parent = parent;
        }

        public DeviceBuilder<T> stroke(Stroke stroke) {
            this.stroke = stroke;

            return this;
        }

        public DeviceBuilder<T> strokeColor(Color strokeColor) {
            this.strokeColor = strokeColor;

            return this;
        }

        public DeviceBuilder<T> fillColor(Color fillColor) {
            this.fillColor = fillColor;

            return this;
        }

        public DeviceBuilder<T> name(String name) {
            this.name = name;

            return this;
        }

        public DeviceBuilder<T> size(Dimension size) {
            this.size = size;

            return this;
        }

        public DeviceBuilder<T> position(Point2D position) {
            this.position = position;

            return this;
        }

        public DeviceBuilder<T> scale(double scale) {
            if (scale < MIN_SCALING || scale > MAX_SCALING)
                throw new IllegalArgumentException("Invalid scale value");

            this.scale = scale;

            return this;
        }

        public DeviceBuilder<T> rotation(double rotation) {
            this.rotation = rotation;

            return this;
        }

        public abstract T build();

    }
}
