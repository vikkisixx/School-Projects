
public class ArrayListClass<E> implements ArrayListInterface<E>
{
	// Data elements
	private int size;								// how many elements in  array
	private int capacity;							// size of arrayList
	private E[] myArray;							// array reference to store the actual data
	private static final int INITIAL_CAPACITY = 10;
	
	// constructors
	
	public ArrayListClass()							// default constructor
	{
		this.capacity = INITIAL_CAPACITY;			// default 10
		this.size = 0;
		myArray = (E[]) new Object[this.capacity];	// cast new object as type E. this is arrayList
	}
	
	public ArrayListClass(int capacity)				// constructing arrayList with custom capacity
	{
		this.capacity = capacity;
		this.size = 0;
		myArray = (E[]) new Object[this.capacity];	// cast new object as type E. this is arrayListray
	}
	
	// methods
	
	@Override
	public void add(E a) 							// adds element at end of list
	{
		if(size < capacity)							// there's space to add new element
		{
			myArray[size]  = a;						// size gives index of of first free location
			size++;
		}
		else										// if there's no space for new element
		{
			System.out.println("reallocating more space");
			this.reallocate();
			this.add(a);
		}
	}
	
	private void reallocate()						// called when more space is needed 
	{
		this.capacity *= 2;							// doubling size of array for more elements
		E[] temp = (E[]) new Object[this.capacity];	// new array receives old array data
		
		for(int a = 0; a < myArray.length; a++)		// copying data over to new array
			temp[a] = myArray[a];
		
		myArray = temp;								// old array points to new array
		
	}

	@Override
	public void add(E a, int index) 
	{
		if(index < 0 || index > size)
		{
			System.out.println("Invalid index, no data inserted");
			return;
		}
		else if(index == size)						// element added at end to call other add method to do it again
		{
			this.add(a);;
		}
		else										// when shifting elements is required 
		{
			if(this.size == this.capacity)			
				this.reallocate();
			for(int x = size; x > index; x--)		// mode data one index to the right starting at current end
				myArray[x] = myArray[x-1];			
		}
		
		myArray[index] = a;							// insert data
		size++;										// update total number of elements
		
	}

	@Override
	public E remove(int index) 
	{
		if(index < 0 || index >= size)
		{
			System.out.println("invalid index");
			return null;
		}
		E temp = myArray[index];					// saving value before deleting
		
		for(int a = index; a < size - 1; a++)			// loop to move all elements after index one to the left
			myArray[a] = myArray[a+1];
		
		size--;
		return temp;
	}

	@Override
	public E get(int index) 
	{
		if(index < 0 || index >= size)
		{
			System.out.println("invalid index");
			return null;
		}
		return myArray[index];
	}

	@Override
	public void set(E a, int index) 
	{
		if(index < 0 || index >= size)
		{
			System.out.println("invalid index");
			return;
		}
		myArray[index] = a;							// update with new value
	}

	@Override
	public int getSize() 
	{
		return size;
	}

	@Override
	public int indexOf(E a) 
	{
		int x;
		for(x = 0; x < myArray.length; x++)
		{
			if(myArray[x] == a) 
				return x;
		}
		if(x == myArray.length)
			System.out.println("Element " + a + " not found");
		
		return 999;
	}
	
	public String toString()							// returns string representation of arrayList object
	{
		String s = "";
		
		for(int a = 0; a < size; a++)
			s += myArray[a] + " ";
		
		return s;
	}
	
}
