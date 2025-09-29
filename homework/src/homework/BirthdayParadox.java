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
			if (HasDuplicate(BirthdaySet)) 
			{
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
	 * Action: Displays a set of 23 birthdays; generates random birthdays if input is null.
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
		
		// Sort Birthdays
		SelectionSort.sort(Birthdays);
		
		// Display table
		System.out.println("\nHere are the results of generating a set of 23 birthdays");
		System.out.println("\n=================================================================");
	
		
		int CurrentDate = 0;   // Tracks date being displayed
		int ColCount = 1;      // Tracks columns

		for (int Day : Birthdays) 
		{
		    if (Day == CurrentDate) 
		    {
		        continue; // skip duplicates beyond the first
		    }

		    ConvertDayOfYear(Day);

		    String entry;
		    if (MatchCount[Day] > 1) 
		    {
		        // Multiple birthdays
		        entry = String.format("(%d) %-10s %2d",
		                               MatchCount[Day], MonthName(MonthNumber), DayNumber);
		    } 
		    else 
		    {
		        // Normal birthday
		        entry = String.format("    %-10s %2d",
		                               MonthName(MonthNumber), DayNumber);
		    }

		    // Print the entry
		    System.out.print(entry);

		    // Handle spacing or newline
		    if (ColCount % 3 == 0) 
		    {
		        System.out.println();
		    } 
		    else 
		    {
		        System.out.print("    ");
		    }

		    CurrentDate = Day;
		    ColCount++;
		}

		// End with newline if not already
		if ((ColCount - 1) % 3 != 0) 
		{
		    System.out.println();
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

/* ************************ PROGRAM OUTPUT ************************

Menu Option: 1

============ Birthday Paradox Menu ============
1) Explain birthday paradox
2) Check birthday paradox by generating 1000 sets of birthdays
3) Display one set of 23 birthdays
E) Exit
Enter your choice --> 1

If 23 persons are chosen at random, then the chances are more than 50% that at least two will have the same birthday!

------------------------------------------------------------

Menu Option: 2 (Tested twice)

============ Birthday Paradox Menu ============
1) Explain birthday paradox
2) Check birthday paradox by generating 1000 sets of birthdays
3) Display one set of 23 birthdays
E) Exit
Enter your choice --> 2

Generating 1000 sets of 23 birthdays and checking for matches...

Results : 484 out of 1000 (48.4%) of the sets contained matching birthdays
==============================================================================

============ Birthday Paradox Menu ============
1) Explain birthday paradox
2) Check birthday paradox by generating 1000 sets of birthdays
3) Display one set of 23 birthdays
E) Exit
Enter your choice --> 2

Generating 1000 sets of 23 birthdays and checking for matches...

Results : 506 out of 1000 (50.6%) of the sets contained matching birthdays
==============================================================================

------------------------------------------------------------

Menu Option: 3 (Tested nine times)
	- Six hard-coded test for edge cases (Reference TestDisplaySet() for test data)
	- Three random test

	
===== Test Case 1 =====

Here are the results of generating a set of 23 birthdays

=================================================================
    January     1        January     2        January     3
    January     4        January     5        January     6
    January     7        January     8        January     9
    January    10        January    11        January    12
    January    13        January    14        January    15
    January    16        January    17        January    18
    January    19        January    20        January    21
    January    22        January    23    


===== Test Case 2 =====

Here are the results of generating a set of 23 birthdays

=================================================================
(23) April      10  


===== Test Case 3 =====

Here are the results of generating a set of 23 birthdays

=================================================================
(2) January     5        January    10        January    20
    January    30        February    9        February   19
    March       1        March      11        March      21
    March      31        April      10        April      20
    April      30        May        10        May        20
    May        30        June        9        June       19
    June       29        July        9        July       19
    July       29 
    
    
===== Test Case 4 =====

Here are the results of generating a set of 23 birthdays

=================================================================
    January     1        January    15        January    25
    February    4        February   14        February   24
    March       6        March      16        March      26
    April       5        April      15        April      25
    May         5        May        15        May        25
    June        4        June       14        June       24
    July        4        July       14        July       24
(2) December   31    

    
===== Test Case 5 =====

Here are the results of generating a set of 23 birthdays

=================================================================
(2) January     1        February   19        March       1
    March      11        March      21        March      31
    April      10        April      20        April      30
    May        10        May        20        May        30
    June        9        June       19        June       29
    July        9        July       19        July       29
    August      8        August     18    (2) December   31
    
    
===== Test Case 6 =====

Here are the results of generating a set of 23 birthdays

=================================================================
(2) February    1        February    2        February   14
    March      31    (2) May        30        May        31
    July       19        July       29    (2) August      8
    August      9        September   7        September  17
    September  27        October     7        October    17
    October    27        November    6        November   16
    November   26        December    6   
    
    
===== Random Test 1 =====

Here are the results of generating a set of 23 birthdays

=================================================================
    January    23        March       8        March      12
    March      29        April      28        May         6
    May        17        May        20        June        2
    June       12        June       14        July       13
    July       21        August     11        August     29
(2) October     4        November    1        November    4
(2) November   28        December    5        December   20

===== Random Test 2 =====

Here are the results of generating a set of 23 birthdays

=================================================================
    January     2        January    12        February    2
    February    8        February   11        February   25
    March      12        March      16        March      19
    April       6        June        3        June        9
    June       23        June       24        September  13
    September  18        October     9        October    25
    October    29        October    30        November   10
    November   20        December   25  

===== Random Test 3 =====

Here are the results of generating a set of 23 birthdays

=================================================================
    February    3        February   12        February   17
    March       7        March      21        March      30
    April       8        April      23        April      30
    May         4        May        24        June        5
    June       16        June       26        July       29
    July       31        August      3        September   7
    November    3        November   23        November   28
    December    2        December   18 

------------------------------------------------------------

Menu Option: E (Tested three times)

============ Birthday Paradox Menu ============
1) Explain birthday paradox
2) Check birthday paradox by generating 1000 sets of birthdays
3) Display one set of 23 birthdays
E) Exit
Enter your choice --> e

Exiting program.


============ Birthday Paradox Menu ============
1) Explain birthday paradox
2) Check birthday paradox by generating 1000 sets of birthdays
3) Display one set of 23 birthdays
E) Exit
Enter your choice --> exit

Exiting program.


============ Birthday Paradox Menu ============
1) Explain birthday paradox
2) Check birthday paradox by generating 1000 sets of birthdays
3) Display one set of 23 birthdays
E) Exit
Enter your choice --> E

Exiting program.

 */ 












