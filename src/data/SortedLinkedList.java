package data;

public class SortedLinkedList<T extends CompareShape<T>> extends LinkedList<T> {

    public SortedLinkedList() {
        super();
    }

    public void insertInOrder(T current) {
            LinkedListIterator iter = this.iterator();
            while(iter.hasNext()) {
                T item = iter.next(); //set the current item in the SORTED list to item


                //checks item in sorted list to the item passed in.
                //if item passed in is smaller, then add
                if(item.compareTo(current)) {
                    //insert(current, iter);
                    return;
                }
            }

            //set the iterator to the sentinel
            iter = iterator();
            //add the item passed in before the sentinel.
            prepend(current);
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