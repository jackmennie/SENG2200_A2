package shapes;

public class SemiCircle extends PlanarShape {
    private Point[] vertices;

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
     * @name toString
     * @return string in the form SEMI=[(x,y)(x,y)...]: X.X
     * Precondition: all vertices have a value
     * Postcondition: concatenates all points in array
     * 				  formats area value to 5.2f specifications
     * 				  returns string
     */
    @Override
    public String toString() {
        String temp = "";

        for(int i = 0; i < vertices.length-1; i++){
            temp = temp + vertices[i];
        }

        String area = String.format("%5.2f", area());

        return "SEMI=[" + temp + "]: " + area;
    }

    /**
     * @name calcArea
     * @return area of semicircle
     * Precondition:	all vertices have been set
     * Postcondition:	calculates the area of the semi-circle
     */
    @Override
    public float area() {
        return (float)(Math.PI * Math.pow(calcRadius(), 2))/2;
    }

    /**
     * @name originDistance
     * @return distance
     *
     * Precondition: all vertices have a value
     * Postcondition: - calculates the rise and run of the semi-circle.
     *				  - calculates both end points of semi-circle using the rise and run
     *				  - checks all points in array, and replaces distance variable
     * 				  - if closer one is found.
     *                - returns that value
     */
    @Override
    public float originDistance() {
        float rise = vertices[1].getYCoordinate() - vertices[0].getYCoordinate();
        float run = vertices[1].getXCoordinate() - vertices[0].getXCoordinate();

        Point extrema1 = new Point();
        Point extrema2 = new Point();

        extrema1.setXCoordinate(vertices[0].getXCoordinate()-rise);
        extrema1.setYCoordinate(vertices[0].getYCoordinate()+run);
        extrema2.setXCoordinate(vertices[0].getXCoordinate()+rise);
        extrema2.setYCoordinate(vertices[0].getYCoordinate()-run);

        float distance = vertices[0].getDistance();

        //Check the closest point to origin
        for(int i = 0; i < vertices.length; i++) {
            if (vertices[i].getDistance() < distance)
                distance = vertices[i].getDistance();
        }

        if (extrema1.getDistance() < distance)
            distance = extrema1.getDistance();
        if (extrema2.getDistance() < distance)
            distance = extrema2.getDistance();

        return distance;
    }

    /**
     * @name calcRadius
     * @return radius of the semi-circle
     * Precondition: semi-circle has both points defined
     * Postcondition: calculates the radius using the distance formula.
     */
    private float calcRadius() {
        float radius = (float)Math.pow((vertices[1].getXCoordinate()-vertices[0].getXCoordinate()), 2);
        radius = radius + (float)Math.pow((vertices[1].getYCoordinate()-vertices[0].getYCoordinate()), 2);
        radius = (float)Math.sqrt(radius);

        return radius;
    }


}
