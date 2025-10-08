package labs;

/**
 * Name: Vincent Goldberg
 * Date: 8 Oct 2025
 * Lab #: 5
 * Source File: labs/Lab5.java
 * 
 * Action: This program prompts the user for a file name and
 * 		   a sentence, then writes each character and it's ASCII 
 * 		   value, followed by counts of letters and digits. 
 */
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Lab5 
{
	public static void main(String[] args) throws IOException
	{
		Scanner Input = new Scanner(System.in);
		
		// Prompt user for file name
		System.out.print("Enter the name of the output data file ---> ");
		String Filename = ("/home/buntu-1/Documents/IU/CSCI-255/labs/Data/" +
							Input.nextLine() ); 
		
		// Write output file
		PrintWriter OutFile = new PrintWriter(new FileWriter(Filename));
		
		
		// Prompt user for sentence
		System.out.println("Enter your sentence:");
		String Sentence = Input.nextLine();
		
		// Writing output to file
		OutFile.printf("%-8s %-12s%n", "Letter", "ASCII Value");
		
		int LetterCount = 0;
		int DigitCount = 0;
		
		for (int i = 0; i < Sentence.length(); i++)
		{
			char CurrentChar = Sentence.charAt(i);
			OutFile.printf("%-8s %5d%n", CurrentChar, (int) CurrentChar);
			
			if (Character.isLetter(CurrentChar))
			{
				LetterCount++;
			}
			else if (Character.isDigit(CurrentChar))
			{
				DigitCount++;
			}
		}
		
		OutFile.println();
		OutFile.printf("Letters = %d     Digits = %d%n", LetterCount, DigitCount);
		OutFile.close();
		
		// Process Completed Msg
		System.out.println("\nProgram Done.");
	}
}

/* ********************************* OUTPUT *********************************


Terminal Input:

Enter the name of the output data file ---> Lab5out.txt
Enter your sentence:
ABC 123 Hello!

Program Done.



File Output:

Letter   ASCII Value 
A           65
B           66
C           67
            32
1           49
2           50
3           51
            32
H           72
e          101
l          108
l          108
o          111
!           33

Letters = 8     Digits = 3


 */ 

