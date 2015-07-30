//import java.awt.Color;

public class Saw {

	static int timeLapse;

	static int[] sawX, sawY;
	static int [] sawXTotal, sawYTotal;
	protected static int sawnum;
	
	static int turn = 0;
	static int temp = 0;
	static int scenario = 0;

	static int [] firstX, firstY, secondX, secondY;


	public Saw ()
	{
		
	}//Saw

	public static int getSawX (int sawnum)
	{

		return Saw.sawX[sawnum];
	}

	public static int getSawY (int sawnum)
	{

		return Saw.sawY[sawnum];
	}
	
	public static void setSawNum (int sawNum)
	{
		sawnum = sawNum;
		
		sawX = new int [sawnum+1];
		sawY = new int [sawnum+1];
		firstX = new int [sawnum+1];
		firstY = new int [sawnum+1];
		secondX = new int [sawnum+1];
		secondY = new int [sawnum+1];
		
		sawXTotal = new int [sawnum+1];
		sawYTotal = new int [sawnum+1];
		
	}
	
	public static void setSawXY (int firstX1, int firstY1, int secondX1, int secondY1, int currentSawNum)
	{
		firstX[currentSawNum] = firstX1;
		firstY[currentSawNum] = firstY1;
		secondX[currentSawNum] = secondX1;
		secondY[currentSawNum] = secondY1;
		
		sawXTotal[currentSawNum] = firstX1;
		sawYTotal[currentSawNum] = firstY1;
	}

	public static void math ()
	{
		for (temp = 0; temp < sawnum; temp ++)
		{
			if (firstX[temp] < secondX[temp] && firstY[temp] < secondY[temp])
			{
				scenario = 1;
			}
			else if (firstX[temp] > secondX[temp] && firstY[temp] < secondY[temp])
			{
				scenario = 2;
			}
			else if (firstX[temp] > secondX[temp] && firstY[temp] > secondY[temp])
			{
				scenario = 3;
			}
			else if (firstX[temp] < secondX[temp] && firstY[temp] > secondY[temp])
			{
				scenario = 4;
			}
			else if (firstY[temp] == secondY[temp] && firstX[temp] < secondX[temp])
			{
				scenario = 5;
			}
			else if (firstY[temp] == secondY[temp] && firstX[temp] > secondX[temp])
			{
				scenario = 6;
			}
			else if (firstX[temp] == secondX[temp] && firstY[temp] > secondY[temp])
			{
				scenario = 7;
			}
			else if (firstX[temp] == secondX[temp] && firstY[temp] < secondY[temp])
			{
				scenario = 8;
			}
			else
			{
				scenario = 9;
			} //determining the situation that the coordinates fall into


			switch (scenario)
			{
			case 1: //1: Point 1 is at the top left; Point 2 is on the bottom right
				if (turn == 0)
				{
					sawX[temp] = sawX[temp] + 4;
					sawY[temp] = sawY[temp] + 4;
					sawXTotal[temp] = sawX[temp] + firstX[temp];
					sawYTotal[temp] = sawY[temp] + firstY[temp];

					if (sawXTotal[temp] >= secondX[temp] && sawYTotal[temp] >= secondY[temp])
					{
						turn = 1;
					}
				} //if = 0
				else if (turn == 1)
				{
					sawX[temp] = sawX[temp] - 4;
					sawY[temp] = sawY[temp] - 4;
					sawXTotal[temp] = sawX[temp] + firstX[temp];
					sawYTotal[temp] = sawY[temp] + firstY[temp];

					if (sawXTotal[temp] <= firstX[temp] && sawYTotal[temp] <= firstY[temp])
					{
						turn = 0;
					}
				} //else if
				break;

			case 2: //2: Point 1 is at the top right; Point 2 is at the bottom left
					if (turn == 0)
					{
						sawX[temp] = sawX[temp] + 4;
						sawY[temp] = sawY[temp] + 4;
						sawXTotal[temp] = sawX[temp] + firstX[temp];
						sawYTotal[temp] = sawY[temp] + firstY[temp];

						if (sawXTotal[temp] >= secondX[temp] && sawYTotal[temp] >= secondY[temp])
						{
							turn = 1;
						}
					} //while turn = 0
					else if (turn == 1)
					{
						sawX[temp] = sawX[temp] + 4;
						sawY[temp] = sawY[temp] + 4;
						sawXTotal[temp] = sawX[temp] + firstX[temp];
						sawYTotal[temp] = sawY[temp] + firstY[temp];

						if (sawXTotal[temp] <= firstX[temp] && sawYTotal[temp] <= firstY[temp])
						{
							turn = 0;
						}
					} //while turn = 1
				break;

			case 3: //3: Point 1 is at the bottom right; Point 2 is at the top left
					if (turn == 0)
					{
						sawX[temp] = sawX[temp] - 4;
						sawY[temp] = sawY[temp] - 4;
						sawXTotal[temp] = sawX[temp] + firstX[temp];
						sawYTotal[temp] = sawY[temp] + firstY[temp];

						if (sawXTotal[temp] <= secondX[temp] && sawYTotal[temp] <= secondY[temp])
						{
							turn = 1;
						}
					} //while turn = 0
					else if (turn == 1)
					{
						sawX[temp] = sawX[temp] + 4;
						sawY[temp] = sawY[temp] + 4;
						sawXTotal[temp] = sawX[temp] + firstX[temp];
						sawYTotal[temp] = sawY[temp] + firstY[temp];

						if (sawXTotal[temp] >= firstX[temp] && sawYTotal[temp] >= firstY[temp])
						{
							turn = 0;
						}
					} //while turn = 1
				break;
			case 4: //4: Point 1 is at the bottom left; Point 2 is at the top right
					if (turn == 0)
					{
						sawX[temp] = sawX[temp] + 4;
						sawY[temp] = sawY[temp] - 4;
						sawXTotal[temp] = sawX[temp] + firstX[temp];
						sawYTotal[temp] = sawY[temp] + firstY[temp];

						if (sawXTotal[temp] >= secondX[temp] && sawYTotal[temp] <= secondY[temp])
						{
							turn = 1;
						}
					} //while turn = 0
					else if (turn == 1)
					{
						sawX[temp] = sawX[temp] - 4;
						sawY[temp] = sawY[temp] + 4;
						sawXTotal[temp] = sawX[temp] + firstX[temp];
						sawYTotal[temp] = sawY[temp] + firstY[temp];

						if (sawXTotal[temp] <= firstX[temp] && sawYTotal[temp] >= firstY[temp])
						{
							turn = 0;
						}
					} //while turn = 1
				break;

			case 5: //5: Point 1 and 2 are on the same horizontal line with Point 2 being farther right; saw moves side to side
					if (turn == 0)
					{
						sawX[temp] = sawX[temp] + 4;
						sawXTotal[temp] = sawX[temp] + firstX[temp];
						sawYTotal[temp] = firstY[temp];

						if (sawXTotal[temp] >= secondX[temp])
						{
							turn = 1;
						}
					} //while turn = 0
					else if (turn == 1)
					{
						sawX[temp] = sawX[temp] - 4;
						sawXTotal[temp] = sawX[temp] + firstX[temp];
						sawYTotal[temp] = firstY[temp];

						if (sawXTotal[temp] <= firstX[temp])
						{
							turn = 0;
						}
					} //while turn = 1
				break;
			case 6: //6: Point 1 and 2 are on the same horizontal line with Point 1 being farther right; saw moves side to side
					if (turn == 0)
					{
						sawX[temp] = sawX[temp] - 4;
						sawXTotal[temp] = sawX[temp] + firstX[temp];
						sawYTotal[temp] = firstY[temp];

						if (sawXTotal[temp] <= secondX[temp])
						{
							turn = 1;
						}
					} //while turn = 0
					else if (turn == 1)
					{
						sawX[temp] = sawX[temp] + 4;
						sawXTotal[temp] = sawX[temp] + firstX[temp];
						sawYTotal[temp] = firstY[temp];

						if (sawXTotal[temp] >= firstX[temp])
						{
							turn = 0;
						}
					} //while turn = 1
				break;
			case 7: //7: Point 1 and 2 are on the same vertical line; saw moves up and down
					if (turn == 0)
					{
						sawY[temp] = sawY[temp] - 4;
						sawXTotal[temp] = firstX[temp];
						sawYTotal[temp] = sawY[temp] + firstY[temp];

						if (sawYTotal[temp] <= firstY[temp])
						{
							turn = 1;
						}
					} //while turn = 0
					else if (turn == 1)
					{
						sawY[temp] = sawY[temp] + 4;
						sawYTotal[temp] = sawY[temp] + firstY[temp];
						sawXTotal[temp] = firstX[temp];

						if (sawYTotal[temp] >= secondY[temp])
						{
							turn = 0;
						}
					} //while turn = 1
				break;
			case 8: //8: Point 1 and 2 are on the same vertical line; Point 1 is above Point 2 ;saw moves up and down
					if (turn == 0)
					{
						sawY[temp] = sawY[temp] + 4;
						sawXTotal[temp] = secondX[temp];
						sawYTotal[temp] = sawY[temp] + secondY[temp];
						
						if (sawYTotal[temp] >= firstY[temp])
						{
							turn = 1;
						}
					} //while turn = 0
					else if (turn == 1)
					{
						sawY[temp] = sawY[temp] - 4;
						sawYTotal[temp] = sawY[temp] + secondY[temp];
						sawXTotal[temp] = secondX[temp];

						if (sawYTotal[temp] <= secondY[temp])
						{
							turn = 0;
						}
					} //while turn = 1
				break;
			case 9: //Situation not accounted for
				System.out.println ("There is a situation not accounted for");
				break;
			}
			//System.out.println (sawXTotal[temp] + " " + sawYTotal[temp]);
			//sawXTotal[cnt1] += MainMenu.posx;
			//sawYTotal[cnt1] += MainMenu.posy;
		}//for		
	}//math
}//Saw