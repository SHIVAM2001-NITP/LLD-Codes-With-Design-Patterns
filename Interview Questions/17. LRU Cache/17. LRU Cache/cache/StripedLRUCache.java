package cache;

// Route each key to one of N shards; each shard is an independent LRUCache
public class StripedLRUCache<K, V> {
    private final LRUCache<K, V>[] shards;

    @SuppressWarnings("unchecked")
    public StripedLRUCache(int shardCount, int perShardCapacity) {
        shards = new LRUCache[shardCount];
        for (int i = 0; i < shardCount; i++) {
            shards[i] = new LRUCache<>(perShardCapacity);
        }
    }

    private LRUCache<K, V> shardFor(K key) {
        int index = (key.hashCode() & 0x7fffffff) % shards.length;
        return shards[index];
    }

    public V get(K key) { return shardFor(key).get(key); }
    public void put(K key, V value) { shardFor(key).put(key, value); }

    public int size() {
        int total = 0;
        for (LRUCache<K, V> shard : shards) {
            total += shard.size();
        }
        return total;
    }
}
