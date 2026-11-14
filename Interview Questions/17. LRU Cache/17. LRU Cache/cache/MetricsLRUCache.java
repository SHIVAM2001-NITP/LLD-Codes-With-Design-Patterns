package cache;

import java.util.HashMap;
import java.util.Map;

// Extension 7.3: hit / miss counters updated inside the same locked section as the lookup.
public class MetricsLRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> map = new HashMap<>();
    private final DoublyLinkedList<K, V> list = new DoublyLinkedList<>();
    private long hits;
    private long misses;

    public MetricsLRUCache(int capacity) {
        this.capacity = capacity;
    }

    public synchronized V get(K key) {
        Node<K, V> node = map.get(key);
        if (node == null) {
            misses++;
            return null;
        }
        hits++;
        list.moveToFront(node);
        return node.value;
    }

    public synchronized void put(K key, V value) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            node.value = value;
            list.moveToFront(node);
        } else {
            if (map.size() == capacity) {
                Node<K, V> lru = list.removeLast();
                if (lru != null) {
                    map.remove(lru.key);
                }
            }
            Node<K, V> newNode = new Node<>(key, value);
            list.addFirst(newNode);
            map.put(key, newNode);
        }
    }

    public synchronized double hitRatio() {
        long total = hits + misses;
        return total == 0 ? 0.0 : (double) hits / total;
    }

    public synchronized long hits() { return hits; }
    public synchronized long misses() { return misses; }
}
