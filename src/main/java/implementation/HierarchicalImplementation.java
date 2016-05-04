package implementation;

import data.Lookup;
import data.Row;
import db.IntegerDBManager;

import java.util.List;

public class HierarchicalImplementation extends Implementation {

    private static int BUCKET_SIZE = 32;

    @Override
    protected void execute() {
        for (int i = 0; i < TOTAL_ELEMENTS; i++) {
            insertTestElement(i);
        }
    }

    @Override
    public void insert(Row element) {
        int hRank = element.getTRank();
        int tRank = hRank * BUCKET_SIZE;
        String name = element.getName();
        float gpa = element.getGPA();
        long student_id = element.getStundent_id();

        // If there is anything in the bucket, increment elements
        if (queryByTRank(tRank).size() > 0) {
            incrementTRanks(element);
        }

        // Insert into bucket
        String statement = "INSERT INTO test (trank, name, gpa, student_id) VALUES ("
                + tRank + ", " + "'" + name + "'" +  "," + gpa + "," + student_id  + ");";
        mDBManager.executeWriteStatement(statement);

        // Update table of elements
        insertLookupElement(tRank);
    }

    @Override
    public void delete(Row element) {
        int lRank = element.getTRank();
        int tRank = deleteLookupElement(lRank);
        String statement = "DELETE FROM Example WHERE trank=" + tRank + ";";
        mDBManager.executeWriteStatement(statement);
    }

    protected void insertTestElement(int hRank) {
        Row row = new Row(hRank, "Stephen", 4.0f, 1234l);
        insert(row);
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
        return mDBManager.query(query);
    }

    private void incrementBucket(int hRank) {
        int tRank = hRank * BUCKET_SIZE;
        // Increment tuple rank of all elements in current bucket
        String statement = "UPDATE Example SET trank = trank + 1 WHERE trank>=" + tRank
                + " AND trank<" + (tRank + BUCKET_SIZE) + ";";
        mDBManager.executeWriteStatement(statement);

        // If we have more elements in the bucket than can fit, transfer any spillover elements to next bucket
        String spilloverStatement = "UPDATE Example SET hrank = hrank + 1 WHERE hrank=" +
                hRank + " AND trank >=" + (hRank * BUCKET_SIZE + BUCKET_SIZE) + ";";
        mDBManager.executeWriteStatement(spilloverStatement);
    }


    private void insertLookupElement(int tRank) {
        // Perform lookup to find lrank to use
        List<Lookup> lookupElements = mDBManager.queryLookup("SELECT * FROM lookup where trank < " + tRank +
                " ORDER BY trank DESC LIMIT 1;");
        int lrank = 1;

        if (lookupElements.size() > 0) {
            lrank = lookupElements.get(0).getLRank() + 1;
        }

        String updateStatement = "UPDATE lookup SET lrank = lrank + 1 WHERE trank>=" + tRank + ";";
        mDBManager.executeWriteStatement(updateStatement);

        String insertStatement = "INSERT INTO lookup (lrank, trank) VALUES (" + lrank + ", " + tRank + ");";
        mDBManager.executeWriteStatement(insertStatement);
    }

    private int deleteLookupElement(int lRank) {
        List<Lookup> elements = mDBManager.queryLookup("SELECT * FROM lookup WHERE lrank=" + lRank + ";");
        String statement = "DELETE FROM lookup WHERE lrank=" + lRank + ";";
        mDBManager.executeWriteStatement(statement);

        String updateStatement = "UPDATE lookup SET lrank = lrank - 1 WHERE lrank>=" + lRank + ";";
        mDBManager.executeWriteStatement(updateStatement);

        if (elements.size() > 0) {
            return elements.get(0).getTRank();
        } else {
            return -1;
        }
    }

}
