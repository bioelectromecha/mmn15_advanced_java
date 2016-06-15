package com.company.threading;

import com.company.SharedData;

import java.util.Random;

/**
 * This class is a thread which generates random numbers and stores in a SharedData instance
 * package-level-access  - meant to be accessed by ThreadBuilder only
 */
class StoreThread extends Thread {

    // range of numbers to generate (i.e 0-10)
    private static final int RAND_RANGE = 10;
    //SharedData instance to be shared
    private SharedData mSharedData;
    // random number generator
    private Random mRandom;

    /**
     * main and only constructor
     * @param sharedData SharedData instance to be shared
     */
    StoreThread(SharedData sharedData){
        // no null check because IMHO the default exception down the line is better form (?)
        mSharedData = sharedData;
        mRandom = new Random();
    }

    /**
     *  This method is executed when start() is called
     */
    @Override
    public void run(){
        //go over the code the alloted num of times
        for(int i = 0; i< ThreadBuilder.ITER_NUM; i++){
            //generate two random doulbe numbers - int to double cast is for console printout to look pretty
            double x =  (double) mRandom.nextInt(RAND_RANGE);
            double y =  (double) mRandom.nextInt(RAND_RANGE);
            System.out.println("First number " +x+" ; "+"Second number "+y);
            //store the numbers in the SharedObject instance
            mSharedData.store(x, y);
            //put the thread to sleep for a bit  - for printout to look nice and readable
            try {
                Thread.sleep(ThreadBuilder.SLEEP_PERIOD);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
