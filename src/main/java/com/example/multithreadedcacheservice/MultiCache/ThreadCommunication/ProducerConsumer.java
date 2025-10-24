//package com.example.multithreadedcacheservice.MultiCache.ThreadCommunication;

class producer_consumer{
    private int data;
    Boolean isdata=false;
    public void consumer(){
        synchronized(this){
            while(!isdata){
                wait();
            }
            System.out.println("current value - " + data);
            isdata = false;
            notify();
        }
    }
    public void producer(){
        for(int i=0;i<10;i++){
            synchronized(this){
                while(isdata){
                    wait();
                }
                data = i;
                isdata = true;
                notify();
            }
        }
    }
}
public class ProducerConsumer {

    public static void main(String[] args) {
        
    }
}
