package implementation;

import data.DatabaseElement;

import java.util.List;

public class HierarchicalImplementation extends Implementation {

    private static int BUCKET_SIZE = 16;

    @Override
    public void insert(DatabaseElement element) {
        int number = element.getNum();
        int hRank = element.getTRank();
        int tRank = hRank * BUCKET_SIZE;

        String statement = "INSERT INTO Example (number, trank, hrank) VALUES ("
                + number + ", " + tRank + "," + hRank + ");";
        mIntegerDBManager.executeWriteStatement(statement);
    }

    @Override
    protected void insertWithRandomTRank(int num) {
        int hRank = (int) (Math.random() * TOTAL_ELEMENTS);
        int tRank = hRank * BUCKET_SIZE;

        DatabaseElement element = new DatabaseElement(num, tRank, hRank);
        if (queryByTRank(tRank).size() > 0) {
            incrementTRanks(element);
        }

        insert(element);
    }

    @Override
    public void incrementTRanks(DatabaseElement element) {
        int hRank = element.getHRank();
        if (queryByHRank(hRank).size() < 16) {
            // If bucket is not full, we simply increment elements in bucket
            incrementBucket(hRank);
        } else {
            // Otherwise, we have to increment all elements the next bucket, then
            // increment all elements in current bucket (accounting for spillover)
            DatabaseElement nextBucket = new DatabaseElement(-1, -1, hRank + 1);
            incrementTRanks(nextBucket);
            incrementBucket(hRank);
        }
    }


    private List<DatabaseElement> queryByHRank(int hRank) {
        String query = "SELECT * FROM Example WHERE hrank=" + hRank + ";";
        return mIntegerDBManager.query(query);
    }

    private void incrementBucket(int hRank) {
        // Increment tuple rank of all elements in current bucket
        String statement = "UPDATE Example SET trank = trank + 1 WHERE hrank=" + hRank + ";";
        mIntegerDBManager.executeWriteStatement(statement);

        // If we have more elements in the bucket than can fit, transfer any spillover elements to next bucket
        String spilloverStatement = "UPDATE Example SET hrank = hrank + 1 WHERE hrank=" +
                hRank + " AND trank >=" + (hRank * BUCKET_SIZE + 16) + ";";
        mIntegerDBManager.executeWriteStatement(spilloverStatement);
    }

}
