package com.company;

import com.company.airporthandling.AirPortFacade;


/**
 * The main class for a program which demonstrates synchronized flight control via threads
 * @author Roman Smirnov 312914443
 * @version 0.1
 * @since 2016-06-10
 */
public class Main {
    private static final int NUM_OF_FLIGHTS = 10;
    private static final int NUM_OF_RUNWAYS = 3;
    private static final int NUM_OF_AIRPORTS = 2;

    /**
     * The main method
     * @param args
     */
    public static void main(String[] args) {
	    // instantiate  the airports and flights via the facade
        AirPortFacade airPortFacade = new AirPortFacade(NUM_OF_AIRPORTS,NUM_OF_FLIGHTS,NUM_OF_RUNWAYS);
        // start the flight threads
        airPortFacade.startFlights();
    }
}
