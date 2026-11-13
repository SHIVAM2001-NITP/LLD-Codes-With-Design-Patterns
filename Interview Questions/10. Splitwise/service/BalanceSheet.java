package service;

import entities.User;

import java.util.HashMap;
import java.util.Map;

// balances[A][B] = how much A owes B. Always kept symmetric: balances[A][B] == -balances[B][A].
// Keyed directly by User (the same instances are reused everywhere, so identity equality is fine).
public class BalanceSheet {
    private final Map<User, Map<User, Double>> balances = new HashMap<>();

    public synchronized void adjust(User debtor, User creditor, double amount) {
        if (debtor.getId().equals(creditor.getId()) || amount == 0) {
            return;
        }
        add(debtor, creditor, amount);
        add(creditor, debtor, -amount);
    }

    private void add(User a, User b, double amount) {
        balances.computeIfAbsent(a, k -> new HashMap<>()).merge(b, amount, Double::sum);
    }

    public double getNet(User debtor, User creditor) {
        return balances.getOrDefault(debtor, new HashMap<>()).getOrDefault(creditor, 0.0);
    }

    public void printBalancesFor(User user) {
        Map<User, Double> row = balances.getOrDefault(user, new HashMap<>());
        boolean settled = true;
        for (Map.Entry<User, Double> e : row.entrySet()) {
            double amount = Math.round(e.getValue() * 100) / 100.0;
            if (amount > 0) {
                System.out.println("  " + user.getName() + " owes " + e.getKey().getName() + ": " + amount);
                settled = false;
            } else if (amount < 0) {
                System.out.println("  " + e.getKey().getName() + " owes " + user.getName() + ": " + (-amount));
                settled = false;
            }
        }
        if (settled) {
            System.out.println("  " + user.getName() + " is settled up");
        }
    }
}
