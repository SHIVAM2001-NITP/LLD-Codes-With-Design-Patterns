// Returns a scripted sequence of values: perfect for tests and demos
public class FixedDice implements Dice {
    private final int[] values;
    private int next = 0;

    public FixedDice(int... values) {
        this.values = values;
    }

    @Override
    public int roll() {
        if (next >= values.length) {
            throw new IllegalStateException("FixedDice ran out of values");
        }
        return values[next++];
    }
}
