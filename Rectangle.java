public class Rectangle extends Shape {
    private static final long serialVersionUID = 1L;
    private double width;
    private double height;

    public Rectangle(String shapeColor, double width, double height) {
        super(shapeColor);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle [" + super.toString() + ", width=" + width + ", height=" + height + "]";
    }
}