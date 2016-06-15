package com.company;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * this class is meant shared between several reading and writing threads
 */
public class SharedData {
    // instance variables
    private double mX =0;
    private double mY =0;
    // this lock will lock an instance for the use of a single writing thread or several reading threads
    private ReentrantReadWriteLock mLock;

    /**
     * main and only constructor
     */
    public SharedData(){
        mLock = new ReentrantReadWriteLock();
    }

    /**
     * calculate the average of the two stored numbers
     * @return  the average
     * store() won't execute until this one is finished
     */
    public double calc(){
        //lock instance for the use of only the CalcThreads
        mLock.readLock().lock();
        try {
            //return the average of the two numbers
            return (mX + mY)/2;
        }finally {
            mLock.readLock().unlock();
        }
    }

    /**
     * store two double numbers
     * @param x the first double to store
     * @param y the second double to store
     */
    public void store(double x, double y){
        //lock instance for the use of only a single StoreThread
        mLock.writeLock().lock();
        try{
            mX = x;
            mY = y;
        }finally {
            mLock.writeLock().unlock();
        }
    }
}
