package serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import util.HexUtil;
import util.SerializationUtil;


/**
 * super class gets also serialized automatically
 * (when interface Serializable is inherited)
 */
@SuppressWarnings("serial")
public class SerializableSubClass extends SerializableSuperClass /* implements Serializable */ {
    /* attribute with getter and setter */
    private String subString = null;

    public void setSubString(String subString) {
        this.subString = subString;
    }

    public String getSubString() {
        return subString;
    }

    public String toString() {
        return "[SerializableSubClass: subString=" + getSubString() +
                ", superString=" + getSuperString() + "]";
    }


//    private void readObject(ObjectInputStream oin) throws IOException, ClassNotFoundException {
////    oin.defaultReadObject();
//
//        setSubString((String) oin.readObject());
//        setSuperString((String) oin.readObject());
//    }

//    private void writeObject(ObjectOutputStream oout) throws IOException {
////    oout.defaultWriteObject();
//
//        oout.writeObject(getSubString() + "2");
//        oout.writeObject(getSuperString() + "2");
//    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 0. no Serializable implemented
        // 1. only standard serialization in super class with no own read- + writeObject()
        // 2. standard serialization without Serializable in SuperClass (Serializable now in SubClass)
        // 3. own written read- + writeObject() in SubClass for SubClass variable
        // 4. own written readObject() + writeObject() in SubClass for SubClass and SuperClass variable
        SerializableSubClass ssc = new SerializableSubClass();
        ssc.setSuperString("super");
        ssc.setSubString("sub");

        System.out.println("initial object: " + ssc);

        byte[] serialData = SerializationUtil.serializeObject(ssc);
        System.out.println(HexUtil.dump(serialData));

        SerializableSubClass deserialized =
                (SerializableSubClass) SerializationUtil.deserializeObject(serialData);
        System.out.println("after deserialization: " + deserialized);
    }
}
