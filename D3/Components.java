import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Components {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of elements in subtree rooted at i

    public Components(int n) {
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


    public static void main(String[] args) {
        int edges = 0;
        int n = Integer.parseInt(args[0]);
        Components uf = new Components(n);
        boolean end = false;
        while (!end) {
            int p = StdRandom.uniform(n);
            int q = StdRandom.uniform(n);

            int result = uf.union(p, q);

            if (result != -1) {
                StdOut.println(p + " " + q + ", size of combined component: " + result);
                edges++;
            }
            if (result > n / 2) {
                StdOut.println("biggest component size is " + result + " and it needed " + edges
                                       + " edges to emerge");
                end = true;
            }
        }
    }

}
