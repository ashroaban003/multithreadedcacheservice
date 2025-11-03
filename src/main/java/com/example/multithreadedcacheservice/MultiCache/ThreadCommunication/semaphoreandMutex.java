package com.example.multithreadedcacheservice.MultiCache.ThreadCommunication;
import java.util.concurrent.Semaphore;

class Mutex{
    Semaphore sf ,sb;
    Mutex(){  // mutex - basically 0/1 semaphore
        sf = new Semaphore(1);
        sb = new Semaphore(0);
    }
    public void printfoo() throws InterruptedException{
        for(int i=0;i<10;i++){
            sf.acquire(); // acquires the lock and decrease count to 0
            System.out.print("foo");
            sb.release(); //increase count of sb to 1
        }
    }
    public void printbar() throws InterruptedException{
        for(int i=0;i<10;i++){
            sb.acquire(); // aquires only when sb value is 1 and decrease the count
            System.out.print("bar");
            sf.release();// increase sf count to 1
        }
    }
}
public class semaphoreandMutex  {
    public static void main(String[] args) {
        //c
        Mutex mutex = new Mutex();
        Thread t1 = new Thread(()->{
                try{
                    mutex.printbar();
                }catch(Exception e){}
        });
        Thread t2 = new Thread(()->{
                try{
                    mutex.printfoo();
                }catch(Exception e){}
        });
        //Thread t2 = new Thread(()->mutex.printfoo());
        t1.start();
        t2.start();
    }
}
