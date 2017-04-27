package movieTheaterPackage;

public class Theater {
	
	/*
	 * NOTE: The "Row" class will be kept simply to allow us to see if a certain
	 * row is empty or not. This will allow for easy access to the first available
	 * seat in a theater. This way, we don't have to go through an almost completely
	 * full theater just to sit one customer.
	 */
	
	private Row [] rows;
	private String movieName;
	private int numSeatsPerRow;
	private int numRows;
	private int numInTheater; // number of customers currently in Theater
	private final int MAX_NUM_CUSTOMERS; // maximum capacity of customers in Theater
	
	/**
	 * Constructor for Theater Class
	 * @param numRows - the number of rows to have in the theater 
	 * @param numSeatsPerRow - the number of seats per row in the theater
	 * @param movieName - the name of the movie being screened in this cinema
	 */
	public Theater(int numRows, int numSeatsPerRow, String movieName)
	{
		this.movieName = movieName;
		this.numSeatsPerRow = numSeatsPerRow;
		this.numRows = numRows;
		rows = new Row[numRows]; // LABGen should take in the number of rows
									  // to avoid unused data. No need to resize.
		for (int i = 0; i < numRows; i++)
		{
			rows[i] = new Row(numSeatsPerRow); // fill theater with rows containing
											// the correct number of seats.
		}
		MAX_NUM_CUSTOMERS = numRows * numSeatsPerRow;
		numInTheater = 0;
	}
	
	/**
	 * Give the name of the movie in this theater
	 * @return movieName - the name of the movie in this theater
	 */
	public String getMovieName()
	{
		return movieName;
	}
	
	/**
	 * Give the number of Rows in this Theater
	 * @return numRows - the number of rows in this theater.
	 */
	public int getNumRows()
	{
		return numRows;
	}
	
	/**
	 * Give the number of customers currently in this Theater
	 * @return numInTheater - number of customers currently in theater
	 */
	public int getNumInTheater()
	{
		return numInTheater;
	}
	
	/**
	 * Whether or not the Theater has enough room for a given group
	 * @param group - The group to check if they can fit in the theater
	 * @return true if there is room, false if not
	 */
	public boolean hasRoom(Group group)
	{
		return (numInTheater + group.getGroupNum() <= MAX_NUM_CUSTOMERS);
	}
	
	/**
	 * Remove all customers from the Theater
	 */
	public void clearTheater()
	{
		rows = new Row [numRows]; // empty out all rows
		for (int i = 0; i < numRows; i++)
		{
			rows[i] = new Row(numSeatsPerRow);
		}
	}
	
	/**
	 * Seat all customers in a Group into their own seats
	 * @param group - Group to seat
	 * @throws FullTheaterException - if the theater cannot hold this group
	 * @throws FullRowException - not thrown
	 */
	public void seatGroup(Group group) throws FullTheaterException, FullRowException
	{
		// if the theater is full, throw an exception
		// else if the theater cannot hold enough customers, throw an exception
		// else, find first available row, first available seat, seat first customer
		// 	then do the same for the next customer and so on until there are none
		if (!(hasRoom(group)))//not enough room
		{
			throw new FullTheaterException("FullTheaterException on seatGroup!");
		}
		else // there is enough room for the group
		{
			int groupNum = group.getGroupNum();
			int rowIndex = 0;
			int seatIndex = 0;
			for (int i = 1; i <= groupNum; i++) // for each customer
			{
				// while the current row is full and valid
				while (rowIndex < numRows && rows[rowIndex].isFull())
				{
					rowIndex++; // move on to next row until non-full is reached
					seatIndex = 0;
				}
				if (rowIndex < numRows) // if valid row index
				{
					// while the current seat is filled and the seatIndex is valid
					while (seatIndex < numSeatsPerRow && 
							(rows[rowIndex].getGroup(seatIndex) != null))
					{
						seatIndex++; // move on to next seat until non-full is reached
					}
					// fill seat
					rows[rowIndex].seatCustomer(group, seatIndex);
					//if (seatIndex == numSeatsPerRow)
					//{
					//	seatIndex = 0; // reset index
					//}
				}
			}
			numInTheater += groupNum; // add to number of customers in theater
		}
	}
	
	/**
	 * Remove all members with the given Group name from the Theater
	 * @param groupName - name of group to find and remove
	 */
	public void removeGroup(String groupName)
	{
		// remove all customers from the theater with this name
		/*
		 * In order to do so, we need to find the first customer (group) in the 
		 * theater with that name. Once found, take the groupNumber from them.
		 * This will determine how many total customers we need to find 
		 * with that name!
		*/
		boolean found = false;
		int i = 0;
		int numberGuestInGroup = 0;
		while (i < numRows && found == false)
		{
			if (rows[i].isEmpty() == false) // if the row isn't empty
			{
				int j = 0;
				while (j < numSeatsPerRow && found == false) // check each seat
				{
					if (rows[i].getGroup(j) != null)
					{
						if (rows[i].getGroup(j).getGroupName().equals(groupName)) // if match
						{
							found = true; // they have been found in the theater
							numberGuestInGroup = rows[i].getGroup(j).getGroupNum();
						}
					}
					j++;
				}
			} // else if isEmpty(), move on to next row
			i++;
		}
		System.out.println("remove: " + numberGuestInGroup);
		/*
		 * This way, we reduce the number of searches done and don't end up
		 * with unnecessary searches after all group members have been found.
		 */
		int counter = 0;
		int x = 0;
		while (counter < numberGuestInGroup) 
		{
			while (x < numRows && counter < numberGuestInGroup)
			{
				if (rows[x].isEmpty() == false)		//if the row isn't empty
				{
					int y = 0;
					while (y < numSeatsPerRow && counter < numberGuestInGroup)
					{
						if (rows[x].getGroup(y) != null) // not empty seat
						{
							if (rows[x].getGroup(y).getGroupName().equals(groupName))	//if match
							{
								System.out.println(groupName + " left. Goodbye!");
								rows[x].clearSeat(y);
								counter++;
								numInTheater--;
							}
						}
						y++;
					}
				}
				x++;
			}
		}
	}
	
	/**
	 * 
	 * @param name - name of group in theater
	 * @return true if that name is the name of group of anyone seated in the theater, false otherwise
	 */
	public boolean inTheater(String name)
	{
		boolean found = false;
		int i = 0;
		while (i < numRows && found == false)
		{
			if (rows[i].isEmpty() == false) // if the row isn't empty
			{
				int j = 0;
				while (j < numSeatsPerRow && found == false) // check each seat
				{
					if (rows[i].getGroup(j) != null) // not empty seat
					{
						if (rows[i].getGroup(j).getGroupName().equals(name)) // if match
						{
							found = true; // they have been found in the theater
						}
					}
					j++;
				}
			} // else if isEmpty(), move on to next row
			i++;
		}
		return found;
	}
	
	/**
	 * Create and return a seating chart to represent the Theater layout
	 * @return layout - a beautiful seating chart, ty based Jack
	 */
	public String getSeatingChart()
	{
		// Start with movie title and number of customers in theater
		String layout = movieName + " Theater: " + numInTheater;
		for (int i = 0; i < numRows; i++) // for each row
		{ // then each row with the number of customers in the row
			layout += "\nRow " + (i+1) + ": " + rows[i].getNumFilledSeats() + "\n";
			for (int j = 0; j < numSeatsPerRow; j++)
			{
				String seatName = "empty";
				if (rows[i].getGroup(j) != null) // if the seat is filled
				{ // update name for the current seat
					seatName = rows[i].getGroup(j).getGroupName();
				} // display the exact customer in the seat
				layout += "[ " + seatName + " ]  "; // add to output
			}
		}
		return layout;
	}
	
}
