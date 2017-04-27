package movieTheaterPackage;

import java.io.*;

public class Driver {
	
	private static BufferedReader stdin = 
			new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String [] args) throws IOException, QueueException, FullTheaterException
	{
		// Fields for Ticket Lines
		int numLine1 = 0;		// number of customers in reg1
		int numLine2 = 0;		// number of customers in reg2
		int numLineExpress = 0;	// number of customers in express
		QueueList<Group> reg1 = new QueueList();
		QueueList<Group> reg2 = new QueueList();
		QueueList<Group> express = new QueueList();
		int lineCounter = 0;	// number of current line (0 for reg1, 1 for reg2, 2 for express)
		
		// Fields for Sales
		double ticketPrice = 0.0;	//cost of a single ticket
		int numTicketsSoldLogan = 0;// total number of tickets sold for Logan
		int numTicketsSoldLife = 0;	// total number of tickets sold for Life
		double totalEarnings = 0.0;	// total income from tickets sales
		
		// Fields for Theaters
		int numRowsLogan = 0;	// number of Rows in Logan Theater
		int numRowsLife = 0;	// number of Rows in Life Theater
		int numSeatsLogan = 0;	// number of Seats per Row in Logan Theater
		int numSeatsLife = 0;	// number of Seats per Row in Life Theater
		
		//////////////////////////////////////////////////////////////////
		// - - - - - - - - Retrieve General Information - - - - - - - - //
		//////////////////////////////////////////////////////////////////
		
		
		System.out.println("Welcome to the Wonderful Movie Theater program!\n\tTonight's features are:\n\t\t\"Life\" and \"Logan\"");
		System.out.println("\nPlease specify the size of the movie theaters:");
		// - - - - - - - - Logan - - - - - - - - //
		System.out.println("\tEnter information about the Logan Movie Theater:");	
		boolean gettingRows= true;
		while (gettingRows)
		{
			System.out.print("\t\tEnter number of rows: ");
			int num1 = Integer.parseInt(stdin.readLine().trim());
			if (num1 <= 0) // invalid input
			{
				System.out.println("Invalid Number of Rows!\n"
						+ "Must be Greater Than Zero.");
			}
			else // valid input
			{
				numRowsLogan = num1;
				gettingRows = false;
			}
		}
		
		boolean gettingSeats = true;
		while (gettingSeats)
		{
			System.out.print("\t\tEnter number of seats: ");
			int num2 = Integer.parseInt(stdin.readLine().trim());
			if (num2 <= 0) // invalid input
			{
				System.out.println("Invalid Number of Seats!\n"
						+ "Must be Greater Than Zero.");
			}
			else // valid input
			{
				numSeatsLogan = num2;
				gettingSeats = false;
			}
		}
		
		Theater loganTheater = new Theater(numRowsLogan, numSeatsLogan, "Logan");
		
		// - - - - - - - - Life - - - - - - - - //
		System.out.println("\tEnter information about the Life Movie Theater:");
		boolean gettingRows2= true;
		while (gettingRows2)
		{
			System.out.print("\t\tEnter number of rows: ");
			int num1 = Integer.parseInt(stdin.readLine().trim());
			if (num1 <= 0) // invalid input
			{
				System.out.println("Invalid Number of Rows!\n"
						+ "Must be Greater Than Zero.");
			}
			else // valid input
			{
				numRowsLife = num1;
				gettingRows2 = false;
			}
		}
		
		boolean gettingSeats2 = true;
		while (gettingSeats2)
		{
			System.out.print("\t\tEnter number of seats: ");
			int num2 = Integer.parseInt(stdin.readLine().trim());
			if (num2 <= 0) // invalid input
			{
				System.out.println("Invalid Number of Seats!\n"
						+ "Must be Greater Than Zero.");
			}
			else // valid input
			{
				numSeatsLife = num2;
				gettingSeats2 = false;
			}
		}
		
		Theater lifeTheater = new Theater(numRowsLife, numSeatsLife, "Life");
		
		boolean gettingTicketPrice = true;
		while (gettingTicketPrice)
		{
			System.out.print("\tEnter the price of a ticket: ");
			double num3 = Double.parseDouble(stdin.readLine().trim());
			if (num3 <= 0) //Invalid Input
			{
				System.out.println("Invalid cost!\n"
						+ "Must be Greater Than Zero.");
			}
			else	//valid input
			{
				ticketPrice = num3;
				gettingTicketPrice = false;
			}
		}

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
							System.out.println("Hmm... Seems like your party is un-naturally small.");
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
						boolean gettingLineInfo = true;
						while (gettingLineInfo) {
							System.out.println("Select your line preference: ");
							System.out.println("\tReg1");
							System.out.println("\tReg2");
							System.out.println("\tExpress");
							System.out.print("Make your selection now: ");
							String lineSelection = stdin.readLine().trim().toUpperCase();
							System.out.println(lineSelection);
							switch (lineSelection)
							{
								case "REG1":
									lineCounter = 0;
									gettingLineInfo = false;
									break;
								case "REG2":
									lineCounter = 1;
									gettingLineInfo = false;
									break;
								case "EXPRESS":
									lineCounter = 2;
									gettingLineInfo = false;
									break;
								default:
									System.out.println("Improper Input!");
									System.out.println("Type: Reg1, Reg2, or Express");
									break;
							}
							
						}
						firstLoop = false; // after first time, don't allow this
					}
					// - - - - - - - - Check Lines - - - - - - - - //
					if (reg1.isEmpty() && reg2.isEmpty() && express.isEmpty()) // both are empty
					{
						System.out.println("No customers are in the lines!");
					}
					else
					{
						for (int i = 0; i < 3; i++) // for each line...
						{
							QueueList<Group> lineToWorkWith;
							switch (lineCounter)
							{
								case 0: // Reg1
									lineToWorkWith = reg1;
									break;
								case 1: // Reg2
									lineToWorkWith = reg2;
									break;
								case 2: // Express
									lineToWorkWith = express;
									break;
							} // end of switch
							if (lineToWorkWith.isEmpty())
							{
								System.out.println("No customers in this line!");
							}
							else // take care of first customer
							{
								String movieChoice = lineToWorkWith.peek().getMovieToSee();
								if (loganTheater.hasRoom(lineToWorkWith.peek()) == false
										&& lifeTheater.hasRoom(lineToWorkWith.peek()) == false)
								{
									System.out.println("Sorry, both movies are sold out!");
								}
								else
								{
									if (movieChoice.equals("Logan")) // if Logan
									{
										// check for room
										if (loganTheater.hasRoom(lineToWorkWith.peek()))
										{
											loganTheater.seatGroup(lineToWorkWith.peek());
											numTicketsSoldLogan += lineToWorkWith.peek().getGroupNum();
											System.out.println(lineToWorkWith.peek().getGroupName() + " has entered"
													+ " the Logan Theater.");
										}
										else
										{
											System.out.println("Sorry, this movie is sold out!");
											if (lifeTheater.hasRoom(lineToWorkWith.peek()))
											{
												System.out.print("Would you like to see another movie? (Y/N): ");
												String seeOtherMovie = stdin.readLine().trim().toUpperCase();
												System.out.println(seeOtherMovie);
												if (seeOtherMovie.equals("Y"))
												{
													lifeTheater.seatGroup(lineToWorkWith.peek()); // place in other theater
													numTicketsSoldLife += lineToWorkWith.peek().getGroupNum();
													totalEarnings += lineToWorkWith.peek().getGroupNum() * ticketPrice;
													System.out.println(lineToWorkWith.peek().getGroupName() + " has entered"
															+ " the Life Theater.");
												}
												else
												{
													System.out.println("Goodbye!");
												}
											}
											else
											{
												System.out.println("Goodbye!");
											}
										}
									}
									else // if Life
									{
										// check for room
										if (lifeTheater.hasRoom(lineToWorkWith.peek()))
										{
											lifeTheater.seatGroup(lineToWorkWith.peek());
											numTicketsSoldLife += lineToWorkWith.peek().getGroupNum();
											System.out.println(lineToWorkWith.peek().getGroupName() + " has entered"
													+ " the Life Theater.");
										}
										else
										{
											System.out.println("Sorry, this movie is sold out!");
											if (loganTheater.hasRoom(lineToWorkWith.peek()))
											{
												System.out.print("Would you like to see another movie? (Y/N): ");
												String seeOtherMovie = stdin.readLine().trim().toUpperCase();
												System.out.println(seeOtherMovie);
												if (seeOtherMovie.equals("Y"))
												{
													loganTheater.seatGroup(lineToWorkWith.peek()); // place in other theater
													numTicketsSoldLogan += lineToWorkWith.peek().getGroupNum();
													totalEarnings += lineToWorkWith.peek().getGroupNum() * ticketPrice;
													System.out.println(lineToWorkWith.peek().getGroupName() + " has entered"
															+ " the Logan Theater.");
												}
												else
												{
													System.out.println("Goodbye!");
												}
											}
											else
											{
												System.out.println("Goodbye!");
											}
										}
									}
								}
								lineToWorkWith.dequeue(); // remove from line regardless
							} // end of if (line is empty)
							lineCounter = (lineCounter + 1) % 3; // increment circularly
						} // end of for loop
					}
					break;
					
				///////////////////////////////////////////////////////////
				// - - - - - - - - - - - Option 3. - - - - - - - - - - - //
				///////////////////////////////////////////////////////////
				case "3":	// Customer(s) Leave(s) Theater
					System.out.println("| - Customer(s) Leave(s) Theater - |");
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
					System.out.println(lifeTheater.getSeatingChart());
					System.out.println();
					break;
					
				///////////////////////////////////////////////////////////
				// - - - - - - - - - - - Option 6. - - - - - - - - - - - //
				///////////////////////////////////////////////////////////
				case "6":	// Display Seating Chart for Logan
					System.out.println("| - Display Seating Chart for \"Logan\" - |");
					System.out.println(loganTheater.getSeatingChart());
					System.out.println();
					break;
				///////////////////////////////////////////////////////////
				// - - - - - - - - - - - Option 7. - - - - - - - - - - - //
				///////////////////////////////////////////////////////////
				case "7":	// Display Tickets Sold and Earnings
					System.out.println("| - Display Tickets Sold and Total Earnings - |");
					
					System.out.println("\tTickets Sold for Logan: " + numTicketsSoldLogan);
					System.out.println("\tTickets Sold for Life: " + numTicketsSoldLife);
					System.out.println("\tTotal Earnings: $" + totalEarnings);
					System.out.println();
					break;
					
				///////////////////////////////////////////////////////////
				// - - - - - - - - - - - Option 8. - - - - - - - - - - - //
				///////////////////////////////////////////////////////////
				case "8":	// End Program
					// Clear Both Theaters
					lifeTheater.clearTheater();
					loganTheater.clearTheater();
					reg1.dequeueAll();
					reg2.dequeueAll();
					express.dequeueAll();
					System.out.println("The Wonderful Movie Theater who earned $" + totalEarnings + " kicks out remaining customers and closes...");
					System.out.println("Good Bye!");
					reading = false; // Stop "reading" input
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
