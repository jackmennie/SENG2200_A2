/*
 * Name:		Jack Mennie
 * Number:		c3238004
 * ClassName:	PA1
 * Description:	scans polygons file and adds the data to the following:
 * 				-gets polygon data and determines how many vertices are in that polygon
 * 				-stores each point in array, which each point object stores x, y coordinate
 * 				-stores each polygon in a list MyPolygons
 * 				-orders the list
 * 				-prints both unordered list, and ordered list
 */

import shapes.Point;
import shapes.Polygon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class PA1 {
    private Scanner input;
    private MyPolygons unorderedList;
    private MyPolygons orderedList;

    public static void main(String[] args) {
        PA1 app = new PA1();

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
        unorderedList = new MyPolygons();
        orderedList = new MyPolygons();

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

        Point point = new Point();      //point is required to be instantiated for cases to work
        Polygon poly = new Polygon();   //polygon is required to be instantiated for cases to work

        /* position is used to add the point to the certain index of the array
           This is done because we set the size of the array after we create the polygon
           The position variable is also used to append the first point to the last point.
         */
        int position = 0;

        //ENUM InputType which determines what step to add the data to
        InputType inputType = InputType.POLYGON;

        while(input.hasNext()) {
            String value  = input.next(); //set the value to the input.next so it can be reused later

            //Ignore space characters
            if(value.equals(" ")) {
                continue;
            }


            //Switch through the steps in the create process
            //Each step will go onto the next process. e.g POLYGON -> SIZE
            switch (inputType) {
                    case POLYGON:
                        //Creates a new shapes.Polygon
                        poly = new Polygon();
                        inputType = InputType.SIZE;
                        break;
                    case SIZE:
                        //Sets the size of the array in polygon
                        poly.setSize(Integer.parseInt(value));
                        inputType = InputType.XCOORDINATE;
                        break;
                    case XCOORDINATE:
                        //Sets the x-coordinate of a point
                        point.setXCoordinate(Float.parseFloat(value));
                        inputType = InputType.YCOORDINATE;
                        break;
                    case YCOORDINATE:
                        //Sets the y-coordinate of a point
                        point.setYCoordinate(Float.parseFloat(value));

                        //shapes.Point now has two values, so add to shapes.Polygon
                        poly.addPoint(point, position);

                        //Reset point so we don't get any reference issues
                        point = new Point();
                        position++;

                        //Check if all points have been added or not
                        if(position == poly.getSize()-1) {
                            //All points have been added, hence:

                            poly.addFirstPoint(); //add the first point to the array
                            unorderedList.append(poly); //append polygon to the list

                            //Set the input type back to polygon to add a new polygon
                            inputType = InputType.POLYGON;
                            position = 0; //reset position to add items to the start of the array
                        } else {
                            //More points to be added
                            inputType = InputType.XCOORDINATE;
                        }
                        break;
            }
        }
        input.close();
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

                for(int i = 0; i < unorderedList.getSize(); i++) {
                    System.out.println("\t" + unorderedList.getCurrent().getData());
                    unorderedList.next();
                }

                unorderedList.reset();
                break;
            case "ordered":
                orderedList.reset();
                System.out.println("4. Printing ordered list");

                for(int i = 0; i < orderedList.getSize(); i++) {
                    System.out.println("\t" + orderedList.getCurrent().getData());
                    orderedList.next();
                }

                orderedList.reset();
                break;
            default: break;
        }
    }

    /***
     * Order list goes through the unorderedList and inserts into the orderedList in ascending order.
     */
    private void orderList() {
        System.out.println("3. Ordering the list");

        //Loop through each item in unordered list and place in new list
        for(int i = 0; i < unorderedList.getSize(); i++) {
            //if list is empty then we dont care where the first item goes so append will suffice
            if(orderedList.getSize() == 0) {
                orderedList.append(unorderedList.getCurrent().getData());
            } else {
                //ensure we are checking from the start of the list
                orderedList.reset();
                //Loop through ordered list to find where to insert the current item
                for(int j = 0; j < orderedList.getSize(); j++) {
                    if (orderedList.getCurrent().getData().compare(unorderedList.getCurrent().getData())) {
                        //Area is smaller or closer to origin hence insert before current, and then go to the next unordered item
                        orderedList.insert(unorderedList.getCurrent().getData());
                        break;
                    } else {
                        /* Until now, j will always be 0 if area is smaller
                            So the last item will always be appended, but then
                            we need to check if items after that will be inserted
                            in the correct spot, hence increasing the size once
                            appended.
                         */
                        if(j == orderedList.getSize()-1) {
                            orderedList.append(unorderedList.getCurrent().getData());
                            j = orderedList.getSize();
                        }

                        orderedList.next();
                    }
                }
            }
            unorderedList.next();
        }
    }
}
