package data;

/**
 * trank, Name, GPA, Student_ID
 */
public class Row {

    private int mTRank;
    private int mHRank;

    private int tRank;
    private String name;
    private float gpa;
    private long student_id;

    public Row(String name, float gpa, long student_id, int tRank, int hRank) {
        this.gpa = gpa;
        this.student_id = student_id;
        this.name = name;
        mTRank = tRank;
        mHRank = hRank;
    }
    public Row(int tRank, String name, float gpa, long student_id){
        this.tRank = tRank;
        this.name = name;
        this.gpa = gpa;
        this.student_id = student_id;
    }

    public int getTRank() {
        return this.tRank;
    }

    public int getHRank() {
        return mHRank;
    }

    public String getName() { return  this.name;}

    public float getGPA() { return  this.gpa;}

    public long getStundent_id() {return this.student_id;}

}
