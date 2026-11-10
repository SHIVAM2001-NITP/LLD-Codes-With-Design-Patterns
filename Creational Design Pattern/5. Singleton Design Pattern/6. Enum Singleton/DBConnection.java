// Enum Singleton (recommended by Joshua Bloch, Effective Java, Item 3).
// The JVM creates exactly one constant, INSTANCE, and nothing else can ever create another.
public enum DBConnection {

    INSTANCE;   // the one and only object

    // normal fields and methods work exactly like in a class
    public void connect() {
        System.out.println("Connected using " + this.hashCode());
    }
}
