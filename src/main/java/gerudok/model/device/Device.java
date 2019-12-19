package gerudok.model.device;

import com.sun.nio.sctp.IllegalReceiveException;
import gerudok.model.Element;
import gerudok.model.Slot;
import gerudok.model.device.handle.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public abstract class Device<T extends Device<T>> extends Element {

    private static final double MIN_SCALING = 0.2;

    private static final double MAX_SCALING = 3;

    private final Stroke stroke;

    private final Color strokeColor;

    private final Stroke selectionStroke;

    private final Color selectionStrokeColor;

    private final Color fillColor;

    private final Dimension size;

    private final Point2D position;

    private final int numberOfInputs;

    private final int numberOfOutputs;

    private final double scale;

    private final double rotation;

    private final List<DeviceIO> inputs = new ArrayList<>();

    private final List<DeviceIO> outputs = new ArrayList<>();

    private final List<DeviceHandle> handles = new ArrayList<>();

    private final Shape shape;

    protected Device(DeviceBuilder<T> builder) {
        super(builder.parent, builder.name);

        this.stroke               = builder.stroke;
        this.strokeColor          = builder.strokeColor;
        this.selectionStroke      = builder.selectionStroke;
        this.selectionStrokeColor = builder.selectionStrokeColor;
        this.fillColor            = builder.fillColor;
        this.size                 = builder.size;
        this.position             = builder.position;
        this.numberOfInputs       = builder.numberOfInputs;
        this.numberOfOutputs      = builder.numberOfOutputs;
        this.scale                = builder.scale;
        this.rotation             = builder.rotation;
        // last call, subclasses use above parameters to define their shape
        this.shape                = defineShape();

        initializeDevice();
    }

    private void initializeDevice() {
        centerPosition();
        initializeInputs();
        initializeOutputs();
        initializeHandles();
    }

    private void centerPosition() {
        position.setLocation(position.getX() - size.getWidth() / 2, position.getY() - size.getHeight() / 2);
    }

    private Dimension getScaledSize() {
        return new Dimension((int) (size.getWidth() * scale), (int) (size.getHeight() * scale));
    }

    private void initializeInputs() {
        IntStream.range(0, numberOfInputs)
                .forEach(index -> inputs.add(new DeviceIO(this, DeviceIO.Type.INPUT, index)));
    }

    private void initializeOutputs() {
        IntStream.range(0, numberOfOutputs)
                .forEach(index -> outputs.add(new DeviceIO(this, DeviceIO.Type.OUTPUT, index)));
    }

    private void initializeHandles() {
        handles.add(new DeviceNorthWestHandle(this));
        handles.add(new DeviceNorthHandle(this));
        handles.add(new DeviceNorthEastHandle(this));
        handles.add(new DeviceWestHandle(this));
        handles.add(new DeviceEastHandle(this));
        handles.add(new DeviceSouthWestHandle(this));
        handles.add(new DeviceSouthHandle(this));
        handles.add(new DeviceSouthEastHandle(this));
    }

    protected abstract Shape defineShape();

    private DeviceIO closestInput;

    public DeviceIO getClosestInputTo(Point2D position) {
        closestInput = inputs.get(0);

        inputs.stream()
                .filter(input -> isClosestInput(position, input))
                .forEach(input -> closestInput = input);

        return closestInput;
    }

    private boolean isClosestInput(Point2D position, DeviceIO input) {
        return position.distance(input.getPosition()) < position.distance(closestInput.getPosition());
    }

    private DeviceIO closestOutput;

    public DeviceIO getClosestOutputTo(Point2D position) {
        closestOutput = outputs.get(0);

        outputs.stream()
                .filter(output -> isClosestOutput(position, output))
                .forEach(output -> closestOutput = output);

        return closestOutput;
    }

    private boolean isClosestOutput(Point2D position, DeviceIO output) {
        return position.distance(output.getPosition()) < position.distance(closestOutput.getPosition());
    }

    public boolean isHandleAt(Point2D position) {
        return handles.stream().anyMatch(handle -> handle.isHandleAt(position));
    }

    public DeviceHandle getHandleAt(Point2D position) {
        return handles.stream()
                .filter(handle -> handle.isHandleAt(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No handles found!"));
    }

    public double getWidth() {
        return getScaledSize().getWidth();
    }

    public double getHeight() {
        return getScaledSize().getHeight();
    }

    public double getPositionX() {
        return position.getX();
    }

    public double getPositionY() {
        return position.getY();
    }

    public void setPosition(double x, double y) {
        position.setLocation(x, y);
        reinitializeHandles();
    }

    private void reinitializeHandles() {
        handles.clear();
        initializeHandles();
    }

    public int getNumberOfInputs() {
        return numberOfInputs;
    }

    public int getNumberOfOutputs() {
        return numberOfOutputs;
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        AffineTransform originalTransformationMatrix = graphics2D.getTransform();

        initializeGraphics(graphics2D);
        graphics2D.draw(shape);
        graphics2D.fill(shape);

        graphics2D.setTransform(originalTransformationMatrix);

        paintInputs(graphics2D);
        paintOutputs(graphics2D);
    }

    private void initializeGraphics(Graphics2D graphics2D) {
        graphics2D.translate(getPositionX(), getPositionY());
        graphics2D.scale(scale, scale);
        graphics2D.rotate(rotation);
        graphics2D.setPaint(strokeColor);
        graphics2D.setStroke(stroke);
        graphics2D.setPaint(fillColor);
    }

    private void paintInputs(Graphics2D graphics2D) {
        inputs.forEach(input -> input.paint(graphics2D));
    }

    private void paintOutputs(Graphics2D graphics2D) {
        outputs.forEach(output -> output.paint(graphics2D));
    }

    @Override
    public void paintSelection(Graphics2D graphics2D) {
        graphics2D.setStroke(selectionStroke);
        graphics2D.setPaint(selectionStrokeColor);

        graphics2D.drawRect((int) getPositionX(), (int) getPositionY(), (int) getWidth(), (int) getHeight());

        paintHandles(graphics2D);
    }

    private void paintHandles(Graphics2D graphics2D) {
        handles.forEach(handle -> handle.paint(graphics2D));
    }

    @Override
    public boolean isElementAt(Point2D position) {
        Rectangle2D deviceBounds = new Rectangle2D.Double(getPositionX(), getPositionY(), getWidth(), getHeight());

        return deviceBounds.contains(position);
    }

    @Override
    public String toString() {
        return "Device{" +
                "stroke=" + stroke +
                ", strokeColor=" + strokeColor +
                ", fillColor=" + fillColor +
                ", size=" + size +
                ", position=" + position +
                ", shape=" + shape +
                ", numberOfInputs=" + numberOfInputs +
                ", numberOfOutputs=" + numberOfOutputs +
                ", scale=" + scale +
                ", rotation=" + rotation +
                ", inputs=" + inputs +
                ", outputs=" + outputs +
                "}";
    }

    public static abstract class DeviceBuilder<T> {

        private final Slot parent;

        private Stroke stroke = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);

        private Color strokeColor = Color.BLACK;

        private Stroke selectionStroke = new BasicStroke(
                1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1f, new float[] { 3f, 6f }, 0
        );

        private Color selectionStrokeColor = Color.BLACK;

        private Color fillColor = new Color(36, 105, 170);

        private String name = "Device";

        private Dimension size = new Dimension(50, 50);

        private Point2D position = new Point2D.Double(0, 0);

        private int numberOfInputs = 2;

        private int numberOfOutputs = 1;

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

        public DeviceBuilder<T> selectionStroke(Stroke selectionStroke) {
            this.selectionStroke = selectionStroke;

            return this;
        }

        public DeviceBuilder<T> selectionStrokeColor(Color selectionStrokeColor) {
            this.selectionStrokeColor = selectionStrokeColor;

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

        public DeviceBuilder<T> numberOfInputs(int numberOfInputs) {
            if (numberOfInputs < 0)
                throw new IllegalReceiveException(String.format("Invalid number of inputs = %d", numberOfInputs));

            this.numberOfInputs = numberOfInputs;

            return this;
        }

        public DeviceBuilder<T> numberOfOutputs(int numberOfOutputs) {
            if (numberOfOutputs < 0)
                throw new IllegalReceiveException(String.format("Invalid number of outputs = %d", numberOfOutputs));

            this.numberOfOutputs = numberOfOutputs;

            return this;
        }

        public DeviceBuilder<T> scale(double scale) {
            if (scale < MIN_SCALING || scale > MAX_SCALING)
                throw new IllegalArgumentException(String.format("Invalid scale = %f", scale));

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
