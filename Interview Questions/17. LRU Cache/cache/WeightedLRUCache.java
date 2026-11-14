package cache;

import java.util.HashMap;
import java.util.Map;

// Extension 7.2: cap the cache by total weight instead of entry count.
public class WeightedLRUCache<K, V> {
    private final long maxWeight;
    private long currentWeight;
    private final Map<K, Node<K, V>> map = new HashMap<>();
    private final DoublyLinkedList<K, V> list = new DoublyLinkedList<>();

    public WeightedLRUCache(long maxWeight) {
        this.maxWeight = maxWeight;
    }

    public synchronized V get(K key) {
        Node<K, V> node = map.get(key);
        if (node == null) {
            return null;
        }
        list.moveToFront(node);
        return node.value;
    }

    @SuppressWarnings("unchecked")
    public synchronized void put(K key, V value, long weight) {
        // Edge case: an entry that can never fit is rejected, so it cannot flush the whole cache.
        if (weight > maxWeight) {
            throw new IllegalArgumentException("weight " + weight + " exceeds maxWeight " + maxWeight);
        }
        if (map.containsKey(key)) {
            WeightedNode<K, V> node = (WeightedNode<K, V>) map.get(key);
            currentWeight += weight - node.weight;
            node.value = value;
            node.weight = weight;
            list.moveToFront(node);
        } else {
            WeightedNode<K, V> newNode = new WeightedNode<>(key, value, weight);
            list.addFirst(newNode);
            map.put(key, newNode);
            currentWeight += weight;
        }
        // evict until we fit under the weight budget
        while (currentWeight > maxWeight) {
            Node<K, V> lru = list.removeLast();
            if (lru == null) break;
            map.remove(lru.key);
            currentWeight -= ((WeightedNode<K, V>) lru).weight;
        }
    }

    public synchronized long currentWeight() {
        return currentWeight;
    }

    public synchronized int size() {
        return map.size();
    }
}
