//package implementation;
//
//import data.Row;
//import tree.CountedBTree;
//
//public class CountedBTreeImplementation extends Implementation {
//
//    private static int ORDER = 8;
//    private CountedBTree mTree;
//
//    public CountedBTreeImplementation() {
//        super();
//        mTree = new CountedBTree(ORDER, mDBManager);
//    }
//
//    @Override
//    public void insert(Row element) {
//        mTree.insert(element);
//    }
//
//    @Override
//    public void delete(Row element) {
//        mTree.delete(element);
//    }
//
//    @Override
//    public void incrementTRanks(Row element) {
//        // Do nothing
//    }
//
//}
