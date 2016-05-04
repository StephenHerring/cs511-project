package db;


public class IntegerDBManager extends DBManager {

    public IntegerDBManager() {
        super();
    }

    @Override
    public void start() {
        super.start();
        String dropStatement = "DROP TABLE test;";
        executeWriteStatement(dropStatement);
        String dropLookupStatement = "DROP TABLE lookup;";
        executeWriteStatement(dropLookupStatement);
        String createTest = "CREATE TABLE test (trank int, name varchar(255), gpa float, student_id bigint)";
        executeWriteStatement(createTest);
        String createStatement = "CREATE TABLE lookup (lrank int, trank int);";
        executeWriteStatement(createStatement);
    }

    @Override
    public void finish() {
        String dropStatement = "DROP TABLE lookup;";
        executeWriteStatement(dropStatement);
        dropStatement = "DROP TABLE test;";
        executeWriteStatement(dropStatement);
        super.finish();
    }

}
