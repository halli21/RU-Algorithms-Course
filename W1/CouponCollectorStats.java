import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class CouponCollectorStats {
    private double mean;
    private double stddev;


    public static int couponCollectorTest(int N) {
        //Runs the Coupon collector test
        boolean[] arr = new boolean[N];
        int card_num = 0;
        int iteration = 0;
        while (card_num < N) {
            iteration += 1;
            int id = StdRandom.uniform(N);

            if (!arr[id]) {
                arr[id] = true;
                card_num += 1;
            }
        }
        return iteration;
    }

    public CouponCollectorStats(int N, int T) {
        //Collects stats about the test
        double[] times_arr = new double[T];
        for (int i = 0; i < T; i++) {
            Stopwatch watch = new Stopwatch();
            CouponCollectorStats.couponCollectorTest(N);
            double timespent = watch.elapsedTime();
            times_arr[i] = timespent;
        }
        mean = StdStats.mean(times_arr);
        stddev = StdStats.stddev(times_arr);
    }

    public double mean() {
        //Finds the mean of an array
        return mean;
    }

    public double stddev() {
        //Finds the standard deviation of an array
        return stddev;
    }


    public static void main(String[] args) {
        //a) Test for CouponCollectorTest
        StdOut.println("a) Testing CouponCollectorTest" + "\n");
        int[] cards = { 100000, 1000000 };
        for (int card : cards) {
            for (int i = 0; i < 3; i++) {
                StdOut.println("Card quantity(N): " + card + ", Test no: " + (i + 1));
                Stopwatch watch = new Stopwatch();
                int iterations = couponCollectorTest(card);
                double timespan = watch.elapsedTime();
                StdOut.println("Iterations: " + iterations);
                StdOut.println("Time: " + timespan + " s" + "\n");
            }
        }


        // b-c) Test for CouponCollectorStats
        StdOut.println("b) Testing CouponCollectorStats" + "\n");
        int N = 100000;
        int[] T = { 10, 100, 1000 };
        for (int j : T) {
            StdOut.println("Cards (N): " + N + ", Number of tests (T): " + j);
            Stopwatch watch = new Stopwatch();
            CouponCollectorStats stats = new CouponCollectorStats(N, j);
            double timespan = watch.elapsedTime();
            StdOut.println("Mean: " + stats.mean + "s");
            StdOut.println("Stddev: " + stats.stddev + "s");
            StdOut.println("Time performing " + j + " tests: " + timespan + " s \n");
        }
    }
}

