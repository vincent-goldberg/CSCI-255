package labs;

/**
 * Name: Vincent Goldberg
 * Date: 22 Oct 2025
 * Lab #: 6
 * Source File: labs/Lab6.java
 * 
 * Action: Reads student names and test scores from a file,
 * 		   calculates the average score for each student,
 * 		   and prints their name with the computed average.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Lab6 {

	public static void main(String[] args) {
		
		try 
		{
			Scanner FileIn = new Scanner(new File("/home/buntu-1/Documents/IU/CSCI-255/labs/Data/Lab6Data.txt"));
			
			// Loop through file
			while (FileIn.hasNextLine()) 
			{
				Student CurrentStudent = new Student();
				
				// Read name
				CurrentStudent.Name = FileIn.nextLine().trim();
				
				// Read 5 scores
				for (int i = 0; i < 5; i++)
				{
					if (FileIn.hasNextLine())
					{
						CurrentStudent.Scores[i] = FileIn.nextInt();
					}
				}
				
				// Compute average
				CurrentStudent.ComputerAverage();
				
				// Display output
				System.out.println("Student's Name ---> " + CurrentStudent.Name);
				System.out.println("Average is ---> " + CurrentStudent.AverageScore);
				System.out.println();
				
				// Remove leftover newline
				if (FileIn.hasNextLine()) FileIn.nextLine();
			}
			FileIn.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found. Double check your file path for \"Lab6Data.txt\".");
		}
	}

}

// Student class to store name, scores, and average
class Student 
{
	String Name;
	int[] Scores = new int[5];
	float AverageScore;
	
	// Method to compute average of the 5 scores
	void ComputerAverage()
	{
		int Sum = 0;
		for (int Score : Scores) 
		{
			Sum += Score;
		}
		AverageScore = (float) Sum / Scores.length;
	}
}

/* ********************************* OUTPUT *********************************


Correct File Path Output:

Student's Name ---> Steve
Average is ---> 82.0

Student's Name ---> Jane
Average is ---> 73.2

Student's Name ---> Mike
Average is ---> 92.6

Student's Name ---> Kelly
Average is ---> 74.2


Incorrect File Path Output ("Lab6Data.txt"):

File not found. Double check your file path for "Lab6Data.txt".

 */ 

