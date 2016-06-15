package com.company.airporthandling;

import com.company.flighthandling.Flight;
import com.company.flighthandling.FlightFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is in charge of creating all the flights, airports, runways
 */
public class AirPortFacade {
    //list of airport objects
    private List<AirPort> mAirPortList;
    //list of flights
    private List<Flight> mFlightList;

    /**
     * main and only construcot
     * @param numOfAirports how many airport to create
     * @param numOfFlights how many flights to create
     * @param numOfRunways how many runways per airport
     */
    public AirPortFacade(int numOfAirports, int numOfFlights, int numOfRunways){
        //instantiate the lists
        mAirPortList = new ArrayList<>();
        mFlightList = new ArrayList<>();

        //create the airports and add to list
        for(int i = 0; i < numOfAirports; i++) {
            mAirPortList.add(new AirPort(numOfRunways,String.valueOf(i)));
        }
        //create the flights
        for (int i = 0; i < numOfFlights ; i++) {
            mFlightList.add(FlightFactory.getFlight(mAirPortList,i));
        }
    }

    /**
     * start all the flight threads
     */
    public void startFlights(){
        for (Flight flight: mFlightList) {
            flight.start();
        }
    }


}
