package abstraction.assigment_problems;

public abstract class Shape {

    private static int counter = 0;

    private final String shapeId;

    protected Shape() {
        counter++;
        shapeId = "SHP-" + counter;
    }

    public abstract double calculateArea();

    // Each subclass decides how its own dimensions respond to a stretch.
    protected abstract void resize(double xFactor, double yFactor);

    public void scale(double factor) {
        resize(factor, factor);
    }

    public void scale(double xFactor, double yFactor) {
        resize(xFactor, yFactor);
    }

    public String getShapeId() {
        return shapeId;
    }
}
