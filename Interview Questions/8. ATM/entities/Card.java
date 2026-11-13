package entities;

public class Card {
    private final String number;
    private final String accountNumber;
    private int failedAttempts = 0;
    private boolean blocked = false;

    public Card(String number, String accountNumber) {
        this.number = number;
        this.accountNumber = accountNumber;
    }

    public String getNumber() { return number; }

    public String getAccountNumber() { return accountNumber; }

    public boolean isBlocked() { return blocked; }

    public void registerFailedAttempt() {
        failedAttempts++;
        if (failedAttempts >= 3) {
            blocked = true;
        }
    }

    public void resetFailedAttempts() { failedAttempts = 0; }

    @Override
    public String toString() { return "Card " + number; }
}
