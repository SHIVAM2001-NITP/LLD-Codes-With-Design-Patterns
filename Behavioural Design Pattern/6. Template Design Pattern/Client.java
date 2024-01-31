public class Client {
    public static void main(String[] args){
        PaymentFlow obj1=new PayToFriend();
        obj1.sendMoney();

        PaymentFlow obj2=new PayToMerchantFlow();
        obj2.sendMoney();
    }
}
