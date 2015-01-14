import java.util.LinkedList;
/**
 * This class sets up the user display nicely by first storing 
 * the angle, number of bounces and a list of all the circles 
 * used and prints them in a user friendly manner.
 * 
 * @author Sneha Ganesh
 * @version 1.0
 */
public class Bounce 
{    
    private double angle;
    private int bounces;
    private LinkedList<String> insertStore;

    /**
     * Bounce Constructor which sets up 
     * the following parameters
     *
     * @param angle
     *        which represents the angle in radians
     * @param bounces
     *        which represents the number of bounces
     * @param insertStore
     *        which stores all the circle insertions
     */
    public Bounce(double angle, int bounces, LinkedList<String> insertStore) 
    {
        this.angle = angle;
        this.bounces = bounces;
        this.insertStore = insertStore;
    }

    /**
     * Method getAngle which returns the
     * angle in radians (decimal format)
     *
     * @return angle
     *         which represents the angle recorded
     *         to 16 decimal places
     */
    public double getAngle()
    {
        return angle;
    }

    /**
     * Method getBounces which returns the
     * number of bounces (integer format)
     *
     * @return bounces
     */
    public int getBounces()
    { 
        return bounces;
    }

    /**
     * Method setBounces which sets the
     * number of bounces based on user input
     *
     * @param bounces
     *        which represents the number of bounces
     */
    public void setBounces(int bounces)
    {
        this.bounces = bounces;
    }

    /**
     * Method toString which prints out all the 
     * information in a simple user friendly manner
     *
     * @return print
     *         which represents a line of the execution
     */
    public String toString() 
    {
        String print = "";
        String angleString = Double.toString(angle);
        
        for(int i = 0; i<18; i++)
        {
            if(angleString.length() == i)
            {
                for(int j = i; j<18; j++)
                {
                    angleString = angleString + "0";
                }
            }
        }

        if(bounces < 10)
        {
            print = "\n |*| " + angleString + " radians, " +  "0" + bounces + " bounces |*| ";
        }
        else
        {
            print = "\n |*| " + angleString + " radians, " + bounces + " bounces |*| ";
        }

        for (int i=0; i < insertStore.size(); i++)
        {
            print = print + insertStore.get(i);
            if(i == insertStore.size() -1)
            {
                print = print + " ";
            }
            else
            {
                print = print + ": ";
            }
        }

        return print; 
    }
}