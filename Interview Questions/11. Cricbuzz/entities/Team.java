package entities;

public class Team {
    private final String name;

    public Team(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public String toString() { return name; }
}
