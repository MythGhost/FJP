package serialization;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

import util.SerializationUtil;

@SuppressWarnings("serial")
public class Highlander implements Serializable {
    // There can be only one: Singleton!
    private static final Highlander INSTANCE = new Highlander();

    private Highlander() {
    }

    /**
     * Methode wird vor Rueckgabe des Objekts aus dem Stream aufgerufen
     * und kann den Rueckgabewert bestimmen!!!
     *
     * @return
     * @throws ObjectStreamException
     */
//    private Object readResolve() throws ObjectStreamException {
//        return INSTANCE;
//    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 1. without readResolve
        // 2. with readResolve
        System.out.println("The one: " + Highlander.INSTANCE);

        final byte[] serialized = SerializationUtil.serializeObject(Highlander.INSTANCE);

        // now we can create a second Highlander instance!!!
        final Highlander deserialized = (Highlander) SerializationUtil.deserializeObject(serialized);

        System.out.println("Deserialized: " + deserialized);
    }
}
