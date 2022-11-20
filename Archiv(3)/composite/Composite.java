package composite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Definiert das Verhalten von Komponenten mit untergeordneten Elementen.
 * Speichert untergeordnete Komponenten. Implementiert auf untergeordnete
 * Elemente bezogene Operationen im Interface Component.
 *
 * @role __Composite
 */

public class Composite extends Component {

    /**
     * Speichert Komponenten dieser Composite-Klasse
     *
     * @link aggregation
     * @associates Component
     */
    private ArrayList<Component> components;

    public void addChild(Component child) {
        if (components == null) {
            components = new ArrayList<Component>();
        }
        components.add(child);
    }

    public void removeChild(Component child) {
        if (components != null) {
            components.remove(child);
        }
    }

    public Iterator<Component> getChildren() {
        if (components == null) {
            return null;
        }
        return components.iterator();
    }

    public void sampleOperation() {
        Iterator<Component> it = getChildren();
        while (it.hasNext()) {
            ((Component) it.next()).sampleOperation();
        }
    }
}