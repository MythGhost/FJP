package composite;

import java.util.Iterator;

import composite.Component;

/**
 * Deklariert ein Interface fuer in der Komposition enthaltene Objekte.
 * Implementiert bei Bedarf das Standardverhalten fuer das Interface, das allen
 * Klassen gemeinsam ist.
 *
 * @role __Component
 */

public abstract class Component {
    /**
     * Composite untergeordnetes Element hinzufuegen
     */
    public void addChild(Component child) {
        // default implementation - throw error
        throw new RuntimeException("addChild(): Not intended to be called");
    }

    /**
     * Aus Composite untergeordnetes Element entfernen
     */
    public void removeChild(Component child) {
        // default implementation - throw error
        throw new RuntimeException("remove(): Not intended to be called");
    }

    /**
     * @return Iterator fuer untergeordnete Elemente dieser Komponente oder
     * <code>null</code>, falls keine untergeordneten Elemente
     * vorhanden sind.
     */
    public Iterator<Component> getChildren() {
        return null;
    }

    /**
     * Beispielmethode
     */
    public abstract void sampleOperation();
}