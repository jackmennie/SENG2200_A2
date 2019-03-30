/*
 * Name: 		Jack Mennie
 * Number:		c3238040
 * Class:		Polygon
 * Implmements:	ComparePoly
 * Description:	stores point objects in an array vertices
 * 				calculates the area of the polygon
 * 				calculates the closest vertice to origin
 * 				compares two polygons A and B. Checks if polygons are same size, bigger or smaller. Returns true,
 * 					if B polygon is smaller then A, or if both the same, checks if the distance of B is closer then A.
 */

public class Polygon implements ComparePoly {
    private Point[] vertices; //array to store the objects

    /**
     * Sets the size of the polygon
     * @param size
     */
    public void setSize(int size) {
        vertices = new Point[size+1];
    }

    /**
     * returns the size of the polygon
     * @return size
     */
    public int getSize() {
        return vertices.length;
    }

    /**
     * adds a point to a position in the array
     * @param point
     * @param position
     */
    public void addPoint(Point point, int position) {
        vertices[position] = point;
    }

    /**
     * adds the first point to the last index
     */
    public void addFirstPoint() {
        vertices[vertices.length-1] = vertices[0];
    }

    /**
     * calculates the area of the polygon
     * @return area
     */
    private float calculateArea() {
        float sum = 0.0f;

        for(int i = 0; i < vertices.length - 1; i++) {
            float x = vertices[i+1].getXCoordinate() + vertices[i].getXCoordinate();
            float y = vertices[i+1].getYCoordinate() + vertices[i].getYCoordinate();

            sum += x*y;
        }

        return Math.abs(sum/2);
    }

    /**
     * finds the point that is closest to the origin
     * @return distance
     */
    private float findClosestToOrigin() {
        float distance = vertices[0].getDistance();

        for(Point point : vertices) {
            if (point.getDistance() < distance)
                distance = point.getDistance();
        }

        return distance;
    }

    /**
     * returns a string which is the polygon in format [(x,y),(x,y)]: area
     * @return output
     */
    public String toString() {
        String output = "";

        for (Point point : vertices) {
            output += point;
        }

        String formattedArea = String.format("%4.2f", calculateArea());

        return "[" + output + "]: " + formattedArea;
    }

    /**
     * used for ordering the lists
     * @param o
     * @return true/false
     */
    @Override
    public boolean compare(Object o) {
        Polygon temp = (Polygon)o;

        float min = this.calculateArea() - this.calculateArea()*0.0005f; //0.05%
        float max = this.calculateArea() + this.calculateArea()*0.0005f;

        System.out.println("MIN: " + min + ", actual: " + this.calculateArea() + ". MAX: " + max);

        if(min < temp.calculateArea() && temp.calculateArea() < max) {
            if(temp.findClosestToOrigin() < this.findClosestToOrigin()) {
                return true;
            }

        } else if(temp.calculateArea() < this.calculateArea()) {
            return true;
        }

        return false;
    }
}