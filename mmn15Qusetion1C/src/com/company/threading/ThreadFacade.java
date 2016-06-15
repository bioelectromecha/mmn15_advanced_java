package com.company.threading;

import com.company.SharedData;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates the StoreThread and CalcThread instances
 * it also holds some constants relevant to both threads
 */
public class ThreadBuilder {

    //list of store threads
    private List<StoreThread> mStoreThreadList;
    // list of calc threads
    private List<CalcThread> mCalcThreadList;
    // number of milliseconds for thread to sleep after each store
    protected static final int SLEEP_PERIOD = 250;
    //number of times to iterate the process of store/calc
    protected static final int ITER_NUM = 10;
    //number of threads to create from each type
    private static final int THREAD_NUM = 4;

    /**
     * main and only constructor
     * @param sharedData SharedData object - NOT NULL
     * @throws NullPointerException in case sharedData is null
     */
    public ThreadFacade(SharedData sharedData) throws NullPointerException{
        //decided not to rely on default behaviour
        if(sharedData==null){
            throw new NullPointerException("SharedData object must not be null!");
        }

        //instantiate the lists
        mStoreThreadList = new ArrayList<>();
        mCalcThreadList = new ArrayList<>();

        //threads which will access store method in SharedObject and store two random numbers
        for(int i = 0; i<THREAD_NUM; i++){
            mStoreThreadList.add(new StoreThread(sharedData));
        }
        //threads which will access calc method in SharedObject and display result
        for(int i = 0; i<THREAD_NUM; i++){
            mCalcThreadList.add(new CalcThread(sharedData));
        }
    }

    /**
     * methods to start the threads
     */
    public void startThreads(){
        //iterate through the thread lists
        for(int i=0; i<THREAD_NUM; i++){
            //start the threads
            mCalcThreadList.get(i).start();
            mStoreThreadList.get(i).start();
        }
    }

}
