package implementation;

import data.DatabaseElement;
import db.DBManager;
import db.IntegerDBManager;

import java.util.List;

/**
 * Created by Stephen on 4/25/2016.
 */
public class NaiveImplementation extends Implementation {

    DBManager mIntegerDBManager;

    public NaiveImplementation() {
        mIntegerDBManager = new IntegerDBManager();
    }

    @Override
    protected void execute(int numElements) {
        for (int i = 0; i < numElements; i++) {
            insertWithRandomTRank(i);
        }
    }

    @Override
    public void insert(DatabaseElement element) {
        int number = element.getNum();
        int tRank = element.getTRank();

        String statement = "INSERT INTO Example (number, trank) VALUES ("
                + number + ", " + tRank + ");";
        mIntegerDBManager.executeWriteStatement(statement);
    }

    @Override
    public List<DatabaseElement> queryByTRank(int tRank) {
        String query = "SELECT * FROM Example WHERE trank=" + tRank + ";";
        return mIntegerDBManager.query(query);
    }

    @Override
    public void incrementTRanks(int tRank) {
        String statement = "UPDATE Example SET trank = trank + 1 WHERE number>=" + tRank + ";";
        mIntegerDBManager.executeWriteStatement(statement);
    }

    private void insertWithRandomTRank(int num) {
        int tRank = (int) (Math.random() * TOTAL_ELEMENTS);

        if (queryByTRank(tRank).size() > 0) {
            incrementTRanks(tRank);
        }
        DatabaseElement element = new DatabaseElement(num, tRank);
        insert(element);
    }

}
