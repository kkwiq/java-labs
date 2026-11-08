import java.util.List;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static final IntPredicate isPrime = n -> {
        if (n < 2) return false;
        return IntStream.rangeClosed(2, (int) Math.sqrt(n))
                .noneMatch(divisor -> n % divisor == 0);
    };

    public static final IntUnaryOperator reverseNumber = n -> {
        int reversed = 0;
        int temp = n;
        while (temp > 0) {
            reversed = reversed * 10 + (temp % 10);
            temp /= 10;
        }
        return reversed;
    };

    public static final IntPredicate isSuperPrime = n -> 
            isPrime.test(n) && isPrime.test(reverseNumber.applyAsInt(n));

    public static long countSuperPrimes(int n) {
        return IntStream.rangeClosed(1, n)
                .filter(isSuperPrime)
                .count();
    }

    public static List<Integer> getSuperPrimes(int n) {
        return IntStream.rangeClosed(1, n)
                .filter(isSuperPrime)
                .boxed()
                .collect(Collectors.toList());
    }

    public static void printFormattedList(List<Integer> list, int itemsPerRow) {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%5d", list.get(i));
            if ((i + 1) % itemsPerRow == 0 || i == list.size() - 1) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Lab 7: Super-Prime Numbers (Lambdas & Stream API) ===");
        System.out.print("Enter natural number n (n <= 1000): ");

        if (!scanner.hasNextInt()) {
            System.out.println("Error: Please enter a valid integer.");
            scanner.close();
            return;
        }

        int n = scanner.nextInt();

        if (n < 1 || n > 1000) {
            System.out.println("Error: Number n must be in range [1, 1000].");
            scanner.close();
            return;
        }

        long count = countSuperPrimes(n);
        List<Integer> list = getSuperPrimes(n);

        System.out.println("\n------------------------------------------------------------");
        System.out.println("RESULTS FOR n = " + n);
        System.out.println("------------------------------------------------------------");
        System.out.println("Total super-prime numbers found : " + count);
        System.out.println("\nList of super-prime numbers (10 per row):");
        System.out.println("------------------------------------------------------------");
        printFormattedList(list, 10);
        System.out.println("------------------------------------------------------------");

        scanner.close();
    }
}