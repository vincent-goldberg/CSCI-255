package homework;

/**
 * Name: Vincent Goldberg
 * Date: 21 Sep 2025
 * Homework #: 2
 * Source File: homework/WordLengthAnalyzer.java
 * Class: C201
 * Action: This program reads a text manuscript from standard input and analyzes 
 * 		   the frequency of word lengths from 1 to 15 characters, accounting for
 * 		   internal hyphens and apostrophes. Outputs a frequency table and average
 * 		   word length. 
 */
import java.io.IOException;

public class WordLengthAnalyzer {

	public static void main(String[] args) throws IOException {
		// Local variables
		int[] NumOfWords = new int[16]; // Index 1-15 used, 0 unused
		int Length = NextWordLength(); // Length of next word
		
		// Program logic
		while (Length != 0)
		{
			if (Length > 15) 
			{
				Length = 15;
			}
			NumOfWords[Length]++;
			Length = NextWordLength();
		}
		
		// Display frequency table
		DisplayTable(NumOfWords);
	}
	
	/**
	 * Action: Reads characters from standard input and determines length
	 * 		   of next word. 
	 * Returns: int - the length of the next word (0 if EOF or no more words).
	 * Precondition: Input stream is valid and open. 
	 */
	static int NextWordLength() throws IOException {
		// Local variables
		int Length = 0;
		int ch = System.in.read();
		
		
		// Skip leading non-alphanumeric characters
		while (ch != -1 && ch != 26 && ch != '~' && !Character.isLetterOrDigit(ch)) 
		{
			ch = System.in.read(); // Keep reading until a letter or digit is found
		}
		
		// Check if EOF is reached before find a word
		if (ch == -1 || ch == 26 || ch == '~') return 0;
		
		// Found first character of word (letter/digit)
		Length++;
		
		// Read remaining characters of word
		while (true) 
		{
			ch = System.in.read();
			
			if (Character.isLetterOrDigit(ch)) 
			{
				Length++;
			}
			else if (ch == '-' || ch == '\'') // Determine hyphen/apostrophe use
			{
				System.in.mark(2); 			  // Mark this position in input
				int NextCh = System.in.read();
				
				// Special case: line break after hyphen
				if (ch == '-' && (NextCh == '\r' || NextCh == '\n'))
				{
					if (NextCh == '\r') System.in.read(); // Windows: skip \n if \r\n
					ch = System.in.read(); // Read next real char
					continue; // Continue with loop
				}
				
				// Valid hyphen/apostrophe inside word
				if (Character.isLetterOrDigit(NextCh)) 
				{
					Length++;	  // Count the punctuation
					Length++;	  // Count the next letter/digit
					ch = NextCh;  // Move forward with counting
				} else {
					System.in.reset(); // Not valid - go back to punctuation
					break; // End of word
				}
			} else {
				break; // Any other punctuation ends the word
			}
		}
		return Length; // Return total characters counted
	}
	
	/**
	 * Action: Displays the frequency of each word length and calculates average.
	 * Parameters: freq - array containing counts of words by length (1-15).
	 * Returns: void
	 * Precondition: freq[1..15] contains valid word length counts. 
	 */
	static void DisplayTable(int[] freq) {
		// Local variables
		int TotalWords = 0;
		int TotalLength = 0;
		
		System.out.printf("%-15s%11s\n", "\n Word Length ", "  Frequency ");
		System.out.printf("%-15s%11s\n", " -----------   ", "----------");
		
		// Display frequency table 
		for (int i = 1; i <= 15; i++)
		{
			System.out.printf("% -17d%-11d\n", i, freq[i]);

			TotalWords += freq[i];
			TotalLength += freq[i] * i;
		}
		
		// Calculate/display average 
		double Average = (TotalWords > 0) ?((double) TotalLength / TotalWords) : 0.0;
		System.out.printf("\nAverage word length:  %.6f\n", Average);
	}
}
