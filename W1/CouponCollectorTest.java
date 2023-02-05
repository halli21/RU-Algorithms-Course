import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;


public class CouponCollectorTest {

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


    public static void main(String[] args) {
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
    }
}




