package strategy;

import entities.Split;
import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Divides evenly; the last participant absorbs the rounding remainder so the splits sum exactly
public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> calculateSplits(double totalAmount, List<User> participants, Map<User, Double> values) {
        int n = participants.size();
        long totalCents = Math.round(totalAmount * 100);
        long shareCents = totalCents / n;
        long remainder = totalCents - shareCents * n;

        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long cents = shareCents + (i == n - 1 ? remainder : 0);
            splits.add(new Split(participants.get(i), cents / 100.0));
        }
        return splits;
    }
}
