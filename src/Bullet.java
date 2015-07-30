public class Bullet
{
	static int[] bx, by;


	public Bullet ()
	{
		Bullet.bx = new int [LoadingClass.turretNum];
		Bullet.by = new int [LoadingClass.turretNum];
	}

	public Bullet(int bx, int by, int turretnum) 
	{

		Bullet.bx [turretnum]= bx;
		Bullet.by [turretnum]= by;

	}



	public static void setBulletX(int turretnum, int bx)
	{

		Bullet.bx[turretnum] = bx;


	}

	public static void setBulletY(int turretnum, int by)
	{

		Bullet.by[turretnum] = by;

	}
	public static int getBulletX (int turretnum)
	{

		return Bullet.bx[turretnum];
	}

	public static int getBulletY (int turretnum)
	{

		return Bullet.by[turretnum];
	}


}
