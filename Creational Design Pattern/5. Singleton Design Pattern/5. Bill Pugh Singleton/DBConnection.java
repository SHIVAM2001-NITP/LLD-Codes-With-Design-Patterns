public class DBConnection {

    private DBConnection(){}

    // Bill Pugh Singleton (initialization-on-demand holder idiom).
    // The nested class is NOT loaded when DBConnection is loaded, only when getInstance()
    // touches DBConnectionHolder for the first time. So the object is created lazily.
    // The JVM guarantees that class initialisation is thread-safe, so we need no
    // synchronized, no volatile and no double check, and every call is just a field read.
    private static class DBConnectionHolder {
        private static final DBConnection INSTANCE = new DBConnection();
    }

    public static DBConnection getInstance(){
        return DBConnectionHolder.INSTANCE;
    }
}
