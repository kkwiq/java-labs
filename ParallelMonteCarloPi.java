import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class ParallelMonteCarloPi {

    private static final long TOTAL_ITERATIONS = 1_000_000_000L;

    public static void main(String[] args) {
        int numThreads;

        if (args.length > 0) {
            try {
                numThreads = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid thread count argument. Falling back to input.");
                numThreads = askThreadCount();
            }
        } else {
            numThreads = askThreadCount();
        }

        if (numThreads <= 0) {
            numThreads = Runtime.getRuntime().availableProcessors();
        }

        long iterationsPerThread = TOTAL_ITERATIONS / numThreads;
        AtomicLong totalPointsInCircle = new AtomicLong(0);

        Thread[] threads = new Thread[numThreads];

        long startTime = System.nanoTime();

        for (int i = 0; i < numThreads; i++) {
            final long iters = (i == numThreads - 1) 
                    ? iterationsPerThread + (TOTAL_ITERATIONS % numThreads) 
                    : iterationsPerThread;

            threads[i] = new Thread(() -> {
                long localCount = 0;
                ThreadLocalRandom random = ThreadLocalRandom.current();

                for (long j = 0; j < iters; j++) {
                    double x = random.nextDouble();
                    double y = random.nextDouble();
                    if (x * x + y * y <= 1.0) {
                        localCount++;
                    }
                }
                totalPointsInCircle.addAndGet(localCount);
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }

        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;

        double pi = 4.0 * totalPointsInCircle.get() / TOTAL_ITERATIONS;

        NumberFormat numFormat = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat timeFormat = new DecimalFormat("0.00", new java.text.DecimalFormatSymbols(Locale.US));

        System.out.println("PI is " + String.format(Locale.US, "%.5f", pi));
        System.out.println("THREADS " + numThreads);
        System.out.println("ITERATIONS " + numFormat.format(TOTAL_ITERATIONS));
        System.out.println("TIME " + timeFormat.format(executionTimeMs) + "ms");
    }

    private static int askThreadCount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of threads (e.g. 1, 2, 4, 8): ");
        int threads = 4;
        if (scanner.hasNextInt()) {
            threads = scanner.nextInt();
        }
        return threads;
    }
}