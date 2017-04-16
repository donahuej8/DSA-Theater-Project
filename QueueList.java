package movieTheaterPackage;

public class QueueList<T> implements QueueInterface<T> {

	protected T[] items;
	protected int front;
	protected int back;
	protected int numItems;
	
	@SuppressWarnings("unchecked")
	public QueueList()
	{
		items = (T[]) new Object[3];
		front = 0;
		back = 0;
		numItems = 0;
	} // end of Constructor
	
	@Override
	public boolean isEmpty() {
		return (numItems == 0);
	}

	@Override
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
	public void dequeueAll() {
		items = (T[]) new Object[3];
		front = back = numItems = 0;
		
	} // end of dequeueAll

	@Override
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
	public String toString()
	{
		String result = "List of size " + numItems + " contains:\n";
		for (int i = 0; i < numItems; i++)
		{
			result += (items[(front + i) % items.length]) + " ";
		}
		return result;
	}
} // end of QueueList
