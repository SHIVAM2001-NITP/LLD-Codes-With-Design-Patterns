package entities;

import java.util.ArrayList;
import java.util.List;

public class Over {
    private final String bowler;
    private final List<Delivery> deliveries = new ArrayList<>();

    public Over(String bowler) {
        this.bowler = bowler;
    }

    public String getBowler() { return bowler; }

    public List<Delivery> getDeliveries() { return deliveries; }

    public void add(Delivery delivery) { deliveries.add(delivery); }

    public int legalBallCount() {
        int count = 0;
        for (Delivery d : deliveries) {
            if (d.isLegal()) {
                count++;
            }
        }
        return count;
    }

    public boolean isComplete() { return legalBallCount() == 6; }
}
