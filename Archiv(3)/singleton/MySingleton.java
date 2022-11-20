package singleton;

/**
 * Repraesentiert eine Singleton-Instanz.
 */

public class MySingleton {
    /**
     * Nimmt die Singleton-Instanz auf.
     */
    private static MySingleton instance;

    /**
     * verhindert die Instantiierung
     */
    private MySingleton() {
        // prevent creation
    }

    /**
     * Gibt eine Singleton-Instanz zurueck.
     *
     * @return Singleton-Instanz
     */
    static public MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }
}