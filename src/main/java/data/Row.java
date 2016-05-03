package data;

/**
 * trank, Name, GPA, Student_ID
 */
public class Row {

    private int mNum;
    private int mTRank;
    private int mHRank;

    public Row(int num, int tRank) {
        mNum = num;
        mTRank = tRank;
        mHRank = -1;
    }

    public Row(int num, int tRank, int hRank) {
        mNum = num;
        mTRank = tRank;
        mHRank = hRank;
    }

    public int getNum() {
        return mNum;
    }

    public int getTRank() {
        return mTRank;
    }

    public int getHRank() {
        return mHRank;
    }
}
