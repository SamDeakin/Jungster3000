/*
Caleb Jung
ICS 4U0
May.1.2013
Mr. Jone
Character Object
 */

/*
Creation of the character object; sets up the initial
requirements for the character to be used within the game Jungster3000.
This class contains the methods to draw the character, its
"health" in the form of a "chance". Upon colliding with unfriendly
objects the character uses up this "chance". If the character
collides with an unfriendly object and does not have a chance, then
the character dies. 3 Lives are given to the character.
 */

public class Character
{


	static String name;
	static int lives;
	static int chance;
	static int score;
	static String info;
	static int gender;
	static int x,y;

	//Khamil start
	public Character (String name, int score)
	{
		Character.name = name;
		Character.score = score;

		info = name + " " + score;
		
		
	}
	public Character ()
	{
		lives = 2;//temp unless 2
		chance = 1;
		score = 0;
	}
	//end

	public void setName (String name)
	{
		Character.name = name;
	}//setting the name of the character

	public void setChance (int chance)
	{
		Character.chance = chance;
	}//sets the chance of the character
	public int getChance(){
		return chance;
	}

	public void setLives (int lives)
	{
		Character.lives = lives;
	}//method to set the number of lives for the character
	public int getLives(){
		return lives;
	}

	public int getScore ()
	{
		return Character.score;
	}

	public void setGender (int x)
	{
		gender = x;
	}

	public int getColor ()
	{
		return gender;
	}
	public void setPlace(int x, int y){
		Character.x = x;
		Character.y = y;
	}
	public int getx (){
		return x;
	}
	public int gety (){
		return y;
	}
	public void scoreplusplus (){
		score++;
	}
	public void setScore(int newscore){
		score = newscore;
	}
}
