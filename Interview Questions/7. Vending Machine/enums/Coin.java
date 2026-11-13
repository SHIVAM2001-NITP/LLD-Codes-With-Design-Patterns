package enums;

// Value in cents
public enum Coin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25),
    DOLLAR(100);

    private final int cents;

    Coin(int cents) { this.cents = cents; }

    public int getCents() { return cents; }
}
