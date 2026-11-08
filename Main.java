import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Translator translator = new Translator();

        translator.addWord("student", "студент");
        translator.addWord("is", "це");
        translator.addWord("learning", "вивчає");
        translator.addWord("java", "джава");
        translator.addWord("cat", "котик");
        translator.addWord("says", "каже");
        translator.addWord("meow", "мяу");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== English-Ukrainian Translator ===");
            System.out.println("1. Translate phrase");
            System.out.println("2. Add new word to dictionary");
            System.out.println("3. Show current dictionary");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("\nEnter English phrase: ");
                    String inputPhrase = scanner.nextLine();
                    String translation = translator.translatePhrase(inputPhrase);
                    System.out.println("Translation: " + translation);
                    break;

                case "2":
                    System.out.print("\nEnter English word: ");
                    String en = scanner.nextLine();
                    System.out.print("Enter Ukrainian translation: ");
                    String ua = scanner.nextLine();
                    translator.addWord(en, ua);
                    System.out.println("Successfully added: " + en + " -> " + ua);
                    break;

                case "3":
                    System.out.println("\n--- Dictionary (" + translator.getDictionary().size() + " words) ---");
                    translator.getDictionary().forEach((k, v) -> System.out.println(k + " -> " + v));
                    break;

                case "0":
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}