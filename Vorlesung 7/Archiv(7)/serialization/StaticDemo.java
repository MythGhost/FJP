package serialization;

import util.HexUtil;
import util.SerializationUtil;

import java.io.IOException;
import java.io.Serializable;

@SuppressWarnings("serial")
public class StaticDemo implements Serializable {
    public static int staticState;
    private int state;

    @SuppressWarnings("static-access")
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 1. What's the expected staticState after deserialization?
        final StaticDemo sd = new StaticDemo();
        sd.state = 5;
        sd.staticState = 777;
        System.out.println("Initial state: " + sd.state + ", staticState: " + sd.staticState + "\n");

        final byte[] serial = SerializationUtil.serializeObject(sd);
        System.out.println(HexUtil.dump(serial));

        sd.staticState = 888;

        final StaticDemo deser = (StaticDemo) SerializationUtil.deserializeObject(serial);

        System.out.println("Deserialized state: " + deser.state + ", staticState: " + deser.staticState);
    }
}
