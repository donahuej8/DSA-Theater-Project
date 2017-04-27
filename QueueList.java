package movieTheaterPackage;

public class QueueList<T> implements QueueInterface<T> {

	protected T[] items;
	protected int front;
	protected int back;
	protected int numItems;
	
	@SuppressWarnings("unchecked")
	/**
	 * Constructor: creates the array, keeps track of front and back, and numItems
	 */
	public QueueList()
	{
		items = (T[]) new Object[3];
		front = 0;
		back = 0;
		numItems = 0;
	} // end of Constructor
	
	
	@Override
	/**
	 * isEmpty: returns true if the queue is empty, false if not
	 */
	public boolean isEmpty() {
		return (numItems == 0);
	}

	@Override
	/**
	 * @param newItem - item to enqueue into queue
	 */
	public void enqueue(T newItem) throws QueueException {
		if (numItems == items.length) // list is full
		{
			resize();
		}
		//Add item to list
		items[back] = newItem;
		//Increment back
		back = ((back + 1) % items.length);
		numItems++;
	} // end of enqueue
	
	@SuppressWarnings("unchecked")
	/**
	 * resize: resizes array to double the size and copys over items
	 */
	protected void resize()
	{
		//Make temporary list that is twice the size of the current list
		T [] tempList = (T[])new Object[numItems*2];
		// NOTE: We can use the numItems reference rather than the length of
		// the list ONLY because this method is only called when the
		// list is full.
		for (int i = 0; i < numItems; i++)
		{
			tempList[i] = items[(front + i) % items.length]; // set Objects in temp to those in items
		}
		items = tempList; // fill queue with Objects
		front = 0;
		back = numItems;
	} // end of resize

	@Override
	/**
	 * dequeue: removes item from front and resets index of front element
	 * @return ref - object being removed from the line
	 */
	public T dequeue() throws QueueException {
		T ref;
		if (numItems != 0)
		{
			ref = items[front];
			items[front] = null;
			front = ((front + 1) % items.length);
			numItems --;
		}
		else
		{
			throw new QueueException(
					"QueueException on dequeue.");
		}
		return ref;
	} // end of dequeue

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * dequeueAll: removes everything from the line
	 */
	public void dequeueAll() {
		items = (T[]) new Object[3];
		front = back = numItems = 0;
		
	} // end of dequeueAll

	@Override
	/**
	 * peek: returns the front object without removing it
	 * @return t - item at front of array
	 */
	public T peek() throws QueueException {
		if (numItems != 0)
		{
			return items[front];
		}
		else
		{
			throw new QueueException(
					"QueueException on peek.");
		}
	} // end of peek
	
	@Override
	/**
	 * toString: returns String representation of queue
	 * @return result - string form of queue
	 */
	public String toString()
	{
		String result = "";
		for (int i = 0; i < numItems; i++)
		{
			result += "\t" + (i+1) + " - " + items[(front+i)%items.length].toString() + "\n";
		}
		return result;
	}
} // end of QueueList