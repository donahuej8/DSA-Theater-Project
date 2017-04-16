package movieTheaterPackage;

public class Theater {
	
	private LABGen<Row> rows;
	private int numSeatsPerRow;
	private int numRows;
	private int numInTheater; // number of customers currently in Theater
	private final int MAX_NUM_CUSTOMERS; // maximum capacity of customers in Theater
	
	/**
	 * Constructor for Theater Class
	 * @param numRows
	 * @param numSeatsPerRow
	 */
	public Theater(int numRows, int numSeatsPerRow)
	{
		this.numSeatsPerRow = numSeatsPerRow;
		this.numRows = numRows;
		rows = new LABGen<>(numRows); // LABGen should take in the number of rows
									  // to avoid unused data. No need to resize.
		for (int i = 0; i < numRows; i++)
		{
			rows.add(i, new Row(numSeatsPerRow)); // fill theater with rows containing
											// the correct number of seats.
		}
		MAX_NUM_CUSTOMERS = numRows * numSeatsPerRow;
		numInTheater = 0;
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
		rows = new LABGen<>(numRows); // empty out all rows
		for (int i = 0; i < numRows; i++)
		{
			rows.add(i, new Row(numSeatsPerRow));
		}
	}
	
	/**
	 * Seat all customers in a Group into their own seats
	 * @param group
	 * @throws FullTheaterException
	 * @throws ListIndexOutOfBoundsException
	 * @throws FullRowException
	 */
	public void seatGroup(Group group) throws FullTheaterException, ListIndexOutOfBoundsException, FullRowException
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
				while (((Row) rows.get(firstRowIndex)).isFull())
				{
					firstRowIndex++; // move on to next row until non-full is reached
				}
				// while the current seat is filled
				while (((Row) rows.get(firstRowIndex)).getGroup(firstSeatIndex) != null)
				{
					firstSeatIndex++; // move on to next seat until non-null is reached
				}
				// fill seat
				((Row) rows.get(firstRowIndex)).seatCustomer(group, firstSeatIndex);
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
	
	/**
	 * Create and return a seating chart to represent the Theater layout
	 * @return layout
	 */
	public String getSeatingChart()
	{
		String layout = "";
		return layout;
	}
	
}
