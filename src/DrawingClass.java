
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.io.File;
import java.io.IOException;


class DrawingClass extends JPanel {

    private static final long serialVersionUID = 1L;
    static int k = 1;
    public String levelName;
    static LoadingClass level;
    static int onGround = 0 ,xt , yt, xb , yb, cnt1=0, cnt=0, drawn;
    public int levelBeat = 0;
    static int[]  bulletCheck, currentX, currentY, bulletMovementX, Shoot, bulletValue;
    static BufferedImage character;
    
    public DrawingClass(String levelName){
    	this.levelName = levelName;
    	level = new LoadingClass(levelName);
    	MainMenu.posx = level.getSpawnX() * 10;
    	MainMenu.posy = level.getSpawnY() * 10;
    	currentX = new int [LoadingClass.turretNum];

		currentY = new int [LoadingClass.turretNum];

		bulletMovementX = new int [LoadingClass.turretNum];

		Shoot = new int [LoadingClass.turretNum];

		bulletValue = new int [LoadingClass.turretNum];

    	MainMenu.c.setPlace(MainMenu.posx, MainMenu.posy);
    	
    	//File img = new File("/images/character2.png");
    	try {
			character = ImageIO.read(getClass().getResource("/images/character2.png"));
		} catch (IOException e) {
		}
    	
    	
    }
    
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(100, 100);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MainMenu.c.getLives() > -1){
        	movementMath();
        	//anything that needs to be stopped goes here
        }
        
        //LoadingClass level = new LoadingClass(levelName);
        BufferedImage temp = level.getImage();
        g.drawImage(temp, -(MainMenu.posx - 400), -(MainMenu.posy - 400), null);
        //System.out.println(MainMenu.posx + MainMenu.posy);
        if (MainMenu.c.getColor() == 1)
        {
        	g.setColor(Color.blue);
        }
        else if (MainMenu.c.getColor()==2)
        {
        	g.setColor(new Color (255, 105, 180));
        }     
        if (MainMenu.c.getChance() > 0 || MainMenu.invulnerable % 2 == 1){
        	g.fillOval(390, 390, 45, 60);
        }
        g.drawImage(character, 400, 400, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        if (MainMenu.c.getScore() < Integer.MAX_VALUE){
        	g.drawString("SCORE: " + MainMenu.c.getScore() + "", 5, 25);
        }
        else {
        	g.drawString("You suck", 5, 25);
        }
        g.drawString("LIVES: " + MainMenu.c.getLives(), 5, 50);
        //Caleb and Jordan start here: Turrets
    	
    	cnt = 0;
    	cnt1 = 0;
    	while (cnt < LoadingClass.turretNum)
    	{

    		if (Shoot[cnt] == 0)
    		{

    			bulletCheck = Turret.Shoot(LoadingClass.turretNum);

    		}

    		if (bulletCheck[cnt] == 1)
    		{ 
    			//level.setDeath(Bullet.getBulletX(cnt) / 10, Bullet.getBulletY(cnt) / 10, 0);//removed for better method
    			bulletMovementX[cnt] = bulletMovementX[cnt] - 15;


    			Shoot[cnt] = 1;

    		}

    		if (bulletCheck[cnt] == 2)
    		{ 
    			//level.setDeath(Bullet.getBulletX(cnt) / 10, Bullet.getBulletY(cnt) / 10, 0);//removed for better method
    			bulletMovementX[cnt] = bulletMovementX[cnt] + 15;

    			Shoot[cnt] = 1;

    		}

    		if (bulletCheck[cnt] == 0)

    		{ 
    			bulletMovementX[cnt] = 0;
    			Shoot[cnt] = 0;

    		}


    		bulletValue[cnt] = Math.abs(bulletMovementX[cnt]);

    		if (bulletValue[cnt] > 500)
    		{

    			Shoot[cnt] = 0;

    			bulletMovementX[cnt] = 0;

    			bulletValue[cnt] = 0;

    		}

    		g.setColor(Color.RED);

    		cnt1 = 0;
    			
    		while (cnt1 < LoadingClass.turretNum)
    		{

    			xb = Bullet.getBulletX(cnt1);

    			yb = Bullet.getBulletY(cnt1);
    			//level.setDeath((Bullet.getBulletX(cnt) + bulletMovementX[cnt]) / 10, Bullet.getBulletY(cnt) / 10, 2); //make method to reset and then set all
    			//currently has all of line kill you
    			//like a laser
    			//removed for better method
    			g.fillRect(xb - MainMenu.posx + bulletMovementX[cnt], yb - MainMenu.posy, 10, 10);
    			
    			cnt1 = cnt1 + 1;
    		}

    		cnt1 = 0;
    		g.setColor(Color.ORANGE);
    		while (cnt1 < LoadingClass.turretNum)
    		{

    			xt = Turret.getTurretX(cnt1);
    			yt = Turret.getTurretY(cnt1);


    			currentX[cnt1] = xt - MainMenu.posx;
    			currentY[cnt1] = yt - MainMenu.posy;

    			g.fillRect(xt - MainMenu.posx, yt - MainMenu.posy, 30, 40);
    				
    			cnt1 = cnt1 + 1;
    		}

    		cnt++;
    	}
    	//Saw.math(firstX, firstY, secondX, secondY, sawnum);
    	Saw.math();
    	g.setColor(Color.BLUE);
    	for (int cnt=0; cnt<Saw.sawnum; cnt++){
    		//System.out.println(Saw.sawXTotal[cnt]+ " " + Saw.sawYTotal[cnt]);
    		g.fillOval(Saw.sawXTotal[cnt] - MainMenu.posx, Saw.sawYTotal[cnt] - MainMenu.posy, 50, 50);
    	}
    	
    	level.setNewDeath();
    	
        
    }
    
    public void redraw(){
    	javax.swing.SwingUtilities.invokeLater(new Runnable (){
			public void run (){
				repaint();
			}
		});
    }
    
    public void setLevel(String levelName){
    	this.levelName = levelName;
    }
    
    public void movementMath(){
    	//start user movement
    	
    	//System.out.println(level.getStatus(0,0));
    		
    	
    	if ((level.getStatus((MainMenu.c.getx() / 10), (MainMenu.c.gety() / 10) + 4) == 1) || 
        		(level.getStatus((MainMenu.c.getx() / 10) + 1, (MainMenu.c.gety() / 10) + 4) == 1) || 
        		(level.getStatus((MainMenu.c.getx() / 10) + 2, (MainMenu.c.gety() / 10) + 4) == 1))//Check if the player is standing on a block
    	{
        	onGround = 1;
    	}
    	else
    	{
    		onGround=0;
    	}
    	
    	//System.out.println (onGround + " " + MainMenu.jump);
    	if (onGround == 1 && MainMenu.jump == 1)//jump only when on the ground
    	{
    		MainMenu.speedy = -15;
    		onGround = 0;
    	}
        
    	if (MainMenu.jump == 0 && MainMenu.speedy < 0) //stop moving up when up is released
    	{
    		MainMenu.speedy = 0;
    	}
    	
    	if (MainMenu.speedy > 0)
    	{
    		for (int cnt = MainMenu.c.gety(); cnt <= (MainMenu.c.gety() + MainMenu.speedy); cnt++)//Checks blocks below
    		{
    			if ((level.getStatus((MainMenu.c.getx() / 10), (cnt / 10) + 4) == 1) || 
    					(level.getStatus((MainMenu.c.getx() / 10) + 1, (cnt / 10) + 4) == 1) || 
    					(level.getStatus((MainMenu.c.getx() / 10) + 2, (cnt / 10) + 4) == 1)){
    				MainMenu.speedy = 0;
    				MainMenu.posy = cnt;
    				onGround = 1;
    				break;
    			}
    		}
    	}
    	else if(MainMenu.speedy <0)
    	{
    		for (int cnt = MainMenu.c.gety(); cnt >= (MainMenu.c.gety() + MainMenu.speedy); cnt--)//Checks blocks above
    		{
    			if ((level.getStatus((MainMenu.c.getx() / 10), (cnt / 10) - 1) == 1) || 
    					(level.getStatus((MainMenu.c.getx() / 10) + 1, (cnt / 10) - 1) == 1) || 
    					(level.getStatus((MainMenu.c.getx() / 10) + 2, (cnt / 10) - 1) == 1)){
    				MainMenu.speedy = 0;
    				MainMenu.posy = cnt;
    				onGround = 1;
    				break;
    			}
    		}
    	}
		
		if (onGround == 0)
		{
			MainMenu.posy += MainMenu.speedy;//one operation here
			MainMenu.speedy += 1;
		}
    	
		//System.out.println (MainMenu.posy);
		//k = Math.ceil(MainMenu.posy/100)*100;
		//System.out.println(k);
		
		if (MainMenu.speedX<0) //Checks blocks to the left
		{
			if ((level.getStatus((MainMenu.c.getx() / 10) - 1, (MainMenu.posy / 10) + 3) != 1) && 
					(level.getStatus((MainMenu.c.getx() / 10) - 1, (MainMenu.posy / 10) + 2) != 1) && 
					(level.getStatus((MainMenu.c.getx() / 10) - 1, (MainMenu.posy / 10) + 1) != 1)&& 
					(level.getStatus((MainMenu.c.getx() / 10) - 1, (MainMenu.posy / 10) + 0) != 1))
			{
				MainMenu.posx += MainMenu.speedX;
			}
			else 
			{
				MainMenu.speedX = 0;
			}
		}
		
		else if (MainMenu.speedX>0) //Checks blocks to the right
		{
			if ((level.getStatus((MainMenu.c.getx() / 10) + 3, (MainMenu.c.gety()/ 10) + 3) != 1) && 
					(level.getStatus((MainMenu.c.getx() / 10) + 3, (MainMenu.c.gety() / 10) + 2) != 1) && 
					(level.getStatus((MainMenu.c.getx() / 10) + 3, (MainMenu.c.gety() / 10) + 1) != 1)&& 
					(level.getStatus((MainMenu.c.getx() / 10) + 3, (MainMenu.c.gety() / 10) + 0) != 1))
			{
				MainMenu.posx += MainMenu.speedX;
			}
			else 
			{
				MainMenu.speedX = 0;
			}
		}
			
			//MainMenu.posx = (MainMenu.posx/10)*10;
			//MainMenu.posy = (MainMenu.posy/10)*10;
		
    	MainMenu.c.setPlace(MainMenu.posx, MainMenu.posy);
    	
    	//Check for end of level
    	//System.out.println ("");
    	if (level.getStatus(MainMenu.c.getx()/10 , MainMenu.c.gety()/10 + 2) == 3 ||
    			level.getStatus(MainMenu.c.getx()/10 + 1, MainMenu.c.gety()/10 + 2) == 3 ||
    			level.getStatus(MainMenu.c.getx()/10 + 2, MainMenu.c.gety()/10 + 2) == 3)
    	{
    		//System.out.println("END OF LEVEL");
    		if (MainMenu.levelBeat < 2){
    			MainMenu.c.setChance(1);
				MainMenu.upKey = false;
				MainMenu.rightKey = false;
				MainMenu.leftKey = false;
				MainMenu.speedX = 0;
				MainMenu.speedy = 0;
    			MainMenu.levelBeat++;
    			MainMenu.drawLevel();
    		}
    		else {
    			MainMenu.upKey = false;
				MainMenu.rightKey = false;
				MainMenu.leftKey = false;
				MainMenu.speedX = 0;
				MainMenu.speedy = 0;
				MainMenu.c = new Character();
				MainMenu.x.setVisible(false);
				MainMenu.threadrun = false;
				
				JOptionPane.showMessageDialog(null, "Congratulations! You Won!");
				//add score here
				
				
				MainMenu.contents.removeAll();
				MainMenu.contents.add(MainMenu.buttonHolder);
				MainMenu.mainMenu();
				MainMenu.reversePerson();
				MainMenu.frame.setVisible(false);
				MainMenu.frame.setVisible(true);
    		}
    		//Win menu, next level option
    		//reset variables?
    		//setLevel(next file name);
    		//new drawing class?
    	} 
    	
    	
    	//round down (go up)
    	if (!(MainMenu.c.gety() % 10 == 0) && onGround == 1){
    		int a = MainMenu.c.gety();
    		a /= 10;
    		a *= 10;
    		MainMenu.c.setPlace(MainMenu.c.getx(), a);
    		MainMenu.posy = MainMenu.c.gety();
    	}
    	/*else if (MainMenu.c.gety() % 10 != 0)//round up (go down)
    	{
    		int a = MainMenu.c.gety();
    		a = (int) Math.ceil(a/10)*10;
    		MainMenu.c.setPlace(MainMenu.c.getx(), a);
    		MainMenu.posy = MainMenu.c.gety();
    	}*/
    	
    	
    	//end user movement
    }

}