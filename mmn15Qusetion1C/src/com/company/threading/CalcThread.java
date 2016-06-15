package com.company.threading;

import com.company.SharedData;

/**
 * This class is a thread which gets the calc number from a SharedData instance and print it out
 * package-level-access  - meant to be accessed by ThreadFacade only
 */
class CalcThread extends Thread{
    // number of times to get calculated value from ShareData object

    //SharedData object
    private SharedData mSharedData;

    /**
     * main and only constructor
     * @param sharedData SharedData instace to be shared between threads
     */
    CalcThread(SharedData sharedData){
        // no null check because IMHO the default exception down the line is better form (?)
        mSharedData = sharedData;
    }

    @Override
    public void run() {
        //go over the code the alloted num of times
        for(int i=0; i<ThreadFacade.ITER_NUM; i++) {
            //print out the calcualted value
            System.out.println("The calculated value is " + mSharedData.calc());

            //put the thread to sleep for a bit  - for printout to look nice and readable
            try {
                Thread.sleep(ThreadFacade.SLEEP_PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
