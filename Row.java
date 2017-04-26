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
	public boolean isFull()
	{
		return (numFilledSeats == seats.length);
	}
	
	/**
	 * Whether or not the row is empty
	 * @return
	 */
	public boolean isEmpty()
	{
		return (numFilledSeats == 0);
	}
	
	/**
	 * Give the number of filled seats in the row
	 * @return
	 */
	public int getNumFilledSeats()
	{
		return numFilledSeats;
	}
	
	/**
	 * Put the given customer (actually a Group) in the given index
	 * @param groupName
	 * @param index
	 * @throws FullRowException
	 */
	public void seatCustomer(Group groupName, int index) throws FullRowException
	{
		if (numFilledSeats < seats.length && index < seats.length)
		{
			seats[index] = groupName;
			numFilledSeats++;
		}
		else
		{
			throw new FullRowException("FullRow/IndexOutOfBounds Exception on seatCustomer!");
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
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException on getGroup!");
		}
		else
		{
			return seats[index];
		}
	}
	
	/**
	 * Remove the person from the given seat
	 * @param index - seat number in the Row
	 */
	public void clearSeat(int index) 
	{
		if (index < seats.length) 
		{
			seats[index] = null;
		} 
		else 
		{
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException on clearSeat!");
		}
	}
}
