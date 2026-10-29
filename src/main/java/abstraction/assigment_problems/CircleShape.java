package abstraction.assigment_problems;

public class CircleShape extends Shape {

    private double radius;

    public CircleShape(double radius) {
        super();
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    // A circle cannot stretch unevenly, so it grows by the average of both factors.
    @Override
    protected void resize(double xFactor, double yFactor) {
        radius *= (xFactor + yFactor) / 2;
    }
}
