package provider;

// What one provider's attempt came back with - not exposed to the merchant, only used
// internally by the gateway while it walks the fallback chain.
public class PaymentResult {
    private final boolean success;
    private final String providerName;
    private final String message;

    private PaymentResult(boolean success, String providerName, String message) {
        this.success = success;
        this.providerName = providerName;
        this.message = message;
    }

    public static PaymentResult success(String providerName) {
        return new PaymentResult(true, providerName, "approved");
    }

    public static PaymentResult failure(String providerName, String message) {
        return new PaymentResult(false, providerName, message);
    }

    public boolean isSuccess() { return success; }

    public String getProviderName() { return providerName; }

    public String getMessage() { return message; }
}
