public abstract  class PaymentFlow {
    public abstract void validateRequest();
    public abstract void calculateFees();
    public abstract void debitAmount();
    public abstract void creditAmount();
    //this is Template method: which defines the order of steps to execute the task.
    public final void sendMoney(){
        //step 01
        validateRequest();
        //step 02
        debitAmount();
        //step 03
        calculateFees();
        //step 04
        creditAmount();
    }
}
