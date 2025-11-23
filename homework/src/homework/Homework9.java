/* *************************************************************************
   AUTHOR  : Vincent Goldberg and Matt Holloway  (with John Russo )
                                     
   SOURCE  : Homework9.java

   DATE    : 23 November 2025

   COMPILER: Eclipse

   ACTION  : The program tests routines designed to perform various
             operations on singly linked lists. The lists have a
             dummy head node that holds the "Happy Face" character. 
             The tail of the lists points to NULL.

             This is an extended program of homework #8. The menu choices 
             include now reading in a list from an external file and sorting a list.
---------------------------------------------------------------------------------*/ 

package homework;
import java.util.Scanner;
import java.io.*;

class Node
{
    final int DUMMY_VALUE = 1;
    char Ch;
    Node Link;
}

public class Homework9
{
    static Scanner Input = new Scanner(System.in);

    public static void main(String[] args)
    {
        Node List = new Node();
        List.Ch = (char)List.DUMMY_VALUE;
        List.Link = null;
        char MenuChoice;

        System.out.println("This program allows you to test the routines needed \nfor homework 8.");

        do
        {
            MenuChoice = DisplayMenuAndGetMenuChoice();

            switch (MenuChoice)
            {
                case 'Q': break;
                case 'B': TestBuildList(List); break;
                case 'A': TestAddNode(List); break;
                case 'D': TestDeleteNode(List); break;
                case 'Z': TestZapList(List); break;
                case 'R': TestReadList(List); break;
                case 'S': TestSortList(List); break;
                default: System.out.println("\nError: '" + MenuChoice + "' is not an option\n");
            }
        } while (MenuChoice != 'Q');
    }

    public static char DisplayMenuAndGetMenuChoice()
    {
        char Choice;
        String[] Option = {"B(uildList", "A(ddNode", "D(eleteNode", "R(eadList", "S(ortList", "Z(apList", "Q(uit", ""};
        int K = 0;

        System.out.println("----------------------------------------------------");
        while (!Option[K].equals(""))
        {
            System.out.print(Option[K] + "  ");
            ++K;
        }
        System.out.print("=> ");
        Choice = Character.toUpperCase(Input.next().charAt(0));
        System.out.println("----------------------------------------------------");
        Input.nextLine();
        return Choice;
    }

    public static void TestBuildList(Node List)
    {
        System.out.println("\n================  Testing BuildList  ================");
        System.out.print("\nType the characters for the list - when finished, press enter key\n\n -> ");
        BuildList(List);
        System.out.print("\nAfter BuildList, List = ");
        ShowList(List);
    }

    public static void BuildList(Node L)
    {
        ZapList(L);
        String Line = Input.nextLine();
        Node Tail = L;
        for (int i = 0; i < Line.length(); i++)
        {
            Node NewNode = new Node();
            NewNode.Ch = Line.charAt(i);
            NewNode.Link = null;
            Tail.Link = NewNode;
            Tail = NewNode;
        }
    }

    public static void TestAddNode(Node List)
    {
        char NewChar;
        System.out.println("\n================  Testing AddNode  =================\n");
        System.out.print("Character to be added? ");
        NewChar = Input.next().charAt(0);
        System.out.println(" --  Adding '" + NewChar + "'");
        AddNode(NewChar, List);
        System.out.print("\nThe new list: ");
        ShowList(List);
    }

    public static void AddNode(char NewChar, Node L)
    {
        while (L.Link != null)
        {
            L = L.Link;
        }
        Node NewNode = new Node();
        NewNode.Ch = NewChar;
        NewNode.Link = null;
        L.Link = NewNode;
    }

    public static void TestDeleteNode(Node List)
    {
        boolean CharThere;
        char CharToBeDeleted;
        System.out.println("\n===============  Testing DeleteNode  ================\n");
        System.out.print("Character to be deleted? ");
        CharToBeDeleted = Input.next().charAt(0);
        CharThere = DeleteNode(CharToBeDeleted, List);
        if (CharThere)
        {
            System.out.println("\n'" + CharToBeDeleted + "' has been deleted,");
        }
        else
        {
            System.out.println("\n'" + CharToBeDeleted + "' was not in the list,");
        }
        System.out.print("\nList = ");
        ShowList(List);
    }

    public static boolean DeleteNode(char CharToDelete, Node L)
    {
        Node Prev = L;
        Node Curr = L.Link;
        while (Curr != null)
        {
            if (Curr.Ch == CharToDelete)
            {
                Prev.Link = Curr.Link;
                return true;
            }
            Prev = Curr;
            Curr = Curr.Link;
        }
        return false;
    }

    public static void TestZapList(Node List)
    {
        System.out.println("\n===============  Calling ZapList  ====================\n");
        ZapList(List);
        System.out.print("\nList = ");
        ShowList(List);
    }

    public static void ZapList(Node L)
    {
        Node Curr = L.Link, Temp;
        while (Curr != null)
        {
            Temp = Curr.Link;
            Curr.Link = null;
            Curr = Temp;
        }
        L.Link = null;
    }

    public static void ShowList(Node L)
    {
        if (L == null || L.Link == null)
        {
            System.out.println(" NULL LIST\n");
            return;
        }
        System.out.print("\"");
        while (L.Link != null)
        {
            L = L.Link;
            System.out.print(L.Ch);
        }
        System.out.println("\"\n");
    }

    public static void TestReadList(Node List)
    {
        String FileName;
        System.out.println("\n================  Testing ReadList ==================\n");
        System.out.print("Please enter the file to read from? ");
        FileName = Input.nextLine();
        if (!ReadList(List, FileName))
        {
            System.err.println("\nError in opening the file " + FileName);
            System.err.print("Press ENTER KEY");
            Input.nextLine();
            return;
        }
        System.out.println("\nThe list created from the file -- " + FileName + " --\n");
        ShowList(List);
    }

    /* ***************************   ReadList    *****************************
    DESCRIPTION   Builds a singly linked list with a dummy head node. The
                  characters in the list are read in from an external file
                  in the same order in which they are found in file.

                  Input to list terminates when the End of File is encountered             
    PARAMETERS
      IN : L     A reference to a singly linked list with a dummy head node.
                 It is imperative that List be initialized before calling
                 this routine.
     IN : FileName  A string that has the name of the file to open, if error 
                    in opening then return a false.
    RETURNS:     true if file opened successfully, else false

    NOTE        Before building the new list, ZapList is called.            
    ----------------------------------------------------------------------------*/
    public static boolean ReadList(Node L, String FileName)
    {
    	try (BufferedReader FileIn = new BufferedReader(new FileReader(FileName)))
    	{
    		ZapList(L);
    		Node Tail = L;
    		int Ch;
    		// Loop until End of File (-1)
    		while ((Ch = FileIn.read()) != -1)
    		{
    			Node NewNode = new Node();	// Create new node
    			NewNode.Ch = (char) Ch;		// Assign char
    			NewNode.Link = null;		// Link to null
    			Tail.Link = NewNode;		// Add to list
    			Tail = NewNode;				// Advance list
    			
    		}
    		return true;
    	} 
    	catch (IOException e)
    	{
    		return false;
    	}	
    }

    public static void TestSortList(Node List)
    {
        System.out.println("\n================   Calling SortList  =================\n");
        SortList(List);
        System.out.println("\n\nList = ");
        ShowList(List);
    }

    /* *************************  SortList ************************************
    Description  Arranges the singly linked list pointed to by L in
                 natural order.  It is assumed that the list has a dummy head node.

                 The algorithm used is a linked variation of the selection
                 sort and works like this:

                   Start with EndSorted aimed at first node of list
                   repeat
                        Find smallest char between EndSorted and end of list
                        Swap smallest element with char in EndSorted
                        Change EndSorted to next node
                   until we get to end of list

                 None of the references in linked list are changed

    Parameters
      IN :  L    A reference to a singly linked list with a dummy head node
    ---------------------------------------------------------------------------*/
    public static void SortList(Node L)
    {
        Node EndSorted = L.Link;	// Skip dummy head
        while (EndSorted != null)
        {
            Node SmallNode = EndSorted;
            Node SearchNode = EndSorted.Link;
            // Search for smallest node
            while (SearchNode != null)
            {
                if (SearchNode.Ch < SmallNode.Ch)
                {
                    SmallNode = SearchNode;
                }
                SearchNode = SearchNode.Link;
            }
            // Swap characters
            char Temp = EndSorted.Ch;
            EndSorted.Ch = SmallNode.Ch;
            SmallNode.Ch = Temp;
            // Advance search
            EndSorted = EndSorted.Link;
        }
    }
}
