// // package com.example.multithreadedcacheservice.MultiCache;

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

class CacheTest {
    public static void main(String[] args) {
        System.out.println("sd");
    }
    
}