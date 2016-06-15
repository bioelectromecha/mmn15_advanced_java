package com.company;

import com.company.threading.ThreadFacade;

/**
 * The main class for a program which demonstrates synchronized thread access to a shared object
 * @author Roman Smirnov 312914443
 * @version 0.1
 * @since 2016-06-10
 */
public class Main {

    /**
     * the main method
     */
    public static void main(String args[]) {
        //the SharedData instance to be shared between the three threads
        SharedData sharedData = new SharedData();
        //create the two threads and run them to share the sharedData
        ThreadBuilder threadBuilder = new ThreadFacade(sharedData);
        //start the threads
        threadBuilder.startThreads();
    }

}
