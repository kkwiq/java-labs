import java.io.Serializable;

public abstract class Shape implements Serializable {
    private static final long serialVersionUID = 1L;
    private String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getShapeColor() {
        return shapeColor;
    }

    public abstract double calcArea();

    @Override
    public String toString() {
        return "Color: " + shapeColor + ", Area: " + String.format("%.2f", calcArea());
    }
}