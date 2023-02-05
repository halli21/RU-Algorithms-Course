import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdOut;

public class PercolationQF {
    private boolean[][] twoD_arr;
    private int countOfOpenSites;
    private int Nsize;
    private QuickFindUF wqu;
    private int topRow;
    private int bottomRow;

    private int getOneDcoordinate(int row, int col) {
        return Nsize * row + col;
    }

    private void validIndex(int row, int col) {
        if (row < 0 || row >= Nsize || col < 0 || col >= Nsize) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public PercolationQF(int N) {
        twoD_arr = new boolean[N][N];
        countOfOpenSites = 0;
        Nsize = N;
        wqu = new QuickFindUF(N * N + 2); // +2 for the top and bottom
        topRow = N * N; // Get valid integers that are not already in the 1D grid
        bottomRow = N * N + 1;
    }

    public void open(int row, int col) {
        validIndex(row, col);
        int site = getOneDcoordinate(row, col);

        if (!twoD_arr[row][col]) {
            twoD_arr[row][col] = true;
            countOfOpenSites++;
        }
        if (row == 0) {
            wqu.union(site, topRow);
        } // If at top row union with top component
        if (row == Nsize - 1) {
            wqu.union(site, bottomRow);
        } // If at bottom row union with bottom component

        if (row > 0) {
            if (isOpen(row - 1, col)) {
                wqu.union(site, getOneDcoordinate(row - 1, col));
            }
        }
        if (col < Nsize - 1) {
            if (isOpen(row, col + 1)) {
                wqu.union(site, getOneDcoordinate(row, col + 1));
            }
        }
        if (col > 0) {
            if (isOpen(row, col - 1)) {
                wqu.union(site, getOneDcoordinate(row, col - 1));
            }
        }
        if (row < Nsize - 1) {
            if (isOpen(row + 1, col)) {
                wqu.union(site, getOneDcoordinate(row + 1, col));
            }

        }
    }


    public boolean isOpen(int row, int col) {
        validIndex(row, col);
        return twoD_arr[row][col];
    }

    public boolean isFull(int row, int col) {
        validIndex(row, col);
        return !twoD_arr[row][col];
    }

    public int numberOfOpenSites() {
        return countOfOpenSites;
    }

    public boolean percolates() {
        return wqu.find(topRow) == wqu.find(bottomRow);
    }

    public static void main(String[] args) {
        PercolationQF test1 = new PercolationQF(3);
        StdOut.println("Test 1:");
        StdOut.println(test1.numberOfOpenSites());
        test1.open(0, 0);
        test1.open(0, 1);
        test1.open(0, 2);
        StdOut.println(test1.numberOfOpenSites());
        StdOut.println(test1.percolates());

        PercolationQF test2 = new PercolationQF(4);
        StdOut.println("\nTest 2:");
        StdOut.println(test2.numberOfOpenSites());
        test2.open(0, 3);
        test2.open(0, 0);
        test2.open(1, 3);
        test2.open(2, 2);
        test2.open(3, 3);
        StdOut.println(test2.numberOfOpenSites());
        StdOut.println(test2.percolates());
        test2.open(2, 3);
        StdOut.println(test2.numberOfOpenSites());
        StdOut.println(test2.percolates());
    }
}
