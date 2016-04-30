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
        String createStatement = "CREATE TABLE Example (number int, trank int, hrank int);";
        executeWriteStatement(createStatement);
    }

    @Override
    public void finish() {
        String dropStatement = "DROP TABLE Example;";
        executeWriteStatement(dropStatement);
        super.finish();
    }

}
