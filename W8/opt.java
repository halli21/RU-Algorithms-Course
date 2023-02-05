import edu.princeton.cs.algs4.StdOut;

public class opt {
    private static long[] profit;
    private static long[] f;
    
    public static void construct() {
        profit = new long[] { 0, 10, 9, 13, 20, 30, 25 };
        f = new long[7];
        long max = opt(6);
        StdOut.println(max);

    }

    // Topdown
    public static long opt(int i) {
        if (i == 0) return 0;
        if (i == 1) return profit[1];
        if (f[i] == 0) f[i] = Math.max(opt(i - 1), profit[i] + opt(i - 2));
        return f[i];
    }

    public static void main(String[] args) {
        construct();
    }
}

