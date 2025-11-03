/* ******************************************************************
Author  :  Vincent Goldberg
Date    :  3 Nov 2025
Homework:  6
Compiler:  JavaSE-21
Source  :  HW5.JAVA

CLASS DESCRIPTION:	 
	MyFloat represents a decimal number between 0 and 1 with up to 20 digits of precision
	stored as characters. It supports digit-wise addition, string conversion, parsing,
	comparison, and copy construction. The number is stored in a fixed-length array with
	logic to ignore insignificant trailing zeros.
	
	Core behaviors:
	 - toMyFloat(String): Parse valid decimal strings like "0.123"
	 - toString(): Return MyFloat as a formatted decimal string
	 - Read()/Write(): Console I/O
	 - Add(): Digit-wise addition (no carry into ones place)
	 - isGreater(): Lexicographical comparison
	 - SetMyFloat(): Direct initialization from character array
	 - Copy constructor: Deep copy of another MyFloat

----------------------------------------------------------------------------*/
package homework;
import java.io.IOException;


class MyFloat 
{
	private final int MAX_DIGITS = 20;
	private char[] Number = new char[MAX_DIGITS +1];
	private char NumberOfDigits;
	
	
	/*
	 * Action: Default constructor to initialize MyFloat Object with 0's
	 * Parameters: None
	 * Returns: Nothing
	 */
	public MyFloat()
	{
		NumberOfDigits = 0;
		for (int i = 1; i <= MAX_DIGITS; i++)
			Number[i] = '0';
	}
	
	/*
	 * Action: Copy constructor for MyFloat
	 * Parameters: Other - another MyFloat object to copy
	 * Returns: Nothing
	 */
	public MyFloat(MyFloat Other)
	{
		this.NumberOfDigits = Other.NumberOfDigits;
		for (int i = 1; i <= MAX_DIGITS; i++)
		{
			this.Number[i] = Other.Number[i];
		}
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
	
	/*
	 * Action: Converts a string to a MyFloat object
	 * Parameters: InputString - input string 
	 * Returns: void
	 * Preconditions: S must be a string in format "0.123" or ".123"
	 */
	public void toMyFloat(String InputString)
	{
		NumberOfDigits = 0;
		int Length = InputString.length();
		int IndexTracker = 0;
		
		// Skip leading 0's and spaces
		while (IndexTracker < Length &&
				(InputString.charAt(IndexTracker) == ' ' ||
				InputString.charAt(IndexTracker) == '0')) 
			IndexTracker++;
		
		// Return 0 is properly format found
		if (IndexTracker >= Length || InputString.charAt(IndexTracker) != '.')
		{
			NumberOfDigits = 0;
			return;
		}
		
		// Read digits after decimal
		int DigitIndex = 1;
		IndexTracker++; // Move to next digit after '.'
		while (IndexTracker < Length && DigitIndex <= MAX_DIGITS)
		{
			char Digit = InputString.charAt(IndexTracker);
			if (Digit < '0' || Digit > '9')
				break; // Invalid character — stop reading
			Number[DigitIndex++] = Digit; // Add and increment
			NumberOfDigits++;
			IndexTracker++;
					
		}
		
		// Trim trailing zeros for NumberOfDigits count
		while (NumberOfDigits > 1 && Number[NumberOfDigits] == '0')
			NumberOfDigits--;
		
		// Pad unused array digits with '0' for addition 
		for (int i = NumberOfDigits + 1; i <= MAX_DIGITS; i++)
			Number[i] = '0';
	}
	
	/*
	 * Action: Converts this MyFloat to a string in the format 0.xxx
	 * Parameters: None
	 * Returns: String - formatted decimal string or "0.?" if invalid
	 * Preconditions: None
	 */
	public String toString()
	{
		// Invalid or empty number
		if (NumberOfDigits == 0)
			return "0.?";
		
		// Start with leading zero
		String Result = "0.";
		
		// Add each stored digit
		for (int i = 1; i <= NumberOfDigits; i++)
			Result = Result + Number[i]; // Concatenate digits
		
		return Result;
	}
	
	/*
	 * Action: Compares this.MyFloat to another and determine if it's greater
	 * Parameters: Y - the MyFloat to compare
	 * Returns: true if this > Y, false otherwise
	 * Preconditions: Both MyFloat objects are properly initialized
	 */
	public boolean isGreater(MyFloat Y)
	{
		for (int i = 1; i <= MAX_DIGITS; i++)
		{
			if (this.Number[i] > Y.Number[i])
				return true;
			if (this.Number[i] < Y.Number[i])
				return false;
		}
		return false; // All digits equal		
	}
	
	/*
	 * Action: Sets MyFloat object with given digits from char array
	 * Parameters:
	 * 		HowMany - number of digits to copy (char, not int)
	 * 		NumArray - char array containing digit characters ('0' to '9')
	 * Returns: void
	 * Preconditions:
	 * 		- HowMany must be <= MAX_DIGITS
	 * 		- NumArray must contain at least HowMany digit characters
	 */
	public void SetMyFloat(char HowMany, char[] NumArray)
	{
		NumberOfDigits = HowMany;
		
		// Copy digits
		for (int i = 1; i <= NumberOfDigits; i++)
			Number[i] = NumArray[i - 1];
		
		// Pad rest of array with 0's
		for (int i = NumberOfDigits + 1; i <= MAX_DIGITS; i++)
			Number[i] = '0';
	
	}
	
}

/* ************************ PROGRAM OUTPUT ************************



------------------------------------------------------------



------------------------------------------------------------



------------------------------------------------------------



------------------------------------------------------------



------------------------------------------------------------



------------------------------------------------------------



------------------------------------------------------------



------------------------------------------------------------




 */ 