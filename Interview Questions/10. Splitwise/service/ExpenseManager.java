package service;

import entities.Expense;
import entities.Split;
import entities.User;
import enums.SplitType;
import exceptions.InvalidSplitException;
import strategy.EqualSplitStrategy;
import strategy.ExactSplitStrategy;
import strategy.PercentSplitStrategy;
import strategy.SplitStrategy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

// Single entry point: records an expense (computing its splits through the right strategy),
// updates the balance sheet, and lets debts be settled directly between two users.
public class ExpenseManager {
    private final Map<SplitType, SplitStrategy> strategies = new EnumMap<>(SplitType.class);
    private final List<Expense> expenses = new ArrayList<>();
    private final BalanceSheet balanceSheet = new BalanceSheet();
    private int counter = 0;

    public ExpenseManager() {
        strategies.put(SplitType.EQUAL, new EqualSplitStrategy());
        strategies.put(SplitType.EXACT, new ExactSplitStrategy());
        strategies.put(SplitType.PERCENT, new PercentSplitStrategy());
    }

    public BalanceSheet getBalanceSheet() { return balanceSheet; }

    public synchronized Expense addExpense(String description, User paidBy, double amount,
                                           List<User> participants, SplitType type, Map<User, Double> values) {
        if (!participants.contains(paidBy)) {
            throw new InvalidSplitException(paidBy + " must be one of the participants");
        }
        SplitStrategy strategy = strategies.get(type);
        List<Split> splits = strategy.calculateSplits(amount, participants, values);

        Expense expense = new Expense("E-" + (++counter), description, paidBy, amount, splits);
        expenses.add(expense);
        for (Split split : splits) {
            balanceSheet.adjust(split.getUser(), paidBy, split.getAmount());
        }
        return expense;
    }

    // Direct payment between two users, outside of any shared expense
    public synchronized void settle(User payer, User receiver, double amount) {
        balanceSheet.adjust(payer, receiver, -amount);
    }

    public List<Expense> getExpenses() { return expenses; }
}
