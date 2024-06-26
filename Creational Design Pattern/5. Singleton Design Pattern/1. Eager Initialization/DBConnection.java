public class DBConnection {
    private static DBConnection conObject=new DBConnection();
    //So called Eager Initialization because it is initialized
    // early due to static  so only one object is created when
    // java class is run then only 1 object is created

    private DBConnection(){}

    public static DBConnection getInstance(){
        return conObject;
    }
    //method to expose to create new only 1 Object
    //Does not matter you are using it or not it is linked to class
    // not object due to static
}
