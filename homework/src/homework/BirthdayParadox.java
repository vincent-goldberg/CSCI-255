package homework;
/**
 * Name: Vincent Goldberg
 * Date: 28 Sep 2025
 * Homework #: 3
 * Source File: homework/BirthdayParadox.java
 * Class: C201
 * Action: The program explains and simulates the Birthday Paradox depending on
 * 		   user input. Using random numbers between 1-365, the program will 
 * 		   generate 23 birthdays and count the occurrences of duplicates. These
 * 		   samples either statically measures or shown to users to showcase the
 * 		   Birthday Paradox.
 */
import java.util.Scanner;
import java.util.Random;
import utils.SelectionSort;

public class BirthdayParadox 
{

	static final int SAMPLE_SIZE = 23;
	static final int NUMBER_OF_SETS = 1000;
	static int MonthNumber;
	static int DayNumber;
	static final Scanner Input = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		char UserChoice;
		
		do 
		{
			DisplayMenu();
			UserChoice = Input.next().toUpperCase().charAt(0);
			Input.nextLine();	// Clear input buffer
			
			switch (UserChoice)
			{
				case '1':
					ExplainParadox();
					break;
				case '2':
					CheckParadox();
					break;
				case '3':
					DisplaySet();
					break;
				case 'E':
					System.out.println("\nExiting program.");
					break;
				default:
					System.out.println("\nInvalid selection. Please try again.");
			}
		} 
		while (UserChoice != 'E');
	}
	
	
	/**
	 * Action: Displays the main menu for user selection.
	 * Parameters: None
	 * Returns: void
	 * Precondition: None 
	 */
	static void DisplayMenu()
	{
		System.out.println("\n============ Birthday Paradox Menu ============");
		System.out.println("1) Explain birthday paradox");
		System.out.println("2) Check birthday paradox by generating 1000 sets of birthdays");
		System.out.println("3) Display one set of 23 birthdays");
		System.out.println("E) Exit");
		System.out.print("Enter your choice --> ");
	}
	
	
	/**
	 * Action: Prints explanation of birthday paradox.
	 * Parameters: None
	 * Returns: void
	 * Precondition: None 
	 */
	static void ExplainParadox()
	{
		System.out.println("\nIf 23 persons are chosen at random, then the chances are more than "+
							"50% that at least two will have the same birthday!");
	}
	
	
	/**
	 * Action: Generates 1000 sets of 23 birthdays and calculates the percentage of matches.
	 * Parameters: none
	 * Returns: void
	 * Precondition: none 
	 */
	static void CheckParadox()
	{
		int MatchCount = 0;
		
		for (int i = 0; i < NUMBER_OF_SETS; i++)
		{
			int[] BirthdaySet = GenerateBirthdaySet();
			if (HasDuplicate(BirthdaySet)) {
				MatchCount++;
			}
		}
		
		double MatchPercent = (100.0 * MatchCount) / NUMBER_OF_SETS;
				
		System.out.println("\nGenerating 1000 sets of 23 birthdays and checking for matches...");
		System.out.printf("\nResults : %d out of %d (%.1f%%) of the sets contained matching birthdays\n",
						  MatchCount, NUMBER_OF_SETS, MatchPercent);
		System.out.println("==============================================================================");
	}
	
	
	/**
	 * Action: Generates one set of 23 birthdays, sorts them,
	 * 		   and prints them with duplicates marked.
	 * Parameters: None
	 * Returns: void
	 * Precondition: None
	 */
	static void DisplaySet()
	{
		int[] Birthdays = GenerateBirthdaySet();
		
		// Count frequencies
		int[] MatchCount = new int[366];
		for (int Day : Birthdays) MatchCount[Day]++;
		
		// Sort Birthdays
		SelectionSort.sort(Birthdays);
		
		System.out.println("\nHere are the results of generating a set of 23 birthdays");
		System.out.println("\n=================================================================");
		
		boolean[] Shown = new boolean[366];
		for (int i = 0; i < Birthdays.length; i++)
		{
			int Day = Birthdays[i];
			ConvertDayOfYear(Day);
			
			// Mark duplicates only on first occurrence
			String Prefix = "     ";
			if (MatchCount[Day] > 1 && !Shown[Day])
			{
				Prefix = " (" + MatchCount[Day] + ") ";
				Shown[Day] = true;
			}
			
			// Display table
			String Entry = String.format("%s%-10s %2d", Prefix, MonthName(MonthNumber), DayNumber);
			System.out.print(Entry);
			// Print seperator or newline depending on column
			if ((i + 1) % 3 == 0 || i == Birthdays.length -1) 
			{
				System.out.println(); 	// End of row
			}
			else
			{
				System.out.print("     "); // Space between columns
			}
		}
	}
	
	
	/**
	 * Action: Generates ar array of 23 random birthdays between 1-365.
	 * Parameters: None
	 * Returns: int[] of size 23 with values in range 1-365.
	 * Precondition: None 
	 */
	static int[] GenerateBirthdaySet()
	{
		Random RanNum = new Random();
		int[] Birthdays = new int[SAMPLE_SIZE];
		
		for (int i = 0; i < SAMPLE_SIZE; i++)
		{
			Birthdays[i] = RanNum.nextInt(365) + 1;		// 1 to 365
		}
		return Birthdays;
	}
	
	
	/**
	 * Action: Checks if birthday set contains duplicates.
	 * Parameters: int[] BirthdaySet - an array of day numbers.
	 * Returns: true if duplicates exists, false otherwise.
	 * Precondition: BirthdaySet must contain values in range 1-365.
	 */
	static boolean HasDuplicate(int[] BirthdaySet)
	{
		boolean[] Seen = new boolean[366];		// 1-based index
		
		for (int day : BirthdaySet)
		{
			if (Seen[day])
			{
				return true;	// Returns true if birthday has been seen before
			}
			Seen[day] = true;	// Sets day to true first time it's been seen
		}
		return false; 			// Return false if no birthdays matched
	}
	
	
	/**
	 * Action: Converts a day-of-year (1-365) to a month and day.
	 * Parameters: int DayOfYear
	 * Returns: void (sets global MonthNumber and DayNumber)
	 * Precondition: 1 <= DayofYear <= 365
	 */
	static void ConvertDayOfYear(int DayOfYear)
	{
		// Days in each month (non-leap year)
		int[] MonthDays = {31, 28, 31, 30, 31, 30,
						   31, 31, 30, 31, 30, 31};
		
		int Month = 1;
		while (DayOfYear > MonthDays[Month - 1])
		{
			DayOfYear -= MonthDays[Month - 1];
			Month++;
		}
		MonthNumber = Month;
		DayNumber = DayOfYear;
	}
	
	
	/**
	 * Action: Returns month name for a given month number.
	 * Parameters: int Month - number to be converted to name.
	 * Returns: Month name
	 * Precondition: Month number must be between 1 to 12. 
	 */
	static String MonthName(int Month)
	{
		String[] Months = {"January", "February", "March", "April",
						   "May", "June", "July", "August", "September",
						   "October", "November", "December"};
		return Months[Month - 1];
	}

	
	

}












