import java.util.Arrays;

public class ShapeController {
    private Shape[] shapes;
    private ShapeView view;

    public ShapeController(Shape[] shapes, ShapeView view) {
        this.shapes = shapes;
        this.view = view;
    }

    public void displayAllShapes() {
        view.printShapes("All Shapes (Initial)", shapes);
    }

    public double calculateTotalArea() {
        double total = 0.0;
        for (Shape shape : shapes) {
            total += shape.calcArea();
        }
        return total;
    }

    public double calculateTotalAreaByType(Class<?> type) {
        double total = 0.0;
        for (Shape shape : shapes) {
            if (type.isInstance(shape)) {
                total += shape.calcArea();
            }
        }
        return total;
    }

    public void sortByArea() {
        Arrays.sort(shapes, new AreaComparator());
        view.printShapes("Shapes sorted by Area (ascending)", shapes);
    }

    public void sortByColor() {
        Arrays.sort(shapes, new ColorComparator());
        view.printShapes("Shapes sorted by Color", shapes);
    }

    public void run() {
        displayAllShapes();

        double totalArea = calculateTotalArea();
        view.printTotalArea(totalArea);

        view.printTotalAreaByType(Rectangle.class, calculateTotalAreaByType(Rectangle.class));
        view.printTotalAreaByType(Triangle.class, calculateTotalAreaByType(Triangle.class));
        view.printTotalAreaByType(Circle.class, calculateTotalAreaByType(Circle.class));

        sortByArea();
        sortByColor();
    }
}