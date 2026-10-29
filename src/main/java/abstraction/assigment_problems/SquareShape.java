package abstraction.assigment_problems;

public class SquareShape extends Shape {

    private double side;

    public SquareShape(double side) {
        super();
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    // A square must stay square, so it grows by the average of both factors.
    @Override
    protected void resize(double xFactor, double yFactor) {
        side *= (xFactor + yFactor) / 2;
    }
}
