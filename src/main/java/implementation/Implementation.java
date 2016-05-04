package implementation;


import data.Row;
import db.DBManager;

import java.util.List;

public abstract class Implementation {

    protected static int TOTAL_ELEMENTS = 1024;

    protected DBManager mDBManager;

    public Implementation() {
        mDBManager = new DBManager();
    }

    public long timeExecution() {
        long start = System.nanoTime();
        execute();
        long end = System.nanoTime();
        return end - start;
    }

    protected void execute() {
        for (int i = 0; i < TOTAL_ELEMENTS; i++) {
            ;//insertWithRandomTRank(i);
        }
    }

    public List<Row> queryByTRank(int tRank) {
        String query = "SELECT * FROM test WHERE trank=" + tRank + ";";
        return mDBManager.query(query);
    }
    public List<Row> query(String qstmt){
        return mDBManager.query(qstmt);
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
