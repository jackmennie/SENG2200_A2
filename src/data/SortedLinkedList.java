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

        System.out.println("Comparing data in: " + newNode.getData() + " against current: " + currentNode.getData());

            //LinkedListIterator iter = this.iterator();
            while(currentNode != this.sentinel) {
                System.out.println("current node is not sentinel");
                //checks item in sorted list to the item passed in.
                //if item passed in is smaller, then add
                if(newNode.getData().compareTo(currentNode.getData()) <= 0) {
                    System.out.println("data is bigger :)");
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

//        //Loop through each item in unordered list and place in new list
//        for(int i = 0; i < unorderedList.getSize(); i++) {
//            //if list is empty then we dont care where the first item goes so append will suffice
//            if(orderedList.getSize() == 0) {
//                orderedList.append(unorderedList.getCurrent().getData());
//            } else {
//                //ensure we are checking from the start of the list
//                orderedList.reset();
//                //Loop through ordered list to find where to insert the current item
//                for(int j = 0; j < orderedList.getSize(); j++) {
//                    if (orderedList.getCurrent().getData().compare(unorderedList.getCurrent().getData())) {
//                        //Area is smaller or closer to origin hence insert before current, and then go to the next unordered item
//                        orderedList.insert(unorderedList.getCurrent().getData());
//                        break;
//                    } else {
//                        /* Until now, j will always be 0 if area is smaller
//                            So the last item will always be appended, but then
//                            we need to check if items after that will be inserted
//                            in the correct spot, hence increasing the size once
//                            appended.
//                         */
//                        if(j == orderedList.getSize()-1) {
//                            orderedList.append(unorderedList.getCurrent().getData());
//                            j = orderedList.getSize();
//                        }
//
//                        orderedList.next();
//                    }
//                }
//            }
//            unorderedList.next();
//        }