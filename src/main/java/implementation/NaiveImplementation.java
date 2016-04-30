package implementation;

import data.DatabaseElement;

public class NaiveImplementation extends Implementation {

    @Override
    public void insert(DatabaseElement element) {
        int number = element.getNum();
        int tRank = element.getTRank();

        String statement = "INSERT INTO Example (number, trank, hrank) VALUES ("
                + number + ", " + tRank + ", " + -1 + ");";
        mIntegerDBManager.executeWriteStatement(statement);
    }

    @Override
    public void incrementTRanks(DatabaseElement element) {
        int tRank = element.getTRank();
        String statement = "UPDATE Example SET trank = trank + 1 WHERE trank>=" + tRank + ";";
        mIntegerDBManager.executeWriteStatement(statement);
    }

    @Override
    protected void insertWithRandomTRank(int num) {
        int tRank = (int) (Math.random() * TOTAL_ELEMENTS);

        DatabaseElement element = new DatabaseElement(num, tRank);
        if (queryByTRank(tRank).size() > 0) {
            incrementTRanks(element);
        }
        insert(element);
    }

}
