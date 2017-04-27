package movieTheaterPackage;

public class Row {

	private Group [] seats;
	private int numFilledSeats;
	
	/**
	 * Constructor for the Row class
	 * @param numSeats - the number of seats to put in the row (in Group[] seats)
	 */
	public Row(int numSeats)
	{
		seats = new Group [numSeats];
		numFilledSeats = 0;
	}
	
	/**
	 * Whether or not the row is full
	 * @return true if row is full, false if it isnt
	 */
	public boolean isFull()
	{
		return (numFilledSeats == seats.length);
	}
	
	/**
	 * Whether or not the row is empty
	 * @return if the numFilledSeats = 0
	 */
	public boolean isEmpty()
	{
		return (numFilledSeats == 0);
	}
	
	/**
	 * Give the number of filled seats in the row
	 * @return numFilledSeats - the number of seats taken in the row
	 */
	public int getNumFilledSeats()
	{
		return numFilledSeats;
	}
	
	/**
	 * Put the given customer (actually a Group) in the given index
	 * @param groupName - Group to sit down
	 * @param index - place in row to sit the member of the group
	 * @throws FullRowException - if numFilledSeats goes above the length of the row or index is out of bounds.
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
	 * @param index - the place im the row to check for the group
	 * @return the Group at that seat in the row
	 * @throws IndexOutOfBoundsException - if the index is not in the bounds of the row
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
	 * @param index - seat number in the Row to clear from
	 */
	public void clearSeat(int index) 
	{
		if (index < seats.length) 
		{
			seats[index] = null;
			numFilledSeats--;
		} 
		else 
		{
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException on clearSeat!");
		}
	}
}
