package movieTheaterPackage;

public class Row {

	private Group [] seats;
	private int numFilledSeats;
	
	/**
	 * Constructor for the Row class
	 * @param numSeats
	 */
	public Row(int numSeats)
	{
		seats = new Group [numSeats];
		numFilledSeats = 0;
	}
	
	/**
	 * Whether or not the row is full
	 * @return
	 */
	boolean isFull()
	{
		return (numFilledSeats == seats.length);
	}
	
	/**
	 * Whether or not the row is empty
	 * @return
	 */
	boolean isEmpty()
	{
		return (numFilledSeats == 0);
	}
	
	/**
	 * Put the given customer (actually a Group) in the given index
	 * @param groupName
	 * @param index
	 * @throws FullRowException
	 */
	public void seatCustomer(Group groupName, int index) throws FullRowException
	{
		if (numFilledSeats < seats.length)
		{
			seats[index] = groupName;
		}
		else
		{
			throw new FullRowException("Row Filled!");
		}
	}
	
	/**
	 * Give the Group object in the given index
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public Group getGroup(int index) throws IndexOutOfBoundsException
	{
		if (index >= seats.length)
		{
			throw new IndexOutOfBoundsException("IOBException on getGroup!");
		}
		else
		{
			return seats[index];
		}
	}
}
