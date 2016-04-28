package implementation;


import data.DatabaseElement;

import java.util.List;

/**
 * Created by Stephen on 4/25/2016.
 */
public abstract class Implementation {

    protected static int TOTAL_ELEMENTS = 1000;

    public long timeExecution() {
        long start = System.nanoTime();
        execute(TOTAL_ELEMENTS);
        long end = System.nanoTime();
        return end - start;
    }

    protected abstract void execute(int numElements);

    public abstract void insert(DatabaseElement element);

    public abstract List<DatabaseElement> queryByTRank(int tRank);

    public abstract void incrementTRanks(int tRank);

    public int getTotalElements() {
        return TOTAL_ELEMENTS;
    }

}
