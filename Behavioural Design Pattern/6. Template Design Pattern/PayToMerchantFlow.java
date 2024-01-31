public class PayToMerchantFlow extends PaymentFlow{
    @Override
    public void validateRequest() {
        //specific validation for PayToMerchant Flow
        System.out.println("Validate logic of PayToMerchant");
    }
    @Override
    public void debitAmount() {
        //debit the amount
        System.out.println("debit the Amount logic of PayToMerchant");
    }
    @Override
    public void calculateFees() {
        //specific fees calculation logic for PayToMerchant flow
        System.out.println("2 % Fee charged");
    }
    @Override
    public void creditAmount() {
    //credit the amount logic
        System.out.println("Credit the remaining Amount");
    }
}
