package shapes;/*
 * Name: 		Jack Mennie
 * Number:		c3238040
 * Class:		shapes.Polygon
 * Implmements:	ComparePoly
 * Description:	stores point objects in an array vertices
 * 				calculates the area of the polygon
 * 				calculates the closest vertice to origin
 * 				compares two polygons A and B. Checks if polygons are same size, bigger or smaller. Returns true,
 * 					if B polygon is smaller then A, or if both the same, checks if the distance of B is closer then A.
 */

public class Polygon extends PlanarShape {
    private Point[] vertices; //array to store the objects

    /**
     * Sets the size of the polygon
     * @param size
     */
    @Override
    public void setSize(int size) {
        vertices = new Point[size+1];
    }

    /**
     * returns the size of the polygon
     * @return size
     */
    @Override
    public int getSize() {
        return vertices.length;
    }

    /**
     * adds a point to a position in the array
     * @param point
     * @param position
     */
    @Override
    public void addPoint(Point point, int position) {
        vertices[position] = point;
    }

    /**
     * adds the first point to the last index
     */
    @Override
    public void addFirstPoint() {
        vertices[vertices.length-1] = vertices[0];
    }

    /**
     * calculates the area of the polygon
     * @return area
     */
    @Override
    public float area() {
        float sum = 0.0f;

        for(int i = 0; i < vertices.length - 1; i++) {
            float x = vertices[i+1].getXCoordinate() + vertices[i].getXCoordinate();
            float y = vertices[i+1].getYCoordinate() - vertices[i].getYCoordinate();

            sum += x*y;
        }

        return Math.abs(sum/2);
    }

    /**
     * finds the point that is closest to the origin
     * @return distance
     */
    @Override
    public float originDistance() {
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
    @Override
    public String toString() {
        String output = "";

        for(int i = 0; i < vertices.length-1; i++) {
            output += vertices[i];
        }

        String formattedArea = String.format("%5.2f", area());

        return "POLY=[" + output + "]: " + formattedArea;
    }
}