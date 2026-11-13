package map;

import entities.Entry;
import exceptions.NoSuchKeyException;

import java.util.ArrayList;
import java.util.List;

// A HashMap built from scratch: an array of buckets, each a singly linked list of entries
// (separate chaining). Resizes when the load factor is exceeded, like java.util.HashMap.
public class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    private Entry<K, V>[] buckets = new Entry[DEFAULT_CAPACITY];
    private int size = 0;

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    // Spreads the bits of hashCode() so close hash codes do not collide in the same bucket
    private int indexFor(K key, int capacity) {
        int h = key == null ? 0 : key.hashCode();
        h ^= (h >>> 16);
        return (h & 0x7fffffff) % capacity;
    }

    public void put(K key, V value) {
        int index = indexFor(key, buckets.length);
        Entry<K, V> head = buckets[index];
        for (Entry<K, V> e = head; e != null; e = e.getNext()) {
            if (keyEquals(e.getKey(), key)) {
                e.setValue(value);        // key already present: overwrite
                return;
            }
        }
        Entry<K, V> entry = new Entry<>(key, value);
        entry.setNext(head);              // insert at the head of the chain: O(1)
        buckets[index] = entry;
        size++;
        if ((double) size / buckets.length > LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        Entry<K, V> entry = findEntry(key);
        return entry == null ? null : entry.getValue();
    }

    public boolean containsKey(K key) {
        return findEntry(key) != null;
    }

    public V getOrThrow(K key) {
        Entry<K, V> entry = findEntry(key);
        if (entry == null) {
            throw new NoSuchKeyException("No value for key: " + key);
        }
        return entry.getValue();
    }

    public V remove(K key) {
        int index = indexFor(key, buckets.length);
        Entry<K, V> prev = null;
        for (Entry<K, V> e = buckets[index]; e != null; prev = e, e = e.getNext()) {
            if (keyEquals(e.getKey(), key)) {
                if (prev == null) {
                    buckets[index] = e.getNext();
                } else {
                    prev.setNext(e.getNext());
                }
                size--;
                return e.getValue();
            }
        }
        return null;
    }

    public List<K> keys() {
        List<K> keys = new ArrayList<>();
        for (Entry<K, V> head : buckets) {
            for (Entry<K, V> e = head; e != null; e = e.getNext()) {
                keys.add(e.getKey());
            }
        }
        return keys;
    }

    // Doubles the bucket array and re-places every entry, since the bucket index depends on the capacity
    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] old = buckets;
        buckets = new Entry[old.length * 2];
        for (Entry<K, V> head : old) {
            for (Entry<K, V> e = head; e != null; ) {
                Entry<K, V> next = e.getNext();
                int index = indexFor(e.getKey(), buckets.length);
                e.setNext(buckets[index]);
                buckets[index] = e;
                e = next;
            }
        }
    }

    private Entry<K, V> findEntry(K key) {
        int index = indexFor(key, buckets.length);
        for (Entry<K, V> e = buckets[index]; e != null; e = e.getNext()) {
            if (keyEquals(e.getKey(), key)) {
                return e;
            }
        }
        return null;
    }

    private boolean keyEquals(K a, K b) {
        return a == null ? b == null : a.equals(b);
    }

    // For the demo only: shows how entries land across buckets
    public int bucketCount() { return buckets.length; }

    public int chainLength(int bucketIndex) {
        int count = 0;
        for (Entry<K, V> e = buckets[bucketIndex]; e != null; e = e.getNext()) {
            count++;
        }
        return count;
    }
}
