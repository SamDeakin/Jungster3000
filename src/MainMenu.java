/* Things not implemented
 * 
 * Sort
 * Multiple turrets per level
 * Enemies that chase you
 * 
 * 
 * These are all absent because the people that were assigned to do them didn't do them
 * They said they would send them to me by 11pm saturday, but I did not receive any code from them
 * 
 * note by sam
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

import javax.swing.*;

public class MainMenu extends JPanel implements ActionListener{

	//changeable variables
	public static Color background = Color.WHITE;
	static String highscore;
	static int levelBeat = -1, started = 0;
	static boolean pause = false;
	//end changeable variables

	static int highscores[];
	static JButton male, female, enter, resumeButton;;
	static JLabel label;
	static JTextField inputname;
	static int gender = 0;
	static String name;
	static Character c = new Character();
	private static final long serialVersionUID = 1L;//just leave this here
	static JFrame frame;
	protected static MainMenu contents;
	static JButton start, quit, options, returnButton, leaderboard, instructions;
	static JLabel logo;
	static JPanel buttonHolder;
	static DrawingClass x;
	//private static Action enterAction, upAction, leftAction, rightAction, escapeAction;
	BufferedReader input;
	static Thread t;
	static boolean threadrun = false;

	static boolean leftKey = false;
	static boolean rightKey = false;
	static boolean upKey = false;
	static boolean downKey = false;
    static boolean escKey = false;
	static int posx = 0, posy = 200, speedy = 0;
	static int speedX = 0, jump = 0;
	
	static int invulnerable = 0;

	public MainMenu (){
		buttonHolder = new JPanel ();
		buttonHolder.setLayout(new BoxLayout(buttonHolder, BoxLayout.Y_AXIS));

		logo = new JLabel(new ImageIcon(getClass().getResource("/images/JUNGSTER3000Logo.png")));

		start = new JButton ("Start");//button to start a new game
		start.setActionCommand("start");
		start.addActionListener(this);

		quit = new JButton ("Quit");//button to quit
		quit.setActionCommand("quit");
		quit.addActionListener(this);

		options = new JButton ("Options");//button to go to the options menu
		options.setActionCommand("options");
		options.addActionListener(this);

		returnButton = new JButton ("Return");//button to return to the main menu
		returnButton.setActionCommand("return");
		returnButton.addActionListener(this);

		leaderboard = new JButton ("Leaderboards");
		leaderboard.setActionCommand("leaderboard");
		leaderboard.addActionListener(this);

		instructions = new JButton ("Instructions");
		instructions.setActionCommand("instructions");
		instructions.addActionListener(this);

		male = new JButton ("Male");
		male.setVerticalTextPosition (AbstractButton.CENTER);
		male.setHorizontalTextPosition (AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		male.setActionCommand ("male");
		male.addActionListener (this);
		male.setVisible (false);

		female = new JButton ("Female");
		female.setVerticalTextPosition (AbstractButton.BOTTOM);
		female.setHorizontalTextPosition (AbstractButton.CENTER);
		female.setActionCommand ("female");
		female.addActionListener (this);
		female.setVisible (false);

		label = new JLabel ("Please enter your name");
		label.setVisible (false);

		inputname = new JTextField (15);
		inputname.setVisible (false);

		enter = new JButton ("Enter");
		enter.setActionCommand ("enter");
		enter.setVisible (false);
		enter.addActionListener (this);
		//Use the default text position of CENTER, TRAILING (RIGHT).	

		male.setToolTipText ("Click this button if you are male.");
		female.setToolTipText ("Click this button if you are female.");
		
		resumeButton = new JButton ("Resume");
		resumeButton.setActionCommand("resume");
		resumeButton.addActionListener(this);

		//Add Components to this container, using the default FlowLayout.

		mainMenu();

		add (buttonHolder);
		/*
		
		//Khamil start
		try {
			input = new BufferedReader (new FileReader ("LeaderScores.txt"));
		} catch (FileNotFoundException e) {

		}

		
		String line;
		highscores = new int[5];
		try {

			line = input.readLine();
			if (line == null)
			{
				line = "";
			}

			for (int cnt = 0; cnt < 4; cnt++)
			{
				line = input.readLine();
				if (line == null)
				{
					line = "";
				}
				highscore = highscore + "\n" + line;
			}

		} catch (IOException e1) {

			e1.printStackTrace();
		}
		//Khamil ends
		
		
		*/
		frame.addKeyListener (new ThisIsAGoodNameForAKeylistener ());
		t = new Thread (new ThisIsAGoodNameForAThread());
		//t.start();

	}
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable (){
			public void run (){
				makeGUI ();
			}
		});
	}
	public static void makeGUI (){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setBackground(background);
		frame.setResizable(false);

		contents = new MainMenu ();
		contents.setOpaque (true);
		frame.setContentPane (contents);

		frame.pack();
		frame.setVisible(true);
		//System.out.println(frame.getHeight() + " " + frame.getWidth());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("start")){
			Person();
			//drawLevel();
		}

		if (e.getActionCommand().equals("male"))
		{
			gender = 1;
			c.setGender(gender);
			male.setVisible (false);
			female.setVisible (false);
			enter.setVisible (true);
		}

		if (e.getActionCommand().equals("female"))
		{
			gender = 2;
			c.setGender(gender);
			male.setVisible (false);
			female.setVisible (false);
			enter.setVisible (true);
		}

		if (e.getActionCommand().equals("enter"))
		{
			name = inputname.getText ();
			c.setName(name);
			drawLevel();
		}

		if (e.getActionCommand().equals("options")){
			optionMenu();
			frame.setVisible(true);
		}
		if (e.getActionCommand().equals("quit")){
			int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
		
		if (e.getActionCommand().equals("return")){
			mainMenu();
			frame.setVisible(true);
		}
		
		if (e.getActionCommand().equals("leaderboard")){
			//Khamil
			JOptionPane.showMessageDialog (frame, highscore);
			//leaderboards go here
		}
		
		if (e.getActionCommand().equals("instructions")){

			//Khamil
			JOptionPane.showMessageDialog (frame, "The objective of this game is to make your way through each level while avoiding enemies and other dangers."  + "\n" + " " + "\n" + 
					"Up arrow key: Use this arrow key to jump" + "\n" + 
					"Left arrow key: Use this arrow key to move backwards on the screen"+ "\n"+
					"Right arown key: Use this arrow key to move forward on the screen");
			//instructions go here
		}
		if (e.getActionCommand().equals("resume")){//if resume button is pressed (Jonathan)
			pause = false;
			buttonHolder.removeAll();
			x.setVisible(true);
			frame.toFront();
			frame.setState(JFrame.NORMAL);
			frame.requestFocus();
			//regains focus so the player doesn't have to manually regain focus through minimizing (Jonathan) 
		}
	}


public static void mainMenu (){
	//shows main menu
	buttonHolder.removeAll();
	buttonHolder.add(logo);
	buttonHolder.add(start);
	//buttonHolder.add(leaderboard);
	buttonHolder.add(options);
	buttonHolder.add(quit);
	buttonHolder.add(male);
	buttonHolder.add(female);
	buttonHolder.add(label);
	buttonHolder.add(inputname);
	buttonHolder.add(enter);	
	frame.setVisible(true);
	
}
public void optionMenu (){
	//shows option menu
	buttonHolder.removeAll();
	buttonHolder.add(logo);
	buttonHolder.add(instructions);
	buttonHolder.add(returnButton);
}
public static void pauseMenu(){
	//shows pause menu (Jonathan)
	contents.add(buttonHolder);
	x.setVisible(false);
	buttonHolder.removeAll();
	buttonHolder.add(logo);
	buttonHolder.add(resumeButton);
	buttonHolder.add(quit);
	quit.setVisible(true);
	frame.setVisible(true);
}




//can be changed later
public static void drawLevel (){
	contents.removeAll();
	if (levelBeat == -1){
		t.start();
		levelBeat = 0;
	}
	if(levelBeat==0)
	{
		x = new DrawingClass("LevelDesign2");
	}
	if(levelBeat==1)
	{
		x = new DrawingClass("LevelDesign1");
	}
	if(levelBeat==2)
	{
		x = new DrawingClass("LevelDesign3");//temp 1
	}
	

	//x.setLevel("ExampleSave");
	contents.add(x);
	frame.setVisible(true);

	/*these next 3 lines are the most important in the entire program
	 */

	frame.toFront();
	frame.setState(JFrame.NORMAL);
	frame.requestFocus();

	
	//System.out.println(threadrun);
	//Don't delete them
	//please
	
	
	threadrun = true;
	//just don't do it
	// or the jungster3000 will find you
	/*
	 * 
	 * 
		//Christiaan and Sam until end of file
		enterAction = new EnterAction();
		upAction = new UpAction();
		leftAction = new LeftAction();
		rightAction = new RightAction();
		escapeAction = new EscapeAction();
		x.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "EnterAction");
		x.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "UpAction");
		x.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "LeftAction");
		x.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "RightAction");
		x.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "EscapeAction");
		x.getActionMap().put("EnterAction", enterAction);
		x.getActionMap().put("UpAction", upAction);
		x.getActionMap().put("LeftAction", leftAction);
		x.getActionMap().put("RightAction", rightAction);
		x.getActionMap().put("EscapeAction", escapeAction);
	 */





}

public class ThisIsAGoodNameForAThread implements Runnable
{
	public ThisIsAGoodNameForAThread ()
	{
	}

	@SuppressWarnings("static-access")
	public void run ()
	{
		while (true)
		{
			//System.out.println(threadrun);
			while(!pause && threadrun)//while pause is not true (Jonathan)
				//also while the thread should run (sam)
			{
				jump = 0;
				speedX = 0;
				if (upKey)
				{
					jump = 1;
					//System.out.println ("up");
				}
				if (rightKey)
				{
					speedX = 10;
					//System.out.println ("right");
				}
				if (leftKey)
				{
					speedX = -10;
					//System.out.println ("left");
				}
				if(escKey)
				{
					pause=true;
					pauseMenu();
				}
				
				//end movement
			
				//start DEATH
				if (invulnerable == 0){
					if (checkDeath()){
						if (c.getChance() == 0){
						
							if (c.getLives() > 0){
								Object [] options = new Object[] {"Respawn", "Quit"};
								int n = JOptionPane.showOptionDialog(frame, "You Died", "You Died. You have " + c.getLives() + " Lives left", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Respawn");
								if (n == 0){
									posx = x.level.spawnx * 10;
									posy = x.level.spawny * 10;
									c.setChance(1);
									upKey = false;
									rightKey = false;
									leftKey = false;
									speedX = 0;
									speedy = 0;
									c.setLives(c.getLives() - 1);
								}
								else if (n == 1){
									//highscore not scored because character didn't beat the game
									upKey = false;
									rightKey = false;
									leftKey = false;
									speedX = 0;
									speedy = 0;
									c = new Character();
									x.setVisible(false);
									threadrun = false;
									contents.removeAll();
									contents.add(buttonHolder);
									mainMenu();
									reversePerson();
									frame.setVisible(true);
								}
							}
							else {
								//System.out.println(c.getLives());
								Object [] options = new Object[] {"Try again", "Quit"};
								int n = JOptionPane.showOptionDialog(frame, "You Died", "You are out of lives", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Dead");
								if (n == 0){
									upKey = false;
									rightKey = false;
									leftKey = false;
									speedX = 0;
									speedy = 0;
									c.setLives(2);
									c.setChance(1);
									c.setScore(0);
									levelBeat = 0;
									drawLevel();
								}
								else {
									//highscore not scored because character didn't beat the game
									upKey = false;
									rightKey = false;
									leftKey = false;
									speedX = 0;
									speedy = 0;
									c = new Character();
									x.setVisible(false);
									threadrun = false;
									contents.removeAll();
									contents.add(buttonHolder);
									mainMenu();
									reversePerson();
									frame.setVisible(true);
								}
							}
						}
						else {
							c.setChance(c.getChance() - 1);
							invulnerable = 45;
						}
					}
					if (c.getScore() < Integer.MAX_VALUE){
						c.scoreplusplus();
					}
				}
				else {
					invulnerable--;
				}
			
				//end DEATH
				//System.out.println(x.level.getDeath(0, 0));
				x.redraw ();
				try
				{
					Thread.sleep (30);
				}
				catch (InterruptedException ex)
				{
				}
			}
			try
			{
				Thread.sleep (30);
			}
			catch (InterruptedException ex)
			{
			}

		}

	}
	
	@SuppressWarnings("static-access")
	public boolean checkDeath (){
		if (x.level.getDeath(c.getx(), c.gety()) == 0 && x.level.getDeath(c.getx() + 10, c.gety()) == 0 && x.level.getDeath(c.getx() + 20, c.gety()) == 0 && 
				x.level.getDeath(c.getx(), c.gety() + 10) == 0 && x.level.getDeath(c.getx() + 10, c.gety() + 10) == 0 && x.level.getDeath(c.getx() + 20, c.gety() + 10) == 0 && 
				x.level.getDeath(c.getx(), c.gety() + 20) == 0 && x.level.getDeath(c.getx() + 10, c.gety() + 20) == 0 && x.level.getDeath(c.getx() + 20, c.gety() + 20) == 0 &&
				x.level.getDeath(c.getx(), c.gety() + 30) == 0 && x.level.getDeath(c.getx() + 10, c.gety() + 30) == 0 && x.level.getDeath(c.getx() + 20, c.gety() + 30) == 0){
			//System.out.println("111111");
			return false;
		}
		//System.out.println("0");
		
		return true;
	}

}
private class ThisIsAGoodNameForAKeylistener extends KeyAdapter
{

	public void keyPressed (KeyEvent e)
	{
		switch (e.getKeyCode ())
		{
		case KeyEvent.VK_LEFT:
			//System.out.println("notboop");
			leftKey = true;
			break;
		case KeyEvent.VK_RIGHT:
			rightKey = true;
			break;
		case KeyEvent.VK_UP:
			upKey = true;
			break;
		case KeyEvent.VK_ESCAPE:
			escKey = true;
			break;
		}
	}

	public void keyReleased (KeyEvent e)
	{
		switch (e.getKeyCode ())
		{
		case KeyEvent.VK_LEFT:
			leftKey = false;
			break;
		case KeyEvent.VK_RIGHT:
			rightKey = false;
			break;
		case KeyEvent.VK_UP:
			upKey = false;
			break;
		case KeyEvent.VK_ESCAPE:
			escKey = false;
			break;

		}
	}
}





//not used anymore

/*
class EnterAction extends AbstractAction
{

	private static final long serialVersionUID = 1L;

	public void actionPerformed( ActionEvent tf )

    {

        System.out.println( "The Enter key has been pressed." );


    } 
} 
class UpAction extends AbstractAction
{

	private static final long serialVersionUID = 1L;

	public void actionPerformed( ActionEvent tf )

    {

        System.out.println( "The Up key has been pressed." );


    } 
}
class LeftAction extends AbstractAction
{

	private static final long serialVersionUID = 1L;

	public void actionPerformed( ActionEvent tf )

    {

        System.out.println( "The Left key has been pressed." );


    } 
}
class RightAction extends AbstractAction
{

	private static final long serialVersionUID = 1L;

	public void actionPerformed( ActionEvent tf )

    {

        System.out.println( "The Right key has been pressed." );


    } 
} 
class EscapeAction extends AbstractAction
{

	private static final long serialVersionUID = 1L;

	public void actionPerformed( ActionEvent tf )

    {

        System.out.println( "The Escape key has been pressed." );


    } 
} 
*/



	public static void Person ()
	{	
		start.setVisible(false);
		options.setVisible(false);
		quit.setVisible(false);
		returnButton.setVisible(false);
		leaderboard.setVisible(false);
		instructions.setVisible(false);
		male.setVisible (true);
		female.setVisible (true);
		label.setVisible (true);
		inputname.setVisible (true);
		enter.setVisible (false);
	}
	public static void reversePerson(){
		start.setVisible(true);
		options.setVisible(true);
		quit.setVisible(true);
		returnButton.setVisible(true);
		leaderboard.setVisible(true);
		instructions.setVisible(true);
		male.setVisible (false);
		female.setVisible (false);
		label.setVisible (false);
		inputname.setVisible (false);
		enter.setVisible (false);
	}

}
