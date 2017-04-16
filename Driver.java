package movieTheaterPackage;

import java.io.*;

public class Driver {
	
	private static BufferedReader stdin = 
			new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String [] args) throws IOException
	{
		// Fields for Ticket Lines
		int numLine1;		// number of customers in reg1
		int numLine2;		// number of customers in reg2
		int numLineExpress;	// number of customers in express
		QueueList<Group> Reg1 = new QueueList();
		QueueList<Group> Reg2 = new QueueList();
		QueueList<Group> Express = new QueueList();
		// Fields for Sales
		int numTicketsSold = 0;	// total number of tickets sold
		int totalEarnings = 0;	// total income from tickets sales
		
		// - - - - - - - - Retrieve General Information - - - - - - - - //
		
		int numRows = 0;	// number of Rows per Theater
		int numSeats = 0;	// number of Seats per Row
		
		boolean gettingRows = true;
		while (gettingRows)
		{
			System.out.println("How many Rows will be in each Theater?");
			int num1 = Integer.parseInt(stdin.readLine().trim());
			if (num1 <= 0) // invalid input
			{
				System.out.println("Invalid Number of Rows!\n"
						+ "Must be Greater Than Zero.");
			}
			else // valid input
			{
				numRows = num1;
				gettingRows = false;
			}
		}
		
		boolean gettingSeats = true;
		while (gettingSeats)
		{
			System.out.println("How many Seats will be in each Row?");
			int num2 = Integer.parseInt(stdin.readLine().trim());
			if (num2 <= 0) // invalid input
			{
				System.out.println("Invalid Number of Seats!\n"
						+ "Must be Greater Than Zero.");
			}
			else // valid input
			{
				numSeats = num2;
				gettingSeats = false;
			}
		}
		
		// Create two theaters
		Theater lifeTheater = new Theater("Life", numRows, numSeats);
		Theater loganTheater = new Theater("Logan", numRows, numSeats);
		System.out.println(); // space for visual appeal
		System.out.println("Theaters Successfully Created.");
		System.out.println("Rows per Theater: " + numRows);
		System.out.println("Seats per Row: " + numSeats);
		System.out.println(); // space for visual appeal
		
		// - - - - - - - -  - - Run Main Program - - - - - - - - - - //
		
		boolean reading = true;
		while (reading)
		{
			// Display Options
			System.out.println("Choose From The Following:");
			System.out.println("1.) Customer(s) enter(s) Movie Theater.");
			System.out.println("2.) Customer buys tiket(s).");
			System.out.println("3.) Customer(s) leave(s) the theater.");
			System.out.println("4.) Display info about "
					+ "customers waiting for tickets.");
			System.out.println("5.) Display seating chart for "
					+ "Life Movie Theater.");
			System.out.println("6.) Display seating chart for "
					+ "Logan Movie Theater.");
			System.out.println("7.) Display number of tickets sold "
					+ "and total earnings.");
			System.out.println("8.) End the program.");
			System.out.print("Make your selection now: ");
			// Process Input
			String input = stdin.readLine();
			input.trim();
			System.out.println(input);
			
			switch(input)
			{
				case "1":	// Customer(s) Enter(s) Theater
					System.out.println("| - Customer(s) Enter(s) Theater - |");
					/*
					 * 1.) Ask for name of group
					 * 		- Check if name is already in use in either theater
					 * 2.) Ask for number of customers in group
					 * 3.) Ask if any of the customers are <=11 in group Y/N
					 * 4.) Place in proper ticket line
					 */
					break;
				case "2":	// Customer Buys Ticket(s)
					System.out.println("| - Customer Buys Ticket(s) - |");
					break;
				case "3":	// Customer(s) Leave(s) Theater
					System.out.println("| - Customer(s) Leave(s) Theater - |");
					/*
					 * Ask which customer group is leaving
					 * 		- Check both theaters for customers
					 * Remove these customers from the theater they are in
					 */
					break;
				case "4":	// Display Info About Waiting Customers
					System.out.println("| - Display Info About Waiting Customers - |");
					System.out.println("Regular Ticket Line 1:");
					// display
					System.out.println(); // leave space
					System.out.println("Regular Ticket Line 2:");
					// display
					System.out.println(); // leave space
					System.out.println("Express Ticket Line:");
					// display
					System.out.println(); // leave space
					break;
				case "5":	// Display Seating Chart for Life
					System.out.println("| - Display Seating Chart for \"Life\" - |");
					break;
				case "6":	// Display Seating Chart for Logan
					System.out.println("| - Display Seating Chart for \"Logan\" - |");
					break;
				case "7":	// Display Tickets Sold and Earnings
					System.out.println("| - Display Tickets Sold and Total Earnings - |");
					break;
				case "8":	// End Program
					// Clear Both Theaters
					lifeTheater.clearTheater();
					loganTheater.clearTheater();
					reading = false;
					System.out.println("| - - - Program Ended - - - |");
					break;
				default:	// Invalid Input
					System.out.println("Invalid Input!");
					break;
			}
		}
	}
}
