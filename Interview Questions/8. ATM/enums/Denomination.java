package enums;

// Note values, highest first, used by the cash dispenser's greedy algorithm
public enum Denomination {
    TWO_THOUSAND(2000),
    FIVE_HUNDRED(500),
    TWO_HUNDRED(200),
    HUNDRED(100);

    private final int value;

    Denomination(int value) { this.value = value; }

    public int getValue() { return value; }
}
