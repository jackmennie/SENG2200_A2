package shapes;

public class Circle extends PlanarShape {
    private Point centre;
    float radius;

    /**
     * @name shapes.Circle
     * Constructor: instantiates centre and radius
     */
    public Circle(){
        centre = new Point();
        radius = 0;
    }

    /**
     * @name addCircle
     * @param point
     * Precondition: point is provided by text file
     * Postcondition: set the centre point of the circle
     */
    public void addCircle(Point point){
        centre = point;
    }

    /**
     * @name addRadius
     * @param parseFloat
     * Precondition: parseFloat is parsed in from input
     * Postcondition: sets the radius of the circle
     */
    public void addRadius(float parseFloat) {
        radius = parseFloat;
    }

    /**
     * @name toString
     * @return string in the form CIRC=[(x,y)(x,y)...]: X.X
     * Precondition: all vertices have a value
     * Postcondition: formats radius value to 5.2f specifications
     * 				  formats area value to 5.2f specifications
     * 				  returns string
     */
    @Override
    public String toString() {
        String area = String.format("%5.2f", area());
        String rad = String.format("%4.2f", radius);
        return "CIRC=[" + centre + "," + rad + "]: " + area;
    }

    /**
     * @name area
     * @return area of circle
     * Precondition:	radius has been set
     * Postcondition:	calculates the area of the circle
     */
    @Override
    public float area() {
        return (float)(Math.PI * Math.pow(radius, 2));
    }

    /**
     * @name originDistance
     * @return distance
     *
     * Precondition: centre has a value
     * Postcondition: returns the centres distance from origin - radius.
     */
    @Override
    public float originDistance() {
        return centre.getDistance()-radius;
    }
}
