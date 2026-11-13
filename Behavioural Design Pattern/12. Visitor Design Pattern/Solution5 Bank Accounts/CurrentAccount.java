public class CurrentAccount implements Account {
    private String owner;
    private double balance;
    private double overdraftLimit;

    public CurrentAccount(String owner, double balance, double overdraftLimit) {
        this.owner = owner;
        this.balance = balance;
        this.overdraftLimit = overdraftLimit;
    }

    public String getOwner() { return owner; }
    public double getBalance() { return balance; }
    public double getOverdraftLimit() { return overdraftLimit; }

    @Override
    public void accept(AccountVisitor visitor) { visitor.visit(this); }
}
