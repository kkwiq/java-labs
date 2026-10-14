public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[] {
            new Rectangle("Red", 4.0, 5.0),
            new Circle("Blue", 3.0),
            new Triangle("Green", 6.0, 2.5),
            new Rectangle("Yellow", 2.0, 8.0),
            new Circle("Red", 1.5),
            new Triangle("Black", 4.0, 3.0),
            new Rectangle("White", 7.0, 3.0),
            new Circle("Green", 5.0),
            new Triangle("Blue", 5.0, 4.0),
            new Rectangle("Orange", 3.5, 4.0),
            new Circle("Purple", 2.2)
        };

        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(shapes, view);

        controller.run();
    }
}