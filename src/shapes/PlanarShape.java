package shapes;

/*
 * Class: 			shapes.PlanarShape.java
 * Name:			Jack Mennie
 * Number:			c3238004
 * Description:		Planar shape abstract class
 					defines abstract methods for polygon, circle and semi-circle
 					implements comparable for comparing if two shapes are smaller, bigger or equal.
 */
public abstract class PlanarShape implements Comparable<PlanarShape> {
    public abstract String toString();
    public abstract float area();
    public abstract float originDistance();

    public abstract void setSize(int size);
    public abstract int getSize();
    public abstract void addPoint(Point point, int position);
    public abstract void addFirstPoint();

    /**
     * used for ordering the lists
     * @param shape
     * @return true/false
     */
    @Override
    public int compareTo(PlanarShape shape) {
        float min = this.area() - this.area()*0.0005f; //0.05%
        float max = this.area() + this.area()*0.0005f;

        if(min <= shape.area() && shape.area() <= max) {
            if(shape.originDistance() < this.originDistance()) {
                return 1;
            }

        } else if(shape.area() < this.area()) {
            return 1;
        }

        return 0;
    }
}
