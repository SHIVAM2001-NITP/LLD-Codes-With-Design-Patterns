public class LoanAccount implements Account {
    private String owner;
    private double outstanding;
    private double interestRatePercent;

    public LoanAccount(String owner, double outstanding, double interestRatePercent) {
        this.owner = owner;
        this.outstanding = outstanding;
        this.interestRatePercent = interestRatePercent;
    }

    public String getOwner() { return owner; }
    public double getOutstanding() { return outstanding; }
    public double getInterestRatePercent() { return interestRatePercent; }

    @Override
    public void accept(AccountVisitor visitor) { visitor.visit(this); }
}
