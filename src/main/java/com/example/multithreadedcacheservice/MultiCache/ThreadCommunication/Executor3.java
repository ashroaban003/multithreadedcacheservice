//package com.example.multithreadedcacheservice.MultiCache.ThreadCommunication;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor3 {
    public static void main(String[] args) throws ExecutionException,InterruptedException{
        int ns = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(ns);
        CountDownLatch latch = new CountDownLatch(ns);
        
    }
}
