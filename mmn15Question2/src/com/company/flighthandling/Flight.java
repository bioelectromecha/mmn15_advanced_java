package com.company.flighthandling;

import com.company.airporthandling.AirPort;

/**
 * This class represents a single flight thread
 */
public class Flight extends Thread {
    // the id number of this flight
    private int mFlightNum;
    // the airport the flight is departing from
    private AirPort mDepartureAirport;
    // the airport the flight is landing at
    private AirPort mLandingAirport;

    // delay period per each flight stage
    private static final int DEPART_DELAY = 2000;
    private static final int LAND_DELAY = 2000;
    private static final int FLIGHT_DELAY = 2000;


    /**
     * main and only constructor
     * @param flightNum flight id number
     * @param departureAirport  the airport the flight is departing from
     * @param landingAirport    the airport the flight is landing at
     */
    public Flight(int flightNum, AirPort departureAirport, AirPort landingAirport){
        // instance variable set
        mFlightNum = flightNum;
        mDepartureAirport = departureAirport;
        mLandingAirport = landingAirport;
    }

    /**
     * run() is called by start()
     */
    @Override
    public void run() {
        //get the runway to takeoff from
        int departureRunway = mDepartureAirport.depart(mFlightNum);
        System.out.println("flight "+ mFlightNum + " taking off from runway "+departureRunway+" at airport "+ mDepartureAirport.getAirportName());
        //takeoff simulation delay
        try{
            Thread.sleep(DEPART_DELAY);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //tell the airport the runway is free
        mDepartureAirport.freeRunway(mFlightNum, departureRunway);
        System.out.println("flight "+ mFlightNum + " has cleared runway "+departureRunway+" at airport "+ mDepartureAirport.getAirportName());
        //in-flight simulation delay
        try{
            Thread.sleep(FLIGHT_DELAY);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //get the runway to land on
        int landingRunway = mLandingAirport.land(mFlightNum);
        System.out.println("flight "+ mFlightNum + " is landing on runway "+landingRunway+" at airport "+ mLandingAirport.getAirportName());
        //landing simulation delay
        try{
            Thread.sleep(LAND_DELAY);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        mLandingAirport.freeRunway(mFlightNum,landingRunway);
        System.out.println("flight "+ mFlightNum + " has cleared runway "+landingRunway+" at airport "+ mLandingAirport.getAirportName());

    }
}

