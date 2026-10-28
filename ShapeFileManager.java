import java.io.*;
import java.util.List;

public class ShapeFileManager {

    public static void saveShapes(String filePath, List<Shape> shapes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(shapes);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Shape> loadShapes(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Shape>) ois.readObject();
        }
    }
}