package serialization;

import java.io.*;

public class SerMe implements Serializable {
    // IntelliJ:  enable option Preferences -> Editor -> Inspections -> JVM Languages-> "Serializable class without ..."
//    @Serial
//    private static final long serialVersionUID = 1664747137790564524L;

    int i;
//    double d; // new attribute in next version

    SerMe(int i) {
        this.i = i;
    }

    public String toString() {
    return "SerMe: i: " + i;
//        return "SerMe: i: " + i + ", d: " + d;
    }

//    public void justAnotherMethod() {
//        System.out.println("Hello SerMe!");
//    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 1. one attribute int i, with write, no serialVerID
        // 2. no write, read is o.k.
        // 3. problem already with additional method justAnotherMethod
        // 4. additional double d (+ toString)
        // 5. additional serialVerID

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serialver.ser"));
        oos.writeObject(new SerMe(5));
        System.out.println("SerMe written to file");
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serialver.ser"));
        SerMe s = (SerMe) ois.readObject();
        System.out.println(s);
        ois.close();
    }
}
