import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Brute {
    private Point[][] results;
    private int index;
    private int size;

    public Brute(Point[] points, int n) {
        index = 0;
        size = n;
        results = new Point[size][];
        for (int i = 0; i < results.length; i++) {
            results[i] = new Point[4];
        }
        isCollinear(points);
    }

    private void isCollinear(Point[] points) {

        if (points.length < 4) {
            return;
        }
        Arrays.sort(points);

        for (int p = 0; p < points.length; p++)
            for (int q = p + 1; q < points.length; q++)
                for (int r = q + 1; r < points.length; r++)
                    for (int s = r + 1; s < points.length; s++) {
                        double pq = points[p].slopeTo(points[q]);
                        double pr = points[p].slopeTo(points[r]);
                        double ps = points[p].slopeTo(points[s]);
                        if (pq == pr && pr == ps) {
                            results[index][0] = points[p];
                            results[index][1] = points[q];
                            results[index][2] = points[r];
                            results[index][3] = points[s];
                            index++;
                        }
                    }
    }

    private String pointToStr() {
        String output = "";
        for (Point[] pointsArr : results) {
            if (pointsArr[0] == null) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                if (i != 3) {
                    output += pointsArr[i].toString() + " -> ";
                }
                else {
                    output += pointsArr[i].toString() + "\n";
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
        Brute collinear = new Brute(points, n);

        StdOut.println(collinear.pointToStr());
    }
}
