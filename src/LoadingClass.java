//Jordan and Sam did this together


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;
public class LoadingClass {

	protected int width, height;
	protected Block[][] blocks;
	public Turret turret, turrets;
	public Bullet bullet, bullets;
	protected BufferedImage levelImage;
	protected int spawnx, spawny;
	protected int[][] DEATH;
	protected static int turretX;
	protected static int turretY;
	protected static int turretNum;
	protected int EnemyType;
	
	//Saw variables
	int firstX;
	int firstY;
	int secondX;
	int secondY;
	
	public LoadingClass (String loading){
		try {
			blocks = loadLevel(loading);
		}
		catch (IOException e){
			
		}
		levelImage = new BufferedImage(width*10, height*10, BufferedImage.TYPE_INT_ARGB);
		Graphics g = levelImage.createGraphics();
		for (int cnt = 0; cnt < width; cnt++){
			
			for (int cnt1 = 0; cnt1 < height; cnt1++){
				if (blocks [cnt][cnt1].getStatus() == 1){
					g.setColor(Color.BLACK);
					g.fillRect(cnt*10, cnt1*10, 10, 10);
				}
				else if (blocks [cnt][cnt1].getStatus() == 2){
					g.setColor(Color.RED);
					g.fillRect(cnt*10, cnt1*10, 10, 10);
				}
				else if (blocks [cnt][cnt1].getStatus() == 3){
					g.setColor(Color.BLUE);
					g.fillRect(cnt*10, cnt1*10, 10, 10);
				}
			}
		}
	}
	
	public Block [][] loadLevel (String loading) throws IOException{
		InputStream is = getClass().getResource("/images/" + loading + ".txt").openStream();
		System.out.println("/images/" + loading + ".txt");
		System.out.println(getClass().getResource("/images/" + loading + ".txt").openStream());
		BufferedReader input = new BufferedReader (new InputStreamReader(is));
		
		
		
		width = Integer.parseInt(input.readLine());
		height = Integer.parseInt(input.readLine());
		
		
		Block [][] level = new Block [width][height];
		DEATH = new int [width][height];
		
		for (int cnt = 0; cnt < height; cnt++){
			char[] temp = input.readLine().toCharArray();
			//System.out.println(temp);
			for (int cnt1 = 0; cnt1 < width; cnt1++){
				if (temp[cnt1] == '0'){
					level[cnt1][cnt] = new Block (cnt1, cnt, 1, 1, 0);
					DEATH[cnt1][cnt] = 0;
				}
				else if (temp[cnt1] == '1'){
					level[cnt1][cnt] = new Block (cnt1, cnt, 1, 1, 1);
					DEATH[cnt1][cnt] = 0;
				}
				else if (temp[cnt1] == '2'){
					level[cnt1][cnt] = new Block (cnt1, cnt, 1, 1, 2);
					DEATH[cnt1][cnt] = 1;
				}
				else if (temp[cnt1] == '3'){
					level[cnt1][cnt] = new Block (cnt1, cnt, 1, 1, 3);
					DEATH[cnt1][cnt] = 0;
					//System.out.println (cnt1 + " " + cnt);
				}
			}
		}
		
		
		
		spawnx = Integer.parseInt(input.readLine());
		spawny = Integer.parseInt(input.readLine());
		
		EnemyType = Integer.parseInt(input.readLine());
		turretNum = Integer.parseInt(input.readLine());
		
		turret = new Turret ();
		bullet = new Bullet ();
		
		int cnt = 0;

		while (cnt < turretNum)
		{
			//System.out.println("other working");
			turretX = Integer.parseInt(input.readLine());
			turretY = Integer.parseInt(input.readLine());   

			turrets = new Turret (turretX, turretY, cnt);
			bullets = new Bullet (turretX, turretY, cnt);

			cnt = cnt + 1;

		}
		
		EnemyType = Integer.parseInt(input.readLine());
		//System.out.println(EnemyType);
		int sawNum = Integer.parseInt(input.readLine());
		//System.out.println(sawNum);
		Saw.setSawNum (sawNum);
		
		for (cnt = 0; cnt < sawNum; cnt++){
			//System.out.println("working");
			firstX = Integer.parseInt(input.readLine());
			firstY = Integer.parseInt(input.readLine());
			secondX = Integer.parseInt(input.readLine());
			secondY = Integer.parseInt(input.readLine());
			
			Saw.setSawXY (firstX, firstY, secondX, secondY, cnt);
		}
		
		input.close();
		
		return level;
	}
	
	public BufferedImage getImage(){
		return levelImage;
	}
	
	public int getStatus(int x, int y){
		//System.out.print (x + " " + y);
		if (x>=0 && x<width && y< height && y>=0)//boop
		{
			//System.out.println (" " + blocks[x][y].getStatus());
			return blocks[x][y].getStatus();
		}
		else
		{
			//System.out.println();
			return 0;
		}
		
	}
	
	public int getSpawnX (){
		return spawnx;
	}
	
	public int getSpawnY (){
		return spawny;
	}
	
	public int getDeath(int x, int y){
		x /= 10;
		y /= 10;
		//System.out.println(x + " " + y);
		if (x>=0 && x<width && y< height && y>=0)//boop
		{
			return DEATH[x][y];
		}
		else {
			return 1000;
		}
		//returns an int that will be 0 if the location will not kill the character
		//all other values correspond to a certain enemy
	}
	public void setDeath(int x, int y, int death){
		//System.out.println(x + " " + y);
		DEATH[x - 40][y - 40] = death;
	}
	
	@SuppressWarnings("static-access")
	public void setNewDeath(){//method to keep track of what areas kill you
		for (int cnt = 0; cnt < width; cnt++){//checking spike blocks
			for (int cnt1 = 0; cnt1 < height; cnt1++){
				DEATH[cnt][cnt1] = 0;
				if (blocks[cnt][cnt1].getStatus() == 2){
					DEATH[cnt][cnt1] = 1;
				}
			}
		}
		//enemies
		//bullets
		int cnt3 = 0;
		while (cnt3 < turretNum){
			DEATH[(Bullet.getBulletX(cnt3) + MainMenu.x.bulletMovementX[cnt3]) / 10 - 40][Bullet.getBulletY(cnt3) / 10 - 40] = 2;
			cnt3++;
		}
		//add more enemies here
		int cnt2 = 0;
		while (cnt2 < Saw.sawnum){
			//System.out.println(Saw.getSawX(cnt2) + " " + Saw.getSawY(cnt2));
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 1][Saw.sawYTotal[cnt2] / 10 - 40] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 2][Saw.sawYTotal[cnt2] / 10 - 40] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 3][Saw.sawYTotal[cnt2] / 10 - 40] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40][Saw.sawYTotal[cnt2] / 10 - 40 + 1] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 1][Saw.sawYTotal[cnt2] / 10 - 40 + 1] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 2][Saw.sawYTotal[cnt2] / 10 - 40 + 1] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 3][Saw.sawYTotal[cnt2] / 10 - 40 + 1] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 4][Saw.sawYTotal[cnt2] / 10 - 40 + 1] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40][Saw.sawYTotal[cnt2] / 10 - 40 + 2] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 1][Saw.sawYTotal[cnt2] / 10 - 40 + 2] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 2][Saw.sawYTotal[cnt2] / 10 - 40 + 2] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 3][Saw.sawYTotal[cnt2] / 10 - 40 + 2] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 4][Saw.sawYTotal[cnt2] / 10 - 40 + 2] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40][Saw.sawYTotal[cnt2] / 10 - 40 + 3] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 1][Saw.sawYTotal[cnt2] / 10 - 40 + 3] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 2][Saw.sawYTotal[cnt2] / 10 - 40 + 3] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 3][Saw.sawYTotal[cnt2] / 10 - 40 + 3] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 4][Saw.sawYTotal[cnt2] / 10 - 40 + 3] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 1][Saw.sawYTotal[cnt2] / 10 - 40 + 4] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 2][Saw.sawYTotal[cnt2] / 10 - 40 + 4] = 2;
			DEATH[Saw.sawXTotal[cnt2] / 10 - 40 + 3][Saw.sawYTotal[cnt2] / 10 - 40 + 4] = 2;
			cnt2++;
		}
	}
}
