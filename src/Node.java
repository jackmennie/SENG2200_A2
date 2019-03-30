public class Node {
    private Node previous;
    private Node next;
    private Polygon data;

    public Node() {
        data = null;
    }

    /**
     * Creates a new node with the data provided
     * @param data
     */
    public Node(Polygon data) {
        this.data = data;
    }

    /**
     * gets the reference to the previous node
     * @return
     */
    public Node getPrevious() {
        return previous;
    }

    /**
     * sets the reference to the previous node
     * @param previous
     */
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    /**
     * gets the reference to the next node
     * @return
     */
    public Node getNext() {
        return next;
    }

    /**
     * sets the reference to the next ndoe
     * @param next
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * returns the data of the node
     * @return
     */
    public Polygon getData() {
        return data;
    }
}
