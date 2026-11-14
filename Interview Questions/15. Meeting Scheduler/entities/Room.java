package entities;

public class Room {
    private final String id;
    private final String name;
    private final int capacity;

    public Room(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public int getCapacity() { return capacity; }

    @Override
    public String toString() { return name + "(cap " + capacity + ")"; }
}
