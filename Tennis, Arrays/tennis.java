import java.util.Random;

public class tennis 
{
	static Random random = new Random();
	
	public static void main(String[] args) 
	{
		String players[][] = {
					{"Chonchito", "Gordis", "Panchito", "Chata", "Lupis", "Chuy", "Chipis", "Chucho"},
					{"", "", "", "", "", "", "", ""}	};
		assigningRanks(players);
		printer(players, "Player's Ranks: ");
		bubbleSort(players);
		printer(players, "Sorted Ranks: ");
		quarterFinals(players); 									// begins tournament
	}
	
	static void assigningRanks( String players[][]) 							// only assign ranks where ranksTaken[x] == false
	{	
		boolean ranksTaken[] = { false, false, false, false, false, false, false, false, false };	// rank corresponding to index = true if taken, ignoring [0], no rank 0
		int number = 0;											// stores potential number
		
		for(int a = 0; a < 8; a++)
		{
			number = random.nextInt(8) + 1;								// argument is exclusive.  interval = [1 - 8]
			
			if(!ranksTaken[number])
			{
				players[1][a] = Integer.toString(number);
				ranksTaken[number] = true;
			}
			else
				--a;
		}
	}
	
	static void bubbleSort( String players[][] )
	{
		String temporaryRank = "";
		String temporaryName = "";
		
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 7; y++)
			{
				if(Integer.parseInt(players[1][y+1]) < Integer.parseInt(players[1][y]))
				{
					temporaryRank = players[1][y];	// rank
					temporaryName = players[0][y];	// name
					
					players[1][y] = players[1][y+1];	// rank
					players[0][y] = players[0][y+1];	// name
					
					players[1][y+1] = temporaryRank;	// rank
					players[0][y+1] = temporaryName;	// name
				}
			}
		}
	}
	
	static void printer(String players[][], String message)
	{
		System.out.printf("\n%s\n", message);
		for(int a = 0; a < 8; a++)											
			System.out.printf("%s %s\n", players[0][a], players[1][a]);
	}
	
	static void quarterFinals(String players[][])
	{
		String quarterWinners[][] = new String[4][4]; // holds winners
		
		System.out.println("\nOrder of Matches (Quarter-Final): ");
		for(int x = 0, y = 7; (x < 4) && (y > 3); x++, y--)
			System.out.println("[" + players[1][x] + "] " + "[" + players[0][x] + "]\tvs.\t" + "[" + players[1][y] + "] " + players[0][y]);
		
		System.out.println("\nResult of Matches (Quarter-Final): ");
		for(int x = 0, y = 7, z = 0; (x < 4) && (y > 3); x++, y--, z++) // x for ascending, y for descending, z to add winners
		{
			int outcome = random.nextInt(10);
			if(outcome > 5) 
			{
				quarterWinners[0][z] = players[0][x];
				quarterWinners[1][z] = players[1][x];
				System.out.println("Winner: [" + quarterWinners[1][z] + "] " + players[0][z]);
			}
			else 
			{
				quarterWinners[0][z] = players[0][y];
				quarterWinners[1][z] = players[1][y];
				System.out.println("Winner: [" + quarterWinners[1][z] + "] " + quarterWinners[0][z]);
			}
		}
		semiFinals(quarterWinners);
	}
	
	static void semiFinals(String players[][])
	{
		String semiWinners[][] = new String[2][2]; // holds winners
		
		System.out.println("\n\nOrder of Matches (Semi-Final): ");
		for(int x = 0, y = 3; (x < 2) && (y > 1); x++, y--)
			System.out.println("[" + players[1][x] + "] " + "[" + players[0][x] + "]\tvs.\t" + "[" + players[1][y] + "] " + players[0][y]);
		
		System.out.println("\nResult of Matches (Semi-Final): ");
		for(int x = 0, y = 3, z = 0; (x < 2) && (y > 1); x++, y--, z++) // x for ascending, y for descending, z to add winners
		{
			int outcome = random.nextInt(10);
			if(outcome > 5) 
			{
				semiWinners[0][z] = players[0][x];
				semiWinners[1][z] = players[1][x];
				System.out.println("Winner: [" + semiWinners[1][z] + "] " + players[0][z]);
			}
			else 
			{
				semiWinners[0][z] = players[0][y];
				semiWinners[1][z] = players[1][y];
				System.out.println("Winner: [" + semiWinners[1][z] + "] " + semiWinners[0][z]);
			}
		}
		finals(semiWinners);
	}
	
	static void finals(String players[][])
	{
		String winners[][] = new String[2][2]; // holds winners
		
		System.out.println("\n\nOrder of Matches (Final): ");
		System.out.println("[" + players[1][0] + "] " + "[" + players[0][0] + "]\tvs.\t" + "[" + players[1][1] + "] " + players[0][1]);
		
		System.out.println("\nResult of Matches (Final): ");
		int outcome = random.nextInt(10);
		
		if(outcome > 5) 
		{
			winners[0][0] = players[0][0]; // winner
			winners[1][0] = players[1][0];
			winners[0][1] = players[0][1]; // runner up
			winners[1][1] = players[1][1];
			System.out.println("Winner: [" + winners[1][0] + "] " + players[0][0]);
		}
		else 
		{
			winners[0][0] = players[0][1]; // winner
			winners[1][0] = players[1][1];
			winners[0][1] = players[0][0]; // runner up
			winners[1][1] = players[1][0];
			System.out.println("Winner: [" + winners[1][0] + "] " + winners[0][0]);
		}
		
		System.out.println("\n\nWinner: [" + winners[1][0] + "] " + winners[0][0] + "\nRunner up: [" + winners[1][1] + "] " + winners[0][1]);
	}
}
