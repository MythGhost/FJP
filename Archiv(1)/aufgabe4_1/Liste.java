package aufgabe4_1;

import java.util.Iterator;

/**
 * Liste is an implementation of a subset of the <tt>List</tt> interface. It's
 * not a complete implementation of all list operations.
 *
 * @param <T> the type of elements held in this collection
 * @author Leonhard Laumeyer
 * @version 1.0, 23.01.2007
 * @see java.util.List
 */
public class Liste<T> implements Iterable<T> {
    protected static class ListElem<T> {
        T data;
        ListElem<T> next;

        ListElem(T data, ListElem<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    protected ListElem<T> header;

    /**
     * Adds the specified element to the top of the list
     *
     * @param data element to be added to the list
     */
    public void add(T data) {
        if (header == null)
            header = new ListElem<T>(data, null);
        else {
            ListElem<T> nle = new ListElem<T>(data, header);
            header = nle;
        }
    }

    /**
     * Removes the first occurrence of the specified element in this list. If the
     * list does not contain the element, it is unchanged. More formally, removes
     * the element with the lowest index <tt>i</tt> such that
     * <tt>(data==null ? get(i)==null : data.equals(get(i)))</tt> (if such an
     * element exists).
     *
     * @param data element to be removed from this list, if present.
     * @return <tt>true</tt> if the list contained the specified element.
     */
    public boolean delete(T data) {
        ListElem<T> le = header;
        ListElem<T> prev = null;

        while (le != null) {
            // check elem
            if (le.data.equals(data))
            // if( le.data == data ) // kann funktionieren !?!?
            {
                // we've got it, delete it!!!
                if (prev == null)
                    header = le.next;
                else
                    prev.next = le.next;

                return true;
            }

            // remember previous element
            prev = le;

            le = le.next;
        }

        return false;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private ListElem<T> iter = header;

            public boolean hasNext() {
                return iter != null;
            }

            public T next() {
                T ret = iter.data;
                iter = iter.next;
                return ret;
            }
        };
    }


    /******************** begin homework *************************/

    /**
     * @return size of list
     */
    public int size() {
        int length = 0;

        ListElem<T> le = header;

        while (le != null) {
            length++;
            le = le.next;
        }
        return length;
    }

    /**
     * first position in list is at index 0
     *
     * @param index position to find
     * @return found element at the specified index position
     * @throws IndexOutOfBoundsException, if there's no element at the specified index position, that means
     *                                    the index is larger than list.size()
     */
    public T get(int index) throws IndexOutOfBoundsException {
        int i = 0;
        ListElem<T> le = header;

        if (index < 0 || le == null)
            throw new IndexOutOfBoundsException("Falscher Index!");

        while (i < index) {
            if (le.next == null)
                throw new IndexOutOfBoundsException("Index " + index
                        + " ist groesser als Anzahl vorhandener Elemente");

            i++;
            le = le.next;
        }

        return le.data;
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element. More
     * formally, returns <tt>true</tt> if and only if this list contains at
     * least one element <tt>e</tt> such that <tt>(data==null ? e==null
     * : data.equals(e))</tt>.
     *
     * @param data element whose presence in this list is to be tested.
     * @return <tt>true</tt> if this list contains the specified element.
     */
    public boolean contains(T data) {
        for (T t : this) {
            if (t.equals(data))
                return true;
        }

        return false;
    }

    /**
     * @param index position to remove
     * @throws IndexOutOfBoundsException
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        T data = null;

        data = get(index);
        delete(data);
    }

    /******************** end homework *************************/

    /**
     * Returns the index in this list of the first occurrence of the specified
     * element, or -1 if the List does not contain this element. More formally,
     * returns the lowest index i such that
     * <tt>(data==null ? get(i)==null : data.equals(get(i)))</tt>, or -1 if
     * there is no such index.
     *
     * @param data element to search for.
     * @return the index in this list of the first occurrence of the specified
     * element, or -1 if the list does not contain this element.
     */
    public int indexOf(T data) {
        ListElem<T> le = header;
        int index = 0;

        while (le != null) {
            // check elem
            // if( le.data == data )
            if (le.data.equals(data))
                break;

            le = le.next;
            index++;
        }

        if (le == null)
            return -1;
        else
            return index;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Liste<Integer> l = new Liste<>();
        l.add(17);
        l.add(11);
        l.add(42);
        l.add(17);
        l.add(99);

        for (Integer i : l) {
            System.out.print(i + " -> ");
        }
        System.out.println();

        System.out.println("List contains 42: " + l.contains(42));

        System.out.println("Size: " + l.size());
        System.out.println("Position: " + l.indexOf(42));
        System.out.println("Delete was " + l.delete(42));
        System.out.println("Position: " + l.indexOf(42));
        System.out.println("Delete was " + l.delete(42));
        System.out.println("Size: " + l.size());
        System.out.println("List contains 42: " + l.contains(42));

        System.out.println("Get(3) :" + l.get(3));

        for (Integer i : l) {
            System.out.print(i + " -> ");
        }
        System.out.println();
    }
}