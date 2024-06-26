public class DBConnection {
    private static DBConnection conObject;
    // Object is not initialized
    //here it is null due to static  so only one object is created when
    // java class is run then only 1 object is created

    private DBConnection(){}

      static DBConnection getInstance(){

        if(conObject==null) {
            synchronized (DBConnection.class) {//When two threads T1 and T2 comes Simultaneously
                // here then here  due to synchronized method it will allow only one
                // thread to comes in and allow others to wait till first one completes its execution
                // at a time if condition is true and then
                // will create one objects only
                // one object to create so it will follow singleton DP

                //Synchronized class is cheaper than synchronized Method and it also checking
                // for Double condition then creates the objects.
                return conObject = new DBConnection();
            }
        }
       return conObject;
    }
    //method to expose to create new only 1 Object
    //Does not matter you are using it or not it is linked to class
    // not object due to static
}
