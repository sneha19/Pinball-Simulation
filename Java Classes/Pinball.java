import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * This class sets up the applet view for the pinball simulation
 * which includes the use of buttons, radiobuttons, labels and textfields
 * It also calls methods from the pinballSimulation class and provides a 
 * simple user interface for the given game
 * 
 * @author Sneha Ganesh
 * @version 1.0
 */
public class Pinball extends JPanel
{
    private JLabel title;   
    private JLabel name;   
    private JLabel numberOfSides;
    private JLabel radiusOfCircle;
    private JLabel angleOfLaunch;
    private JLabel numberOfTrials;
    private JLabel descriptionOne;
    private JLabel descriptionTwo;

    private JTextField sides;
    private JTextField radius;
    private JTextField angle;
    private JTextField trials;

    private JButton run;
    private JButton clear;

    private JRadioButton random;
    private JRadioButton targeted;
    private JRadioButton user;

    /**
     * Pinball Constructor which sets up a variety of 
     * elements for the applet
     */
    public Pinball()
    {
        setSize(650,350);
        setBackground(Color.BLACK);

        title = new JLabel("Math 2605: Pinball Simulation");
        title.setFont(new Font("Biondi", Font.BOLD, 15));
        title.setForeground(Color.GREEN);

        name = new JLabel("By Sneha Ganesh");
        name.setFont(new Font("Calibri", Font.ITALIC, 15));
        name.setForeground(Color.GREEN);

        numberOfSides = new JLabel("Number of Sides:");
        numberOfSides.setFont(new Font("Calibri", Font.PLAIN, 14));
        numberOfSides.setForeground(Color.WHITE);

        radiusOfCircle = new JLabel("Radius of Circle:");
        radiusOfCircle.setFont(new Font("Calibri", Font.PLAIN, 14));
        radiusOfCircle.setForeground(Color.WHITE);

        angleOfLaunch = new JLabel("Angle of Launch:");
        angleOfLaunch.setFont(new Font("Calibri", Font.PLAIN, 14));
        angleOfLaunch.setForeground(Color.WHITE);

        numberOfTrials = new JLabel("Number of Trials:");
        numberOfTrials.setFont(new Font("Calibri", Font.PLAIN, 14));
        numberOfTrials.setForeground(Color.WHITE);

        descriptionOne = new JLabel("This program runs either a pinball simulation based on the data provided or your own data. If you want to use 'user data' select the");
        descriptionOne.setFont(new Font("Kalinga", Font.ITALIC, 14));
        descriptionOne.setForeground(Color.WHITE);

        descriptionTwo = new JLabel("box and make sure the number of trials equals 10000000. For a Targeted or Random run check the boxes below. Default data is provided.");
        descriptionTwo.setFont(new Font("Kalinga", Font.ITALIC, 14));
        descriptionTwo.setForeground(Color.WHITE);

        sides = new JTextField(10);
        radius = new JTextField(10);
        angle = new JTextField(10);
        trials = new JTextField(10);

        run = new JButton("Run Simulation");
        run.addActionListener(new ButtonListener());
        clear = new JButton("Clear Terminal Window");
        clear.addActionListener(new ButtonListener());

        random = new JRadioButton ("For Random Data, Check this Box");
        random.setActionCommand("Random");
        targeted = new JRadioButton ("For Targeted Data, Check this Box");
        targeted.setActionCommand("Targeted");
        user = new JRadioButton ("For User Data, Check this Box");
        user.setActionCommand("User");

        ButtonGroup group = new ButtonGroup();
        group.add(random);
        group.add(targeted);
        group.add(user);

        random.addActionListener(new RadioListener());
        targeted.addActionListener(new RadioListener());
        user.addActionListener(new RadioListener());

        add(title);
        add(name);
        add(numberOfSides);
        add(radiusOfCircle);
        add(angleOfLaunch);
        add(numberOfTrials);
        add(descriptionOne);
        add(descriptionTwo);
        add(sides);
        add(radius);
        add(angle);
        add(trials);
        add(run);
        add(clear);
        add(random);
        add(targeted);
        add(user);
    }

    /**
     * Method paintComponent which defines the location for all the elements
     * created in the applet
     *
     * @param paint
     *        which is the paint graphics object
     */
    public void paintComponent(Graphics paint)
    {
        super.paintComponent(paint);

        title.setBounds(300, 10, 300, 20);
        name.setBounds(500, 30, 200, 20);

        numberOfSides.setBounds(80, 150, 100, 20);
        sides.setBounds(180, 150, 80, 20);

        radiusOfCircle.setBounds(280, 150, 100, 20);
        radius.setBounds(380, 150, 80, 20);

        angleOfLaunch.setBounds(480, 150, 100, 20);
        angle.setBounds(580, 150, 80, 20);

        numberOfTrials.setBounds(680, 150, 100, 20);
        trials.setBounds(780, 150, 80, 20);

        descriptionOne.setBounds(50, 85, 800, 20);
        descriptionTwo.setBounds(25, 105, 920, 20);

        random.setBounds(100, 185, 215, 20);
        targeted.setBounds(345, 185, 232, 20);
        user.setBounds(607, 185, 198, 20);

        run.setBounds(520, 215, 150, 20);
        clear.setBounds(680, 215, 180, 20);
    }

    /**
     * This is an internal private class RadioListener which determines
     * what takes place when a radio button is selected
     * 
     * @author Sneha Ganesh
     * @version 1.0
     */
    private class RadioListener implements ActionListener
    {
        /**
         * Method actionPerformed determines the action that
         * takes place when a button is selected
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e)
        {
            if(random.isSelected())
            {
                sides.setText("6.0");
                radius.setText("1.0");
                angle.setText("Random");
                angle.setEditable(false);
                trials.setText("10000000");

                descriptionOne.setText("You have selected Random Data Mode. Default data is provided but feel free to edit the sides and radius boxes. You can also edit the");
                descriptionTwo.setText("number of trials box but make sure the value entered is > 1000. For convenience, keep the default value. Do not close the terminal window in BlueJ!");
            }
            else if(targeted.isSelected())
            {
                sides.setText("6.0");
                radius.setText("1.0");
                angle.setText("Targeted");
                angle.setEditable(false);
                trials.setText("10000000");

                descriptionOne.setText("You have selected Targeted Data Mode. Default data is provided but feel free to edit the sides and radius boxes. You can also edit the");
                descriptionTwo.setText("number of trials box but make sure the value entered is > 1000. For convenience, keep the default value. Do not close the terminal window in BlueJ!");
            }
            else
            {
                sides.setText("");
                radius.setText("");
                angle.setText("");
                angle.setEditable(true);
                trials.setText("10000000");

                descriptionOne.setText("You have selected User Data Mode. Please fill in all the boxes required. Remember that the angle entered should be in radians. You can");
                descriptionTwo.setText("also edit the number of trials box but make sure the value entered > 1000. For convenience, keep the default value. Do not close the terminal window in BlueJ!");
            }
        }
    }

    /**
     * This is an internal private class ButtonListener which determines
     * what happens when a button is clicked
     * 
     * @author Sneha Ganesh
     * @version 1.0
     */
    private class ButtonListener implements ActionListener
    {
        /**
         * Method actionPerformed determines the action 
         * of a button click
         *
         * @param event
         */
        public void actionPerformed (ActionEvent event)
        {   
            if(run == event.getSource())
            {           
                if(random.isSelected())
                {
                    String text1 = sides.getText();
                    double side = Double.parseDouble(text1);
                    String text2 = radius.getText();
                    double rad = Double.parseDouble(text2);
                    String text3 = trials.getText();
                    int trial = Integer.parseInt(text3);

                    PinballSimulation pst = new PinballSimulation(side, rad);
                    pst.randomRun(side, rad, "Random", trial);
                }
                else if(targeted.isSelected())
                {
                    String text1 = sides.getText();
                    double side = Double.parseDouble(text1);
                    String text2 = radius.getText();
                    double rad = Double.parseDouble(text2);
                    String text3 = trials.getText();
                    int trial = Integer.parseInt(text3);

                    PinballSimulation pst = new PinballSimulation(side, rad);
                    pst.targetedRun(side, rad, "Systematic", trial);
                }
                else if(user.isSelected())
                {
                    String text1 = sides.getText();
                    double side = Double.parseDouble(text1);
                    String text2 = radius.getText();
                    double rad = Double.parseDouble(text2);
                    String text3 = angle.getText();
                    double ang = Double.parseDouble(text3);
                    String text4 = trials.getText();
                    int trial = Integer.parseInt(text4);

                    PinballSimulation pst = new PinballSimulation(side, rad);
                    pst.userRun(side, rad, ang, trial);
                }
            }
            else if(clear == event.getSource())
            {
                System.out.print('\f');
            }
        }
    }

    /**
     * This is the main method which run the full program
     * applet and initializes the classes for user use
     */
    public static void main (String [] args)
    {
        JFrame frame = new JFrame ("Pinball Simulation ~ Sneha Ganesh");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        Pinball pin = new Pinball();
        frame.getContentPane().add(pin);
        frame.pack();
        frame.setSize(960, 350);
        frame.setVisible(true);
    }
}