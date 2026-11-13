// Monthly maintenance fees, one rule per account type
public class FeeVisitor implements AccountVisitor {
    private double totalFees = 0;

    @Override
    public void visit(SavingsAccount account) { totalFees += 0; }

    @Override
    public void visit(CurrentAccount account) { totalFees += 50; }

    @Override
    public void visit(LoanAccount account) { totalFees += 100; }

    public double getTotalFees() { return totalFees; }
}
