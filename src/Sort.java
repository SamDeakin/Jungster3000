//errors will be fixed when character class is added

//Khamil did this
public class Sort {

	static Character [] newChar;
	static Character temp1, temp2;
	static String line;
	static int check, against;
	static int numEntries; 

	public static void sort ()
	{
		int cnt = 0;
		int cnt1 = 1;
		if (numEntries>1)
		{
			check = newChar[numEntries-cnt].getScore();
			against = newChar [numEntries-cnt1].getScore();

			System.out.println(check);
			System.out.println(against);

			while (check < against)
			{
				temp1 = newChar[numEntries-cnt];
				temp2 = newChar[numEntries -cnt1];

				newChar[numEntries-cnt] = temp2;
				newChar[numEntries-cnt1] = temp1;
				cnt++;
				cnt1++;

				if ((numEntries - cnt1) >= 1)
				{
					check = newChar[numEntries-cnt].getScore();
					against = newChar[numEntries-cnt1].getScore();
				}
				else 
				{
					break;
				}

			}
		}
	}
}