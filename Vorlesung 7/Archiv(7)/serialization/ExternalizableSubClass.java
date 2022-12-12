package serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import util.HexUtil;
import util.SerializationUtil;

/**
 * super class gets not serialized automatically
 */
@SuppressWarnings("serial")
public class ExternalizableSubClass extends SerializableSuperClass implements Externalizable {
    private String subString = null;

    //  @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // we have to do the work for the super class ourselves
        setSuperString((String) in.readObject());
        setSubString((String) in.readObject());
    }

    //  @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // we have to do the work for the super class ourselves
        out.writeObject(getSuperString() + "3");      // accessible?
        out.writeObject(getSubString() + "3");
    }

    /**
     * @return the subString
     */
    public String getSubString() {
        return subString;
    }

    /**
     * @param subString
     */
    public void setSubString(String subString) {
        this.subString = subString;
    }

    public String toString() {
        return "[ExternalizableSubClass: subString=" + subString + ", superString=" +
                getSuperString() + "]";
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // just run it (use HexUtil.dump()) with no Serializable in Super Class
        ExternalizableSubClass ssc = new ExternalizableSubClass();
        ssc.setSuperString("super");
        ssc.setSubString("sub");

        System.out.println("initial object: " + ssc);

        byte[] serialData = SerializationUtil.serializeObject(ssc);
        System.out.println(HexUtil.dump(serialData));

        ExternalizableSubClass deserialized =
                (ExternalizableSubClass) SerializationUtil.deserializeObject(serialData);
        System.out.println("after deserialization: " + deserialized);
    }
}
