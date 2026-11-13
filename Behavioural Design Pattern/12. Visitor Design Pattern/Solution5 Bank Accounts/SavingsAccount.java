public class SavingsAccount implements Account {
    private String owner;
    private double balance;
    private double interestRatePercent;

    public SavingsAccount(String owner, double balance, double interestRatePercent) {
        this.owner = owner;
        this.balance = balance;
        this.interestRatePercent = interestRatePercent;
    }

    public String getOwner() { return owner; }
    public double getBalance() { return balance; }
    public double getInterestRatePercent() { return interestRatePercent; }

    @Override
    public void accept(AccountVisitor visitor) { visitor.visit(this); }
}
