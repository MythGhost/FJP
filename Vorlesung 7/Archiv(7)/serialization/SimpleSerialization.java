package serialization;

import java.io.Serializable;

import util.HexUtil;
import util.SerializationUtil;

@SuppressWarnings("serial")
public class SimpleSerialization implements Serializable {
    private final int state;
    private final String moreState;
    private final transient String transState;

    public SimpleSerialization(int i, String s, String t) {
        state = i;
        moreState = s;
        transState = t;
    }

    public String getStateAsString() {
        return "state=" + state + ", moreState=" + moreState + ", transState=" + transState;
    }

    /**
     * 1. execution without dump()
     * 2. implementation serializeObject()
     */
    public static void main(String[] args) throws Exception {
        final SimpleSerialization simplSer = new SimpleSerialization(5, "Hallo", "HokusPokus");
        System.out.println("Initial object: " + simplSer);  // normal toString with object address
        System.out.println("Initial state: " + simplSer.getStateAsString() + "\n");

        final byte[] serialized = SerializationUtil.serializeObject(simplSer);
        System.out.println("Serialized size: " + serialized.length + " bytes");
        // comment out for details of serialized bytes
//        System.out.println(HexUtil.dump(serialized));

        final SimpleSerialization deser = (SimpleSerialization) SerializationUtil.deserializeObject(serialized);
        System.out.println("Deserialized object: " + deser);  // different object!
        System.out.println("Deserialized state: " + deser.getStateAsString());
    }
}
