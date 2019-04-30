package shapes;

public class Circle extends PlanarShape {
    private Point centre;
    double radius;

    /**
     * @name shapes.Circle
     * Constructor: instantiates centre and radius
     */
    public Circle(){
        super();
        centre = new Point();
        radius = 0;
    }

    /**
     * @name addCircle
     * @param point
     * Precondition: point is provided by text file
     * Postcondition: set the centre point of the circle
     */
    @Override
    public void addPoint(Point point, int position){
        this.centre = point;
    }

    /**
     * @name addRadius
     * @param radius
     * Precondition: parsedouble is parsed in from input
     * Postcondition: sets the radius of the circle
     */
    public void addRadius(double radius) {
        this.radius = radius;
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
    public double area() {
        return (Math.PI * Math.pow(radius, 2));
    }

    /**
     * @name originDistance
     * @return distance
     *
     * Precondition: centre has a value
     * Postcondition: returns the centres distance from origin - radius.
     */
    @Override
    public double originDistance() {
        return centre.getDistance()-radius;
    }

    /** FOLLOWING METHODS NOT NEEDED */
    @Override
    public void setSize(int size) { }

    @Override
    public int getSize() { return 0; }

    @Override
    public void addFirstPoint() {}
}
