package data;
import java.util.Iterator;

/*
 * Name:		Jack Mennie
 * Number:		c3238004
 * Class:		data.LinkedList
 * Description:	- circular linked-list
 * 				- creates a new iterator
 * 				- contains iterator
 */

public class LinkedList<T> implements Iterable<T> {
    protected Node<T> sentinel;
    protected int size;

    /**
     * Creates the sentinel node
     */
    public LinkedList() {
        sentinel = new Node<>(null);
        sentinel.setNext(sentinel);
        sentinel.setPrevious(sentinel);
        size = 0;
    }

    /**
     * adds a node at the start of the list
     * current is still the head of the list
     * @param data
     */
    public void prepend(T data) {
        Node<T> startNode = sentinel.getNext();
        Node<T> temp = new Node<>(data);

        temp.setNext(startNode);
        temp.setPrevious(sentinel);
        startNode.setPrevious(temp);
        sentinel.setNext(temp);

        size++;
    }

    /**
     * creates a node at the end of the list
     * current is still the head of the list
     * @param data
     */
    public void append(T data) {
        Node<T> endNode = sentinel.getPrevious();
        Node<T> temp = new Node<>(data);

        temp.setNext(sentinel);
        temp.setPrevious(endNode);
        endNode.setNext(temp);
        sentinel.setPrevious(temp);

        size++;
    }

    /** returns the current node of the list
     *  and then removes that node
     * @return
     */
    public Node pop(LinkedListIterator iter) {
//        Node returnNode = new Node(iter.current.getData());
//        Node temp = new Node(iter.current.getNext().getData());
//
//        current.getPrevious().setNext(current.getNext());
//        current.getNext().setPrevious(current.getPrevious());
//
//        current = temp;

        return null;
    }

    /**
     * Returns the size of the list
     * @return
     */
    public int getSize() {
        return size;
    }

    @Override
    public LinkedListIterator iterator() {
        return new LinkedListIterator();
    }

    protected class LinkedListIterator implements Iterator<T> {
        private Node<T> current; //Iterator node
        private LinkedListIterator() {
            super();
            current = sentinel;
        }

        @Override
        public boolean hasNext() {
            if (current.getNext()!=sentinel) //checks for all items until list has reached sentinel.
                return true;
            else
                return false;
        }

        /**
         * sets current to current.getNext()
         * returns the currents data.
         */
        @Override
        public T next() {
            current = current.getNext();
            return current.getData();
        }
    }
}
