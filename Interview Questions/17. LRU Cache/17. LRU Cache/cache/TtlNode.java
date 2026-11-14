package cache;

// Extension 7.1: Node with an expiry field, set when the entry is written
class TtlNode<K, V> extends Node<K, V> {
    long expiryAtMillis; // clock.now() + ttl

    TtlNode(K key, V value, long expiryAtMillis) {
        super(key, value);
        this.expiryAtMillis = expiryAtMillis;
    }
}
