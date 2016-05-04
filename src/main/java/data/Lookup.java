package data;

/**
 * Created by Stephen on 5/4/2016.
 */
public class Lookup {

    private int mLRank;
    private int mTRank;

    public Lookup(int lRank, int tRank) {
        mLRank = lRank;
        mTRank = tRank;
    }

    public int getLRank() {
        return mLRank;
    }

    public int getTRank() {
        return mTRank;
    }

}
