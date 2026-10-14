public abstract class Shape implements Drawable {
    private String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    // Абстрактний метод для обчислення площі
    public abstract double calcArea();

    @Override
    public String toString() {
        return "Color: " + shapeColor + ", Area: " + String.format("%.2f", calcArea());
    }

    @Override
    public void draw() {
        System.out.println("Drawing: " + this);
    }
}