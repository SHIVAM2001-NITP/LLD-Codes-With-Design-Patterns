package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.function.LongSupplier;

// Extension 7.1: per-entry TTL, enforced lazily inside get (expired entry == miss).
public class TtlLRUCache<K, V> {
    private final int capacity;
    private final long ttlMillis;
    private final LongSupplier clock; // System::currentTimeMillis in production, a fake in tests
    private final Map<K, Node<K, V>> map = new HashMap<>();
    private final DoublyLinkedList<K, V> list = new DoublyLinkedList<>();

    public TtlLRUCache(int capacity, long ttlMillis) {
        this(capacity, ttlMillis, System::currentTimeMillis);
    }

    public TtlLRUCache(int capacity, long ttlMillis, LongSupplier clock) {
        this.capacity = capacity;
        this.ttlMillis = ttlMillis;
        this.clock = clock;
    }

    @SuppressWarnings("unchecked")
    public synchronized V get(K key) {
        TtlNode<K, V> node = (TtlNode<K, V>) map.get(key);
        if (node == null) {
            return null;
        }
        if (clock.getAsLong() >= node.expiryAtMillis) {
            list.remove(node);      // expired: drop it and report a miss
            map.remove(key);
            return null;
        }
        list.moveToFront(node);
        return node.value;
    }

    @SuppressWarnings("unchecked")
    public synchronized void put(K key, V value) {
        long expiry = clock.getAsLong() + ttlMillis;
        if (map.containsKey(key)) {
            TtlNode<K, V> node = (TtlNode<K, V>) map.get(key);
            node.value = value;
            node.expiryAtMillis = expiry; // a write refreshes the TTL
            list.moveToFront(node);
        } else {
            if (map.size() == capacity) {
                Node<K, V> lru = list.removeLast();
                if (lru != null) {
                    map.remove(lru.key);
                }
            }
            TtlNode<K, V> newNode = new TtlNode<>(key, value, expiry);
            list.addFirst(newNode);
            map.put(key, newNode);
        }
    }

    // Optional "background sweeper" step: call periodically from a scheduled thread.
    public synchronized int evictExpired() {
        int removed = 0;
        long now = clock.getAsLong();
        for (java.util.Iterator<Map.Entry<K, Node<K, V>>> it = map.entrySet().iterator(); it.hasNext(); ) {
            Node<K, V> n = it.next().getValue();
            if (now >= ((TtlNode<K, V>) n).expiryAtMillis) {
                list.remove(n);
                it.remove();
                removed++;
            }
        }
        return removed;
    }

    public synchronized int size() {
        return map.size();
    }
}
