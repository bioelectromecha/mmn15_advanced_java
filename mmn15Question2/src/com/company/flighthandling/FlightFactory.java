package com.company.flighthandling;

import com.company.airporthandling.AirPort;

import java.util.List;
import java.util.Random;

/**
 * This class manufactures an instance of a flight
 */
public class FlightFactory {

    /**
     * instantiates and returns a Fligth instance
     * @param airPortList   list of airports
     * @param flightNum id number of the flight to create
     * @return  instance of Flight
     */
    public static Flight getFlight(List<AirPort> airPortList, int flightNum){
        FlightDirection flightDirection = getFlightDirection(airPortList);
        //set the new flight with the FlightDirection parameters and return it
        return new Flight(flightNum, flightDirection.getDeparture(), flightDirection.getLanding());
    }

    /**
     * returns a flight directions to help crate a Flight object
     * @param airPortList   list of airports
     * @return  Flightdirection instance
     */
    private static FlightDirection getFlightDirection(List<AirPort> airPortList){
        //randomly select a flight origin and destination and return the direciton
        // TODO: unhandled problem - case where flight list has 1 airport only
        Random random = new Random();
        AirPort dport = airPortList.get(random.nextInt(airPortList.size()));
        AirPort lport = airPortList.get(random.nextInt(airPortList.size()));
        //loop until the airports are not the same
        while( lport == dport){
            lport = airPortList.get(random.nextInt(airPortList.size()));
        }
        return new FlightDirection(dport,lport);
    }
}
