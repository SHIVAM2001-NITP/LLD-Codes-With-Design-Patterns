package service;

import enums.Denomination;
import exceptions.InsufficientCashException;

import java.util.EnumMap;
import java.util.Map;
import java.util.TreeMap;

// Holds note counts per denomination and dispenses with a greedy, highest-first algorithm.
// Both the feasibility check and the actual withdrawal use the same simulation, so a rejected
// withdrawal never partially empties the trays.
public class CashDispenser {
    private final Map<Denomination, Integer> notes = new EnumMap<>(Denomination.class);

    public void loadNotes(Denomination denomination, int count) {
        notes.merge(denomination, count, Integer::sum);
    }

    public synchronized Map<Denomination, Integer> withdraw(long amount) {
        if (amount % 100 != 0) {
            throw new InsufficientCashException("Amount must be a multiple of the smallest note (100)");
        }
        Map<Denomination, Integer> plan = plan(amount);
        if (plan == null) {
            throw new InsufficientCashException("Cannot dispense " + amount + " with the notes available");
        }
        plan.forEach((denomination, count) -> notes.merge(denomination, -count, Integer::sum));
        return plan;
    }

    // Greedy from the highest denomination down; returns null if the amount cannot be made exactly
    private Map<Denomination, Integer> plan(long amount) {
        Map<Denomination, Integer> result = new TreeMap<>((a, b) -> b.getValue() - a.getValue());
        long remaining = amount;
        for (Denomination d : Denomination.values()) {
            int available = notes.getOrDefault(d, 0);
            int used = (int) Math.min(available, remaining / d.getValue());
            if (used > 0) {
                result.put(d, used);
                remaining -= (long) used * d.getValue();
            }
        }
        return remaining == 0 ? result : null;
    }
}
