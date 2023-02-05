/****************************************************************************
 *  Compilation:  javac PointSET.java
 *  Execution:
 *  Dependencies:
 *  Author:
 *  Date:
 *
 *  Data structure for maintaining a set of 2-D points,
 *    including rectangle and nearest-neighbor queries
 *
 *************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class PointSET {
    private SET<Point2D> set;

    // construct an empty set of points
    public PointSET() {
        set = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        set.add(p);
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return set.contains(p);
    }


    // draw all of the points to standard draw
    public void draw() {
        for (Point2D p : set) {
            p.draw();
        }
    }


    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> pointsInRange = new SET<Point2D>();

        for (Point2D p : set) {
            if (rect.contains(p)) {
                pointsInRange.add(p);
            }
        }
        return pointsInRange;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        Point2D nearest = null;
        for (Point2D p2 : set) {
            if (nearest == null) {
                nearest = p2;
                continue;
            }
            if (p.equals(p2)) {
                continue;
            }
            double distanceNearest = p.distanceSquaredTo(nearest);
            double distanceP2 = p.distanceSquaredTo(p2);
            if (distanceP2 < distanceNearest) {
                nearest = p2;
            }
        }
        return nearest;
    }
    

    public static void main(String[] args) {
        PointSET pset = new PointSET();
        pset.insert(new Point2D(0.2, 0.1));
        pset.insert(new Point2D(0.3, 0.5));
        pset.insert(new Point2D(0.3, 0.1));
        pset.insert(new Point2D(0.4, 0.5));
        pset.insert(new Point2D(0.2, 0.1));  //Endurtekning
        pset.insert(new Point2D(0.4, 0.2));
        StdOut.println("Nearest (0.4, 0.5): ");
        StdOut.println(pset.nearest(new Point2D(0.4, 0.5)));
        RectHV rect = new RectHV(0.3, 0.2, 0.4, 0.5);


        // Inside rectangle
        Iterable<Point2D> rangeSet = pset.range(rect);

        int ptcount = 0;
        for (Point2D p : rangeSet)
            ptcount++;
        Point2D[] ptarr = new Point2D[ptcount];
        int j = 0;
        for (Point2D p : rangeSet) {
            ptarr[j] = p;
            j++;
        }
        Arrays.sort(ptarr);
        StdOut.println("Inside rectangle: ");
        for (j = 0; j < ptcount; j++)
            StdOut.println(ptarr[j]);

    }

}
