package tree;

/**
 * Created by Stephen on 5/1/2016.
 */
public class Node {

    private int order;
    private int[] keys;
    private Node[] children;

    private boolean isLeaf;
    private int keyCount;

    // TODO: Implement this for Counted B Tree
    private int[] mChildCounts;

    private Node parent;

    public Node(int order, Node parent) {
        this.order = order;
        this.parent = parent;
        keys = new int[2 * order - 1];
        children = new Node[2 * order];

        // All nodes start out as leaf
        isLeaf = true;
        keyCount = 0;

        mChildCounts = new  int[children.length];
    }

    public Node getChild(int index) {
        return children[index];
    }

    public void setChild(int index, Node child) {
        children[index] = child;
    }

    public int getValue(int index) {
        return keys[index];
    }

    public void setValue(int index, int value) {
        keys[index] = value;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public void setKeyCount(int keyCount) {
        this.keyCount = keyCount;
    }

    public int getChildCount(int index) {
        return mChildCounts[index];
    }

    public void setChildCount(int index, int count) {
        mChildCounts[index] = count;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
