package data;

public class Node<T> {
    private Node<T> previous;
    private Node<T> next;
    private T data;

    public Node() {
        data = null;
    }

    /**
     * Creates a new data.Node<T> with the data provided
     * @param data
     */
    public Node(T data) {
        this.data = data;
    }

    /**
     * gets the reference to the previous data.Node<T>
     * @return
     */
    public Node<T> getPrevious() {
        return previous;
    }

    /**
     * sets the reference to the previous data.Node<T>
     * @param previous
     */
    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    /**
     * gets the reference to the next data.Node<T>
     * @return
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * sets the reference to the next ndoe
     * @param next
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * returns the data of the data.Node<T>
     * @return
     */
    public T getData() {
        return data;
    }
}
