import java.util.ArrayList;
import java.util.List;

public class Road {

    public List<Car<?>> carsInRoad = new ArrayList<>();

    public int getCountOfHumans() {
        int count = 0;
        for (Car<?> car : carsInRoad) {
            count += car.getOccupiedSeats();
        }
        return count;
    }

    public void addCarToRoad(Car<?> car) {
        carsInRoad.add(car);
        System.out.println("Car " + car.getClass().getSimpleName() + " added to the road.");
    }
}