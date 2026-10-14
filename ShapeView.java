public class ShapeView {
    public void printShapes(String message, Shape[] shapes) {
        System.out.println("\n--- " + message + " ---");
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    public void printTotalArea(double totalArea) {
        System.out.printf("\nTotal area of all shapes: %.2f%n", totalArea);
    }

    public void printTotalAreaByType(Class<?> shapeType, double totalArea) {
        System.out.printf("Total area of %s: %.2f%n", shapeType.getSimpleName(), totalArea);
    }
}