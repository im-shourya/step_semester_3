package abstraction.assigment_problems;

public class DrawingCanvas {

    public static void printArea(Shape s) {
        System.out.printf("%s area: %.2f%n", s.getShapeId(), s.calculateArea());
    }

    public static void main(String[] args) {
        // Shape s = new Shape(); // does not compile: Shape is abstract
        CircleShape c = new CircleShape(5.0);
        System.out.printf("%.2f%n", c.calculateArea());

        SquareShape sq = new SquareShape(4.0);
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        printArea(c);
        printArea(sq);
    }
}
