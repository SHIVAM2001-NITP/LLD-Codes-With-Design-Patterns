package provider;

import entities.PaymentRequest;

import java.util.HashSet;
import java.util.Set;

// A stand-in for a real processor. Declines deterministically for a configured set of
// instrument ids, so the demo (and any test) can force a specific provider to fail.
public class MockPaymentProvider implements PaymentProvider {
    private final String name;
    private final Set<String> decliningInstrumentIds;
    private int attempts = 0;

    public MockPaymentProvider(String name, String... decliningInstrumentIds) {
        this.name = name;
        this.decliningInstrumentIds = new HashSet<>();
        for (String id : decliningInstrumentIds) {
            this.decliningInstrumentIds.add(id);
        }
    }

    @Override
    public String getName() { return name; }

    public int getAttempts() { return attempts; }

    @Override
    public PaymentResult charge(PaymentRequest request) {
        attempts++;
        if (decliningInstrumentIds.contains(request.getInstrumentId())) {
            System.out.println("  [" + name + "] declined " + request.getInstrumentId());
            return PaymentResult.failure(name, "card declined by issuer");
        }
        System.out.println("  [" + name + "] approved " + request.getInstrumentId());
        return PaymentResult.success(name);
    }

    @Override
    public PaymentResult refund(String transactionId, double amount) {
        System.out.println("  [" + name + "] refunded " + amount + " for " + transactionId);
        return PaymentResult.success(name);
    }
}
