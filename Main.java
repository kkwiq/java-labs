import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Person originalPerson = new Person("Мельник", "Олександр", 20);
        System.out.println("1. Initial object: " + originalPerson);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(originalPerson);
       System.out.println("\n2. JSON string:\n" + jsonString);

        Person deserializedPerson = gson.fromJson(jsonString, Person.class);
        System.out.println("\n3. Restored object: " + deserializedPerson);

        boolean areEqual = originalPerson.equals(deserializedPerson);
        System.out.println("\n4. Equals check: " + areEqual);
    }
}