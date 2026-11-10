import java.io.*;
import java.lang.reflect.Constructor;

public class Main {
    public static void main(String [] args) throws Exception {

        DBConnection connObject1 = DBConnection.INSTANCE;
        DBConnection connObject2 = DBConnection.INSTANCE;
        System.out.println(connObject1 == connObject2);//true
        connObject1.connect();

        // 1) Serialization cannot create a second object: the enum is read back by its NAME,
        //    so we get the same constant, not a copy.
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        new ObjectOutputStream(bytes).writeObject(connObject1);
        DBConnection afterSerialization = (DBConnection) new ObjectInputStream(
                new ByteArrayInputStream(bytes.toByteArray())).readObject();
        System.out.println(connObject1 == afterSerialization);//true

        // 2) Reflection cannot create a second object: the JVM refuses to build enum instances.
        try {
            Constructor<DBConnection> constructor = DBConnection.class.getDeclaredConstructor(String.class, int.class);
            constructor.setAccessible(true);
            constructor.newInstance("HACK", 1);
        } catch (Exception e) {
            System.out.println("Reflection blocked: " + e.getMessage());//Cannot reflectively create enum objects
        }
    }
}
