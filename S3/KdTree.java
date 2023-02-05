/*************************************************************************
 *************************************************************************/

import edu.princeton.cs.algs4.*;

public class KdTree {
    private Node root; // root of tree
    private int size = 0;
    private SET<Point2D> pointsInRange;
    private Node nearest;


    private static class Node {
        private Point2D p;  // the point
        private RectHV rect; // the axis-aligned rectangle corresponding to this node
        private Node lb; // the left/bottom subtree
        private Node rt; // the right/top subtree

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }

    }

    // construct an empty set of points
    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // number of points in the set
    public int size() {
        return this.size;
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        RectHV rect = new RectHV(0, 0, 1, 1);
        if (!this.contains(p))
            root = insertRec(p, root, 0, rect);
    }

    private Node insertRec(Point2D p, Node p2, int depth, RectHV rect) {
        double pValue;
        double p2Value;

        if (p2 == null) {
            size++;
            return new Node(p, rect);
        }

        if (depth % 2 == 0) {
            pValue = p.x();
            p2Value = p2.p.x();
            if (pValue < p2Value) {
                RectHV newRect = new RectHV(rect.xmin(), rect.ymin(), p2Value, rect.ymax());
                p2.lb = insertRec(p, p2.lb, depth + 1, newRect);
            } else if (pValue >= p2Value) {
                RectHV newRect = new RectHV(p2Value, rect.ymin(), rect.xmax(), rect.ymax());
                p2.rt = insertRec(p, p2.rt, depth + 1, newRect);
            }
        } else {
            pValue = p.y();
            p2Value = p2.p.y();
            if (pValue < p2Value) {
                RectHV newRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p2Value);
                p2.lb = insertRec(p, p2.lb, depth + 1, newRect);
            } else if (pValue >= p2Value) {
                RectHV newRect = new RectHV(rect.xmin(), p2Value, rect.xmax(), rect.ymax());
                p2.rt = insertRec(p, p2.rt, depth + 1, newRect);
            }
        }
        return p2;
    }


    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return containsRec(p, root, 0);
    }

    private boolean containsRec(Point2D p, Node p2, int depth) {
        double pValue;
        double p2Value;

        if (p2 == null)
            return false;
        else if (p.compareTo(p2.p) == 0)
            return true;
        else {
            if (depth % 2 == 0) {
                pValue = p.x();
                p2Value = p2.p.x();
            } else {
                pValue = p.y();
                p2Value = p2.p.y();
            }

            if (pValue < p2Value)
                return containsRec(p, p2.lb, depth + 1);
            else
                return containsRec(p, p2.rt, depth + 1);
        }
    }


    // draw all of the points to standard draw
    public void draw() {
        draw(root, 0);
    }

    private void draw(Node node, int depth) {
        if (node == null) return;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.p.draw();

        if (depth % 2 == 0) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
        }
        draw(node.lb, depth + 1);
        draw(node.rt, depth + 1);
    }


    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        pointsInRange = new SET<Point2D>();
        rangeRec(rect, root);
        return pointsInRange;
    }

    private void rangeRec(RectHV rect, Node p2) {
        if (p2 == null) {
            return;
        }
        if (rect.intersects(p2.rect)) {
            if (rect.contains(p2.p))
                pointsInRange.add(p2.p);
            rangeRec(rect, p2.lb);
            rangeRec(rect, p2.rt);
        }
    }


    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        nearest = root;
        nearestRec(p, root, 0);
        return nearest.p;
    }

    private void nearestRec(Point2D p, Node p2, int depth) {

        if (p2 == null) {
            return;
        }


        if (p2.rect.distanceSquaredTo(p) < nearest.p.distanceSquaredTo(p)) {
            if (p2.p.distanceSquaredTo(p) < nearest.p.distanceSquaredTo(p)) {
                nearest = p2;
            }

            Node sameSide;
            Node otherSide;

            if ((depth % 2 == 0 && (p.x() < p2.p.x())) || (depth % 2 == 1 && (p.y() < p2.p.y()))) {
                sameSide = p2.lb;
                otherSide = p2.rt;
            } else {
                sameSide = p2.rt;
                otherSide = p2.lb;
            }

            nearestRec(p, sameSide, depth + 1);
            if (otherSide != null)
                if (otherSide.rect.distanceSquaredTo(p) < nearest.p.distanceSquaredTo(p))
                    nearestRec(p, otherSide, depth + 1);
        }
    }


    /*******************************************************************************
     * Test client
     ******************************************************************************/
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }
        kdtree.draw();
    }
}
