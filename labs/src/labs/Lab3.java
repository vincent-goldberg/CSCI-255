package labs;

import java.util.Scanner;

/**
 * Name: Vincent Goldberg
 * Date: 22 Sep. 2025
 * Lab #: 3
 * Source File: Lab3.java
 * 
 * Action: This program creates two integer arrays of user-defined size,
 * 		   fills them with random values from 1 to 10, then combines and
 * 		   displays all arrays.
 * 
 */

import java.util.Random;

public class Lab3 {

	public static void main(String[] args) {
		Scanner Input = new Scanner(System.in);
		
		int Size1, Size2;
		
		// Collect user input 
		System.out.print("Enter size of first array -> ");
		Size1 = Input.nextInt();
		
		System.out.print("Enter size of second array -> ");
		Size2 = Input.nextInt();
		
		// Generate array 
		int[] FirstArray = new int[Size1];
		int[] SecondArray = new int[Size2];
		int[] CombinedArray = new int[Size1 + Size2];
		// Generate values
		FillArray(FirstArray);
		FillArray(SecondArray);
		// Copy values into third array
		for (int i = 0; i < Size1; i++) {
			CombinedArray[i] = FirstArray[i];
		}
		for (int i = 0; i < Size2; i++) {
			CombinedArray[Size1 + i] = SecondArray[i];
		}
		
		// Display output
		System.out.println("\nFirst Array");
		DisplayArray(FirstArray);
		System.out.println("\nSecond Array");
		DisplayArray(SecondArray);
		System.out.println("\nCombined Array");
		DisplayArray(CombinedArray);
		
	}
	
	/**
	 * Action: Fills integer array with random values 1-10.
	 */
	static void FillArray(int[] Array) {
		Random RandGen = new Random();
		
		for (int i = 0; i < Array.length; i++) {
			Array[i] = RandGen.nextInt(10) + 1;
		}
	}
	
	/**
	 * Action: Display integer array with left-justified width of 4
	 */
	static void DisplayArray(int[] Array) {
		for (int value : Array) {
			System.out.printf("%-4d", value);
		}
		System.out.println();
	}
}

/* ********************************* OUTPUT *********************************

Enter size of first array -> 3
Enter size of second array -> 5

First Array
4   6   4   

Second Array
8   10  8   3   1   

Combined Array
4   6   4   8   10  8   3   1   

 */ 