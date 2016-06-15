package com.company.airporthandling;

/**
 * This class represents a runway of an airport
 */
public class Runway {
    // the id number of this runway
    private int mRunwayNumber;
    //state of the runway - free or allocated to a departing/landing flight
    private boolean mIsFree;

    /**
     * main and only constructor
     * @param runwayMumber  the id number for this runwnay
     */
    public Runway(int runwayMumber){
        //the the instance variables
        mRunwayNumber = runwayMumber;
        mIsFree = true;
    }

    /**
     * returns the id number of the runways
     * @return  runway id
     */
    public int getRunwayNumber() {
        return mRunwayNumber;
    }

    /**
     * sets the ruwnay id
     * @param mRunwayNumber runway id
     */
    public void setRunwayNumber(int mRunwayNumber) {
        this.mRunwayNumber = mRunwayNumber;
    }

    /**
     * return runways state
     * @return runways state
     */
    public boolean getIsFree() {
        return mIsFree;
    }

    /**
     * set runway state
     * @param mIsFree   runway state
     */
    public void setIsFree(boolean mIsFree) {
        this.mIsFree = mIsFree;
    }
}
