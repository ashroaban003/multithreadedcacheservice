//package com.example.multithreadedcacheservice.MultiCache.ThreadCommunication;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorSercice01 {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        ExecutorService ex = Executors.newFixedThreadPool(3);
        long startTime = System.currentTimeMillis();
        for(int i=0;i<10;i++){
             int i0=i;
            ex.submit(()->{
              long x = factorial(i0);
              System.out.println(x);
            });
        }
        Future<Integer> future = ex.submit(()->69);
        System.out.println("the value promised to get is - "+ future.get());
        ex.shutdown();
        try{
            ex.awaitTermination(100, TimeUnit.SECONDS);
        }catch(Exception e){
            System.out.println("error");
        }
        System.out.println("Total Time - "+(System.currentTimeMillis()-startTime));
    }
    public static long factorial(int j){
        long sum = 1;
        for(int i=1;i<=j && i<=10; i++){
            sum*=i;
        }
        return sum;
    }
}
