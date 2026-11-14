package cache;

// Extension 7.2: Node that carries a weight (e.g. size in bytes)
class WeightedNode<K, V> extends Node<K, V> {
    long weight;

    WeightedNode(K key, V value, long weight) {
        super(key, value);
        this.weight = weight;
    }
}
