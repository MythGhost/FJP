package serialization;

import java.io.*;

import util.HexUtil;
import util.SerializationUtil;

@SuppressWarnings("serial")
public class CustomSerialization implements Serializable {
    private transient int a;
    private int d;
    private boolean b;
    private String s;

    public CustomSerialization(int a, boolean b, String s) {
        this.a = a;
        this.b = b;
        this.s = s;
        d = a + 1;
    }

//    @Serial
//    private void writeObject(ObjectOutputStream out) throws IOException {
////        out.defaultWriteObject();
//        out.writeInt(a);
//        out.writeInt(d);
//        out.writeBoolean(b);
//        out.writeObject(s);
//    }

//    @Serial
//    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
////        in.defaultReadObject();
//        a = in.readInt();
//        d = in.readInt();
//        b = in.readBoolean();
//        s = (String) in.readObject();
//    }

    @Override
    public String toString() {
        return "[CustomSerialization: a=" + a + ", d=" + d + ", b=" + b + ", s=" + s + "]";
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 1. both default
        // 2. transient variable + default
        // 3. transient + manual write- + readObject
        // 4. own implemented read- + writeObject (variant: write only a and read only b)
        // 5. shorter version: read- + writeObject with default-Handling + read/write for transient var
        // 6. manual defaultWrite + custom read
        CustomSerialization testObj = new CustomSerialization(5, true, "Test");
        System.out.println("Initial object: " + testObj);
        byte[] serialized = SerializationUtil.serializeObject(testObj);

        System.out.println(HexUtil.dump(serialized));

        CustomSerialization deserialized = (CustomSerialization) SerializationUtil.deserializeObject(serialized);
        System.out.println("Deserialized object: " + deserialized);
    }
}
