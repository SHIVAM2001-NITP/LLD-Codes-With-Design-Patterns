public class PayToFriend extends PaymentFlow{
    @Override
    public void validateRequest() {
        //specific validation for PatToFriend Flow
        System.out.println("Validate logic of PayToFriend");
    }
    @Override
    public void debitAmount() {
        //debit the amount
        System.out.println("debit the Amount logic of PayToFriend");
    }
    @Override
    public void calculateFees() {
        //specific fees calculation logic for PayToFriend flow
        System.out.println("0 % Fee charged");
    }
    @Override
    public void creditAmount() {
        //credit the amount logic
        System.out.println("Credit the Full Amount");
    }
}
