package labs;
/**
 * Name: Vincent Goldberg
 * Date: 16 Sep. 2025
 * Lab #: 2
 * Source File: Lab2.java
 * 
 * Action: Demonstrates array division using try-catch with exception handling. 
 * 
 */

public class Lab2 {

	public static void main(String[] args) {
		// ===== NORMAL RUN =====
		int [] Numer = {4, 8, 16, 32, 64, 128};
		int [] Denom = {2, 2, 4, 4, 64, 8};
		DivideAndPrint(Numer, Denom, "Normal Run") ;
		
		// ===== RUN WITH EXCEPTIONS =====
		 int [] Numer = {4, 8, 16, 32, 64, 128, 256, 512};
		 int [] Denom = {2, 0, 4, 4, 0, 8};
		 DivideAndPrint(Numer, Denom, "Run with Exceptions") ;
		
	}
	
	/**
	 * Actions: Divides and prints two array by corresponding elements. 
	 */
	public static void DivideAndPrint(int[] Numerator, int[] Denominator, String label) {
		System.out.println(label + ":");
		for (int i = 0; i < Numerator.length; i++) {
			try {
				System.out.println(Numerator[i] + " / " + Denominator [i] + " = " + (Numerator[i] / Denominator[i]));
			} catch (ArithmeticException e) {
				System.out.println("Can't divide by zero.");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("No matching element found, out of bounds.");
			}
		}
	}	
}

/* ********************************* OUTPUT *********************************

===== NORMAL RUN =====

Normal Run:
4 / 2 = 2
8 / 2 = 4
16 / 4 = 4
32 / 4 = 8
64 / 64 = 1
128 / 8 = 16


===== RUN WITH EXCEPTIONS =====

Run with Exceptions:
4 / 2 = 2
Can't divide by zero.
16 / 4 = 4
32 / 4 = 8
Can't divide by zero.
128 / 8 = 16
No matching element found, out of bounds.
No matching element found, out of bounds.

 */ 