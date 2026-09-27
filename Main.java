import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a positive whole number: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Error: you need to enter a whole number.");
                return;
            }

            int number = scanner.nextInt();

            if (number <= 0) {
                System.out.println("Error: the number must be positive (> 0).");
                return;
            }

            System.out.println("The entered decimal number: " + number);
            System.out.println("Binary system (Base 2):        " + convertToBase(number, 2));
            System.out.println("Octal system (Base 8):       " + convertToBase(number, 8));
            System.out.println("Hexadecimal system (Base 16): " + convertToBase(number, 16));
        }
    }

    public static String convertToBase(int n, int base) {
        if (n == 0) {
            return "0";
        }

        char[] digits = "0123456789ABCDEF".toCharArray();
        StringBuilder result = new StringBuilder();

        while (n > 0) {
            int remainder = n % base;
            result.append(digits[remainder]);
            n /= base;
        }

        return result.reverse().toString();
    }
}