package implementation;

import data.Row;

public class NaiveImplementation extends Implementation {

    @Override
    public void insert(Row element) {
        int number = element.getNum();
        int tRank = element.getTRank();

        String statement = "INSERT INTO Example (number, trank) VALUES ("
                + number + ", " + tRank + ");";
        mIntegerDBManager.executeWriteStatement(statement);
    }

    @Override
    public void delete(Row element) {
        int number = element.getNum();
        int tRank = element.getTRank();

        String statement = "DELETE FROM Example WHERE trank=" + tRank + ";";
        mIntegerDBManager.executeWriteStatement(statement);
    }

    @Override
    public void incrementTRanks(Row element) {
        int tRank = element.getTRank();
        String statement = "UPDATE Example SET trank = trank + 1 WHERE trank>=" + tRank + ";";
        mIntegerDBManager.executeWriteStatement(statement);
    }

}
