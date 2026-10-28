import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Vehicle<T extends Human> {
    private final int maxSeats;
    private final List<T> passengers;

    public Vehicle(int maxSeats) {
        this.maxSeats = maxSeats;
        this.passengers = new ArrayList<>();
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void boardPassenger(T passenger) {
        if (passengers.size() >= maxSeats) {
            throw new IllegalStateException("There are no free spots in " + getClass().getSimpleName() + " for " + passenger.getName());
        }
        passengers.add(passenger);
        System.out.println("Passenger " + passenger + " seat in " + getClass().getSimpleName());
    }

    public void unboardPassenger(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new IllegalArgumentException("Passenger " + passenger.getName() + " is not located in " + getClass().getSimpleName());
        }
        System.out.println("Passenger " + passenger + " came out of " + getClass().getSimpleName());
    }

    public List<T> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }
}