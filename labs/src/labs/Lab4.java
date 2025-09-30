package labs;

/**
 * Name: Vincent Goldberg
 * Date: 29 Sep. 2025
 * Lab #: 4
 * Source File: labs/Lab4.java
 * 
 * Action: Displays rows or columns of a 3x5 array
 * 		   based on command-line input. 
 */

public class Lab4 
{

	public static void main(String[] args) 
	{
		// Declare 3x5 array
		int[][] Data = 
			{
				{2, 4, 6, 8, 10},
				{1, 3, 5, 7, 9},
				{0, 0, 1, 1, 0}
			};

		// Check if command line arguments are given
		if (args.length == 0)
		{
			System.out.println("No command line arguments given.");
		}
		else 
		{
			// Extract direction and number from args
			char Direction = Character.toUpperCase(args[0].charAt(0)); 
			int Show = Integer.parseInt(args[1]); // Num of col/row to show
		
		
			// Display rows
			if (Direction == 'R')
			{
				for (int row = 0; row < Show; row++)
				{
					for (int col = 0; col < Data[row].length; col++)
					{
						System.out.print(Data[row][col] + " ");
					}
					System.out.println();
				}
			}
			// Display columns
			else if (Direction == 'C')
			{
				for (int row = 0; row < Data.length; row++)
				{
					for (int col = 0; col < Show; col++)
					{
						System.out.print(Data[row][col] + " ");
					}
					System.out.println();
				}
			}
		}
	}
}

/* ********************************* OUTPUT *********************************

R 2:
buntu-1@dev-device:~/Documents/IU/CSCI-255/labs/src/labs$ java Lab4.java R 2
2 4 6 8 10 
1 3 5 7 9 

R 3:
buntu-1@dev-device:~/Documents/IU/CSCI-255/labs/src/labs$ java Lab4.java R 3
2 4 6 8 10 
1 3 5 7 9 
0 0 1 1 0

C 1:
buntu-1@dev-device:~/Documents/IU/CSCI-255/labs/src/labs$ java Lab4.java C 1
2 
1 
0 

No args:
buntu-1@dev-device:~/Documents/IU/CSCI-255/labs/src/labs$ java Lab4.java 
No command line arguments given.

 */ 

