import entities.Expense;
import entities.User;
import enums.SplitType;
import exceptions.InvalidSplitException;
import service.ExpenseManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        User alice = new User("U1", "Alice");
        User bob = new User("U2", "Bob");
        User carol = new User("U3", "Carol");
        List<User> all = Arrays.asList(alice, bob, carol);

        ExpenseManager manager = new ExpenseManager();

        System.out.println("=== 1. Equal split: Alice pays 300 for dinner, split 3 ways ===");
        Expense dinner = manager.addExpense("Dinner", alice, 300.00, all, SplitType.EQUAL, null);
        System.out.println(dinner);

        System.out.println("\n=== 2. Exact split: Bob pays 200 for tickets (Alice 120, Bob 80) ===");
        Map<User, Double> exact = new HashMap<>();
        exact.put(alice, 120.0);
        exact.put(bob, 80.0);
        Expense tickets = manager.addExpense("Tickets", bob, 200.00, Arrays.asList(alice, bob), SplitType.EXACT, exact);
        System.out.println(tickets);

        System.out.println("\n=== 3. Percent split: Carol pays 150 for groceries (50/30/20) ===");
        Map<User, Double> percent = new HashMap<>();
        percent.put(alice, 50.0);
        percent.put(bob, 30.0);
        percent.put(carol, 20.0);
        Expense groceries = manager.addExpense("Groceries", carol, 150.00, all, SplitType.PERCENT, percent);
        System.out.println(groceries);

        System.out.println("\n=== Net balances after all three expenses ===");
        for (User user : all) {
            manager.getBalanceSheet().printBalancesFor(user);
        }

        System.out.println("\n=== Alice settles her debt to Bob directly ===");
        double owed = manager.getBalanceSheet().getNet(alice, bob);
        System.out.println("Alice currently owes Bob: " + owed);
        manager.settle(alice, bob, owed);
        manager.getBalanceSheet().printBalancesFor(alice);
        manager.getBalanceSheet().printBalancesFor(bob);

        System.out.println("\n-- Invalid splits are rejected --");
        try {
            Map<User, Double> badPercent = new HashMap<>();
            badPercent.put(alice, 60.0);
            badPercent.put(bob, 60.0);
            manager.addExpense("Bad split", alice, 100.0, Arrays.asList(alice, bob), SplitType.PERCENT, badPercent);
        } catch (InvalidSplitException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            Map<User, Double> badExact = new HashMap<>();
            badExact.put(alice, 50.0);
            badExact.put(bob, 40.0);
            manager.addExpense("Bad exact", alice, 100.0, Arrays.asList(alice, bob), SplitType.EXACT, badExact);
        } catch (InvalidSplitException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            manager.addExpense("Payer not in group", alice, 90.0, Arrays.asList(bob, carol), SplitType.EQUAL, null);
        } catch (InvalidSplitException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
