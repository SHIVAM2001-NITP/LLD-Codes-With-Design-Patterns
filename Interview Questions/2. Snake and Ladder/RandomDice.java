import java.util.Random;

// Sum of `count` dice, each 1..6. The Random is injected so games can be reproduced.
public class RandomDice implements Dice {
    private final int count;
    private final Random random;

    public RandomDice(int count, Random random) {
        this.count = count;
        this.random = random;
    }

    @Override
    public int roll() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += random.nextInt(6) + 1;
        }
        return total;
    }
}
