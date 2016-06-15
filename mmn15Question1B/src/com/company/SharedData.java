package com.company;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * this class is meant shared between several reading and writing threads
 */
public class SharedData {
    // instance variables
    private double mX =0;
    private double mY =0;
    // this lock will lock an instance for the use of a single writing thread or several reading threads
    private Lock mLock;
    // condition for releasing the writing lock
    private Condition mWriteCondition;
    // condition for releasing the reading lock
    private Condition mReadCondition;
    // flag for denoting whether new data has been stored/calculated or not
    private boolean mHasNewData = false;

    /**
     * main and only constructor
     */
    public SharedData(){
        // instantiate locks and conditions
        mLock = new ReentrantLock();
        mWriteCondition = mLock.newCondition();
        mReadCondition = mLock.newCondition();
    }

    /**
     * calculate the average of the two stored numbers
     * @return  the average
     * store() won't execute until this one is finished
     */
    public double calc(){
        //lock instance for the use of only the CalcThreads
        mLock.lock();
        try {
            while (!mHasNewData) {
                mReadCondition.await();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();

        }finally {
            mHasNewData = false;
            mWriteCondition.signal();
            mLock.unlock();
            //return the average of the two numbers
            return (mX + mY)/2;
        }
    }

    /**
     * store two double numbers
     * @param x the first double to store
     * @param y the second double to store
     */
    public void store(double x, double y){
        //lock instance for the use of only a single StoreThread
        mLock.lock();
        try {
            while (mHasNewData) {
                mWriteCondition.await();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            mX = x;
            mY = y;
            mHasNewData = true;
            mReadCondition.signal();
            mLock.unlock();
        }
    }
}
