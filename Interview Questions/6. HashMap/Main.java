import exceptions.NoSuchKeyException;
import map.MyHashMap;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Basic put / get / overwrite ===");
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        System.out.println("get(two) = " + map.get("two"));
        map.put("two", 22);                       // overwrite, size must not change
        System.out.println("after overwrite, get(two) = " + map.get("two") + ", size = " + map.size());

        System.out.println("\n=== Missing key ===");
        System.out.println("get(missing) = " + map.get("missing"));
        try {
            map.getOrThrow("missing");
        } catch (NoSuchKeyException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n=== Remove ===");
        System.out.println("remove(one) = " + map.remove("one"));
        System.out.println("containsKey(one) = " + map.containsKey("one") + ", size = " + map.size());

        System.out.println("\n=== Collisions in the same bucket chain correctly ===");
        MyHashMap<Integer, String> collidingMap = new MyHashMap<>();
        int capacity = collidingMap.bucketCount();
        collidingMap.put(1, "a");
        collidingMap.put(1 + capacity, "b");       // same bucket as key 1 before any resize
        collidingMap.put(1 + 2 * capacity, "c");    // same bucket again
        System.out.println("bucket 1 chain length = " + collidingMap.chainLength(1 % capacity));
        System.out.println("get(1) = " + collidingMap.get(1)
                + ", get(" + (1 + capacity) + ") = " + collidingMap.get(1 + capacity)
                + ", get(" + (1 + 2 * capacity) + ") = " + collidingMap.get(1 + 2 * capacity));

        System.out.println("\n=== Resize happens automatically past the load factor ===");
        MyHashMap<Integer, Integer> growing = new MyHashMap<>();
        int before = growing.bucketCount();
        for (int i = 0; i < 20; i++) {
            growing.put(i, i * i);
        }
        System.out.println("capacity before = " + before + ", after 20 inserts = " + growing.bucketCount());
        boolean allPresent = true;
        for (int i = 0; i < 20; i++) {
            allPresent &= growing.get(i) == i * i;
        }
        System.out.println("all 20 keys still correct after resize: " + allPresent);
    }
}
