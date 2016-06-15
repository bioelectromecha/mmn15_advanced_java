package com.company.flighthandling;

import com.company.airporthandling.AirPort;

/**
 * This class represents a flights direction
 */
public class FlightDirection {
    //departure airport
    private AirPort mDeparture;
    //arrival airport
    private AirPort mLanding;

    /**
     * main and only constructor
     * @param departure departure airport
     * @param landing arrival airport
     */
    public FlightDirection(AirPort departure, AirPort landing ){
        // instantiate instance variables
        mDeparture = departure;
        mLanding = landing;
    }

    /**
     * returns the departure airport
     * @return departure airport
     */
    public AirPort getDeparture() {
        return mDeparture;
    }


    /**
     * sets the departure airport
     * @param  departure airport
     */
    public void setDeparture(AirPort mDeparture) {
        this.mDeparture = mDeparture;
    }

    /**
     * returns the landing airport
     * @return landing airport
     */
    public AirPort getLanding() {
        return mLanding;
    }

    /**
     * sets the landing airport
     * @param landing airport
     */
    public void setLanding(AirPort mLanding) {
        this.mLanding = mLanding;
    }
}
