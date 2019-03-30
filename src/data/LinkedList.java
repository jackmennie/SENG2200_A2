package data;

import shapes.Polygon;

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
    private Node<T> sentinel;
    private int size;

    /**
     * Creates the sentinel node
     */
    public LinkedList() {
        sentinel = new Node<>();
        sentinel.setNext(sentinel);
        sentinel.setPrevious(sentinel);
        size = 0;
    }

    /**
     * adds a node at the end of the list
     * current is still the head of the list
     * @param data
     */
    public void prepend(T data) {
        Node<T> startNode = sentinel.getNext();
        if(size != 0) {
            Node<T> temp = new Node<>(data);
            temp.setNext(startNode);
            temp.setPrevious(sentinel);
            startNode.setPrevious(temp);
            sentinel.setNext(temp);
            size++;
        } else {
            createNewList(data);
        }
    }

    /**
     * creates a node at the start of the list
     * current is still the head of the list
     * @param data
     */
    public void append(T data) {
        Node<T> endNode = sentinel.getPrevious();
        if (size != 0) {
            Node<T> temp = new Node<>(data);
            temp.setNext(sentinel);
            temp.setPrevious(endNode);
            endNode.setNext(temp);
            sentinel.setPrevious(temp);
            size++;
        } else {
            createNewList(data);
        }
    }

    /** returns the current node of the list
     *  and then removes that node
     * @return
     */
    public Node pop() {
        Node returnNode = new Node(current.getData());
        Node temp = new Node(current.getNext().getData());

        current.getPrevious().setNext(current.getNext());
        current.getNext().setPrevious(current.getPrevious());

        current = temp;

        return returnNode;
    }

    /**
     * Returns the size of the list
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Duplicated code for append and prepend
     * This adds an initial node to the list
     * @param poly
     */
    private void createNewList(T data) {
        Node<T> current = new Node<>(data);
        current.setNext(sentinel);
        current.setPrevious(sentinel);
        sentinel.setNext(current);
        sentinel.setPrevious(current);
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
