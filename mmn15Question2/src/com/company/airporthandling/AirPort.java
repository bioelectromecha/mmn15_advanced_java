package com.company.airporthandling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The airport class
 * responsible for takeoff and landing management
 */
public class AirPort{

    //the name of the airport, will be displayed at printout
    private String mAirportName;
    // the allocated number of runways for the airport
    private int mNumOfRunways;
    // the list of runway object with their states
    private List<Runway> mRunwayList;
    // the number of runways in current usage
    private int mNumOfRunwaysInUse;
    // concurrent reentrant lock
    private final Lock mLock;
    // concurrent reentrant lock condition
    private final Condition mCondition;

    /*
        NOTE: I've actually tried this with both a Mutex/Semaphore
        and synchronize/etc methods, this one seemed the most reasonable/readable.
     */

    /**
     * main and only constructor
     * @param numOfRunways number of allocated runways
     */
    public AirPort(int numOfRunways, String name){

        mAirportName = "Airport"+name;

        //instantiate instance variables
        mNumOfRunways = numOfRunways;
        mNumOfRunwaysInUse = 0;
        //"true" means the flights will take off and land in FIFO order
        mLock = new ReentrantLock(true);
        mCondition = mLock.newCondition();
        // fill the list with runway objects
        mRunwayList = new ArrayList<>();
        for (int i = 0; i < mNumOfRunways ; i++) {
           mRunwayList.add(new Runway(i));
        }
    }

    /**
     *  allocate a departure runways to a flight
     * @param flightNum the departing flight number
     * @return  number of the allocated runway in this airport
     */
    public int depart(int flightNum){
        try {
            //lock the instance for this threads exclusive usage
            mLock.lock();
            //release lock until there are empty runways available
            while (mNumOfRunwaysInUse >= mNumOfRunways) {
                System.out.println("Flight "+flightNum+" waiting for takeoff runway at airport "+mAirportName);
                mCondition.await();
            }
            return getFreeRunway();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally{
            mLock.unlock();
        }
        // if somehow there's an error
        return -1;
    }

    /**
     *  allocate a landing runwas to a flight
     * @param flightNum the departing flight number
     * @return  number of the allocated runway in this airport
     */
    public int land(int flightNum){
        try{
            //lock the instance for this threads exclusive usage
            mLock.lock();
            //release lock until there are empty runways available
            while (mNumOfRunwaysInUse >= mNumOfRunways) {
                System.out.println("Flight "+flightNum+" waiting for landing runway at airport "+mAirportName);
                mCondition.await();
            }
            return getFreeRunway();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally{
            mLock.unlock();
        }
        return -1;
    }

    public void freeRunway(int flightNum, int runwayNum){
        try {
            //lock the instance for this threads exclusive usage
            mLock.lock();
            //set a runway free
            mRunwayList.get(runwayNum).setIsFree(true);
            // decrement the number of runways in current `2
            mNumOfRunwaysInUse--;
        }finally{
            mCondition.signalAll();
            mLock.unlock();
        }
    }

    /**
     * returns a free runways
     * @return an unallocated runway numnber
     */
    private int getFreeRunway(){
        //iterate the runways list and return a free runway
        for (Runway runway : mRunwayList) {
            if (runway.getIsFree()) {
                runway.setIsFree(false);
                mNumOfRunwaysInUse++;
                return runway.getRunwayNumber();
            }
        }
        return -1;
    }

    public String getAirportName(){
        return mAirportName;
    }
}
