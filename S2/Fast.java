import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Fast {
    private Point[][] results;
    private int index;
    private int size;

    public Fast(Point[] points, int n) {
        index = 0;
        size = n;
        results = new Point[size][];
        for (int i = 0; i < results.length; i++) {
            results[i] = new Point[size + 1];
        }
        isCollinear(points);
    }

    private void isCollinear(Point[] points) {

        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints, 0, size);

        for (int i = 0; i < size; i++) {
            Point p = sortedPoints[i];

            Arrays.sort(points, 0, size);
            Arrays.sort(points, 0, size, p.SLOPE_ORDER);

            int start = 1;
            int end = 2;
            double slope1 = p.slopeTo(points[start]);

            boolean baseSmallest = p.compareTo(points[start]) < 0;

            while (end < size) {
                double slope2 = p.slopeTo(points[end]);
                if ((slope1 != slope2)) {
                    if (end - start >= 3 && baseSmallest) {
                        addPoints(points, start, end);
                    }
                    start = end;
                    slope1 = slope2;
                    baseSmallest = p.compareTo(points[start]) < 0;
                }
                else {
                    if (end - start >= 3 && baseSmallest && end == size - 1) {
                        addPoints(points, start, size);
                    }
                    if (p.compareTo(points[end]) > 0) {
                        baseSmallest = false;
                    }
                }
                end++;
            }
        }
    }

    private void addPoints(Point[] points, int start, int end) {

        // Hérna var ég að sort áður eftir point stærð með compareTo
        /*
        for (int i = start; i < end; i++) {
            int min = i;
            for (int j = i + 1; j < end; j++) {
                if (points[j].compareTo(points[min]) < 0)
                    min = j;
            }

            Point temp = points[min];
            points[min] = points[i];
            points[i] = temp;
        }

         */

        results[index][0] = points[0];
        int j = 1;
        for (int i = start; i < end; i++) {
            results[index][j] = points[i];
            j++;
        }
        index++;

    }

    private void sortArray() {
        for (int i = 0; i < results.length; i++) {
            if (results[i][0] == null) {
                break;
            }
            int min = i;
            for (int j = i + 1; j < results.length; j++) {
                if (results[j][0] == null) {
                    break;
                }
                if (results[j][0] == results[min][0]) {
                    double slope1 = (results[j][0]).slopeTo(results[j][1]);
                    double slope2 = (results[min][0]).slopeTo(results[min][1]);

                    if (slope1 < slope2) {
                        min = j;
                    }
                }
                else if ((results[j][0]).compareTo(results[min][0]) < 0) {
                    min = j;
                }
            }
            Point[] temp = results[min];
            results[min] = results[i];
            results[i] = temp;
        }
    }

    private String debug(Point[] points) {
        String output = "";

        for (int i = 0; i < points.length; i++) {
            output += points[i] + ", ";
        }
        return output;
    }


    private String print() {
        String output = "";
        for (int i = 0; i < results.length; i++) {
            if (results[i][0] == null) {
                continue;
            }
            for (int j = 0; j < results[i].length; j++) {
                if (results[i][j] == null) {
                    continue;
                }
                if (results[i][j + 1] != null) {
                    output += results[i][j].toString() + " -> ";
                }
                else {
                    output += results[i][j].toString() + "\n";
                }
            }
        }
        output = output.substring(0, output.length() - 1);
        return output;
    }

    public static void main(String[] args) {
        In in = new In();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        Fast collinear = new Fast(points, n);
        if (collinear.results[0][0] != null) {
            StdOut.println(collinear.print());
        }
    }
}


