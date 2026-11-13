package entities;

// Head (start) is higher than the tail (end): it takes you down
public class Snake extends BoardEntity {

    public Snake(int head, int tail) {
        super(head, tail);
        if (head <= tail) {
            throw new IllegalArgumentException("Snake head must be above its tail: " + head + "->" + tail);
        }
    }

    @Override
    public String describe() {
        return "bitten by a snake";
    }
}
