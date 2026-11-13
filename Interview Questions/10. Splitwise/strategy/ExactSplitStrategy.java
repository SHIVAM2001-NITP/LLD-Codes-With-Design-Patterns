package strategy;

import entities.Split;
import entities.User;
import exceptions.InvalidSplitException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// `values` gives the exact amount each participant owes; they must add up to the total
public class ExactSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> calculateSplits(double totalAmount, List<User> participants, Map<User, Double> values) {
        double sum = 0;
        List<Split> splits = new ArrayList<>();
        for (User user : participants) {
            Double amount = values.get(user);
            if (amount == null) {
                throw new InvalidSplitException("No exact amount given for " + user);
            }
            sum += amount;
            splits.add(new Split(user, amount));
        }
        if (Math.abs(sum - totalAmount) > 0.01) {
            throw new InvalidSplitException("Exact splits (" + sum + ") do not add up to the total (" + totalAmount + ")");
        }
        return splits;
    }
}
