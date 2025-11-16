/**
 * Name: Vincent Goldberg
 * Date: 17 Nov 2025
 * Lab #: 8
 * Source File: labs/Lab8.java
 * 
 * Action: This program recursively sums the elements
 * 		   in an integer array.
 */

package labs;

public class Lab8 {

	public static void main(String[] args) 
	{
		
		// Test 1
		int[] Numbers = {2, 4, 6, 8, 0};
		int Total = SumArray(Numbers, 0);
		System.out.printf("Sum of array elements %s is %d\n\n", 
							java.util.Arrays.toString(Numbers), Total);
		
		// Test 2
		Numbers = new int[]{2, 4, 6, 8, 7};
		Total = SumArray(Numbers, 0);
		System.out.printf("Sum of array elements %s is %d\n", 
							java.util.Arrays.toString(Numbers), Total);
	}
	
	// Recursively sums elements on integer array
	public static int SumArray(int[] ArrInput, int Index)
	{
		// Base case
		if (ArrInput.length == Index)
			return 0; 
		// Recursive case
		return ArrInput[Index] + SumArray(ArrInput, Index + 1);
	}
}
/* ********************************* OUTPUT *********************************

Sum of array elements [2, 4, 6, 8, 0] is 20

Sum of array elements [2, 4, 6, 8, 7] is 27


 */ 
