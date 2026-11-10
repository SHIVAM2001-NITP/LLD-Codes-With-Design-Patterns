public class DBConnection {
    // volatile is REQUIRED for double-checked locking to be correct:
    //  1) "conObject = new DBConnection()" is NOT one atomic step. It is three steps:
    //        a) allocate memory for the object
    //        b) run the constructor (initialise the fields)
    //        c) assign the reference to conObject
    //     The compiler / JIT / CPU are allowed to reorder b) and c).
    //  2) Without volatile, thread T1 could publish the reference (step c) before the
    //     constructor has finished (step b). Thread T2, doing the first UNsynchronized
    //     check, would then see conObject != null, skip the lock and use a half-built object.
    //  3) volatile forbids that reordering (the constructor's writes happen-before any read
    //     of conObject) and makes the new value visible to all threads immediately.
    private static volatile DBConnection conObject;
    // Object is not initialized
    //here it is null due to static  so only one object is created when
    // java class is run then only 1 object is created

    private DBConnection(){}

    static DBConnection getInstance(){

        if(conObject==null) {//1st check (no lock): fast path, almost every call returns here after the object exists
            synchronized (DBConnection.class) {//When two threads T1 and T2 comes Simultaneously
                // here then here  due to synchronized block it will allow only one
                // thread to comes in and allow others to wait till first one completes its execution
                // Synchronized block is cheaper than synchronized Method because the lock is taken
                // only while the object is still null.

                if(conObject==null) {//2nd check (with lock): T2 may have been waiting while T1 created the
                    // object, so without this check T2 would create a second object.
                    conObject = new DBConnection();
                }
            }
        }
       return conObject;
    }
    //method to expose to create new only 1 Object
    //Does not matter you are using it or not it is linked to class
    // not object due to static
}
