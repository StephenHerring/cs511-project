package implementation;

import data.Row;

import java.util.List;

public class HierarchicalImplementation extends Implementation {

    private static int BUCKET_SIZE = 32;

    @Override
    public void insert(Row element) {
        int hRank = element.getTRank();
        int tRank = hRank * BUCKET_SIZE;
        String name = element.getName();
        float gpa = element.getGPA();
        long student_id = element.getStundent_id();

        String statement = "INSERT INTO test (trank, name, gpa, student_id) VALUES ("
                + tRank + ", " + "'" + name + "'" +  "," + gpa + "," + student_id  + ");";
        mIntegerDBManager.executeWriteStatement(statement);

        if (queryByTRank(tRank).size() > 0) {
            incrementTRanks(element);
        }
    }

    @Override
    public void delete(Row element) {
        int tRank = element.getTRank();

        String statement = "DELETE FROM Example WHERE trank=" + tRank + ";";
        mIntegerDBManager.executeWriteStatement(statement);
    }

    protected void insertWithRandomTRank(int num) {
//        int hRank = (int) (Math.random() * TOTAL_ELEMENTS);
//
//        Row element = new Row(num, tRank, hRank);
//
//        insert(element);
    }

    @Override
    public void incrementTRanks(Row element) {
        int hRank = element.getHRank();
        if (queryByHRank(hRank).size() < 16) {
            // If bucket is not full, we simply increment elements in bucket
            incrementBucket(hRank);
        } else {
            // Otherwise, we have to increment all elements the next bucket, then
            // increment all elements in current bucket (accounting for spillover)
            Row nextBucket = new Row("", -1f, -1, -1, hRank + 1);
            incrementTRanks(nextBucket);
            incrementBucket(hRank);
        }
    }


    private List<Row> queryByHRank(int hRank) {
        int tRank = hRank * BUCKET_SIZE;

        String query = "SELECT * FROM Example WHERE trank>=" + tRank +
                " AND trank<" + (tRank + BUCKET_SIZE) + ";";
        return mIntegerDBManager.query(query);
    }

    private void incrementBucket(int hRank) {
        int tRank = hRank * BUCKET_SIZE;
        // Increment tuple rank of all elements in current bucket
        String statement = "UPDATE Example SET trank = trank + 1 WHERE trank>=" + tRank
                + " AND trank<" + (tRank + BUCKET_SIZE) + ";";
        mIntegerDBManager.executeWriteStatement(statement);

        // If we have more elements in the bucket than can fit, transfer any spillover elements to next bucket
        String spilloverStatement = "UPDATE Example SET hrank = hrank + 1 WHERE hrank=" +
                hRank + " AND trank >=" + (hRank * BUCKET_SIZE + BUCKET_SIZE) + ";";
        mIntegerDBManager.executeWriteStatement(spilloverStatement);
    }

}
