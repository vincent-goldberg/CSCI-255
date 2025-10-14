/* ******************************************************************
Author  :  Vincent Goldberg  and Matt Holloway
Date    :  14 Oct 2025
Homework:  4
Compiler:  JavaSE-21
Source  :  HW4.JAVA

Action  :  Program will read in text and keeps track of the number of
           characters, words and lines from an external file. File name
           is the first argument in command line.  Main program also
           gives the user the ability to use command line input to program.
           This will tell program what to display, i.e. display the
           number of words only, or lines or all of them.
           example at command line if this file is called "hw4.java":

                  hw4  filename  /options

           possible options are
                    l  : liness
                    w  : words
                    c  :characters

   NOTE : This program will only run with command line input from external
          file given on the command line.  Cannot use keyboard input for this
          program.
----------------------------------------------------------------------------*/
package homework;
import java.io.*;

class CmdLineRecord
{
   int SyntaxError;                      // error codes for syntax eror
   boolean WantsLineCount;               // set to true if user wants line count
   boolean WantsWordCount;               // set to true if user wants word count
   boolean WantsCharCount;               // set to true if user wants char count
}

class CountsRecord
{
   int LineCount;          // number of lines
   int WordCount;          // number of words
   int CharCount;          // number of characters
}

public class hw4 
{
	public static void main(String[] args)
	{
		CmdLineRecord User = new CmdLineRecord();
		CountsRecord Data = new CountsRecord();
		int FileOpened = 1;

		DetermineWhatUserWants(User, args);
		   
		if (User.SyntaxError == 0)
		{
			FileOpened = CountLineWordChar(Data, args[0]);
		}
		
		if (FileOpened == 1)
		{
			ReportResults(User, Data, args);
		}
		else
		{
			System.err.print("Cannot open file " + args[0] + "\n");   
		}
	}

//------------------------------------------------------------------------

/* *************  DetermineWhatUserWants  ****************************
Action  :  Function will determine what the user wants to have displayed
           for the count, based on command line input, i.e. does user
           want to display how many lines or words or characters or
           combination of them, or if an input error occurred. First 
           argument in command line must be the file name the second argument
           is the option of display.

           Command line options are inputed after the "/" char and are:
                c : character count
                w : word count
                l : line count

           If no options are inputed then the default of display all count
           totals is done, otherwise the option is done.  One option or two
           is permitted after the "/", illegal character option will result
           in a syntax error.

Parameters:
    U      : User variable of class type given above
  Arg      : array of strings holding the arguments 
    
Returns    : nothing
Preconditions : None
=======================================================================*/
static void DetermineWhatUserWants(CmdLineRecord U, String [] Arg)
{

  U.SyntaxError = 0;           // assume no syntax error
  U.WantsLineCount = false;    // initialize all wants to false to start
  U.WantsCharCount = false;
  U.WantsWordCount = false;
  
  if (Arg.length < 1)
  {
	  U.SyntaxError = 1;	// Missing filename
	  return;
  } 
  
  if (Arg.length > 2) 
  {
	  U.SyntaxError = 2; 	// Too many arguments
  }
  
  if (Arg.length == 1)		// No 2nd Arg - List all counts
  {
	  U.WantsLineCount = true;   
	  U.WantsCharCount = true;
	  U.WantsWordCount = true;
  }
  
  if (Arg.length == 2) 
  {
	  String Options = Arg[1];
	  
	  if (!Options.startsWith("/"))
	  {
		  U.SyntaxError = 3; 	// Options don't start with '/'
		  return;
	  }
	    
	  for (int i = 1; i < Options.length(); i++)
	  {
		  char OptionChar = Options.charAt(i);
		  switch (OptionChar)
		  {
		  case 'l': U.WantsLineCount = true; break;
		  case 'w': U.WantsWordCount = true; break;
		  case 'c': U.WantsCharCount = true; break;
		  default: U.SyntaxError = 4; return;	// Invalid option character
		  }
	  }
  }
}

/* ********************  CountLineWordChar  *****************************
Action  :  Function will count the number of characters, words and lines
           in given input stream of text ended by control Z, ^Z or EOF

Parameters:
    Data    : variable of type class given above
    File  : second command line argument that has the file to read from

Returns   : 1 if file opened, 0 if can not open file or not found or error

NOTE   :  Characters are everything, including newline and form feed.
          Words are delimited by whitespace characters and EOF.
          Does not take into consideration hypentation, words are composed
          numbers and letters, punctuation also included in words.

Precondition : none
=======================================================================*/
static int CountLineWordChar(CountsRecord Data, String File)
{
	
	int CurrentChar;         // current character in stream
	boolean InWord = false; 		 // Tracking words
   
	try (BufferedReader FileIn = new BufferedReader(new FileReader(File)))
	{
		while ((CurrentChar = FileIn.read()) != -1)
		   {
			   Data.CharCount++;
			   if (CurrentChar == '\n' || CurrentChar == '\r') Data.LineCount++;
			   
			   if (Character.isWhitespace(CurrentChar))
			   {
				   InWord = false;	// Finished a word
			   } else 
			   {
				   if (!InWord) 
				   {
					   Data.WordCount++; //Entered a word
					   InWord = true;
				   }
			   }
			 
		   }
		if (Data.CharCount > 0 && Data.LineCount == 0) 
		{
			Data.LineCount++;	// Count single line input
		}
		return 1;
	}
	catch (IOException e) 
	{
		return 0;
	}
}
/* *********************  ReportResults  ********************************
Action  :  Function will display the number of words, lines or characters
           or all of them depending on what the user entered on the command
           line input.
Parameters :
  User - what to display
  Data - holds the number to display
Returns    : nothing
======================================================================*/
static void ReportResults(CmdLineRecord User, CountsRecord Data, String[] Arg) 
{
	if (User.SyntaxError > 0)
	{
		System.out.println("ERROR DETECTED");
		switch (User.SyntaxError)
		{
		case 1: 
			System.out.println("Missing filename on command line.");
			break;
		case 2:
			System.out.println("Too many arguments provided on command line.");
			break;
		case 3:
			System.out.println("Second argument must start with a '/' character.");
			break;
		case 4:
			System.out.println("Invalid option character in second argument. Use only c, w, or l.");
			break;
		default:
			System.out.println("Unknown syntax error.");
		}
		return;
	}
	
	if (User.WantsCharCount)
		System.out.println("characters = " + Data.CharCount);
	if (User.WantsWordCount)
		System.out.println("words = " + Data.WordCount);
	if (User.WantsLineCount)
		System.out.println("lines = " + Data.LineCount);

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