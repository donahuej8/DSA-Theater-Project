package movieTheaterPackage;

public class Theater {
	
	/*
	 * NOTE: The "Row" class will be kept simply to allow us to see if a certain
	 * row is empty or not. This will allow for easy access to the first available
	 * seat in a theater. This way, we don't have to go through an almost completely
	 * full theater just to sit one customer.
	 */
	
	//Read comment about LABgen in getSeatingChart method
	private Row [] rows;
	private String movieName;
	private int numSeatsPerRow;
	private int numRows;
	private int numInTheater; // number of customers currently in Theater
	private final int MAX_NUM_CUSTOMERS; // maximum capacity of customers in Theater
	
	/**
	 * Constructor for Theater Class
	 * @param numRows
	 * @param numSeatsPerRow
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
	 * @return movieName
	 */
	public String getMovieName()
	{
		return movieName;
	}
	
	/**
	 * Give the number of Rows in this Theater
	 * @return numRows
	 */
	public int getNumRows()
	{
		return numRows;
	}
	
	/**
	 * Give the number of customers currently in this Theater
	 * @return numInTheater
	 */
	public int getNumInTheater()
	{
		return numInTheater;
	}
	
	/**
	 * Whether or not the Theater has enough room for a given group
	 * @param group
	 * @return
	 */
	public boolean hasRoom(Group group)
	{
		return (numInTheater + group.getGroupNum() >= MAX_NUM_CUSTOMERS);
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
	 * @param group
	 * @throws FullTheaterException
	 * @throws FullRowException
	 */
	public void seatGroup(Group group) throws FullTheaterException, FullRowException
	{
		// if the theater is full, throw an exception
		// else if the theater cannot hold enough customers, throw an exception
		// else, find first available row, first available seat, seat first customer
		// 	then do the same for the next customer and so on until there are none
		if (numInTheater + group.getGroupNum() >= MAX_NUM_CUSTOMERS)//not enough room
		{
			throw new FullTheaterException("FullTheaterException on seatGroup!");
		}
		else // there is enough room for the group
		{
			int groupNum = group.getGroupNum();
			int firstRowIndex = 0;
			int firstSeatIndex = 0;
			for (int i = 1; i <= groupNum; i++) // for each customer
			{
				// while the current row is full
				while (rows[firstRowIndex].isFull())
				{
					firstRowIndex++; // move on to next row until non-full is reached
				}
				// while the current seat is filled
				while (rows[firstRowIndex].getGroup(firstSeatIndex) != null)
				{
					firstSeatIndex++; // move on to next seat until non-null is reached
				}
				// fill seat
				rows[firstRowIndex].seatCustomer(group, firstSeatIndex);
			}
			numInTheater += groupNum; // add to number of customers in theater
		}
	}
	
	/**
	 * Remove all members with the given Group name from the Theater
	 * @param groupName
	 */
	public void removeGroup(String groupName)
	{
		// remove all customers from the theater with this name
		/*
		 * In order to do so, we need to find the first customer (group) in the 
		 * theater with that name. Once found, take the groupNumber from them.
		 * This will determine how many total customers we need to find 
		 * with that name!
		 * This way, we reduce the number of searches done and don't end up
		 * with unnecessary searches after all group members have been found.
		 */
	}
	
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
					if (rows[i].getGroup(j).getGroupName().equals(name)) // if match
					{
						found = true; // they have been found in the theater
					}
				}
			} // else if isEmpty(), move on to next row
			i++;
		}
		return found;
	}
	
	/**
	 * Create and return a seating chart to represent the Theater layout
	 * @return layout
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