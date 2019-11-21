
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

public class Menu 
{
	static Scanner lineScanner = new Scanner(System.in), intScanner = new Scanner(System.in);
	static String clientID, password, firstName, lastName;					// client
	static ArrayListClass<Client> clients = new ArrayListClass<Client>();	// stores multiple clients
	static int option;
	
	public static void main(String[] args) throws IOException 
	{														
		while( start( readFromFile() ) );
		System.out.println("program terminated");
	}
	
	public static boolean start(boolean fileExists) throws IOException
	{
		if(!fileExists)
			register();
		else
		{
			System.out.println("enter number from the following options\n0. Exit\n1. register\n2. login ");
			option = intScanner.nextInt();
			if(option == 0)
				return false;									// exit program		
			else if(option == 1)								
				register();										// register new user account
			else												
				menu(login());								// log in to existing account once user index is found	
		}
		return true;										// displays this menu again
	}
	
	public static void menu(int clientIndex) throws IOException
	{
		do
		{
			System.out.println("\n3. add food\n4. display food log\n5. display food entered\n6. display calories");
			System.out.println("7. display food from min to max calories\n8. display food most consumed\n9. save and logout\n");
			option = intScanner.nextInt();
			if(option == 3)
				addFood(clientIndex);
			else if(option == 4)
				System.out.println( clients.get(clientIndex).getHistory() );
			else if(option == 5)
				clients.get(clientIndex).getDistinctFoods();
			else if(option == 6)
				System.out.println( "calories: " + clients.get(clientIndex).getTotalCalories() );
			else if(option == 7)
				clients.get(clientIndex).minMaxCalories();
			else if(option == 8)
				System.out.println(clients.get(clientIndex).mostConsumed());
		}
		while(option != 9);
		
		System.out.println("\nlogging out\n");
		saveToFile();
	}
	
	public static void register() throws IOException						// 1	check for unique id later	should be default option for first-use
	{	
		System.out.println("registering new client");
		
		do
		{
			System.out.println("enter client id: ");
			clientID = lineScanner.nextLine();
		}
		while(! checkUniqueID( clientID ) );
		
		System.out.println("enter first name: ");
		firstName = lineScanner.nextLine();
		System.out.println("enter last name: ");
		lastName = lineScanner.nextLine();
		
		int tryCounter = 0;
		Random random = new Random(7);
		do
		{
			System.out.println("enter password 8 characters long: ");
			password = lineScanner.nextLine();
			
			if(tryCounter++ > 1)
			{
				password = Integer.toString( random.nextInt(99999999) + 10000000 );
				System.out.println("auto-generated password: " + password);
			}
		}
		while(password.length() < 8);											// if fail 2x, auto-gen alphanumeric pw based on last name
		clients.add(new Client(clientID, firstName, lastName, password));
		menu(clients.getSize() - 1);											// new client should be the last in the list
	}
	
	public static boolean checkUniqueID( String clientID )
	{
		for(int a = 0; a < clients.getSize(); a++)
		{
			if(clientID.equals( clients.get(a).getClientID() ) )
			{
				System.out.println("client id taken");
				return false;
			}
		}
		return true;
	}
	
	public static int login()												// 2
	{
		System.out.println("Enter client id: ");
		clientID = lineScanner.nextLine();
		
		for(int a = 0; a < clients.getSize(); a++)							// find client
		{
			if( clientID.equals(clients.get(a).getClientID() ) )			// when client is found
			{
				do
				{
					System.out.println("Enter password: ");					// ask for password
					password = lineScanner.nextLine();
				}
				while( !password.equals(clients.get(a).getPassword() ) );
				return a;													// use this client in list
			}
		}
		
		System.out.println("client not found");
		return -1;
	}
	
	public static void addFood(int clientIndex)									// 3
	{	
		do
		{
			System.out.println("enter name of food (one word): ");
			String name = lineScanner.nextLine();
			System.out.println("enter quantity: ");
			int quantity = intScanner.nextInt();
			System.out.println("enter calories: ");
			int calories = intScanner.nextInt();
			System.out.println("enter meal type: ");
			String mealType = lineScanner.nextLine();
			
			clients.get( clientIndex ).addFood( name, quantity, calories, mealType );
			System.out.println("enter 0 to add another meal or 1 to return to menu ");
		}
		while(intScanner.nextInt() != 1);
		
	}
	
	public static void displayFood(int clientIndex)								// 4
	{
		System.out.println( clients.get(clientIndex).getHistory() );
	}
	
	public static void saveToFile() throws IOException
	{
		PrintWriter printWriter = new PrintWriter( new FileWriter("foodRecord.txt") );
		for(int a = 0; a < clients.getSize(); a++)
			printWriter.println("& " + clients.get(a) + "\n" + clients.get(a).getHistory());			// & delimiter to begin reading new client
		printWriter.close();
	}
	
	public static boolean readFromFile() throws FileNotFoundException
	{
		String clientID = "", firstName = "", lastName = "", password = "", name = "", mealType = "";
		int quantity = 0, calories = 0;
		
		try 
		{
			Scanner reader = new Scanner( new File( "foodRecord.txt" ) );			
			reader.next();																					// ignores first ampersand
			while(reader.hasNext())
			{
				clientID = reader.next();
				firstName = reader.next();
				lastName = reader.next();
				password = reader.next();
				clients.add(new Client(clientID, firstName, lastName, password));							// put clients back in arraylist
				
				while(reader.hasNext())
				{
					name = reader.next();
					if( name.equals("&") )
						break;																				// stop reading food, read next client
					quantity = reader.nextInt();
					calories = reader.nextInt();
					mealType = reader.next();
					clients.get(clients.getSize() - 1).addFood(name, quantity, calories, mealType);			// put food back in stack
				}
			}	
		}
		catch(FileNotFoundException e)
		{
			return false;
		}
		return true;
		
	}
}
