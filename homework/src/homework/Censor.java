package homework;

/**
 * Name: Vincent Goldberg
 * Date: 16 Sep 2025
 * Homework #: 1
 * Source File: homework/Censor.java
 * Class: C201
 * Action: The program censors the word "hell" in a sentence using character arrays.
 */
import java.util.Scanner;

public class Censor {

	// Global variables
	private static final int MAX_LENGTH = 60;
	private static int NumberOfChars = 0;
	
	public static void main(String[] args) {
		Scanner Input = new Scanner(System.in);
		char[] Str = new char[MAX_LENGTH];
		char[] StrCopy = new char[MAX_LENGTH];
		
		try {
			GetSentence(Str, Input);
			
			while (NumberOfChars != 0 ) {
				// Program logic
				CopyString(StrCopy, Str);
				MakeUpperCase(StrCopy);
				InsertBlankStartEnd(StrCopy);
				ReplacePuncWithBlanks(StrCopy);
				SearchAndReplaceHell(StrCopy);
				DeleteBlankStartEnd(StrCopy);
				OriginalCaseAndPunc(StrCopy, Str);
				
				// Display output to user
				System.out.print("\nOriginal: \"");
				PrintArray(Str); // Print original characters, no newline
				System.out.println("\"");
				System.out.print("Censored: \"");
				PrintArray(StrCopy); // Print modified characters, no newline
				System.out.println("\"");
				System.out.println("------------".repeat(5));
				
				GetSentence(Str, Input);
			}
			
			System.out.println("Goodbye....."); // User entered an empty string; ending program
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("An Array went out of bounds.");
			System.out.println("Program terminated.");
		}
	}
	
	
	/**
	 * Action: Reads a line of input from the user and stores it into the character array.
	 * Parameters: SentenceArray - the character array to store the sentence.
	 * Returns: void
	 * Precondition: Array size is at least MAX_LENGTH.
	 */
	public static void GetSentence(char[] SentenceArray, Scanner UserInput) {
		System.out.print("\nEnter Sentence ===> ");
		String Sentence = UserInput.nextLine();
		
		// Changes NumberOfChars to either sentence length or MAX_LENGTH, whichever is smallest
		NumberOfChars = Math.min(Sentence.length(), MAX_LENGTH - 2); // Minus 2 to add space for blank before and after sentence
		
		// Copy sentence string into character array, character by character
		for (int i = 0; i < NumberOfChars; i++) {
			SentenceArray[i] = Sentence.charAt(i);
		}
	}
		
	
	/**
	 * Action: Prints the contents of a character array up to NumberOfChars.
	 * Parameters: SentenceArray - character array
	 * Returns: void
	 * Precondition: NumberOfChars <= SentenceArray.length
	 */
	public static void PrintArray(char[] SentenceArray) {
		for (int i = 0; i < NumberOfChars; i++) {
			System.out.print(SentenceArray[i]);
		}
	}
	
	
	/**
	 * Action: Copies contents of source array to destination array.
	 * Parameters: DestArray - target array, SourceArray - source array.
	 * Returns: void
	 * Precondition: Both arrays must be at least MAX_LENGTH in size
	 */
	public static void CopyString(char[] DestArray, char[] SourceArray) {
		for (int i = 0; i < NumberOfChars; i++) {
			DestArray[i] = SourceArray[i];
		}
	}
	
	
	/**
	 * Action: Converts all alphabetic characters in the array to uppercase.
	 * Parameters: SentenceArray – character array to convert.
	 * Returns: void
	 * Precondition: Array contains NumberOfChars valid characters.
	 */
	public static void MakeUpperCase(char[] SentenceArray) {
		for (int i = 0; i < NumberOfChars; i++) {
			SentenceArray[i] = Character.toUpperCase(SentenceArray[i]);
		}
	}
	
	/**
	 * Action: Inserts a blank space at the start and end of the array content.
	 * Parameters: SentenceArray - character array to modify.
	 * Returns: void
	 * Precondition: There must be space in the array (MAX_LENGTH) to add two chars.
	 */
	public static void InsertBlankStartEnd(char[] SentenceArray) {
		// Shift everything right by 1 to make space at start
		for (int i = NumberOfChars; i > 0; i--) {
			SentenceArray[i] = SentenceArray[i - 1];
		}
		SentenceArray[0] = ' '; // Insert space at beginning
		NumberOfChars++;
		SentenceArray[NumberOfChars] = ' '; // Insert space at end
		NumberOfChars++;
	}
	
	
	/**
	 * Action: Replaces all punctuation characters in the array with blanks.
	 * Parameters: SentenceArray - character array to modify.
	 * Returns: void
	 * Precondition: SentenceArray has NumberOfChars
	 */
	public static void ReplacePuncWithBlanks(char[] SentenceArray) {
		for (int i = 0; i < NumberOfChars; i++) {
			if (Character.isLetterOrDigit(SentenceArray[i]) || SentenceArray[i] == ' ') {
				continue; // Leave as-is
			} else {
				SentenceArray[i] = ' ';
			}
		}
	}
	
	
	/**
	 * Action: Searches for the word "HELL" surrounded by spaces and replaces it with "HECK".
	 * Parameters: SentenceArray - character array to modify.
	 * Returns: void
	 * Precondition: SentenceArray is uppercase and has leading/trailing spaces. 
	 */
	public static void SearchAndReplaceHell(char[] SentenceArray) {
		for (int i = 1; i <= NumberOfChars - 5; i++) {
			//Check for pattern: ' ' 'H' 'E' 'L' 'L' ' '
			if (SentenceArray[i - 1] == ' ' &&
				SentenceArray[i] == 'H' &&
				SentenceArray[i + 1] == 'E' &&
				SentenceArray[i + 2] == 'L' &&
				SentenceArray[i + 3] == 'L' &&
				SentenceArray[i + 4] == ' ') {
				
				// If matched, replace with: 'H' 'E' 'C' 'K'
				SentenceArray[i + 2] = 'C';
				SentenceArray[i + 3] = 'K';
			}
		}
	}
	
	
	/**
	 * Action: Removes the first and last character (blanks) from the array content.
	 * Parameters: SentenceArray - character array to modify.
	 * Returns: void
	 * Precondition: First and last characters are blanks inserted earlier. 
	 */
	public static void DeleteBlankStartEnd(char[] SentenceArray) {
		// Shift all characters one position to left to remove the first blank
		for (int i = 0; i < NumberOfChars; i++) {
			SentenceArray[i] = SentenceArray[i + 1];
		}
		NumberOfChars--; // Remove the leading blank
		NumberOfChars--; // Remove the trailing blank
	}
	
	
	/**
	 * Action: Restores original punctuation and casing from Str into StrCopy, 
	 * 		   keeping the censored changes in place. 
	 * Parameters: OriginalArray - original sentence with punctuation/case.
	 * 			   ModifiedArray - modified sentence with censoring applied.
	 * Returns: void
	 * Precondition: Both arrays are aligned by character index.
	 */
	public static void OriginalCaseAndPunc(char[] ModifiedArray, char[] OriginalArray) {
		for (int i = 0; i < NumberOfChars; i++) {
			char OrigChar = OriginalArray[i];
			char ModChar = ModifiedArray[i];
			
			// If original was punctuation, restore it
			if (!Character.isLetterOrDigit(OrigChar) && OrigChar != ' ') {
				ModifiedArray[i] = OrigChar;
			}
			// If ModChar is 'C', match case of original 'L'
			else if (Character.toUpperCase(ModChar) == 'C' && 
					 Character.toUpperCase(OrigChar) == 'L') {
				ModifiedArray[i] = Character.isUpperCase(OrigChar) ? 'C' : 'c';
			}
			// If ModChar is 'K', match case of original 'L'
			else if (Character.toUpperCase(ModChar) == 'K' && 
					 Character.toUpperCase(OrigChar) == 'L') {
				ModifiedArray[i] = Character.isUpperCase(OrigChar) ? 'K' : 'k';
			}
			// Otherwise, restore the original character's case
			else if (Character.toUpperCase(OrigChar) == ModChar) {
				ModifiedArray[i] = OrigChar;
			}
		}
	}
	
}

