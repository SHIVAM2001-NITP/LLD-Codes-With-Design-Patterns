import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Account> accounts = Arrays.asList(
                new SavingsAccount("Asha", 10000, 4),
                new CurrentAccount("Ravi", 5000, 2000),
                new LoanAccount("Meera", 20000, 10));

        StatementVisitor statement = new StatementVisitor();
        InterestVisitor interest = new InterestVisitor();
        FeeVisitor fees = new FeeVisitor();

        for (Account account : accounts) {
            account.accept(statement);
            account.accept(interest);
            account.accept(fees);
        }

        System.out.println("Interest earned : " + interest.getInterestEarned());
        System.out.println("Interest owed   : " + interest.getInterestOwed());
        System.out.println("Monthly fees    : " + fees.getTotalFees());
    }
}
