package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {
    private SerializationUtil() {
        // avoid instantiation
    }

    public static byte[] serializeObject(Object o) throws IOException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final ObjectOutputStream objOut = new ObjectOutputStream(bos);

        objOut.writeObject(o);
        objOut.flush();
        objOut.close();

        return bos.toByteArray();
    }

    public static Object deserializeObject(byte[] data) throws IOException, ClassNotFoundException {
        final ByteArrayInputStream bin = new ByteArrayInputStream(data);
        final ObjectInputStream objIn = new ObjectInputStream(bin);

        return objIn.readObject();
    }
}
