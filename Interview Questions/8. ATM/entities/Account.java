package entities;

public class Account {
    private final String accountNumber;
    private final int pin;
    private long balance;

    public Account(String accountNumber, int pin, long balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }

    public boolean pinMatches(int enteredPin) { return pin == enteredPin; }

    public long getBalance() { return balance; }

    public void debit(long amount) { balance -= amount; }

    public void credit(long amount) { balance += amount; }
}
