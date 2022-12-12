package serialization;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SerializableSuperClass /* implements Serializable */ {
    private String superString = null;

    protected String getSuperString() {
        return superString;
    }

    public void setSuperString(String s) {
        this.superString = s;
    }
}
