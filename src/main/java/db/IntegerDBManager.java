package db;


public class IntegerDBManager extends DBManager {

    public IntegerDBManager() {
        super();
    }

    @Override
    public void start() {
        super.start();
        String dropStatement = "DROP TABLE Example;";
        executeWriteStatement(dropStatement);
        String createStatement = "CREATE TABLE Example (number int, trank int);";
        executeWriteStatement(createStatement);
    }

    @Override
    public void finish() {
        String dropStatement = "DROP TABLE Example;";
        executeWriteStatement(dropStatement);
        super.finish();
    }

    /*
    @Override
    public boolean insert(DatabaseElement element) {
        int number = element.getNum();
        int tRank = element.getTRank();

        String statement = "INSERT INTO Example (number, trank) VALUES ("
                + number + ", " + tRank + ");";
        return executeWriteStatement(statement);
    }

    @Override
    public boolean incrementTRanks(int tRank) {

        String statement = "UPDATE Example SET trank = trank + 1 WHERE number>=" + tRank + ";";
        return executeWriteStatement(statement);
    }

    @Override
    public boolean queryByTRank(int tRank) {
        return false;
    }
    */
}
