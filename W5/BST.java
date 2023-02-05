import edu.princeton.cs.algs4.StdOut;

public class BST {
    private Node root;

    public BST(double[] unsortedArray) {
        for (double num : unsortedArray) {
            put(num);
        }
        StdOut.println("height: " + height(root));
    }

    private class Node {
        private double key;
        private Node left, right;

        public Node(double key) {
            this.key = key;
        }
    }

    public void put(double key) {
        root = put(root, key);
    }

    private Node put(Node x, double key) {
        if (x == null) return new Node(key);
        int cmp = Double.compare(key, x.key);
        if (cmp < 0) x.left = put(x.left, key);
        else if (cmp > 0) x.right = put(x.right, key);
        return x;
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }


    public static void main(String[] args) {
        double[] unsortedArray1 = { 1, 3, 6, 7, 8, 4, 5, 10, 9, 2 };
        BST bst1 = new BST(unsortedArray1);

        double[] unsortedArray2 = { 5, 3, 8, 2, 4, 7, 10, 1, 6, 9 };
        BST bst2 = new BST(unsortedArray2);

    }

}
