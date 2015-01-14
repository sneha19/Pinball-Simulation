import java.util.LinkedList;
/**
 * This class contains all the major method for the pinball
 * simulation to work. It does 4 vector operations:
 *    1.  adding
 *    2.  subtracting
 *    3.  multiplying by a scaler number
 *    4.  dot product
 * and various other methods for circle intercepts etc.
 * 
 * @author Sneha Ganesh
 * @version 1.0
 */
public class PinballSimulation 
{
    public static CreateCircle circle1; 
    public static CreateCircle circle2;
    public static CreateCircle circle3;

    public static double sides;
    public static double radius;
    
    public PinballSimulation(double sides, double radius)
    {
        this.sides = sides;
        this.radius = radius;
    }
    
    /**
     * Method addVectors adds two vectors together
     *
     * @param vector1
     *        which represents the first vector
     * @param vector2
     *        which represents the second vector
     * @return addition
     *         which represents the sum of the 2 vectors
     */
    public static double[] addVectors(double[] vector1, double[] vector2) 
    {
        double[] addition = new double[vector1.length];

        for (int i = 0; i < vector1.length; i++) 
        {
            addition[i] = vector1[i] + vector2[i];
        }

        return addition;
    }

    /**
     * Method subtractVectors subtracts two vectors from each other
     *
     * @param vector1
     *        which represents the first vector
     * @param vector2
     *        which represents the second vector
     * @return subtract
     *         which represents the difference between the 2 vectors
     */
    public static double[] subtractVector(double[] vector1, double[] vector2) 
    {
        double[] subtract = new double[vector1.length];

        for (int i = 0; i < vector1.length ; i++) 
        {
            subtract[i] = vector1[i] - vector2[i];
        }

        return subtract;
    }

    /**
     * Method multiplyVector multiplies a vector by a scaler
     *
     * @param vector1
     *        which represents the first vector
     * @param number
     *        which represents the scaler
     * @return multiply
     *         which represents the multiplied vector
     */
    public static  double[] multiplyVector(double[] vector1, double number)
    {
        double[] multiply = new double[vector1.length];

        for (int i = 0; i < vector1.length; i++) 
        {
            multiply[i] = vector1[i] * number;
        }

        return multiply;
    }

    /**
     * Method dotProductVector finds the dot product between
     * two vectors
     *
     * @param vector1
     *        which represents the first vector
     * @param vector2
     *        which represents the second vector
     * @return dot
     *         which represents the dot product result
     */
    public static double dotProductVector(double[] vector1, double[] vector2)
    {
        double dot = 0;

        for (int i = 0; i < vector1.length; i++) 
        {
            dot = dot + (vector1[i] * vector2[i]);
        }

        return dot;
    }

    /**
     * Method firstIntersection returns the time it takes for
     * the first intersection to be made
     *
     * @param center
     *        which represents the center of the circle
     * @param radius
     *        which represents the radius of the circle
     * @param temp
     *        which represents a temporary array
     * @param vector
     *        which represents another vector
     * @return intersection
     *         which represents the time of the first intersection
     */
    public static double firstIntersection(double[] center, double radius, double[] temp, double[] vector) 
    {
        double[] subtract = subtractVector(center, temp);
        double dotProduct = dotProductVector(vector, subtract);

        if (dotProduct <= 0)
        {
            return -1;
        }

        double D = Math.pow(dotProduct, 2) - dotProductVector(subtract, subtract) + Math.pow(radius, 2);

        if (D <= 0) 
        {
            return -1;
        }

        double intersection = dotProduct - Math.sqrt(D);      
        return intersection;
    }

    /**
     * Method reflect returns the new vecotiy vector after
     * a particular reflection
     *
     * @param center
     *        which represents the center of a circle
     * @param temp
     *        which represents a temporary array
     * @param vector
     *        which represents another vector
     * @return reflection
     *         which represents the new velocity vector
     */
    public static double[] reflect(double[] center, double[] temp, double[] vector) 
    {
        double[] minimumCenter = subtractVector(center, temp);

        double numerator = dotProductVector(multiplyVector(vector, 2), minimumCenter);
        double denomenator = dotProductVector(minimumCenter, minimumCenter);

        double[] temp2 = multiplyVector(minimumCenter, numerator / denomenator);

        double[] reflection = subtractVector(vector, temp2);
        return reflection;
    }

    /**
     * Method intercept determines whether the two vectors are at an intersect
     *
     * @param vector
     *        which represents the first vector
     * @param location
     *        which represents the location vector
     * @return true/false
     *         which represents whether they intersect or not
     */
    public static boolean intercept(double[] vector, double[] location) 
    {
        if ((firstIntersection(circle1.getCoordinates(), circle1.getRadius(), location, vector) == -1) &&
        (firstIntersection(circle2.getCoordinates(), circle2.getRadius(), location, vector) == -1) &&
        (firstIntersection(circle3.getCoordinates(), circle3.getRadius(), location, vector) == -1)) 
        {
            return false;
        }
        else 
        {
            return true;
        }
    }

    /**
     * Method interceptOne find and returns the smallest 
     * intercept between two vectors
     *
     * @param startingCoordinate
     *        which represents the starting coordinate vector
     * @param velocity
     *        which represents the velocity vector
     * @return circle1/circle2/circle3
     *         which represents the intersect
     */
    public static CreateCircle interceptOne(double[] startingCoordinate, double[] velocity)
    {
        double numberOne = firstIntersection(circle1.getCoordinates(), circle1.getRadius(), startingCoordinate, velocity);
        double numberTwo = firstIntersection(circle2.getCoordinates(), circle2.getRadius(), startingCoordinate, velocity);
        double numberThree = firstIntersection(circle3.getCoordinates(), circle3.getRadius(), startingCoordinate, velocity);

        if (numberOne == -1)
        {
            numberOne = 1000000.00;
        }
        
        if (numberTwo == -1)
        {
            numberTwo = 1000000.00;
        }
        
        if (numberThree == -1)
        {
            numberThree = 1000000.00;
        }

        if (numberOne < numberTwo && numberOne < numberThree) 
        {
            return circle1;
        }
        else if (numberTwo < numberThree)
        {
            return circle2;
        }
        else 
        {
            return circle3;
        }
    }
    
    /**
     * This is a simple method which prints out simple text
     * that is displayed in the terminal window at the start
     * of each simulation
     */
    public void startup()
    {
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        System.out.println("***                                  ***");
        System.out.println("***     PINBALL SIMULATION PROJECT   ***");
        System.out.println("***         ---   RUN   ---          ***");
        System.out.println("***                                  ***");
        System.out.println("***            CREATED BY            ***");
        System.out.println("***           SNEHA GANESH           ***");
        System.out.println("***                                  ***");
        System.out.println("***       MATH 2605: PROF LOUNICI    ***"); 
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        System.out.println("");
        System.out.println("Please wait...large amounts of data being processed");
        System.out.println("");
    }
    
    /**
     * Method targetedRun runs the simulation for a targeted angle selection
     *
     * @param sides
     *        which represents the side lengths of the triangle
     * @param radius
     *        which represents the radius of the circle
     * @param angleTargeted
     *        which represents the selection of angles
     * @param MAX
     *        which represents the number of trials
     */
    public void targetedRun(double sides, double radius, String angleTargeted, int MAX) 
    {       
        startup();
        
        BounceList totalList = new BounceList();
        int[] frequency = new int[MAX];
        for (int i = 0; i < MAX; i++)
        {
            double angle = 2.0 * Math.PI * i / MAX;

            LinkedList<String> temp = new LinkedList<String>();

            double[] startingCoordinate = new double[2];
            startingCoordinate[0] = 0;
            startingCoordinate[1] = 0;
            
            double[] velocity = new double[2];
            velocity[0] = Math.cos(angle);
            velocity[1] = Math.sin(angle);

            double[] cir1 = {sides/2, (-sides * Math.sqrt(3) / 6)};
            circle1 = new CreateCircle(radius, cir1);

            double[] cir2 = {-sides/2 , (-sides * Math.sqrt(3) / 6)};
            circle2 = new CreateCircle(radius, cir2);

            double[] cir3 = {0, (sides * Math.sqrt(3) / 3)};
            circle3 = new CreateCircle(radius, cir3);

            int count = 0;
            
            while (intercept(velocity, startingCoordinate)) 
            {
                CreateCircle temp2 = interceptOne(startingCoordinate, velocity);
                
                if(temp2 == circle1)
                {
                    temp.addLast("Circle 1");
                }
                else if(temp2 == circle2)
                {
                    temp.addLast("Circle 2");
                }
                else
                {
                    temp.addLast("Circle 3");
                }
                
                double variable = firstIntersection(temp2.getCoordinates(), temp2.getRadius(), startingCoordinate, velocity);
                startingCoordinate = addVectors(startingCoordinate, multiplyVector(velocity, variable));
                velocity = reflect(temp2.getCoordinates(), startingCoordinate, velocity);
                count++;
            }

            frequency[count]++;
            Bounce bounce = new Bounce(angle, count, temp);
            totalList.add(bounce);
        }
        
        System.out.println("Number of Boucnes" + "\t" + "  Frequency of Bounce");
        for (int i = 0; i < 1000; i++) 
        {
            System.out.println("\t" + i + "\t \t \t" + frequency[i]);
        }

        System.out.println(totalList);
    }  
    
    /**
     * Method randomRun runs the simulation for a random angle selection
     *
     * @param sides
     *        which represents the side lengths of the triangle
     * @param radius
     *        which represents the radius of the circle
     * @param angleTargeted
     *        which represents the selection of angles
     * @param MAX
     *        which represents the number of trials
     */
    public void randomRun(double sides, double radius, String angleRandom, int MAX) 
    {
        startup();
        
        BounceList totalList = new BounceList();
        int[] frequency = new int[MAX];
        
        for (int i = 0; i < MAX; i++)
        {
            double rand = Math.random();
            double angle = (2.0 * Math.PI * rand);

            LinkedList<String> temp = new LinkedList<String>();

            double[] startingCoordinate = new double[2];
            startingCoordinate[0] = 0;
            startingCoordinate[1] = 0;
            
            double[] velocity = new double[2];
            velocity[0] = Math.cos(angle);
            velocity[1] = Math.sin(angle);

            double[] cir1 = {sides/2, (-sides * Math.sqrt(3) / 6)};
            circle1 = new CreateCircle(radius, cir1);

            double[] cir2 = {-sides/2 , (-sides * Math.sqrt(3) / 6)};
            circle2 = new CreateCircle(radius, cir2);

            double[] cir3 = {0, (sides * Math.sqrt(3) / 3)};
            circle3 = new CreateCircle(radius, cir3);

            int count = 0;
            
            while (intercept(velocity, startingCoordinate)) 
            {
                CreateCircle temp2 = interceptOne(startingCoordinate, velocity);
                
                if(temp2 == circle1)
                {
                    temp.addLast("Circle 1");
                }
                else if(temp2 == circle2)
                {
                    temp.addLast("Circle 2");
                }
                else
                {
                    temp.addLast("Circle 3");
                }
                
                double variable = firstIntersection(temp2.getCoordinates(), temp2.getRadius(), startingCoordinate, velocity);
                startingCoordinate = addVectors(startingCoordinate, multiplyVector(velocity, variable));
                velocity = reflect(temp2.getCoordinates(), startingCoordinate, velocity);
                count++;
            }

            frequency[count]++;
            Bounce bounce = new Bounce(angle, count, temp);
            totalList.add(bounce);
        }
        
        System.out.println("Number of Boucnes" + "\t" + "  Frequency of Bounce");
        for (int i = 0; i < 1000; i++) 
        {
            System.out.println("\t" + i + "\t \t \t" + frequency[i]);
        }

        System.out.println(totalList);
    }
    
    /**
     * Method userRun runs the simulation for a user selected angle selection
     *
     * @param sides
     *        which represents the side lengths of the triangle
     * @param radius
     *        which represents the radius of the circle
     * @param angleTargeted
     *        which represents the selection of angles
     * @param MAX
     *        which represents the number of trials
     */
    public void userRun(double sides, double radius, double angle, int MAX) 
    {
        startup();
        
        BounceList totalList = new BounceList();
        int[] frequency = new int[MAX];
        
        for (int i = 0; i < MAX; i++)
        {           
            LinkedList<String> temp = new LinkedList<String>();

            double[] startingCoordinate = new double[2];
            startingCoordinate[0] = 0;
            startingCoordinate[1] = 0;
            
            double[] velocity = new double[2];
            velocity[0] = Math.cos(angle);
            velocity[1] = Math.sin(angle);

            double[] cir1 = {sides/2, ((-1)*sides * Math.sqrt(3) / 6)};
            circle1 = new CreateCircle(radius, cir1);

            double[] cir2 = {-sides/2 , ((-1)*sides * Math.sqrt(3) / 6)};
            circle2 = new CreateCircle(radius, cir2);

            double[] cir3 = {0, (sides * Math.sqrt(3) / 3)};
            circle3 = new CreateCircle(radius, cir3);

            int count = 0;
            
            while (intercept(velocity, startingCoordinate)) 
            {
                CreateCircle temp2 = interceptOne(startingCoordinate, velocity);
                
                if(temp2 == circle1)
                {
                    temp.addLast("Circle 1");
                }
                else if(temp2 == circle2)
                {
                    temp.addLast("Circle 2");
                }
                else
                {
                    temp.addLast("Circle 3");
                }
                
                double variable = firstIntersection(temp2.getCoordinates(), temp2.getRadius(), startingCoordinate, velocity);
                startingCoordinate = addVectors(startingCoordinate, multiplyVector(velocity, variable));
                velocity = reflect(temp2.getCoordinates(), startingCoordinate, velocity);
                count++;
            }

            frequency[count]++;
            Bounce bounce = new Bounce(angle, count, temp);
            totalList.add(bounce);
        }
        
        System.out.println("Number of Bounces" + "\t" + "  Frequency of Bounce");
        for (int i = 0; i < 1000; i++) 
        {
            System.out.println("\t" + i + "\t \t \t" + frequency[i]);
        }

        System.out.println(totalList);
    }
}