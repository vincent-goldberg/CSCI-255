/* ******************************************************************
Author  :  Vincent Goldberg
Date    :  2 Nov 2025
Homework:  5
Compiler:  JavaSE-21
Source  :  HW5.JAVA

CLASS DESCRIPTION:	 The MyFloat class implements a simplified floating-point type
					 for numbers strictly between 0 and 1, represented as digits
					 after the decimal point (e.g., 0.12345). It supports up to
					 20 digits of precision, stored internally in a character array.

DATA MEMBERS:
	     - MAX_DIGITS: constant limit of 20 fractional digits.
	     - Number[]:   char array (index 1–20) holding each digit.
	     - NumberofDigits: number of significant digits currently stored.
 
 FUNCTIONAL OVERVIEW:
	     - Read():   Reads a MyFloat from standard input, skipping leading
	                 whitespace/zeros and validating format (must begin with '.').
	     - Write():  Outputs the value as 0.123… without trailing zeros,
	                 or "0.?" if invalid.
	     - Digits(): Returns how many digits are currently stored.
	     - MaxDigits(): Returns the constant 20.
	     - Add():    Performs digit-by-digit addition of two MyFloat values,
	                 ignoring carry into the ones place, and returns a new MyFloat.
	     - equals(): Compares two MyFloat objects for equality of digits.

----------------------------------------------------------------------------*/
package homework;
import java.io.IOException;


class MyFloat 
{
	private final int MAX_DIGITS = 20;
	private char[] Number = new char[MAX_DIGITS +1];
	private char NumberOfDigits;
	
	
	// Default constructor - setting NumberOfDigits = 0
	public MyFloat()
	{
		NumberOfDigits = 0;
		for (int i = 1; i <= MAX_DIGITS; i++)
			Number[i] = '0';
	}
	
	/*
	 * Action: Returns the number of digits in this MyFloat.
	 * Parameters: None
	 * Returns: int - the number of digits
	 * Preconditions: None
	 */
	public int Digits()
	{
		return NumberOfDigits;
	}
	
	/*
	 * Action: Returns the maximum allowed digits for a MyFloat.
	 * Parameters: None
	 * Returns: int - 20
	 * Precondition: None
	 */
	public int MaxDigits()
	{
		return MAX_DIGITS;
	}
	
	/*
	 * Actions: Writes the MyFloat to standard output in 0.123 format. 
	 * 			If invalid, prints 0.?
	 * Parameters: None
	 * Returns: void
	 * Precondition: Write assumes NumberOfDigits and Number[1..NumberOfDigits] are valid.
	 */
	public void Write()
	{
		if (NumberOfDigits == 0)
		{
			System.out.print("0.?");
			return;
		}
		
		System.out.print("0."); // Leading zero
		
		
		int Last = NumberOfDigits;
		// Trim trailing zeros
		while (Last> 1 && Number[Last] == '0')
			Last--; 
		
		// Output remaining digits
		for (int i = 1; i <= Last; i++)
			System.out.print(Number[i]);
	}
	
	/*
	 * Action: Reads a MyFloat from standard input using byte-by-byte reading.
	 * Parameters: None
	 * Returns: void
	 * Preconditions: None
	 */
	public void Read() throws IOException
	{
		int Ch;
		NumberOfDigits = 0;
		
		// Skip whitespace and leading zeros
		do
		{
			Ch = System.in.read();
		} while (Ch == ' ' || Ch == '\t' || Ch == '\n' || Ch == '0');
		
		// Check to see if input starts with decimal
		if (Ch != '.')
		{
			NumberOfDigits = 0;
			return;
		}
		
		// Read in digits
		for (int i = 1; i <= MAX_DIGITS; i++)
		{
			Ch = System.in.read();
			if (Ch < '0' || Ch > '9')
			{
				break; // Not a digit; NumberOfDigits stays 0
			}
			Number[i] = (char) Ch;
			NumberOfDigits++;
		}
		
		// Trim trailing zeros
		while (NumberOfDigits > 1 && Number[NumberOfDigits] == '0')
			NumberOfDigits--;
		
		// Pad remaining with '0' for addition 
		for (int i = NumberOfDigits + 1; i <= MAX_DIGITS; i++)
		{
			Number[i] = '0';
		}
	}
	
	/*
	 * Action: Adds two MyFloat values digit-wise (no carry into 1s place)
	 * Parameters: Y - the MyFloat to add to this instance
	 * Returns: A new MyFloat representing the sum
	 * Preconditions: Both MyFloats must be properly initialized
	 */
	public MyFloat Add(MyFloat Y)
	{
		MyFloat Z = new MyFloat();
		int Carry = 0;
		int MaxLength = Math.max(this.NumberOfDigits, Y.NumberOfDigits);
		
		for (int i = MAX_DIGITS; i >= 1; i--)
		{
			int Digit1 = this.Number[i] - '0';
			int Digit2 = Y.Number[i] - '0';
			int Sum = Digit1 + Digit2 + Carry;
			Z.Number[i] = (char)((Sum % 10) + '0');
			Carry = (Sum / 10); // Carry discarded after loop  
		}
		
		// Determine actual digit count for Z; removing trailing 0's
		Z.NumberOfDigits = (char) MaxLength;
		while (Z.NumberOfDigits > 1 && Z.Number[Z.NumberOfDigits] == '0')
			Z.NumberOfDigits--;
		return Z;
	}
	
	/*
	 * Action: Compares this MyFloat to another equality.
	 * Parameters: Y - the MyFloat to compare to
	 * Returns: true if both MyFloat objects are identical digits
	 * Precondition: Both MyFloats must be properly initialized
	 */
	public boolean equals(MyFloat Y)
	{
		if (this.NumberOfDigits != Y.NumberOfDigits)
			return false;
		
		for (int i = 1; i <= NumberOfDigits; i++)
		{
			if (this.Number[i] != Y.Number[i])
				return false;
		}
		return true;
	}
}

/* ************************ PROGRAM OUTPUT ************************


All 3 counts, no second argument:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java hw4.java data/words.1 
characters = 12
words = 3
lines = 1


------------------------------------------------------------


Characters only:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java hw4.java data/words.1 /c
characters = 12


------------------------------------------------------------


Characters + lines (argument order mixed):

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java hw4.java data/words.1 /lc
characters = 12
lines = 1


------------------------------------------------------------


Syntax error - invalid option:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java hw4.java data/words.1 /x
ERROR DETECTED
Invalid option character in second argument. Use only c, w, or l.


------------------------------------------------------------


Syntax error - missing filename:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java hw4.java 
ERROR DETECTED
Missing filename on command line.


------------------------------------------------------------


Syntax error - no '/':

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java hw4.java data/words.1 cwl
ERROR DETECTED
Second argument must start with a '/' character.


------------------------------------------------------------


Syntax error - too many arguments:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java hw4.java data/words.1 /lc extra
ERROR DETECTED
Too many arguments provided on command line.


------------------------------------------------------------


File open error - wrong filename:

buntu-1@dev-device:~/Documents/IU/CSCI-255/homework/src/homework$ java hw4.java missingfile.txt
Cannot open file missingfile.txt


 */ 