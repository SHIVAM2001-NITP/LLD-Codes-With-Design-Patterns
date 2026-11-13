// Yearly interest: customers EARN it on savings and OWE it on loans
public class InterestVisitor implements AccountVisitor {
    private double interestEarned = 0;
    private double interestOwed = 0;

    @Override
    public void visit(SavingsAccount account) {
        interestEarned += account.getBalance() * account.getInterestRatePercent() / 100;
    }

    @Override
    public void visit(CurrentAccount account) {
        // a current account pays no interest
    }

    @Override
    public void visit(LoanAccount account) {
        interestOwed += account.getOutstanding() * account.getInterestRatePercent() / 100;
    }

    public double getInterestEarned() { return interestEarned; }
    public double getInterestOwed() { return interestOwed; }
}
