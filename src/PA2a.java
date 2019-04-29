/*
 * Class: 			PA2a.java
 * Name:			Jack Mennie
 * Number:			c3238004
 * Description:		- main class for adding polygons using iterators
 * 					- inputs a text file via command line
 * 					- adds the shape into the list using the shapeFactory function
 * 					- prints the unordered list
 * 					- orders the items in the list
 * 					- prints the ordered list.
 */

import data.LinkedList;
import data.SortedLinkedList;
import shapes.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

public class PA2a {
    private Scanner input;
    private LinkedList<PlanarShape> unorderedList;
    private LinkedList<PlanarShape> orderedList;

    public static void main(String[] args) {
        PA2a app = new PA2a();

        //Read file from arguments
        try {
            String file = args[0];
            app.setUp(file);
        } catch(Exception e) {
            System.out.println("Please enter a data file (e.g. test.txt)");
            System.exit(0);
        }

        app.createList();

        app.orderList();


        app.printList("unordered");
        app.printList("ordered");
    }

    /**
     * Setup instantiates the two lists
     * Sets up the scanner input that reads in the data
     * @param file
     */
    private void setUp(String file)  {
        unorderedList = new LinkedList<>();
        orderedList = new SortedLinkedList<>();

        try {
            input = new Scanner(new BufferedReader(new FileReader("src/"+ file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * create list uses the scanner input and scans through it until there is no more data
     * The data is appended to the list unorderedlist
     */
    private void createList() {
        System.out.println("1. Creating unordered list");

        /**
         * New design, split input in array list so we can directly call the shape factory and create the shape in there
         * The current design from assignment 1 will be too complicated to extend because it is optimised
         * for a polygon, I attempted to keep the same design, but had a check for the instance type, which worked
         * but if we added more shapes that required different reading in implementation then there will be a lot of if
         * statements which would be difficult to maintain.
         */

        while(input.hasNextLine()) {
            String shapeData = input.nextLine();

            System.out.println("SHAPE: " + shapeData);

            unorderedList.append(shapeFactory(shapeData));
        }





//        while(input.hasNext()) {
//            String value  = input.next(); //set the value to the input.next so it can be reused later
//
//            //Ignore space characters
//            if(value.equals(" ")) {
//                continue;
//            }
//
//            //Switch through the steps in the create process
//            //Each step will go onto the next process. e.g POLYGON -> SIZE
//            switch (inputType) {
//                case SHAPE:
//                    System.out.println("CREATING A SHAPE: " + value);
//                    //Creates a new shape
//                    shape = shapeFactory(value);
//
//                    //To keep the same flow, this checks if the type requires the size or not.
//                    if(requireSize(shape)) {
//                        System.out.println("\t Shape is a polygon");
//                        inputType = InputType.SIZE;
//                    } else {
//                        System.out.println("\t Shape is not a polygon");
//                        inputType = InputType.XCOORDINATE;
//                    }
//
//                    break;
//                case SIZE:
//                    //Sets the size of the array in polygon
//                    shape.setSize(Integer.parseInt(value));
//                    inputType = InputType.XCOORDINATE;
//                    break;
//                case XCOORDINATE:
//                    //Sets the x-coordinate of a point
//                    System.out.println("\t Adding x value: " + value);
//                    point.setXCoordinate(Float.parseFloat(value));
//                    inputType = InputType.YCOORDINATE;
//                    break;
//                case YCOORDINATE:
//                    //Sets the y-coordinate of a point
//                    System.out.println("\t Adding y value: " + value);
//                    point.setYCoordinate(Float.parseFloat(value));
//
//                    System.out.println("\t Adding point to the shape: " + point.toString() +  ", Pos: "+  position);
//                    //shapes.Point now has two values, so add to shape
//                    shape.addPoint(point, position);
//
//                    //Reset point so we don't get any reference issues
//                    point = new Point();
//                    position++;
//
//                    if(!requireRadius(shape)) {
//                        //Check if all points have been added or not
//                        if (position == shape.getSize() - 1) {
//                            //All points have been added, hence:
//
//                            shape.addFirstPoint(); //add the first point to the array
//                            unorderedList.append(shape); //append polygon to the list
//
//                            //Set the input type back to polygon to add a new polygon
//                            inputType = InputType.SHAPE;
//                            position = 0; //reset position to add items to the start of the array
//                        } else {
//                            //More points to be added
//                            inputType = InputType.XCOORDINATE;
//
//                        }
//                    } else {
//                        inputType = InputType.RADIUS;
//                    }
//                    break;
//                case RADIUS:
//                    System.out.println("Adding radius: " + value);
//                    ((Circle)shape).addRadius(Float.parseFloat(value));
//
//                    unorderedList.append(shape);
//                    inputType = InputType.SHAPE;
//                    position = 0;
//                    break;
//            }
//        }
        input.close();
    }

    private PlanarShape shapeFactory(String input) {
        String[] shapeData = input.split(" ");

        for(int i = 0; i<shapeData.length;i++) {
            System.out.print(shapeData[i] + "|");
        }

        System.out.println("\n\n");

        Point point = new Point();

        switch(Shapes.getShape(shapeData[0])) {
            case POLYGON: //Polygon will look like:    P SIZE X1 Y1 ... Xn Yn
                PlanarShape polygon = new Polygon(); //0   1  2  3     n-1 n
                InputType coordinate = InputType.XCOORDINATE;   //Set the first coordinate to be stored in x
                polygon.setSize(Integer.parseInt(shapeData[1])); //Set the size of the polygon

                /**
                 * We need to calculate how many points to be added
                 * If the size is 6 (ie. there is 6 points to be added),
                 * then we have 12 values to be added (x,y) for each point
                 */
                int loopSize = (polygon.getSize()-1) * 2 + 1;

                //Add all the points, i=2 because its the first point value in shapeData
                for(int i = 2; i <= loopSize; i++) {
                    switch(coordinate) {
                        case XCOORDINATE:
                            point.setXCoordinate(Double.parseDouble(shapeData[i])); //Add the value for the XCoordinate
                            coordinate = InputType.YCOORDINATE; //Make sure we go to the YCoordinate next
                            break;
                        case YCOORDINATE:
                            point.setYCoordinate(Double.parseDouble(shapeData[i])); //Add the value for the YCoordinate

                            /** Insert created point to the shape at the correct index
                             * For a size 6 shape, i can be max 13, but our array for points is size 6 (including last point)
                             * Hence we need to ensure we don't add at index=13 or this will break.
                             * To add the final point (not including the first point as last), we need to add it to index=5
                             * Hence 13-2 = 11 / 2 = 5.5
                             * Since it is a integer, the decimal point gets taken away, hence
                             * the final value is 5.
                             */
                            polygon.addPoint(point, (i-2)/2);

                            point = new Point(); //reset point
                            coordinate = InputType.XCOORDINATE; //go back to add another XCoordinate
                            break;
                    }
                }

                polygon.addFirstPoint(); //Once finished, add the first point to the last point
                return polygon;
            case CIRCLE:
                /**
                 * NOT REQUIRED FOR PA2a
                 */
                return new Circle();
            case SEMICIRCLE:
                /**
                 * NOT REQUIRED FOR PA2a
                 */
                return new SemiCircle();
            default:
                return null;
        }
    }

    /**
     * Passes in param type which allows you to chose what list to print
     * type='unordered' prints unorderedList
     * type='ordered' prints orderedList
     * @param type
     */
    private void printList(String type) {
        switch(type) {
            case "unordered":
                System.out.println("2. Printing unordered list");

                Iterator<PlanarShape> unorderedListIterator = unorderedList.iterator();

                while(unorderedListIterator.hasNext()) {
                    System.out.println("\t" + unorderedListIterator.next());
                }

                break;
            case "ordered":
                System.out.println("4. Printing ordered list");

                Iterator<PlanarShape> orderedListIterator = orderedList.iterator();

                while(orderedListIterator.hasNext()) {
                    System.out.println("\t" + orderedListIterator.next());
                }

                break;
            default: break;
        }
    }

    /***
     * Order list goes through the unorderedList and inserts into the orderedList in ascending order.
     */
    private void orderList() {
        System.out.println("3. Ordering the list");

        Iterator<PlanarShape> sort = unorderedList.iterator();

        while(sort.hasNext()) {
            ((SortedLinkedList)orderedList).insertInOrder(sort.next());
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
    }
}
