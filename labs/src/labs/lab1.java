package labs;

/**
 * Name: Vincent Goldberg
 * Date: 2025-09-09
 * Lab #: 1
 * Source File: lab1.java
 * 
 * Action: Prompts user to enter three integers, then calculates and displays the average.
 * 
 */
import java.util.Scanner;

public class lab1 {

	public static void main(String[] args) {
		Scanner Input = new Scanner(System.in);
		// Program variables 
		int First, Second, Third;
		float Average;
		
		System.out.print("Enter first number   -> ");
		First = Input.nextInt();
		
		System.out.print("Enter second number  -> ");
		Second = Input.nextInt();
		
		System.out.print("Enter third number   -> ");
		Third = Input.nextInt();
		
		Average = ComputeAverage(First, Second, Third);
		System.out.println("\nThe average is " + Average);
		}
	
	/**
	 * Action: Computes and returns average of three integers.
	 * 
	 * Parameters: FirstNum - first integer, SecondNum - second integer, ThirdNum - third integer
	 * Returns: float - average of the three integers
	 * Precondition: Assumes valid integer inputs
	 */
	public static float ComputeAverage(int FirstNum, int SecondNum, int ThirdNum) {
		return (FirstNum + SecondNum + ThirdNum) / 3.0f;
	}
}


/* ********************************* OUTPUT *********************************
 
First
---------------------------
Enter first number   -> 3
Enter second number  -> 7
Enter third number   -> 9

The average is 6.3333335


Second
--------------------------- 
buntu-1@dev-device:~/Documents/IU/CSCI-255/labs/bin$ java labs.lab1 
Enter first number   -> 3
Enter second number  -> 7
Enter third number   -> 9

The average is 6.3333335

buntu-1@dev-device:~/Documents/IU/CSCI-255/labs/bin$ 


Third
---------------------------
buntu-1@dev-device:~/Documents/IU/CSCI-255/labs/bin$ java labs.lab1 < Lab1Data.txt 
Enter first number   -> Enter second number  -> Enter third number   -> 
The average is 45.333332

buntu-1@dev-device:~/Documents/IU/CSCI-255/labs/bin$  
 */ 
