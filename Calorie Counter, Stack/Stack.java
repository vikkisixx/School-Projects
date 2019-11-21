
public class Stack <E> implements StackInterface <E>
{
	private E[] theData;			// storage for the stack. only reference holder, not an array
	private int topOfStack = -1;	//variable for the top of stack. points to last index of array
	private static final int INITIAL_CAPACITY = 5;
	
	public Stack()						// default constructor
	{
		this.theData = ( E[] ) new Object[INITIAL_CAPACITY];	// actual array created here
	}
	
	public Stack(int capacity)			// overloaded constructor where the user chooses the capacity of stack
	{
		this.theData = ( E[] ) new Object[capacity];
	}
	
	@Override
	public void push(E obj) 
	{
		// first check if there's enough space
		if( topOfStack == this.theData.length - 1)
		{
			System.out.println("stack overflow");
			return;
		}	
		this.theData[++topOfStack] = obj;// to add to next index
		return;
	}
	@Override
	public E pop() 	// deletes element from stack
	{				// first check if there's something to delete
		if(isEmpty())
			System.out.println("stack underflow");
		return this.theData[topOfStack--];		
	}
	
	@Override
	public E peek()
	{
		if(isEmpty())
			System.out.println("stack empty");
		return this.theData[topOfStack];
	}
	
	@Override
	public boolean isEmpty()
	{
		return (topOfStack == -1);		// boolean
	}
	
	public String toString()
	{
		String s = "Stack: ";
		for(int i = 0; i <= topOfStack; i++)	// iterate over elements and add to the string
			s += " " + this.theData[i] +  " | ";
		return s;
	}
}
