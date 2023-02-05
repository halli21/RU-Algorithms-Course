import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class GiantComponentStats {
    private double mean;
    private double stddev;
    private int[] parent;   // parent[i] = parent of i
    private int[] size;
    private int[] edges;

    public void components(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int union(int p, int q) {
        while (p != parent[p])
            p = parent[p];
        while (q != parent[q])
            q = parent[q];
        int rootP = p;
        int rootQ = q;
        if (rootP == rootQ) return -1;

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];

            return size[rootQ];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];

            return size[rootP];
        }
    }

    public GiantComponentStats(int N, int T) {
        edges = new int[T];
        for (int i = 0; i < T; i++) {
            int countEdges = 0;

            Components uf = new Components(N);
            boolean end = false;
            while (!end) {
                int p = StdRandom.uniform(N);
                int q = StdRandom.uniform(N);

                int result = uf.union(p, q);

                if (result != -1) {
                    countEdges++;
                }
                if (result > N / 2) {
                    end = true;
                }
            }
            edges[i] = countEdges;
        }
        mean = StdStats.mean(edges);
        stddev = StdStats.stddev(edges);
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
        int[] N = { 10000, 20000, 40000, 80000, 160000, 320000, 640000, 1280000 };
        int T = 100;
        double doublingRatioPrev = 0;
        StdOut.println("Testing Component Stats");
        for (int j : N) {
            StdOut.println("\nVertices (N): " + j + ", Number of tests (T): " + T);
            GiantComponentStats stats = new GiantComponentStats(j, T);
            StdOut.println("Mean: " + stats.mean);
            StdOut.println("Stddev: " + stats.stddev);

            if (doublingRatioPrev == 0) {
                StdOut.println("No doubling ratio for the first test");
                doublingRatioPrev = stats.mean;
            }
            else {
                double doublingRatio = stats.mean / doublingRatioPrev;
                StdOut.println("Doubling Ratio: " + doublingRatio);
                doublingRatioPrev = stats.mean;
            }

        }

    }
}
