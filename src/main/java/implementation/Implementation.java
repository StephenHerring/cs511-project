package implementation;


import data.Row;
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
            ;//insertWithRandomTRank(i);
        }
    }

    public List<Row> queryByTRank(int tRank) {
        String query = "SELECT * FROM Example WHERE trank=" + tRank + ";";
        return mIntegerDBManager.query(query);
    }
    public List<Row> query(String qstmt){
        return mIntegerDBManager.query(qstmt);
    }

    public abstract void insert(Row element);

    public abstract void delete(Row element);

    public abstract void incrementTRanks(Row element);

    public int getTotalElements() {
        return TOTAL_ELEMENTS;
    }

    protected void insertWithRandomTRank(String name, float gpa,long student_id) {
        int tRank = (int) (Math.random() * TOTAL_ELEMENTS);

        Row element = new Row(tRank, name, gpa, student_id);
        if (queryByTRank(tRank).size() > 0) {
            incrementTRanks(element);
        }
        insert(element);
    }
}
