package movieTheaterPackage;

public class LABGen<T> {
	
	protected T []items;  // an array of list items
    protected int numItems;  // number of items in list

    @SuppressWarnings("unchecked")
	public LABGen()
    {
        items = (T[]) new Object[3];
        numItems = 0;
    }  // end default constructor
    
    // Overloaded Constructor
    @SuppressWarnings("unchecked")
	public LABGen(int size)
    {
        items = (T[]) new Object[size];
        numItems = 0;
    }  // end default constructor

    public boolean isEmpty()
    {
        return (numItems == 0);
    } // end isEmpty

    public int size()
    {
        return numItems;
    }  // end size

    @SuppressWarnings("unchecked")
	public void removeAll()
    {
        // Creates a new array; marks old array for
        // garbage collection.
        items = (T[])new Object[3];
        numItems = 0;
    } // end removeAll

    public void add(int index, T item)
    throws  ListIndexOutOfBoundsException
    {
    	//if array is full
        //resize
        //after, call the super add method
        if (numItems == items.length) //array is full
        {
            resize();
        }  // end if
        if (index >= 0 && index <= numItems)
        {
            // make room for new element by shifting all items at
            // positions >= index toward the end of the
            // list (no shift if index == numItems+1)
            for (int pos = numItems-1; pos >= index; pos--)  //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
            {
                items[pos+1] = items[pos];
            } // end for
            // insert new item
            items[index] = item;
            numItems++;
        }
        else if (index > items.length)
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on add");
        }  // end if
        else
        {
            items[index] = item;
        }
    } //end add
    
    @SuppressWarnings("unchecked")
	private void resize()
    {
    	int doubleItems = (2 * items.length);
        T [] items2 = (T[]) new Object[doubleItems];
        for (int i = 0; i <= (items.length-1); i++)
        {
            items2[i] = items[i];
        }
        //make items reference the new array
        items = (T[]) new Object[doubleItems];
        items = items2;
    }
    
    public Object get(int index)
   	    throws ListIndexOutOfBoundsException
   	    {
   	        if (index >= 0 && index < numItems)
   	        {
   	            return items[index];
   	        }
   	        else
   	        {
   	            // index out of range
   	            throw new ListIndexOutOfBoundsException(
   	                "ListIndexOutOfBoundsException on get");
   	        }  // end if
   	    } // end get

   	    public void remove(int index)
   	    throws ListIndexOutOfBoundsException
   	    {
   	        if (index >= 0 && index < items.length)
   	        {
   	            // delete item by shifting all items at
   	            // positions > index toward the beginning of the list
   	            // (no shift if index == size)
   	            for (int pos = index+1; pos < numItems; pos++) //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
   	            {
   	                items[pos-1] = items[pos];
   	            }  // end for
   	            numItems--;
   	            items[numItems] = null;
   	        }
   	        else
   	        {
   	            // index out of range
   	            throw new ListIndexOutOfBoundsException(
   	                "ListIndexOutOfBoundsException on remove");
   	        }  // end if
   	    } //end remove
}
