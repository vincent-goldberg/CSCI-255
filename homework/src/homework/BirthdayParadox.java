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
		// Run test data
		TestDisplaySet();
		
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
					DisplaySet(null);
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
	 * Action: Displays a set of 23 birthdays; generates random birthdays 
	 * 		   if input is null.
	 * Parameters: int[] Birthdays - array of days (1-365), or null to generate test set 
	 * Returns: void
	 * Precondition: If non-null, array must contain values in range 1-365
	 */
	static void DisplaySet(int[] Birthdays)
	{
		// If not array provided, generate one
		if (Birthdays == null)
		{
			Birthdays = GenerateBirthdaySet();
		}
				
		// Count frequencies
		int[] MatchCount = new int[366];
		for (int Day : Birthdays) MatchCount[Day]++;
//		for (int count : MatchCount) {
//			if (count != 0) {
//				System.out.println(count);
//			}
//		}
//		
		
		// Sort Birthdays
		SelectionSort.sort(Birthdays);
		
		// Display table
		System.out.println("\nHere are the results of generating a set of 23 birthdays");
		System.out.println("\n=================================================================");
	
		
		int CurrentDate = 0;   // Tracks date being displayed
		int ColCount = 1;      // Tracks columns

		for (int Day : Birthdays) {
		    if (Day == CurrentDate) {
		        continue; // skip duplicates beyond the first
		    }

		    ConvertDayOfYear(Day);

		    String entry;
		    if (MatchCount[Day] > 1) {
		        // Multiple birthdays
		        entry = String.format("(%d) %-10s %2d",
		                               MatchCount[Day], MonthName(MonthNumber), DayNumber);
		    } else {
		        // Normal birthday
		        entry = String.format("    %-10s %2d",
		                               MonthName(MonthNumber), DayNumber);
		    }

		    // Print the entry
		    System.out.print(entry);

		    // Handle spacing or newline
		    if (ColCount % 3 == 0) {
		        System.out.println();
		    } else {
		        System.out.print("    ");
		    }

		    CurrentDate = Day;
		    ColCount++;
		}

		// End with newline if not already
		if ((ColCount - 1) % 3 != 0) {
		    System.out.println();
		}

		
//		int CurrentDate = 0;	// Tracks date being displayed
//		int ColCount = 1; 		// Tracks columns
//		// Loop to display duplicates or single birthdays
//		for (int Day : Birthdays)
//		{
//			// Update global variables
//			ConvertDayOfYear(Day);
//			
////			if (Day != CurrentDate) 
//			if (MatchCount[Day] > 1 && Day != CurrentDate)
//			{
//				
////				System.out.println(Day +  " " + MatchCount[Day]);
//				
//				// Multiple birthdays
//				System.out.printf("(%d) %-10s %2d",
//								  MatchCount[Day], MonthName(MonthNumber), DayNumber);
//				// Spacing or newline
//				if (ColCount % 3 == 0)
//				{
//					System.out.println();
//				}
//				else
//				{
//					System.out.print("    ");
//				}
//				
//				CurrentDate = Day;
//				ColCount++;
//			}
//			else if (Day != CurrentDate)
//			{
//				System.out.printf("    %-10s %2d", MonthName(MonthNumber), DayNumber);
//				// Spacing or newline
//				if (ColCount % 3 == 0)
//				{
//					System.out.println();
//				}
//				else
//				{
//					System.out.print("    ");
//				}
//				
//				CurrentDate = Day;
//				ColCount++;
//				
//			}
			
//			ColCount++;
//			
//			// Spacing or newline
//			if (ColCount % 3 == 0)
//			{
//				System.out.println();
//			}
//			else
//			{
//				System.out.print("    ");
//			}
			
//			else
//			{
//				System.out.printf("    %-10s %2d", MonthName(MonthNumber), DayNumber);
//				System.out.println();
//				CurrentDate = Day;
//			}
//		}
		
//		for (int i = 0; i < Birthdays.length; i++)
//		{
//			
//			System.out.println(i + " " + MatchCount[i]);
//		}
		
//		
//		System.out.println("\nHere are the results of generating a set of 23 birthdays");
//		System.out.println("\n=================================================================");
		
//		// Display table
//		int Printed = 0; 	// HOw many birthdays actually printed
//		for (int i = 0; i < Birthdays.length; i++)
//		{
//			// Update global variables
//			int Day = Birthdays[i];
//			ConvertDayOfYear(Day);
//			
//			int BirthdayMatches = MatchCount[i]; 	// Count of matched birthdays
//			
//			System.out.println(BirthdayMatches);
//			if (BirthdayMatches > 1)
//			{
//				// Multiple birthdays
//				System.out.printf("(%d) %-10s %2d",
//								  BirthdayMatches, MonthName(MonthNumber), DayNumber);
//				i += BirthdayMatches - 1; 	// Skip duplicates
//			}
//			else
//			{
//				// Single birthday
//				System.out.printf("    %-10s %2d", MonthName(MonthNumber), DayNumber);
//			}
		}
//			Printed++; 
//			
//			// Manage columns: 3 per row
//			if (Printed % 3 == 0) 
//			{
//				System.out.println(); 	// End of row
//			}
//			else
//			{
//				System.out.print("     "); // Space between columns
//			}
//		}
//		
//		// If last row wasn't completed, end with newline
//		if (Printed % 3 != 0) 
//		{
//			System.out.println();
//		}
//	}
	
	
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
	
	
	/**
	 * Action: Runs six hard-coded test scenarios for DisplaySet().
	 * 		   Each test demonstrates different solutions and edge cases.
	 * Parameters: None
	 * Returns: void
	 * Precondition: None 
	 */
	static void TestDisplaySet()
	{
		int[][] TestCases = {
			// 1. All Unique
	        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
	          11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 },

	        // 2. All the Same (23 copies of day 100)
	        { 100,100,100,100,100,100,100,100,100,100,
	          100,100,100,100,100,100,100,100,100,100,100,100,100 },

	        // 3. Duplicate at Start
	        { 5, 5, 10, 20, 30, 40, 50, 60, 70, 80,
	          90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210 },

	        // 4. Duplicate at End
	        { 1, 15, 25, 35, 45, 55, 65, 75, 85, 95,
	          105, 115, 125, 135, 145, 155, 165, 175, 185, 195, 205, 365, 365 },

	        // 5. Min/Max Duplicates
	        { 1, 1, 365, 365, 50, 60, 70, 80, 90, 100,
	          110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230 },

	        // 6. Mixed Scenario
	        { 32, 32, 33, 45, 90, 150, 150, 151, 200, 210,
	          220, 220, 221, 250, 260, 270, 280, 290, 300, 310, 320, 330, 340 }
		};
		
		for (int i = 0; i < TestCases.length; i++)
		{
			System.out.println("\n===== Test Case " + (i + 1) + " =====");
			DisplaySet(TestCases[i]);
		}
	}
}












