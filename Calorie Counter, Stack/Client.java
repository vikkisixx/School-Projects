import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Client 
{
	String clientID, password, firstName, lastName;										
	int totalCalories, numberOfFoods = 0;
	Stack foodRecord = new Stack(), sortedStack = new Stack();											// holds record of all food consumed
	ArrayList<Food> distinctFoods = new ArrayList<Food>();
	
	public Client(String clientID, String firstName, String lastName, String password)
	{
		this.clientID = clientID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	
	public String getClientID()
	{
		return clientID;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void addFood( String name, int quantity, int calories, String mealType )						// all foods to stack, distinct to arraylist
	{
		Food food = new Food( name, quantity, calories, mealType );
		foodRecord.push( food );
		sortedStack.push( food );																		// sortedStack also has contents of foodRecord
		totalCalories += calories;
		
		numberOfFoods++;																				
		boolean distinct = true;
		
		if( distinctFoods.size() < 1 )																	// if its empty, add something
			distinctFoods.add( food );
		else																							// if it has something
		{
			for(int a = 0; a < distinctFoods.size(); a++)												// if new food is already in list, dont add it
			{
				if( distinctFoods.get(a).getName().equals(food.getName()) )													
					distinct = false;													
			}
			if(distinct)																				// if new food isnt on the list, add it
				distinctFoods.add( food );	
		}
	}
	
	public String getHistory()																			// using 2 stacks to display info
	{	
		Stack tempStack = new Stack();
		String foodString = "";
		
		while( !foodRecord.isEmpty() )
		{
			foodString += (Food) foodRecord.peek();						// need to pop to display complete history. one meal per line
			tempStack.push( foodRecord.pop() );
		}
		
		while( !tempStack.isEmpty() )
			foodRecord.push( tempStack.pop() );							// putting foods back in
		
		return foodString;
	}
	
	public void getDistinctFoods()
	{
		for(int a = 0; a < distinctFoods.size(); a++ )
			System.out.printf( "%s\n", distinctFoods.get( a ).getName() );
	}
	
	public int getTotalCalories()
	{
		return totalCalories;
	}
	
	public void minMaxCalories()															
	{
		Stack tempStack = new Stack();										// copying a stack copies the address
		Food temp = (Food) sortedStack.pop();								// initial step		// sortedStack is good at this point
		
		while( ( !sortedStack.isEmpty() ) || temp != null )					// sorting begins
		{	
			if( tempStack.isEmpty() )
			{
				tempStack.push( temp );
				temp = null;
			}
			else
			{
				if(temp == null)
					temp = (Food) sortedStack.pop();
				if( temp.getCalories() > ( (Food) tempStack.peek() ).getCalories() )
					sortedStack.push( ( tempStack.pop() ) );
				else
				{
					tempStack.push( temp );
					temp = null;
				}
			}	
		}
		
		while( !tempStack.isEmpty() )																	// printing
		{
			System.out.print( tempStack.peek() );
			sortedStack.push( tempStack.pop() );
		}
	}
	
	public String mostConsumed()
	{
		int occurances[] = new int[numberOfFoods];														// records occurrances of foods
		Food foods[] = new Food[numberOfFoods];
		int index = 0;
		
		while(!foodRecord.isEmpty())																	// put everything in an array
			foods[index++] = (Food) foodRecord.pop();
		
		for(int a = 0; a < foods.length; a++)															// counting array
		{
			 for(int b = 0; b < foods.length; b++)														// counts occurances of a food
				 if(foods[a].getName().equals(foods[b].getName()))
					 occurances[a]++;
		}
		
		int maxValue = 0, maxIndex = 0;
		for(int a = 0; a < occurances.length; a++)
		{
			if(maxValue < occurances[a])
			{
				maxValue = occurances[a];
				maxIndex = a;
			}
		}
		
		
		
		return foods[maxIndex].getName();
	}
	
	@Override
	public String toString()
	{
		return clientID + " " + firstName + " " + lastName + " " + password;
	}
}
