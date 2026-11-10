public class Main {
    public static void main(String [] args){

        DBConnection connObject1=DBConnection.getInstance();
        DBConnection connObject2=DBConnection.getInstance();

        // Both references point to the very same object
        System.out.println(connObject1 == connObject2);//true
    }
}
