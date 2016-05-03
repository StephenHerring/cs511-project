//package tree;
//
//import data.Row;
//import db.DBManager;
//
///**
// * Much of the implementation was aided by https://github.com/JPWKU/BTree
// */
//public class CountedBTree {
//
//    private Node mRoot;
//    private DBManager mDBManager;
//    private int mOrder;
//
//    public CountedBTree(int order, DBManager dbManager) {
//        this.mOrder = order;
//        mRoot = new Node(order, null);
//        mDBManager = dbManager;
//    }
//
//    public Node search(Node root, int value) {
//        int index = 0;
//        while (index < root.getKeyCount() && value > root.getValue(index)) {
//            index++;
//        }
//
//        if (index <= root.getKeyCount() && value == root.getValue(index)) {
//            return root;
//        } else if (root.isLeaf()) {
//            return null;
//        } else {
//            return search(root.getChild(index), value);
//        }
//    }
//
//    public void split(Node left, Node right, int index) {
//
//        Node splitNode = new Node(mOrder, null);
//
//        splitNode.setLeaf(right.isLeaf());
//        splitNode.setKeyCount(mOrder - 1);
//
//        for (int i = 0; i < mOrder - 1; i++) {
//            splitNode.setValue(i, right.getValue(i + mOrder));
//        }
//
//        if (!right.isLeaf()) {
//            for (int i = 0; i < mOrder; i++) {
//                splitNode.setChild(i, right.getChild(i + mOrder));
//            }
//        }
//
//        right.setKeyCount(mOrder - 1);
//
//        for (int i = left.getKeyCount(); i > index; i--) {
//            left.setChild(i+1, left.getChild(i));
//        }
//
//        left.setChild(index+1, splitNode);
//
//        for (int i = left.getKeyCount()-1; i > index; i--) {
//            left.setValue(i+1, left.getValue(i));
//        }
//
//        left.setValue(index, right.getValue(mOrder -1));
//
//        right.setValue(mOrder -1, 0);
//
//        for (int i = 0; i < mOrder - 1; i++) {
//            right.setValue(i + mOrder, 0);
//        }
//
//        left.setKeyCount(left.getKeyCount() + 1);
//    }
//
//    public void nonemptyInsert(Node node, int key, Row row) {
//        int index = node.getKeyCount();
//
//        if (node.isLeaf()) {
//            while(index >= 1 && key < node.getValue(index-1)) {
//                node.setValue(index, node.getValue(index-1));
//                index--;
//            }
//
//            node.setValue(index, key);
//            int count = node.getKeyCount();
//            node.setKeyCount(count+1);
//
//          //  String statement = "INSERT INTO Example (number, trank) VALUES ("
//           //         + row.getNum() + ", " + row.getTRank() + ");";
//            mDBManager.executeWriteStatement(statement);
//
//        } else {
//            int i = 0;
//            while (i < node.getKeyCount() && key > node.getValue(i)) {
//                i++;
//            }
//
//            if (node.getChild(i).getKeyCount() == mOrder * 2 - 1)
//            {
//                split(node, node.getChild(i), i);
//
//                if (key > node.getValue(i)) {
//                    i++;
//                }
//            }
//            nonemptyInsert(node.getChild(i), key, row);
//            int childCount = node.getChildCount(i);
//            node.setChildCount(i, childCount+1);
//        }
//    }
//
//    public void insert(Row row) {
//
//        int key = row.getTRank();
//        Node root = mRoot;
//
//        if (root.getKeyCount() == 2 * mOrder - 1) {
//            Node node = new Node(mOrder, null);
//            mRoot = node;
//            node.setLeaf(false);
//            node.setKeyCount(0);
//            node.setChild(0, root);
//            split(node, root, 0);
//            nonemptyInsert(node, key, row);
//        } else {
//            nonemptyInsert(root, key, row);
//        }
//    }
//
//    public void delete(Row row) {
//
//        int key = row.getTRank();
//
//        Node temp = search(mRoot, key);
//
//        if (temp.isLeaf() && temp.getKeyCount() > mOrder - 1) {
//            int i = 0;
//
//            while (key > temp.getValue(i)) {
//                i++;
//            }
//
//            for (int j = i; j < 2 * mOrder - 2; j++) {
//                temp.setValue(j, temp.getValue(j+1));
//            }
//            temp.setKeyCount(temp.getKeyCount()-1);
//            decrementChildCounts(temp.getParent(), temp);
//        }
//    }
//
//    public void decrementChildCounts(Node parent, Node child) {
//
//        for (int i = 0; i < parent.getKeyCount(); i++) {
//            if (parent.getChild(i) == child) {
//                parent.setChildCount(i, parent.getChildCount(i) - 1);
//                decrementChildCounts(parent.getParent(), parent);
//            }
//        }
//
//        if (parent == mRoot) {
//            return;
//        }
//    }
//
//}
