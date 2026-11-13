// Prints a different statement line for each account type
public class StatementVisitor implements AccountVisitor {

    @Override
    public void visit(SavingsAccount account) {
        System.out.println("[SAVINGS] " + account.getOwner() + " balance=" + account.getBalance()
                + " rate=" + account.getInterestRatePercent() + "%");
    }

    @Override
    public void visit(CurrentAccount account) {
        System.out.println("[CURRENT] " + account.getOwner() + " balance=" + account.getBalance()
                + " overdraft limit=" + account.getOverdraftLimit());
    }

    @Override
    public void visit(LoanAccount account) {
        System.out.println("[LOAN]    " + account.getOwner() + " outstanding=" + account.getOutstanding()
                + " rate=" + account.getInterestRatePercent() + "%");
    }
}
