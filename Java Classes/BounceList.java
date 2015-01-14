/**
 * This class initializes a new array to conveniently
 * store all the data from the [reviously made Bounce class
 * 
 * @author Sneha Ganesh
 * @version 1.0
 */
public class BounceList
{
    public static final int maxStorage = 10; 
    private int total;
    private Bounce[] store;

    /**
     * BounceList Constructor which initializes
     * the array
     */
    public BounceList() 
    { 
        store = new Bounce[maxStorage];
        total = 0;
    }

    /**
     * Method toString which allows the user
     * to see the array information in a well displayed and readable
     * manner for his/her convenience
     *
     * @return print
     *         which represents a line of execution
     */
    public String toString() 
    {
        String print = "\n Top 10 Bounces";
        for (int i = 0; i < total; i++) 
        {
            if (i > 0) 
            {
                print = print + "";
            }
            print = print + store[i];
        }
        return print + "\n...Simulation Over";
    }

    /**
     * Method add, adds a new Bounce run
     * to the initialized array above
     *
     * @param bounce
     *        represents a new Bounce Object
     */
    public void add(Bounce bounce) 
    {
        int newTotal = bounce.getBounces();
        
        if (total == maxStorage)
        {
            if (newTotal <= store[total-1].getBounces())
            {
                return;
            }
        }
        else
        {
            total++;
        }
        
        int i = total - 1; 
        
        for (; (i >= 1) && (newTotal > store[i-1].getBounces()); i--)
        {
            store[i] = store[i - 1];    
        }
        
        store[i] = bounce;               
    }
}