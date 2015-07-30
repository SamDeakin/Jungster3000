//Caleb and Jordan

/*The Turret class. Creates the "turret" that will be a stationary enemy
 * within the Jungster3000 game. After the turret has been created within
 * the game environment, it keeps track of the character once it comes
 * within a certain distance of the turret. While the character is within
 * the radius of the turret range, it will fire projectiles at the
 * character. The turret will fire at certain intervals at the character
 * and if one of the projectiles collide with the character, it will use
 * up a "chance". If there is no "chance" to be used up it will kill the
 * character.
 */

public class Turret
{
	static int[] tx, ty, sx, sy, result;


	public Turret ()
	{
		Turret.tx = new int [LoadingClass.turretNum + 1];
		Turret.ty = new int [LoadingClass.turretNum + 1];
		Turret.sy = new int [LoadingClass.turretNum + 1];
		Turret.sx = new int [LoadingClass.turretNum + 1];
		Turret.result = new int [LoadingClass.turretNum + 1];

	}

	public Turret(int tx, int ty, int turretnum) 
	{

		Turret.tx [turretnum]= tx;
		Turret.ty [turretnum]= ty;

	}



	public static void setTurretX(int turretnum, int tx)
	{

		Turret.tx[turretnum] = tx;

	}

	public static void setTurretY(int turretnum, int ty)
	{

		Turret.ty[turretnum] = ty;

	}
	public static int getTurretX (int turretnum)
	{

		return Turret.tx[turretnum];
	}

	public static int getTurretY (int turretnum)
	{

		return Turret.ty[turretnum];

	}

	public static int[] Shoot (int turretnum)
	{
		int temp = 0, cnt = 0;
		int x = MainMenu.c.getx ();
		int y = MainMenu.c.gety ();

		int number = 0;
		int number2 = 0;


		//run through however many turrets there are in the stage
		//and and deduct whether the character is in range of any of
		//the existing turrets
		while (cnt < turretnum)
		{
			number  = (800 - Turret.getTurretX(cnt));
			number2 = (800 - Turret.getTurretY(cnt));
			
			
			
			sx[cnt] =  (DrawingClass.currentX[cnt] - x) - number;

			sy[cnt] = (DrawingClass.currentY[cnt] - y) - number2;

			//System.out.println(sy[0]);
			
			//System.out.println(sy[1]);
			
			

			temp =  temp + 1;
			cnt = cnt + 1;

	
		}//for loop to cover all of the turrets

		cnt = 0;
		while (cnt < turretnum)
		{

			if (sx[cnt] < 800 && (Math.abs(sy[cnt]) < 50))
			{

				if (sx[cnt] > -800)
				{
					result [cnt] = 1;

					if (sx[cnt] < 0)
					{
						result [cnt] = 2;
					}

				}
				
				else 
				{ 
					result [cnt] = 0;
				}
			}//if character is within the radius of the turret
			else 
			{
				result [cnt] = 0;
			}//character is not close enough to the turret

			cnt = cnt + 1;
		}

		return result;
	}//public shoot


}
