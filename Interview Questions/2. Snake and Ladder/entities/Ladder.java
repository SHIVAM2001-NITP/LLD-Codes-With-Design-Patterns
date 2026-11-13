package entities;

// Bottom (start) is lower than the top (end): it takes you up
public class Ladder extends BoardEntity {

    public Ladder(int bottom, int top) {
        super(bottom, top);
        if (bottom >= top) {
            throw new IllegalArgumentException("Ladder top must be above its bottom: " + bottom + "->" + top);
        }
    }

    @Override
    public String describe() {
        return "climbed a ladder";
    }
}
