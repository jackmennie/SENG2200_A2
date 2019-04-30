package data;

import shapes.PlanarShape;

public class SortedLinkedList<T extends PlanarShape> extends LinkedList<T> {

    public SortedLinkedList() {
        super();
    }

    public void insertInOrder(T data) {
        System.out.println("\t\tIn the insertion sort function");
        Node<T> newNode = new Node<>(data);
        Node<T> currentNode = this.sentinel.getNext();

        while(currentNode != this.sentinel) {
            //checks item in sorted list to the item passed in.
            //if item passed in is smaller, then add
            if(newNode.getData().compareTo(currentNode.getData()) <= 0) {
                //insert
                newNode.setNext(currentNode);
                newNode.setPrevious(currentNode.getPrevious());
                currentNode.getPrevious().setNext(newNode);
                currentNode.setPrevious(newNode);
                break;
            }

            currentNode = currentNode.getNext();
        }

        //Last item to be added
        if (currentNode == this.sentinel) {
            newNode.setPrevious(this.sentinel.getPrevious());
            newNode.setNext(this.sentinel);
            this.sentinel.getPrevious().setNext(newNode);
            this.sentinel.setPrevious(newNode);
        }
    }
}