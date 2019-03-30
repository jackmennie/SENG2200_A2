/*
 * Name: 		Jack Mennie
 * Number:		c3238040
 * Class:		MyPolygons
 * Description:	implements a circular doubly linked data structure
 *              contains a single sentinel node to mark the head/tail of container for the polygon objects
 */

public class MyPolygons {
    private Node sentinel;
    private Node current;
    private int size;

    /**
     * Creates the sentinel node
     */
    public MyPolygons() {
        sentinel = new Node();
    }

    /**
     * adds a node at the end of the list
     * current is still the head of the list
     * @param poly
     */
    public void prepend(Polygon poly) {
        Node startNode = sentinel.getNext();
        if(size != 0) {
            Node temp = new Node(poly);
            temp.setNext(startNode);
            temp.setPrevious(sentinel);
            startNode.setPrevious(temp);
            sentinel.setNext(temp);
            current = sentinel.getNext();
            size++;
        } else {
            createNewList(poly);
        }
    }

    /**
     * creates a node at the start of the list
     * current is still the head of the list
     * @param poly
     */
    public void append(Polygon poly) {
        Node endNode = sentinel.getPrevious();
        if (size != 0) {
            Node temp = new Node(poly);
            temp.setNext(sentinel);
            temp.setPrevious(endNode);
            endNode.setNext(temp);
            sentinel.setPrevious(temp);
            current = sentinel.getNext();
            size++;
        } else {
            createNewList(poly);
        }
    }

    /**
     * inserts a node before the current node
     * and then sets that node the new current
     * @param poly
     */
    public void insert(Polygon poly) {
        Node temp = new Node(poly);
        temp.setNext(current);
        temp.setPrevious(current.getPrevious());
        current.getPrevious().setNext(temp);
        current.setPrevious(temp);
        current = temp;
        size++;
    }

    /**
     * sets the next item in the list the current node
     */
    public void next() {
        if(current.getNext() != sentinel) {
            current = current.getNext();
        } else {
            current = sentinel.getNext();
        }
    }

    /**
     * resets the current node to the head of the list
     */
    public void reset() {
        current = sentinel.getNext();
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
     * returns the current node of the list
     * @return
     */
    public Node getCurrent() {
        return current;
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
    private void createNewList(Polygon poly) {
        current = new Node(poly);
        current.setNext(sentinel);
        current.setPrevious(sentinel);
        sentinel.setNext(current);
        sentinel.setPrevious(current);
        size++;
    }
}
