 package com.example.multithreadedcacheservice.MultiCache.cachebasic;

// import java.util.*;
// import java.util.concurrent.*;
// import java.util.concurrent.locks.*;

// class CacheEntry<V> {
//     private final V value;
//     private final long expiryTime; // in millis

//     public CacheEntry(V value, long ttlMillis) {
//         this.value = value;
//         this.expiryTime = System.currentTimeMillis() + ttlMillis;
//     }

//     public V getValue() {
//         return value;
//     }

//     public boolean isExpired() {
//         return System.currentTimeMillis() > expiryTime;
//     }
// }

// public class InMemoryCache<K, V> {
//     private final ConcurrentHashMap<K, CacheEntry<V>> cache = new ConcurrentHashMap<>();
//     private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//     private final long cleanupInterval;
//     private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();

//     public InMemoryCache(long cleanupIntervalMillis) {
//         this.cleanupInterval = cleanupIntervalMillis;
//         startCleanupTask();
//     }

//     public void put(K key, V value, long ttlMillis) {
//         lock.writeLock().lock();
//         try {
//             cache.put(key, new CacheEntry<>(value, ttlMillis));
//         } finally {
//             lock.writeLock().unlock();
//         }
//     }

//     public V get(K key) {
//         lock.readLock().lock();
//         try {
//             CacheEntry<V> entry = cache.get(key);
//             if (entry == null || entry.isExpired()) {
//                 cache.remove(key);
//                 return null;
//             }
//             return entry.getValue();
//         } finally {
//             lock.readLock().unlock();
//         }
//     }

//     private void startCleanupTask() {
//         cleaner.scheduleAtFixedRate(() -> {
//             try {
//                 cleanup();
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         }, cleanupInterval, cleanupInterval, TimeUnit.MILLISECONDS);
//     }

//     private void cleanup() {
//         lock.writeLock().lock();
//         try {
//             for (Map.Entry<K, CacheEntry<V>> entry : cache.entrySet()) {
//                 if (entry.getValue().isExpired()) {
//                     cache.remove(entry.getKey());
//                 }
//             }
//         } finally {
//             lock.writeLock().unlock();
//         }
//     }

//     public int size() {
//         return cache.size();
//     }

//     public void shutdown() {
//         cleaner.shutdown();
//     }

//     // Optional: LRU mode could be implemented using LinkedHashMap with accessOrder = true
// }

// public class CacheTest {
//     public static void main(String[] args) throws InterruptedException {
//         InMemoryCache<String, String> cache = new InMemoryCache<>(2000); // cleanup every 2 sec
//         cache.put("A", "Apple", 3000); // TTL 3s
//         cache.put("B", "Banana", 1000); // TTL 1s

//         System.out.println(cache.get("A")); // Apple
//         Thread.sleep(1500);
//         System.out.println(cache.get("B")); // null (expired)
//         System.out.println("Cache size: " + cache.size());

//         Thread.sleep(2500);
//         System.out.println(cache.get("A")); // null (expired by now)

//         cache.shutdown();
//     }
// }

//package com.example.multithreadedcacheservice.MultiCache;


// // Time to Live Cache

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
class CacheEntry{
    private final int value;
    private final long expiryTime;
    CacheEntry(int value,long sec){
         this.value = value;
         expiryTime = System.currentTimeMillis()+sec*1000;
    }
    public int getvalue(){
        return value;
    }
    public Boolean isExpired(){
        return System.currentTimeMillis() > expiryTime;
    }
}


class InMemoryCache{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReadLock readLock = lock.readLock();
    private WriteLock writeLock = lock.writeLock();
    private ConcurrentHashMap<Integer,CacheEntry> cache = new ConcurrentHashMap<>();
    private ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();

    InMemoryCache(){
        // try{
        // Thread thread1 = new Thread(()-> GarbageThread());
        // thread1.start();
        // }catch(Exception e){
        //     System.out.println("error while garbage cleaning!");
        // }
        startCleanupTask();
    }
        private void startCleanupTask() {
                cleaner.scheduleAtFixedRate(() -> {
                    try {
                        System.out.println("started cleaning");;
                        clean();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, 10000, 10000, TimeUnit.MILLISECONDS);
        }

    public void put(int k,int v, int s) {
        writeLock.lock();
        try{
        cache.put(k, new CacheEntry(v, s));
        }finally{
            writeLock.unlock();
        }
    }
    //Method overloading
    public void put(int k,int v){
        put(k,v,10); //keeping default expiry time as 10 sec
    }
    public void get(int k){
        readLock.lock();
        try{
            if(cache.get(1)!=null) System.out.println(cache.get(1).getvalue());
            else System.out.println("not present");

        }finally{
            readLock.unlock();
        }
    }
    
    public void GarbageThread(){
        try{
            while (true) {
                System.out.println("shreya !");
                Thread.sleep(4000);
            }
       }catch(Exception e){
          System.out.println("d");
       }
    }
    public void clean(){
        writeLock.lock();
        try{
            for(Map.Entry<Integer,CacheEntry> entry : cache.entrySet()){
                if(entry.getValue().isExpired()){
                    cache.remove(entry.getKey());
                }
            }
            System.out.println("cleanup finished");
        }finally{
            writeLock.unlock();
        }
    }


}
class CacheTest {
    public static void main(String[] args) {
        final ConcurrentHashMap<Integer,Integer> cache = new ConcurrentHashMap<>();
        cache.put(1,10);
        cache.put(2,20);
        System.out.println(cache.get(3));
        System.out.println("sd");

        // lets use TTL cache here
        final InMemoryCache inMemoryCache = new InMemoryCache();
        try{
        inMemoryCache.put(1, 10,5);
        Thread.sleep(3000);
        inMemoryCache.put(2,20 );
        Thread.sleep(5000);
        inMemoryCache.put(3, 30,3);
   
        inMemoryCache.get(1);
        Thread.sleep(5000);
        inMemoryCache.get(1);
        }catch(Exception e){

        }
    }
    
}