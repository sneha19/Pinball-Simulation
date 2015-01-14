/**
 * This class instanciates a new Circle
 * object which is used in all other classes
 * 
 * @author Sneha Ganesh
 * @version 1.0
 */
public class CreateCircle 
{
    private double radius;
    private double[] coordinates;

    /**
     * CreateCircle Constructor which creates
     * a new circle object for use
     *
     * @param radius
     *        which represents the radius of the circle
     * @param coordinates
     *        which represents the coordinates of the circle
     */
    public CreateCircle(double radius, double[] coordinates)
    {
        this.radius = radius;
        this.coordinates = coordinates;
    }

    /**
     * Method getRadius which returns the 
     * radius of the created circle
     *
     * @return radius
     *         which represents the radius of the circle
     */
    public double getRadius()
    {
        return radius;
    }

    /**
     * Method getCoordinates which returns the
     * coordinates of the created circle
     *
     * @return coordinates
     *         which represents the coordinates of the circle
     */
    public double[] getCoordinates()
    {
        return coordinates;
    }
}