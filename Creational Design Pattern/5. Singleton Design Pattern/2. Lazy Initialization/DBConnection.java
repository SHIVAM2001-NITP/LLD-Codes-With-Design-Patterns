public class DBConnection {
    private static DBConnection conObject;
    //So called Lazy Initialization because it is not initialized
    // early here it is null due to static  so only one object is created when
    // java class is run then only 1 object is created

    private DBConnection(){}

    public static DBConnection getInstance(){
        if(conObject==null){//When two threads T1 and T2 comes Simultaneously
            // here then here if condition for both is true and then they both
            // will create two different objects but singleton will allow only
            // one object to create so it will not follow singleton DP
            return conObject=new DBConnection();
        }
       return conObject;
    }
    //method to expose to create new only 1 Object
    //Does not matter you are using it or not it is linked to class
    // not object due to static
}
