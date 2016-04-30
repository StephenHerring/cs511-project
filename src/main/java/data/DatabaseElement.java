package data;

/**
 * Created by Stephen on 4/23/2016.
 */
public class DatabaseElement {

    private int mNum;
    private int mTRank;
    private int mHRank;

    public DatabaseElement(int num, int tRank) {
        mNum = num;
        mTRank = tRank;
        mHRank = -1;
    }

    public DatabaseElement(int num, int tRank, int hRank) {
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
