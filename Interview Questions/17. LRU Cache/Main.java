import cache.LRUCache;
import cache.MetricsLRUCache;
import cache.StripedLRUCache;
import cache.TtlLRUCache;
import cache.WeightedLRUCache;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws Exception {
        basicDemo();
        concurrencyDemo();
        stripedDemo();
        ttlDemo();
        weightedDemo();
        metricsDemo();
    }

    // The PDF's example: capacity 3, put 1,2,3, get(1), put(4) evicts key 2.
    static void basicDemo() {
        System.out.println("=== Basic LRU (capacity = 3) ===");
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println("get(1) = " + cache.get(1));
        cache.put(4, "D");
        System.out.println("get(2) = " + cache.get(2) + "   (evicted, expected null)");
        System.out.println("get(3) = " + cache.get(3));
        System.out.println("get(4) = " + cache.get(4));
        cache.put(1, "A2");           // update counts as use
        cache.put(5, "E");            // evicts the LRU one
        System.out.println("after update + put(5): get(1) = " + cache.get(1) + ", size = " + cache.size());
    }

    // Concern 1: many threads share one cache; the single lock keeps map and list in step.
    static void concurrencyDemo() throws Exception {
        System.out.println("\n=== Concurrency: 16 threads on one LRUCache (capacity = 100) ===");
        LRUCache<Integer, Integer> cache = new LRUCache<>(100);
        int threads = 16, opsPerThread = 50_000;
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(threads);
        AtomicLong wrong = new AtomicLong();
        for (int t = 0; t < threads; t++) {
            final int seed = t;
            pool.execute(() -> {
                try {
                    start.await();
                    java.util.Random r = new java.util.Random(seed);
                    for (int i = 0; i < opsPerThread; i++) {
                        int k = r.nextInt(300);
                        if (r.nextBoolean()) {
                            cache.put(k, k * 10);
                        } else {
                            Integer v = cache.get(k);
                            if (v != null && v != k * 10) wrong.incrementAndGet();
                        }
                    }
                } catch (InterruptedException ignored) {
                } finally {
                    done.countDown();
                }
            });
        }
        start.countDown();
        done.await();
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("size = " + cache.size() + " (<= 100: " + (cache.size() <= 100) + "), stale/wrong values = " + wrong.get());
    }

    // Concern 2: striped locking, eviction is per shard.
    static void stripedDemo() throws Exception {
        System.out.println("\n=== Striped locking: 8 shards x 25 entries ===");
        StripedLRUCache<String, Integer> cache = new StripedLRUCache<>(8, 25);
        int threads = 16;
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        CountDownLatch done = new CountDownLatch(threads);
        for (int t = 0; t < threads; t++) {
            final int id = t;
            pool.execute(() -> {
                for (int i = 0; i < 20_000; i++) {
                    String k = "user_" + ((i * 31 + id) % 1000);
                    cache.put(k, i);
                    cache.get(k);
                }
                done.countDown();
            });
        }
        done.await();
        pool.shutdown();
        System.out.println("total size = " + cache.size() + " (<= 200: " + (cache.size() <= 200) + ")");
    }

    // 7.1 TTL, using a fake clock so the demo is deterministic.
    static void ttlDemo() {
        System.out.println("\n=== Extension 7.1: TTL (60s) ===");
        AtomicLong now = new AtomicLong(0);
        TtlLRUCache<String, String> cache = new TtlLRUCache<>(3, 60_000, now::get);
        cache.put("token", "abc");
        now.set(30_000);
        System.out.println("t=30s get(token) = " + cache.get("token"));
        now.set(61_000);
        System.out.println("t=61s get(token) = " + cache.get("token") + "   (expired, expected null)");
        cache.put("a", "1");
        cache.put("b", "2");
        now.set(200_000);
        System.out.println("sweeper removed " + cache.evictExpired() + " dead entries, size = " + cache.size());
    }

    // 7.2 weight-based eviction.
    static void weightedDemo() {
        System.out.println("\n=== Extension 7.2: weight-based eviction (max 10 MB) ===");
        WeightedLRUCache<String, String> cache = new WeightedLRUCache<>(10);
        cache.put("t1", "thumb", 1);
        cache.put("t2", "thumb", 1);
        cache.put("img", "big", 8);
        System.out.println("weight = " + cache.currentWeight() + ", entries = " + cache.size());
        cache.put("img2", "bigger", 9);   // evicts t1, t2 and img
        System.out.println("after 9 MB put: weight = " + cache.currentWeight() + ", entries = " + cache.size()
                + ", get(img) = " + cache.get("img"));
        try {
            cache.put("huge", "x", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("rejected oversize entry: " + e.getMessage());
        }
    }

    // 7.3 hit and miss metrics.
    static void metricsDemo() {
        System.out.println("\n=== Extension 7.3: hit / miss metrics ===");
        MetricsLRUCache<String, Integer> cache = new MetricsLRUCache<>(2);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.get("a");   // hit
        cache.get("z");   // miss
        cache.put("c", 3); // evicts b
        cache.get("b");   // miss
        cache.get("c");   // hit
        System.out.println("hits = " + cache.hits() + ", misses = " + cache.misses()
                + ", hitRatio = " + cache.hitRatio());
    }
}
