/* *************************************************************************
AUTHOR  : Vincent Goldberg and  Matt Holloway  (with John Russo )
                                  
SOURCE  : Homework8.java

DATE    :

COMPILER: NetBeans

ACTION  : The program tests routines designed to perform various
          operations on singly linked lists. The lists have a
          dummy head node that holds the "Happy Face" character. 
          The tail of the lists points to NULL.
------------------------------------------------------------------------------*/ 
package homework;
import java.util.Scanner;

class Node
{
final int DUMMY_VALUE = 1;  //  Value () stored in dummy head node.
char  Ch;                   //  Holds the char data.
Node Link;                  //  Points to another object of type Node.
}

public class Homework8
{
static Scanner Input = new Scanner(System.in);  

public static void main(String[] args)
{      
   Node List = null;
   char MenuChoice;
   
   System.out.println("This program allows you to test the routines needed \n"
        + "for homework 8.");
   
   // Start list with Dummy Head Node
   
   List = new Node();
   List.Ch = (char)List.DUMMY_VALUE;
   List.Link = null;     
   
   do
   {
      MenuChoice = DisplayMenuAndGetMenuChoice();
      
      switch( MenuChoice )
      {
         case 'Q': break;                // Exit program
         case 'B': TestBuildList(List);
                   break;
         case 'A': TestAddNode(List);
                   break;
         case 'D': TestDeleteNode(List);
                   break;
         case 'Z': TestZapList(List);
                   break;
         default : System.out.println("\nError: '" + MenuChoice
                   + "' is not an option\n");
      }         
   }while ( MenuChoice != 'Q' );
}

/* ********************   DisplayMenuAndGetMenuChoice *********************
Displays a menu of options and returns the user's choice.    
The Choice is returned as uppercase.
---------------------------------------------------------------------------*/
public static char DisplayMenuAndGetMenuChoice ()
{
char Choice;
String [] Option = {"B(uildList", "A(ddNode", "D(eleteNode",
                    "Z(apList", "Q(uit", "" };

int K = 0;

System.out.println("----------------------------------------------------");

while ( Option[K] != "" )    // while we haven't gotten to ""
{
  System.out.print(Option[K]);      // Display menu option
  System.out.print("  ");          // Add some white space.
  ++K;
}

System.out.print("=> ");
Choice = Character.toUpperCase(Input.next().charAt(0));

System.out.println("----------------------------------------------------");
Input.nextLine();
return Choice;
}

/* ************************    TestBuildList     **************************
Facilitates the testing of the function BuildList, which is supposed
to build an ordered linked list of characters.
--------------------------------------------------------------------------*/
public static void TestBuildList (Node List)
{
System.out.println("\n================  Testing BuildList  ================");
System.out.print("\nType the characters for the list -  " +
                    "when finished, press enter key\n\n -> ");

BuildList(List);
System.out.print("\nAfter BuildList, List = ");
ShowList(List);
}

/* ***********************    TestAddNode  ********************************
Facilitates the testing of the function AddNode, a function which
adds a node to the tail end of a linked list. 
-------------------------------------------------------------------------*/
public static void TestAddNode (Node List)
{
char NewChar; 

System.out.println("\n================  Testing AddNode  =================\n");

System.out.print("Character to be added? ");
NewChar = Input.next().charAt(0);

System.out.println(" --  Adding \'" + NewChar  + "\'");

AddNode (NewChar, List);

System.out.print("\nThe new list: ");
ShowList(List);
}

/* **********************    TestDeleteNode   *****************************
Facilitates the testing of DeleteNode, a function which is supposed
to delete characters from a linked list.
----------------------------------------------------------------------------*/
public static void TestDeleteNode (Node List)
{
boolean CharThere;
char CharToBeDeleted;

System.out.println("\n===============  Testing DeleteNode  ================\n");

System.out.print("Character to be deleted? ");

CharToBeDeleted = Input.next().charAt(0);

CharThere = DeleteNode(CharToBeDeleted, List);

if ( CharThere )
   System.out.println("\n'" + CharToBeDeleted + "' has been deleted,");
else
   System.out.println("\n'" + CharToBeDeleted + "' was not in the list,");

System.out.print("\nList = ");
ShowList(List);
}

/* **********************    TestZapList  *********************************
Facilitates the testing of ZapList, a function that is supposed to
return all storage allocated for a linked list to the heap (except the
storage occupied by the dummy head node).
----------------------------------------------------------------------------*/
public static void TestZapList (Node List)
{
System.out.println("\n===============  Calling ZapList  ====================\n");

ZapList(List);

System.out.print("\nList = ");

ShowList(List);
}
/* ***************************   BuildList    *****************************
DESCRIPTION  Builds a singly linked list with a dummy head node. The
             characters in the list are in the same order in which the
             user enters them, i.e. new characters are added to the tail
             end of the list.  If there was a list to begin with, this routine
             will disregard it, so is lost.

             Input terminates when the enter key is pressed.
PARAMETERS
  IN : L     A reference to a singly linked list with a dummy head node.
             It is imperative that List be initialized before calling
             this routine.
-----------------------------------------------------------------------------*/
public static void BuildList (Node L)
{
	// Step 1: Zap existing list (clears all nodes except dummy)
	ZapList(L);
	
	// Step 2: Read input line
	String Line = Input.nextLine();
	
	// Step 3: Start from dummy node
	Node Tail = L;
	
	// Step 4: For each character, append a new node
	for (int i = 0; i < Line.length(); i++)
	{
		Node NewNode = new Node();
		NewNode.Ch = Line.charAt(i);
		NewNode.Link = null;
		// Add node to tail of list
		Tail.Link = NewNode;
		Tail = NewNode; // Advance tail
	}	
}

/* ***************************   AddNode  *********************************
DESCRIPTION  Adds a node containing NewChar to the end of List.

PARAMETERS
 IN : NewChar The character to be added to the end of the list.

 IN : L    A reference to a singly linked list with a dummy head node.
           The value of List (address of dummy head node) is not
           changed by this routine.
----------------------------------------------------------------------------*/
public static void AddNode (char NewChar, Node L)
{
	// Step 1: Traverse to tail
	while (L.Link != null)
		L = L.Link;
	
	// Step 2: Create new node
	Node NewNode = new Node();
	NewNode.Ch = NewChar;
	NewNode.Link = null;
	
	// Step 3: Attach to tail
	L.Link = NewNode;
}

/* ****************************   DeleteNode   ****************************
DESCRIPTION  Deletes the first node of List that contains the char
            CharToDelete. The storage occupied by the deleted
            node is returned to the heap.

PARAMETERS
 IN : CharToDelete  The character to be deleted.

 IN : L    A reference to a singly linked list with a dummy head node.
           The value of List is not changed by this routine but the
           linked list itself is changed.

 returns, CharFound Set to true if the CharToDelete is found and deleted and
          false otherwise.
--------------------------------------------------------------------------------*/
public static boolean DeleteNode (char CharToDelete, Node L)
{
	Node Prev = L; // Start at dummy node
	Node Curr = L.Link; // First real node
	
	while (Curr != null)
	{
		if (Curr.Ch == CharToDelete)
		{
			Prev.Link = Curr.Link; // Bypass Curr node
			return true;
		}
		Prev = Curr;		// Advance pointer
		Curr = Curr.Link;	// Point to next node
	}
return false; // Not found
}

/* ****************************   ZapList  ********************************
DESCRIPTION  Frees all the storage space currently occupied by the
            linked list pointed to by List. Does NOT delete the delete
            the dummy head node.

PARAMETER
     OUT :  A reference to a singly linked list with a dummy head node.
            After this call, List will contain only the dummy head node.
-----------------------------------------------------------------------------*/
public static void ZapList (Node L)
{
	Node Curr = L.Link;
	Node Temp;
	
	while (Curr != null)
	{
		Temp = Curr.Link;	// Save point to next node
		Curr.Link = null;	// Break link to next node
		Curr = Temp; 		// Move to next node
	}
	
	L.Link = null; 
	
}

/* ************************   ShowList  ***********************************
DESCRIPTION  Displays the character field of all of the nodes in L, a
            singly linked list with a dummy head node. The list is
            enclosed in quotes.  The dummy head node is not displayed.
PARAMETER
  IN : L   A reference to a singly linked list with a dummy head node.

NOTE         To facilitate debugging this routine displays "NULL"
            if called with L == null or L.Link == null
-----------------------------------------------------------------------------*/
public static void ShowList (Node L)
{
if ( L == null || L.Link == null)
{
   System.out.println(" NULL LIST\n");
   return;
}
System.out.print("\"");           // Display quote for ease of testing.

while ( L.Link != null )
{
   L = L.Link;
   System.out.print(L.Ch);      
}
System.out.println("\"\n");       // Display ending quote
}
}

/* ************************ PROGRAM OUTPUT ************************

Test 1: Build List with Characters 'abcde'

B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => B
----------------------------------------------------

================  Testing BuildList  ================

Type the characters for the list -  when finished, press enter key

 -> abcde

After BuildList, List = "abcde"

------------------------------------------------------------------------

Test 2: Add Character 'X' to Tail

B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => A
----------------------------------------------------

================  Testing AddNode  =================

Character to be added? X
 --  Adding 'X'

The new list: "abcdeX"

------------------------------------------------------------------------

Test 3: Delete a Middle Character 'c'

B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => D
----------------------------------------------------

===============  Testing DeleteNode  ================

Character to be deleted? c

'c' has been deleted,

List = "abdeX"

------------------------------------------------------------------------

Test 4: Delete a Nonexistent Character 'Z'

B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => D
----------------------------------------------------

===============  Testing DeleteNode  ================

Character to be deleted? Z

'Z' was not in the list,

List = "abdeX"

------------------------------------------------------------------------

Test 5: Add Multiple Characters 'Y' and 'Z'

B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => A
----------------------------------------------------

================  Testing AddNode  =================

Character to be added? Y
 --  Adding 'Y'

The new list: "abdeXY"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => A
----------------------------------------------------

================  Testing AddNode  =================

Character to be added? Z
 --  Adding 'Z'

The new list: "abdeXYZ"

------------------------------------------------------------------------

Test 6: Zap the List (Deletes All but Dummy)

B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => Z
----------------------------------------------------

===============  Calling ZapList  ====================


List =  NULL LIST

------------------------------------------------------------------------

Test 7: Rebuild New List 'pqrs' After Zap

B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => B
----------------------------------------------------

================  Testing BuildList  ================

Type the characters for the list -  when finished, press enter key

 -> pqrs

After BuildList, List = "pqrs"

 */ 