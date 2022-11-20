package aufgabe4_1;

/**
 * InorderListe is an implementation of a subset of the <tt>List</tt>
 * interface. It's not a complete implementation of all list operations. The
 * difference to the class Liste is, that elements are inserted at the end of
 * the list and not at the beginning.
 * <p>
 * All of the operations perform as could be expected for a sequentially-linked
 * list. Operations that index into the list will traverse the list from the
 * beginning.
 *
 * @param <T> the type of elements held in this collection
 * @author Leonhard Laumeyer
 * @version 1.0, 24.01.2007
 */
public class InorderListe<T> extends Liste<T> {
    /**
     * Referenz auf den letzten Listeneintrag
     */
    protected Liste.ListElem<T> tail;

    /**
     * @see aufgabe4_1.Liste#add(java.lang.Object)
     */
    @Override
    public void add(T data) {
        Liste.ListElem<T> ne = new Liste.ListElem<T>(data, null);

        if (header == null)
            header = tail = ne;
        else {
            tail.next = ne;
            tail = ne;
        }
    }

    /**
     * @see aufgabe4_1.Liste#delete(java.lang.Object)
     */
    @Override
    public boolean delete(T data) {
        ListElem<T> le = header;
        ListElem<T> prev = null;

        while (le != null) {
            // check elem
            if (le.data.equals(data)) {
                // we've got it, delete it!!!
                if (prev == null)
                    header = le.next;
                else {
                    // we want to delete the last list element
                    if (le == tail)
                        tail = prev;

                    prev.next = le.next;
                }

                return true;
            }

            // remember previous element
            prev = le;

            le = le.next;
        }

        return false;
    }
}
