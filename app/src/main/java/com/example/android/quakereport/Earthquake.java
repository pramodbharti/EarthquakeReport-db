package com.example.android.quakereport;

/**
 * Created by pramod on 23/12/17.
 */

public class Earthquake {
    private double mMag;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;
    private int mFelt;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url, int felt) {
        mMag = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
        mFelt = felt;
    }


    public double getMagnitude() {
        return mMag;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }

    public int getFelt() {
        return mFelt;
    }
}
