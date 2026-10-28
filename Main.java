public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. Creating passengers ===");
        Human citizen = new Human("Ivan");
        Firefighter fireman1 = new Firefighter("Taras");
        Firefighter fireman2 = new Firefighter("Oleh");
        Policeman cop = new Policeman("Petro");

        System.out.println("\n=== 2. Getting on the right transport ===");
        Taxi taxi = new Taxi(2);
        taxi.boardPassenger(citizen);
        taxi.boardPassenger(cop);

        FireTruck fireTruck = new FireTruck(2);
        fireTruck.boardPassenger(foremanOrFireman(fireman1));
        fireTruck.boardPassenger(fireman2);

        PoliceCar policeCar = new PoliceCar(2);
        policeCar.boardPassenger(cop);

        Bus bus = new Bus(30);
        bus.boardPassenger(citizen);
        bus.boardPassenger(fireman1);

        System.out.println("\n=== 3. Adding a car to the road and counting ===");
        Road road = new Road();
        road.addCarToRoad(taxi);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(policeCar);

        System.out.println("\nTotal number of people in cars on the road: " + road.getCountOfHumans());

        System.out.println("\n=== 4. Testing edge cases ===");
        try {
            System.out.println("Trying to squeeze an extra passenger into a taxi (capacity: " + taxi.getMaxSeats() + ")...");
            taxi.boardPassenger(new Human("Olena"));
        } catch (IllegalStateException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        try {
            Human ghost = new Human("Ghost");
            System.out.println("Attempting to drop off a non-existent passenger...");
            taxi.unboardPassenger(ghost);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println("Successful landing:");
        taxi.unboardPassenger(citizen);
        System.out.println("Occupied seats in the taxi now: " + taxi.getOccupiedSeats());
    }

    private static Firefighter foremanOrFireman(Firefighter f) {
        return f;
    }
}