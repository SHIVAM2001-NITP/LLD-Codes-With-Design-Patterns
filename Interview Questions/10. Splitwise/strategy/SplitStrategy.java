package strategy;

import entities.Split;
import entities.User;

import java.util.List;
import java.util.Map;

// Strategy: turns a total amount and a list of participants into each one's Split.
// `values` carries whatever extra input the strategy needs (ignored by EQUAL).
public interface SplitStrategy {
    List<Split> calculateSplits(double totalAmount, List<User> participants, Map<User, Double> values);
}
