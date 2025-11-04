package labs;

/**
 * Name: Vincent Goldberg
 * Date: 3 Nov 2025
 * Lab #: 7
 * Source File: labs/Lab7.java
 * 
 * Action: Reads student names and test scores from a file,
 * 		   calculates the average score for each student,
 * 		   and prints their name with the computed average.
 */

import java.util.Scanner;

/*
 * Temperature class holds and manipulates temperature value
 * in either Celsius or Fahrenheit
 */
class Temperature 
{
	private float Temp;
	private char Scale;
	
	// Default constructor
	Temperature()
	{
		Temp = 0.0f;
		Scale = 'F';
	}
	
	// Parameterized constructor
	Temperature(float T, char S)
	{
		Temp = T;
		Scale = Character.toUpperCase(S);
		if (Scale != 'C' && Scale != 'F')
			Scale = 'F'; // default fallback
	}
	
	// Copy constructor
	Temperature(Temperature Other)
	{
		Temp = Other.Temp;
		Scale = Other.Scale;
	}
	
	// Display output
	void ShowTemp()
	{
		System.out.printf("%.1f degrees %c%n", Temp, Scale);
	}
	
	// Convert scale
	float Convert()
	{
		if (Scale == 'F')
			return (Temp - 32) * 5 / 9;
		else 
			return (Temp * 9 / 5) + 32;
	}
	
	// Set temperature
	void SetTemp(float T, char S)
	{
		Temp = T;
		Scale = Character.toUpperCase(S);
		if (Scale != 'C' && Scale != 'F')
			Scale = 'F'; // default fallback
	}
}

public class Lab7 {

	public static void main(String[] args) {
		Scanner Input = new Scanner(System.in);
		
		// Default constructor
		Temperature T1 = new Temperature();
		// Parameterized constructor
		Temperature T2 = new Temperature(100.0f, 'C');
		// Copy constructor
		Temperature T3 = new Temperature(T2);
		
		// Display T1
		System.out.print("Temperature for T1 is ");
		T1.ShowTemp();
		// Display T2
		System.out.print("Temperature for T2 is ");
		T2.ShowTemp();
		// Display T3
		System.out.print("Temperature for T3 is ");
		T3.ShowTemp();
		
		// Set new value for T1 and show conversion
		T1.SetTemp(212.0f, 'F');
		System.out.printf("%n%.1f degrees %c Converted to other Scale is %.5f%n",
							212.0f, 'F', T1.Convert());
		
		// Convert T2 to Fahrenheit
		System.out.printf("%.1f degrees %c Converted to other Scale is %.1f%n",
							100.0f, 'C', T2.Convert());
		
		// Manual collection
		System.out.print("\nEnter temperature scale: ");
		char Scale = Character.toUpperCase(Input.next().charAt(0));
		System.out.print("Enter temperature: ");
		float Temp = Input.nextFloat();
		
		// Display output
		T3.SetTemp(Temp, Scale);
		System.out.print("\nInputted temperature is ");
		T3.ShowTemp();
		
		Input.close();
	}
}		

/* ********************************* OUTPUT *********************************


Temperature for T1 is 0.0 degrees F
Temperature for T2 is 100.0 degrees C
Temperature for T3 is 100.0 degrees C

212.0 degrees F Converted to other Scale is 100.00000
100.0 degrees C Converted to other Scale is 212.0

Enter temperature scale: c
Enter temperature: 34

Inputted temperature is 34.0 degrees C

 */ 

