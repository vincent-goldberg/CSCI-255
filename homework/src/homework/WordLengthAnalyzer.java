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
					Length++;
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

/* ************************ PROGRAM OUTPUT ************************

words.1 Output:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java WordLengthAnalyzer.java < data/words.1

 Word Length    Frequency 
 -----------    ----------
 1               0          
 2               1          
 3               1          
 4               1          
 5               0          
 6               0          
 7               0          
 8               0          
 9               0          
 10              0          
 11              0          
 12              0          
 13              0          
 14              0          
 15              0          

Average word length:  3.000000

------------------------------------------------------------

words.2 Output:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java WordLengthAnalyzer.java < data/words.2

 Word Length    Frequency 
 -----------    ----------
 1               0          
 2               3          
 3               4          
 4               1          
 5               1          
 6               2          
 7               1          
 8               0          
 9               0          
 10              0          
 11              0          
 12              0          
 13              0          
 14              0          
 15              2          

Average word length:  5.428571


------------------------------------------------------------

words.3 Output:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java WordLengthAnalyzer.java < data/words.3

 Word Length    Frequency 
 -----------    ----------
 1               0          
 2               0          
 3               0          
 4               0          
 5               0          
 6               0          
 7               0          
 8               0          
 9               0          
 10              0          
 11              0          
 12              0          
 13              0          
 14              0          
 15              0          

Average word length:  0.000000


------------------------------------------------------------

words.4 Output:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java WordLengthAnalyzer.java < data/words.4

 Word Length    Frequency 
 -----------    ----------
 1               5          
 2               7          
 3               15         
 4               12         
 5               8          
 6               6          
 7               4          
 8               4          
 9               2          
 10              0          
 11              1          
 12              0          
 13              0          
 14              0          
 15              0          

Average word length:  4.328125


------------------------------------------------------------

words.5 Output:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java WordLengthAnalyzer.java < data/words.5

Word Length    Frequency 
 -----------    ----------
 1               0          
 2               0          
 3               0          
 4               0          
 5               1          
 6               0          
 7               0          
 8               0          
 9               0          
 10              0          
 11              1          
 12              0          
 13              0          
 14              0          
 15              0          

Average word length:  8.000000


------------------------------------------------------------

word.6 --> Hello, world! This is Java.

words.6 Output:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java WordLengthAnalyzer.java < data/words.6

 Word Length    Frequency 
 -----------    ----------
 1               0          
 2               1          
 3               0          
 4               2          
 5               2          
 6               0          
 7               0          
 8               0          
 9               0          
 10              0          
 11              0          
 12              0          
 13              0          
 14              0          
 15              0          

Average word length:  4.000000


------------------------------------------------------------

word.7 --> It's well-known that don't and can't are contractions.

words.7 Output:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java WordLengthAnalyzer.java < data/words.7

 Word Length    Frequency 
 -----------    ----------
 1               0          
 2               0          
 3               2          
 4               2          
 5               2          
 6               0          
 7               0          
 8               0          
 9               0          
 10              1          
 11              0          
 12              1          
 13              0          
 14              0          
 15              0          

Average word length:  5.750000


------------------------------------------------------------

word.8 --> This is a line-
		   break word.

words.8 Output:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java WordLengthAnalyzer.java < data/words.8

 Word Length    Frequency 
 -----------    ----------
 1               1          
 2               1          
 3               0          
 4               2          
 5               0          
 6               0          
 7               0          
 8               0          
 9               1          
 10              0          
 11              0          
 12              0          
 13              0          
 14              0          
 15              0          

Average word length:  4.000000


------------------------------------------------------------

word.9 --> Antidisestablishmentarianism pseudopseudohypoparathyroidism supercalifragilisticexpialidocious

words.9 Output:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java WordLengthAnalyzer.java < data/words.9

 Word Length    Frequency 
 -----------    ----------
 1               0          
 2               0          
 3               0          
 4               0          
 5               0          
 6               0          
 7               0          
 8               0          
 9               0          
 10              0          
 11              0          
 12              0          
 13              0          
 14              0          
 15              3          

Average word length:  15.000000


 */ 
