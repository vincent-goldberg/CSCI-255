/* ******************************************************************
Author  :  Vincent Goldberg  and Matt Holloway
Date    :  13 Oct 2025
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
import java.util.*;
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
 //     FileOpened = CountLineWordChar(Data, args[0]);

   if (FileOpened == 1)
      ReportResults(User, Data);
  // else
  //    System.err.print("Cannot open file " + args[0] + "\n");    
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
  
  if (Arg.length == 1)
  {
	  
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
   int ch;                  // current character in stream
   int NextCh;              // Next character in stream
   BufferedReader FileIn;    // declare FileIn to be input file   
         
      Data.CharCount = 0;
      Data.WordCount = 0;
      Data.LineCount = 0;
            
      return 1;   
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
static void ReportResults(CmdLineRecord User, CountsRecord Data)
{
   System.out.println("Not Yet Written");
    
 }
}