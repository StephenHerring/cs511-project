package implementation;

import data.Row;

public class NaiveImplementation extends Implementation {

    @Override
    public void insert(Row element) {
        int tRank = element.getTRank();
        String name = element.getName();
        float gpa = element.getGPA();
        long student_id = element.getStundent_id();

        String statement = "INSERT INTO test (trank, name, gpa, student_id) VALUES ("
                + tRank + ", " + "'" + name + "'" +  "," + gpa + "," + student_id  + ");";
        mDBManager.executeWriteStatement(statement);
    }

    @Override
    public void delete(Row element) {
        int tRank = element.getTRank();
        String statement = "DELETE FROM test WHERE trank=" + tRank + ";";
        System.out.println(statement);
        mDBManager.executeWriteStatement(statement);
        decrementTRanks(element);
    }

    @Override
    public void incrementTRanks(Row element) {
        System.out.println("I am here");
        int tRank = element.getTRank();

        int maxIndex = get_highest_index();
        for (int index = maxIndex; index >=tRank; index--){
            String statement = "UPDATE test SET trank = trank + 1 WHERE trank = " + index + ";";
            mDBManager.executeWriteStatement(statement);
        }
    }

    public void decrementTRanks(Row element) {
        int tRank = element.getTRank();
        String statement = "UPDATE test SET trank = trank - 1 WHERE trank>=" + tRank + ";";
        mDBManager.executeWriteStatement(statement);
    }

    public int get_highest_index(){
       return  mDBManager.query_max("select max(trank) from test");
    }

}
