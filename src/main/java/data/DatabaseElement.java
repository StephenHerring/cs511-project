package data;

/**
 * Created by Stephen on 4/23/2016.
 */
public class DatabaseElement {

    private int mNum;
    private int mTRank;

    public DatabaseElement(int num, int tRank) {
        mNum = num;
        mTRank = tRank;
    }

    public int getNum() {
        return mNum;
    }

    public int getTRank() {
        return mTRank;
    }
}
