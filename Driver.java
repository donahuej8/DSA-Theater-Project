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
		QueueList<Group> reg1 = new QueueList();
		QueueList<Group> reg2 = new QueueList();
		QueueList<Group> express = new QueueList();
		
		// Fields for Sales
		int numTicketsSold = 0;	// total number of tickets sold
		int totalEarnings = 0;	// total income from tickets sales
		
		// Fields for Theaters
		int numRows = 0;	// number of Rows per Theater
		int numSeats = 0;	// number of Seats per Row
		
		//////////////////////////////////////////////////////////////////
		// - - - - - - - - Retrieve General Information - - - - - - - - //
		//////////////////////////////////////////////////////////////////
		
		// - - - - - - - - Number of Rows - - - - - - - - //
		boolean gettingRows = true;
		while (gettingRows)
		{
			System.out.print("How many rows will be in each theater? ");
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
		
		// - - - - - - - - Number of Seats per Row - - - - - - - - //		
		boolean gettingSeats = true;
		while (gettingSeats)
		{
			System.out.print("How many seats will be in each row? ");
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
		
		// Create Two Theater Objects (Logan and Life)
		Theater lifeTheater = new Theater(numRows, numSeats, "Life");
		Theater loganTheater = new Theater(numRows, numSeats, "Logan");
		// Notify User of Successful Theater Creation
		System.out.println(); // space for visual appeal
		System.out.println("Theaters Successfully Created.");
		System.out.println("Rows per Theater: " + numRows);
		System.out.println("Seats per Row: " + numSeats);
		System.out.println(); // space for visual appeal
		
		////////////////////////////////////////////////////////////////
		// - - - - - - - - - - Run Main Program - - - - - - - - - - - //
		////////////////////////////////////////////////////////////////
		
		boolean reading = true;
		boolean firstLoop = true;
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
					
					//1.) Ask for name of group
					System.out.print("Enter customer name: ");
					String groupName = stdin.readLine().trim();
					  		//Check if name is already in use in either theater
							//Re-ask if it is already in use
					//2.) Ask for number of customers in group
					System.out.print("Enter party size: ");
					int partySize = Integer.parseInt(stdin.readLine().trim());
					//2.5) Ask for movie this customer wants to see
					System.out.print("Enter movie name: ");
					String movieName = stdin.readLine().trim();
					//3.) Ask if any of the customers are <=11 in group Y/N
					System.out.print("Is a child 11 or younger in this party(Y/N)? ");
					String youngChildAnswer = stdin.readLine().trim();
					boolean hasYoungChild = false;
					if (youngChildAnswer.equalsIgnoreCase("Y")) {
						hasYoungChild = true;
					} //else default to No
					//4.) Place in proper ticket line
					//TODO: Figure out ordering of lines
					//something like lineName.enqueue(new Group(groupName, partySize, movieToSee);
					  		//add to number of customers in given ticket line
					 
					break;
				case "2":	// Customer Buys Ticket(s)
					System.out.println("| - Customer Buys Ticket(s) - |");
					
					//If it's the first time using this function,
					if (firstLoop) {
						//- ask what line goes first
					}	
					//For each line
					 		//- ask what movie the first group wants to see
					 		//- check for room in that Theater
					 		//- if not enough room, give alternatives
					 		//- else, seat the group in the theater
					 		//- after actions are carried out, remove from ticket line
					
					break;
				case "3":	// Customer(s) Leave(s) Theater
					System.out.println("| - Customer(s) Leave(s) Theater - |");
					/*
					 * Ask which customer group is leaving
					 * 		- Check both theaters for customers
					 * 		- If not found, print error message
					 * 		- else if found,
					 * 			Remove these customers from the theater they are in
					 */
					break;
				case "4":	// Display Info About Waiting Customers
					System.out.println("| - Display Info About Waiting Customers - |");
					System.out.println("Regular Ticket Line 1:");
					//display
					if (reg1.isEmpty()) {
						System.out.println("No customers in the first line!");
					} else {
						System.out.println(reg1.toString());
					}
					System.out.println(); // leave space
					System.out.println("Regular Ticket Line 2:");
					// display
					if (reg2.isEmpty()) {
						System.out.println("No customers in the second line!");
					} else {
						System.out.println(reg2.toString());
					}
					System.out.println(); // leave space
					System.out.println("Express Ticket Line:");
					// display
					if (express.isEmpty()) {
						System.out.println("No customers in the express line!");
					} else {
						System.out.println(express.toString());
					}
					System.out.println(); // leave space
					break;
				case "5":	// Display Seating Chart for Life
					System.out.println("| - Display Seating Chart for \"Life\" - |");
					//lifeTheater.displaySeatingChart() or something
					break;
				case "6":	// Display Seating Chart for Logan
					System.out.println("| - Display Seating Chart for \"Logan\" - |");
					//loganTheater.displaySeatingChart() or something
					break;
				case "7":	// Display Tickets Sold and Earnings
					System.out.println("| - Display Tickets Sold and Total Earnings - |");
					
					System.out.println("Tickets Sold: \t" + numTicketsSold);
					System.out.println("Total Earnings: \t" + totalEarnings);
					break;
				case "8":	// End Program
					// Clear Both Theaters //Note: you can just call System.exit or have the program just end without clearing anything
					lifeTheater.clearTheater();
					loganTheater.clearTheater();
					reading = false; // Stop "reading" input
					System.out.println("| - - - Program Ended - - - |");
					break;
				default:	// Invalid Input
					System.out.println("Invalid Input!");
					break;
			}
		}
	}
}
