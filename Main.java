import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Task 4: HTML Tag Frequency Counter ===");
        System.out.print("Enter URL (or press Enter for 'https://example.com'): ");
        String urlString = scanner.nextLine().trim();

        if (urlString.isEmpty()) {
            urlString = "https://example.com";
        }

        Map<String, Integer> tagCounts = new HashMap<>();

        try {
            URL url = URI.create(urlString).toURL();
            System.out.println("Fetching page content from: " + urlString + " ...");

            // Зчитування сторінки через мережевий символьний потік
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {

                // Регулярний вираз для пошуку відкриваючих HTML-тегів (наприклад: <div>, <p class="...">)
                Pattern tagPattern = Pattern.compile("<([a-zA-Z][a-zA-Z0-9]*)\\b");
                String line;

                while ((line = reader.readLine()) != null) {
                    Matcher matcher = tagPattern.matcher(line);
                    while (matcher.find()) {
                        String tagName = matcher.group(1).toLowerCase();
                        tagCounts.put(tagName, tagCounts.getOrDefault(tagName, 0) + 1);
                    }
                }
            }

            if (tagCounts.isEmpty()) {
                System.out.println("No HTML tags found on this page.");
                scanner.close();
                return;
            }

            // Пункт a: вивід тегів у порядку зростання за алфавітом (лексикографічно)
            List<Map.Entry<String, Integer>> sortedByName = new ArrayList<>(tagCounts.entrySet());
            sortedByName.sort(Map.Entry.comparingByKey());

            System.out.println("\n--- (a) Tags sorted lexicographically (A -> Z) ---");
            for (Map.Entry<String, Integer> entry : sortedByName) {
                System.out.printf("%-15s : %d%n", "<" + entry.getKey() + ">", entry.getValue());
            }

            // Пункт b: вивід тегів у порядку зростання за частотою появи
            List<Map.Entry<String, Integer>> sortedByFrequency = new ArrayList<>(tagCounts.entrySet());
            sortedByFrequency.sort(Map.Entry.comparingByValue());

            System.out.println("\n--- (b) Tags sorted by frequency (ascending) ---");
            for (Map.Entry<String, Integer> entry : sortedByFrequency) {
                System.out.printf("%-15s : %d%n", "<" + entry.getKey() + ">", entry.getValue());
            }

        } catch (Exception e) {
            System.out.println("Error reading URL: " + e.getMessage());
        }

        scanner.close();
    }
}