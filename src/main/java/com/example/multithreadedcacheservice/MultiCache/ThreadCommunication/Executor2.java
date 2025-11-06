// package com.example.multithreadedcacheservice.MultiCache.ThreadCommunication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Executor2 {
    public static void main(String[] args) {
        ScheduledExecutorService ex = Executors.newScheduledThreadPool(1);
        ex.scheduleAtFixedRate(
            () -> System.out.println("Task executed after 5 second delay!"),
            5,
            5,
            TimeUnit.SECONDS);
        ex.schedule(
            ()->{
                System.out.println("Initiating shutdown");
                ex.shutdown();
            }
            , 20, TimeUnit.SECONDS);
       // ex.shutdown();  
       //note periodic schedule happen after running of whole code, so ex.shutdown() will shutdown bwfore the periodic execution
       //stopping whole code;  
    }
}
