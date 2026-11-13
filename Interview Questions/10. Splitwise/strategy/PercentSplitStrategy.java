package strategy;

import entities.Split;
import entities.User;
import exceptions.InvalidSplitException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// `values` gives each participant's percentage; they must add up to 100
public class PercentSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> calculateSplits(double totalAmount, List<User> participants, Map<User, Double> values) {
        double totalPercent = 0;
        for (User user : participants) {
            Double percent = values.get(user);
            if (percent == null) {
                throw new InvalidSplitException("No percentage given for " + user);
            }
            totalPercent += percent;
        }
        if (Math.abs(totalPercent - 100.0) > 0.01) {
            throw new InvalidSplitException("Percentages add up to " + totalPercent + ", not 100");
        }

        List<Split> splits = new ArrayList<>();
        double runningTotal = 0;
        for (int i = 0; i < participants.size(); i++) {
            User user = participants.get(i);
            double amount;
            if (i == participants.size() - 1) {
                amount = Math.round((totalAmount - runningTotal) * 100) / 100.0;   // absorb rounding
            } else {
                amount = Math.round(totalAmount * values.get(user) / 100.0 * 100) / 100.0;
                runningTotal += amount;
            }
            splits.add(new Split(user, amount));
        }
        return splits;
    }
}
