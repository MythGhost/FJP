package factory;

/*
 * GUIFactory example
 */
abstract class AbstractGUIFactory {
    // zusaetzliche variable Factoryerzeugung
    // oft auch unterschiedliche factory-Methoden als Schnittstelle,
    // z.B. createWinButton und createOSXButton
    public static AbstractGUIFactory getFactory() {
        int sys = 1;
        // int sys = readFromConfigFile("OS_TYPE");

        if (sys == 0) {
            return new WinFactory();
        } else {
            return new OSXFactory();
        }
    }

    // gemaess Konvention besser createInstance()!!!
    public abstract Button createButton();
}

class WinFactory extends AbstractGUIFactory {
    public Button createButton() {
        return new WinButton();
    }
}

class OSXFactory extends AbstractGUIFactory {
    public Button createButton() {
        return new OSXButton();
    }
}

abstract class Button {
    public abstract void paint();
}

class WinButton extends Button {
    public void paint() {
        System.out.println("I'm a WinButton!");
    }
}

class OSXButton extends Button {
    public void paint() {
        System.out.println("I'm an OSXButton!");
    }
}

public class GUIFactory {
    public static void main(String[] args) {
        AbstractGUIFactory factory = AbstractGUIFactory.getFactory();
        Button button = factory.createButton();
        button.paint();
    }
    // Output is either:
    // "I'm a WinButton:"
    // or:
    // "I'm an OSXButton:"
}
