public class IntervalTree {
    static class Interval {
        int low, high;

        public Interval(int low, int high) {
            this.low = low;
            this.high = high;
        }

        public String toString() {
            return "[" + this.low + "," + this.high + "]";
        }
    }

    static class Node {
        Interval range;
        Node left, right;
        int max;

        public Node(Interval range, int max) {
            this.range = range;
            this.max = max;
        }

        public String toString() {
            return "[" + this.range.low + ", "
                    + this.range.high + "] "
                    + "max = " + this.max + "\n";
        }
    }

    public static Node insert(Node root, Interval x) {
        if (root == null) {
            return new Node(x, x.high);
        }
        if (x.low < root.range.low) {
            root.left = insert(root.left, x);
        }
        else {
            root.right = insert(root.right, x);
        }
        if (root.max < x.high) {
            root.max = x.high;
        }
        return root;
    }

    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root);
        inOrder(root.right);
    }

    public static int stabbingCount(Node root, double x) {
        if (root == null) {
            return 0;
        }
        else if ((x >= root.range.low && x <= root.range.high)) {
            return stabbingCount(root.left, x) + 1 + stabbingCount(root.right, x);
        }
        else {
            return stabbingCount(root.left, x) + stabbingCount(root.right, x);
        }
    }

    public static void main(String[] args) {
        Node root = insert(null, new Interval(15, 20));
        root = insert(root, new Interval(10, 30));
        root = insert(root, new Interval(17, 19));
        root = insert(root, new Interval(5, 20));
        root = insert(root, new Interval(12, 15));
        root = insert(root, new Interval(30, 40));

        System.out.println(
                "Inorder traversal of constructed Interval Tree is");
        inOrder(root);
        System.out.println();
        double i = 12;
        System.out.println("Searching for " + i);
        System.out.println("Count: " + stabbingCount(root, i));
    }
}
