package homework;

/* ******************************************************************
Author  :  Vincent Goldberg
Date    :  11 Nov 2025
Homework:  7
Compiler:  JavaSE-21
Source  :  PriceGun.java

CLASS DESCRIPTION:
    - This class models a PriceGun that tracks shopping purchases.
    - It maintains a budget limit, total spent, and last item scanned.
    - Provides functionality to scan items, delete last item, reset totals,
      change the budget, and merge shopping totals from two PriceGuns.
    - Used in conjunction with TestPriceGun.java to simulate shopping behavior

----------------------------------------------------------------------------*/
import java.util.Scanner;

public class PriceGun {
	
	// --------------------------- Data Fields ---------------------------
	private double MaxPrice;
	private double CurrPrice;
	private double LastPrice;
	private static final double DEFAULT_MAX = 250;
	private static final Scanner Input = new Scanner(System.in);
		
	// ------------------------- Constructors -------------------------
	/*
	 * Action: Default constructor
	 * Parameters: None
	 * Returns: N/A
	 * Precondition: None 
	 */
	public PriceGun()
	{
		MaxPrice = DEFAULT_MAX;
		CurrPrice = 0;
		LastPrice = 0;
	}
	
	/*
	 * Action: Normal constructor
	 * Parameters: Max - max budget; Current - current total; Last - last item
	 * Returns: N/A
	 * Precondition: Parameters must be valid amounts (Non-negative) 
	 */
	public PriceGun(double Max, double Current, double Last)
	{
		MaxPrice = Max;
		CurrPrice = Current;
		LastPrice = Last;
	}
	
	/*
	 * Action: Copy constructor
	 * Parameters: Other - another PriceGunObject
	 * Returns: N/A
	 * Precondition: Other is not null 
	 */
	public PriceGun(PriceGun Other)
	{
		MaxPrice = Other.MaxPrice;
		CurrPrice = Other.CurrPrice;
		LastPrice = Other.LastPrice;
	}
	
	// ----------------------- Member Methods -----------------------
	
	/*
	 * Action: Resets values to default: Max = 250, Curr/Last = 0
	 * Parameters: None 
	 * Returns: void
	 * Precondition: None 
	 */
	public void ClearPrice() 
	{
		MaxPrice = DEFAULT_MAX;
		CurrPrice = 0;
		LastPrice = 0;
	}
	
	/*
	 * Action: Sets MaxPrice to a new value
	 * Parameters: NewMax - new budget amount
	 * Returns: void
	 * Precondition: NewMax >= 0 
	 */
	public void SetMaxPrice(double NewMax)
	{
		MaxPrice = NewMax;
	}
	
	/*
	 * Action: Returns current total spent
	 * Parameters: None
	 * Returns: CurrPrice
	 * Precondition: None
	 */
	public double CurrentPrice() 
	{
		return CurrPrice;
	}
	
	/*
	 * Action: Returns remaining amount before exceeding budget
	 * Parameters: None
	 * Returns: amount left (Max - Current)
	 * Precondition: None
	 */
	public double AmountLeft()
	{
		return (MaxPrice - CurrPrice);
	}
	
	/*
	 * Action: Returns budget limit
	 * Parameters: None
	 * Returns: MaxPrice
	 * Precondition: None
	 */
	public double Budget()
	{
		return MaxPrice;	}
	
	/*
	 * Action: Prompts user to enter a price and adds to CurrPrice
	 * Parameters: None
	 * Returns: void
	 * Precondition: price entered must be a valid double 
	 */
	public void Scan()
	{
		System.out.print("Enter item price ---> $");
		LastPrice = Input.nextDouble();
		CurrPrice += LastPrice;
	}
	
	/*
	 * Action: Checks if spending is under or at budget
	 * Parameters: None
	 * Returns: true if CurrPrice <= MaxPrice, false otherwise
	 * Precondition: None
	 */
	public boolean UnderBudget()
	{
		return (CurrPrice <= MaxPrice);
	}
	
	/*
	 * Action: Deletes last scanned item from CurrPrice
	 * Parameters: None
	 * Returns: void
	 * Precondition: LastPrice must be valid (was set by Scan) 
	 */
	public void DeleteItem()
	{
		CurrPrice -= LastPrice;
		LastPrice = 0;
	}
	
	/*
	 * Action: Compares current total to another PriceGun's total
	 * Parameters: Other - another PriceGun object
	 * Returns: true if this CurrPrice is greater
	 * Precondition: Other is not null
	 */
	public boolean isGreater(PriceGun Other)
	{
		return (CurrPrice > Other.CurrPrice);
	}
	
	/*
	 * Action: Merges this object with another PriceGun object
	 * Parameters: Other - another PriceGun object
	 * Returns: new PriceGun with combined MaxPrice and CurrPrice
	 * Precondition: Other is not null
	 */
	public PriceGun Merge(PriceGun Other)
	{
		double MergedMax = MaxPrice + Other.MaxPrice;
		double MergedCurr = CurrPrice + Other.CurrPrice;
		
		return (new PriceGun(MergedMax, MergedCurr, 0));
	}
	
	/*
	 * Action: Displays budget, spent, and amount left
	 * Parameters: None
	 * Returns: void
	 * Precondition: None 
	 */
	public void DisplayAll()
	{
		System.out.printf("Amount Spent: $%.2f\n", CurrPrice);
		System.out.printf("Budget Limit: $%.2f\n", MaxPrice);
		System.out.printf("Amount Left: $%.2f\n", AmountLeft());
	}	
}

/* ************************ PROGRAM OUTPUT ************************

========= Jim's Shopping ================

Let's go shopping!
Jim will go first, with a budget of $250.0
Jim is a party animal so he is buying supplies for a
party this coming weekend, and cannot go over budget.


What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 1

Start shopping!!. Enter item price ---> $25.50
Item added to list
Current Price is $25.5
Continue shopping, Y or N 
Y
Enter item price ---> $90.75
Item added to list
Current Price is $116.25
Continue shopping, Y or N Y
Enter item price ---> $150.00
Item added to list
Current Price is $266.25
Continue shopping, Y or N y

-------- OVER BUDGET -------------
Delete last item?  Enter D to delete D

Current Price is now $116.25
Continue shopping, Y or N y
Enter item price ---> $100
Item added to list
Current Price is $216.25
Continue shopping, Y or N n

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 2

How much do you want to ask for, be reasonable? 
500
Sorry request is denied

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> Q

Good Bye Jim

This is what you have Jim.
Amount Spent: $216.25
Budget Limit: $250.00
Amount Left: $33.75

------------------------------------------------------------

========= Sue's Shopping ================

Now it is little Sue's turn to shop!
She comes from a wealthy family and has a higher 
budget of $500.0, and she loves to 
buy clothes, but still has to stay within budget.

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 1

Start shopping!!. Enter item price ---> $200
Item added to list
Current Price is $200.0
Continue shopping, Y or N y
Enter item price ---> $350.00
Item added to list
Current Price is $550.0
Continue shopping, Y or N y

-------- OVER BUDGET -------------
Delete last item?  Enter D to delete D

Current Price is now $200.0
Continue shopping, Y or N n

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 3


What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 1

Start shopping!!. Enter item price ---> $250
Item added to list
Current Price is $250.0
Continue shopping, Y or N n

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> q

Good Bye Sue

This is what you have Sue
Amount Spent: $250.00
Budget Limit: $250.00
Amount Left: $0.00

Let's see who spent the most!
Sue spent more by $33.75

------------------------------------------------------------

========= Merge Output ================

Jim and Sue are getting married!! About time!
They will now be able to merge their price guns.

This is what they now have together
Amount Spent: $466.25
Budget Limit: $500.00
Amount Left: $33.75


 */ 
