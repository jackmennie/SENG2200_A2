package shapes;

/*
 * Name: 		Jack Mennie
 * Number:		c3238004
 * Class: 		shapes.Point.java
 * Description:	Holds the x and y cordinate of a polygon vertice.
 * 				Calculates distance of point from origin.
 * 				Returns point to string as (x,y)
 */
public class Point {
    private double xCoordinate;
    private double yCoordinate;


    /**
	 * @name: setX
	 * @param xValue
	 * Precondition: 
	 * Postcondition: sets the point x of the polygon
	 */
	public void setXCoordinate(double xValue) {
		this.xCoordinate = xValue;	
	}
	
	/**
	 * @name setY
	 * @param yValue
	 * Precondition: 
	 * Postcondition: sets the point y of the polygon
	 */
	public void setYCoordinate(double yValue) {
		this.yCoordinate = yValue;	
	}
	
	/**
	 * @name: getX
	 * @return: x
	 * Precondition: X is instantiated 
	 * Postcondition: returns X
	 */
	public double getXCoordinate() {
		return xCoordinate;
	}
	
	/**
	 * @name get Y
	 * @return y
	 * Precondition: Y is instantiated
	 * Postcondition: returns Y
	 */
	public double getYCoordinate() {
		return yCoordinate;
	}
	
	/**
	 * @name getDistance
	 * @return distance
	 * Precondition: x and y both have a set value
	 * Postcondition: returns the distance of point from origin.
	 */
	public double getDistance() {
        double x = Math.pow(0.0 - getXCoordinate(), 2.0);
        double y = Math.pow(0.0 - getYCoordinate(), 2.0);
		return Math.sqrt(x + y);
	}
	
	/**
	 * @name toString
	 * @return return string in form of (x,y)
	 * Precondition: x and y both have set value
	 * Postcondition: returns the point in the form (x,y) after formatting
	 * 				  point data to the 4.2 spec
	 */
	public String toString(){
		String xValue = String.format("%4.2f", getXCoordinate());
		String yValue = String.format("%4.2f", getYCoordinate());
		return "(" + xValue + "," + yValue + ")";
	}
}