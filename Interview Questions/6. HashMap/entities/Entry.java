package entities;

// One key/value slot, chained to the next entry that hashed into the same bucket
public class Entry<K, V> {
    private final K key;
    private V value;
    private Entry<K, V> next;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }

    public V getValue() { return value; }

    public void setValue(V value) { this.value = value; }

    public Entry<K, V> getNext() { return next; }

    public void setNext(Entry<K, V> next) { this.next = next; }
}
