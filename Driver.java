package movieTheaterPackage;

import java.io.*;

public class Driver {
	
	private static BufferedReader stdin = 
			new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String [] args) throws IOException
	{
		// Fields for Ticket Lines
		int numLine1 = 0;		// number of customers in reg1
		int numLine2 = 0;		// number of customers in reg2
		int numLineExpress = 0;	// number of customers in express
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
				///////////////////////////////////////////////////////////
				// - - - - - - - - - - - Option 1. - - - - - - - - - - - //
				///////////////////////////////////////////////////////////
				case "1":	// Customer(s) Enter(s) Theater
					System.out.println("| - Customer(s) Enter(s) Theater - |");
					
					// - - - - - - - - Get Info - - - - - - - - //
					
					// 1.) Ask for name of group
					String groupName = "";
					boolean gettingName = true;
					while (gettingName)
					{
						System.out.print("Enter customer name: ");
						groupName = stdin.readLine().trim();
						System.out.println(groupName);
						if (loganTheater.inTheater(groupName))
						{
							System.out.println("Sorry! That name is already in use.");
							System.out.println("Please try again.");
						}
						else
						{
							System.out.println("Welcome, " + groupName + "!");
							gettingName = false;
						}
					}
					// 2.) Ask for number of customers in group
					int partySize = 0;
					boolean gettingNumCustomers = true;
					while (gettingNumCustomers)
					{
						System.out.print("Enter party size: ");
						partySize = Integer.parseInt(stdin.readLine().trim());
						System.out.println(partySize);
						if (partySize < 1)
						{
							System.out.println("Hmm... Seems like your party is a bit small.");
							System.out.println("Please try again.");
						}
						else
						{
							System.out.println(partySize + " customer(s) in this group.");
							gettingNumCustomers = false;
						}
					}					
					// 3.) Ask for movie preference
					String moviePreference = "";
					boolean gettingMoviePref = true;
					while (gettingMoviePref)
					{
						System.out.println("Select your movie preference: ");
						System.out.println("\t1.) Logan");
						System.out.println("\t2.) Life");
						System.out.print("Make your selection now: ");
						String movieSelection = stdin.readLine().trim();
						System.out.println(movieSelection);
						switch (movieSelection)
						{
							case "1":
								moviePreference = "Logan";
								gettingMoviePref = false;
								break;
							case "2":
								moviePreference = "Life";
								gettingMoviePref = false;
								break;
							default:
								System.out.println("Improper Input!");
								System.out.println("Please try again.");
								break;
						}
					}
					// 4.) Ask if any customers in this group are <=11 of age
					boolean hasYoungChild = false;
					boolean gettingChildInfo = true;
					while (gettingChildInfo)
					{
						System.out.print("Does your this party contain a child"
								+ " 11 years of age or under? Y/N");
						String checkAge = stdin.readLine().trim();
						System.out.println(checkAge);
						switch (checkAge.toUpperCase())
						{
							case "Y":
								hasYoungChild = true;
								gettingChildInfo = false;
								break;
							case "N":
								hasYoungChild = false;
								gettingChildInfo = false;
								break;
							default:
								System.out.println("Improper Input!");
								System.out.println("Please try again. "
										+ "(Type \"Y\" or \"N\")");
								break;
						}
					}
					
					// - - - - - - - - Process Info - - - - - - - - //
					
					// 5.) Place into proper ticket line
					boolean checkRegs = false;
					if (hasYoungChild)
					{
						// check if reg1 or reg2 are better options than express
						if (numLine1 < numLineExpress/2 || numLine2 < numLineExpress/2)
						{
							checkRegs = true;
						}
						else // place into express line
						{
							express.enqueue(new Group(groupName, partySize, moviePreference));
							System.out.println(groupName + ", party of "
									+ partySize + " placed in Express Line.");
							numLineExpress++;
						}
					}
					if (checkRegs == true)
					{
						if (numLine1 <= numLine2) // reg1 is shorter
						{ // place into reg1
							reg1.enqueue(new Group(groupName, partySize, moviePreference));
							System.out.println(groupName + ", party of "
									+ partySize + " placed in Regular Line 1.");
							numLine1++;
						}
						else // reg2 is shorter
						{ // place into reg2
							reg2.enqueue(new Group(groupName, partySize, moviePreference));
							System.out.println(groupName + ", party of "
									+ partySize + " placed in Regular Line 2.");
							numLine2++;
						}
					}
					break;
					
				///////////////////////////////////////////////////////////
				// - - - - - - - - - - - Option 2. - - - - - - - - - - - //
				///////////////////////////////////////////////////////////
				case "2":	// Customer Buys Ticket(s)
					System.out.println("| - Customer Buys Ticket(s) - |");
					
					//If it's the first time using this function,
					if (firstLoop) {
						//- ask what line goes first
						firstLoop = false; // after first time, don't allow this
					}
					
					//For each line
					 		//- ask what movie the first group wants to see
					 		//- check for room in that Theater
					 		//- if not enough room, give alternatives
					 		//- else, seat the group in the theater
					 		//- after actions are carried out, remove from ticket line
					break;
					
				///////////////////////////////////////////////////////////
				// - - - - - - - - - - - Option 3. - - - - - - - - - - - //
				///////////////////////////////////////////////////////////
				case "3":	// Customer(s) Leave(s) Theater
					System.out.println("| - Customer(s) Leave(s) Theater - |");
					/*
					 * Ask which customer group is leaving
					 * 		- Check both theaters for customers
					 * 		- If not found, print error message
					 * 		- else if found,
					 * 			Remove these customers from the theater they are in
					 */
					// Ask which group is leaving
					System.out.print("Which customer/group is leaving? (Enter Name)");
					String nameLeaving = stdin.readLine().trim();
					System.out.println(nameLeaving);
					if (loganTheater.inTheater(nameLeaving)) // if found in Logan...
					{
						loganTheater.removeGroup(nameLeaving);
					}
					else if (lifeTheater.inTheater(nameLeaving)) // if found in Life...
					{
						lifeTheater.removeGroup(nameLeaving);
					}
					else
					{
						System.out.println("Sorry, the name you are looking for cannot be found!");
					}
					break;
					
				///////////////////////////////////////////////////////////
				// - - - - - - - - - - - Option 4. - - - - - - - - - - - //
				///////////////////////////////////////////////////////////
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
					
				///////////////////////////////////////////////////////////
				// - - - - - - - - - - - Option 5. - - - - - - - - - - - //
				///////////////////////////////////////////////////////////
				case "5":	// Display Seating Chart for Life
					System.out.println("| - Display Seating Chart for \"Life\" - |");
					//lifeTheater.displaySeatingChart() or something
					break;
					
				///////////////////////////////////////////////////////////
				// - - - - - - - - - - - Option 6. - - - - - - - - - - - //
				///////////////////////////////////////////////////////////
				case "6":	// Display Seating Chart for Logan
					System.out.println("| - Display Seating Chart for \"Logan\" - |");
					//loganTheater.displaySeatingChart() or something
					break;
				///////////////////////////////////////////////////////////
				// - - - - - - - - - - - Option 7. - - - - - - - - - - - //
				///////////////////////////////////////////////////////////
				case "7":	// Display Tickets Sold and Earnings
					System.out.println("| - Display Tickets Sold and Total Earnings - |");
					
					System.out.println("Tickets Sold: \t" + numTicketsSold);
					System.out.println("Total Earnings: \t" + totalEarnings);
					break;
					
				///////////////////////////////////////////////////////////
				// - - - - - - - - - - - Option 8. - - - - - - - - - - - //
				///////////////////////////////////////////////////////////
				case "8":	// End Program
					// Clear Both Theaters //Note: you can just call System.exit or have the program just end without clearing anything
					lifeTheater.clearTheater();
					loganTheater.clearTheater();
					reading = false; // Stop "reading" input
					System.out.println("| - - - Program Ended - - - |");
					break;
					
				///////////////////////////////////////////////////////////
				// - - - - - - - - Handle Improper Input - - - - - - - - //
				///////////////////////////////////////////////////////////
				default:	// Invalid Input
					System.out.println("Invalid Input!");
					break;
			}
		}
	}
}