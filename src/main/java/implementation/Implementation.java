package implementation;


import data.DatabaseElement;
import db.DBManager;
import db.IntegerDBManager;

import java.util.List;

public abstract class Implementation {

    protected static int TOTAL_ELEMENTS = 1024;

    protected DBManager mIntegerDBManager;

    public Implementation() {
        mIntegerDBManager = new IntegerDBManager();
    }

    public long timeExecution() {
        long start = System.nanoTime();
        execute(TOTAL_ELEMENTS);
        long end = System.nanoTime();
        return end - start;
    }

    private void execute(int numElements) {
        for (int i = 0; i < numElements; i++) {
            insertWithRandomTRank(i);
        }
    }

    public List<DatabaseElement> queryByTRank(int tRank) {
        String query = "SELECT * FROM Example WHERE trank=" + tRank + ";";
        return mIntegerDBManager.query(query);
    }

    public abstract void insert(DatabaseElement element);

    public abstract void incrementTRanks(DatabaseElement element);

    public int getTotalElements() {
        return TOTAL_ELEMENTS;
    }

    protected abstract void insertWithRandomTRank(int num);

}
