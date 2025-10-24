//package com.example.multithreadedcacheservice.MultiCache.ThreadCommunication;

class producer_consumer{
    private int data;
    Boolean isdata=false;
    public void consumer(){
        for(int i=0;i<10;i++){
            synchronized(this){  
                try{
                        while(!isdata){
                            wait();
                        }
                        System.out.println("consumed value - " + data);
                        isdata = false;
                        notify();
                }catch(Exception e){System.out.println("Error  while consuming");}
            }
       }
    }
    public void producer(){
        for(int i=0;i<10;i++){
            synchronized(this){
                try{
                    while(isdata){
                        wait();
                    }
                    data = i;
                    isdata = true;
                    System.out.println("Sent " + i);
                    notify();
               }catch(Exception e){System.out.println("Error  while producing");}
            }
            // System.out.println("Sent " + i);
        }
    }
}
public class ProducerConsumer {

    public static void main(String[] args) {
        producer_consumer pc = new producer_consumer();
        Thread t1 = new Thread(()->pc.consumer());
        Thread t2 = new Thread(()->pc.producer());
        t1.start();
        t2.start();
    }
}
