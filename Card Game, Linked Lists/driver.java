import java.util.Random;
import java.util.Scanner;

public class driver 
{	
	static Random random = new Random();
	
	public static void main(String[] args)
	{
		System.out.println("\ncard format: (suit, value)\n");
		linkedListClass<cards> deck = new linkedListClass<cards>();
		linkedListClass<cards> Conchita = new linkedListClass<cards>();
		linkedListClass<cards> Pedro = new linkedListClass<cards>();
		deckBuilder(deck);	
		cards shuffledDeck[] = new cards[deck.size()];
		shuffler( deck, shuffledDeck );					// putting linkedList cards into regular array to shuffle
		deal( Conchita, Pedro, shuffledDeck );				// players obtain hands
		turnChooser(Conchita, Pedro);					// begins game
	}
	
	public static void deckBuilder( linkedListClass deck )
	{
		int index = 0;
		
		for(int suit = 1; suit <= 4; suit++)
		{
			for(int value = 1; value <= 13; value++)
			{
				cards card = new cards(suit, value);
				deck.add( index++, card );						
			}
		}
	}
	
	public static void shuffler( linkedListClass deck, cards shuffledDeck[] )
	{
		for(int a = 1; a < deck.size() + 1; a++)
		{
			shuffledDeck[a - 1] = new cards();
			shuffledDeck[a - 1].setSuit( ( (cards) deck.get(a) ).getSuit() );
			shuffledDeck[a - 1].setValue( ( (cards) deck.get( a ) ).getValue() );
		}
		
		int seedNumbers[] = new int[52];
		cards temp[] = new cards[52];
		int selected = -1, index = 0;
		
		for (int i = 0; i < 52; i++)
			seedNumbers[i] = i + 1;
		
		for (int i = 0; i < 52; i++) 
		{
			selected = random.nextInt(52);						
			if(seedNumbers[ selected ] != 0)	
			{	
				temp[ index++ ] = shuffledDeck[ selected ];	
				seedNumbers[selected] = 0;
			}
			else 
				i--;
		}
		
		for(int a = 0; a < temp.length; a++)
			shuffledDeck[a] = temp[a];
	}
	
	public static void deal( linkedListClass Conchita, linkedListClass Pedro, cards shuffledDeck[])
	{	
		for(int a = 0; a < 26; a++)
			Conchita.add(a, shuffledDeck[a]);
		int p2 = 0;
		for(int b = 26; b < shuffledDeck.length; b++)
			Pedro.add(p2++, shuffledDeck[b]);
	}
	
	public static void turnChooser(linkedListClass Conchita, linkedListClass Pedro) 
	{
		int first = random.nextInt(10);					// choose first player randomly
		
		if(first > 4)
		{
			System.out.println("Conchita goes first");
			game(Conchita, Pedro);
		}
		else
		{
			System.out.println("Pedro goes first");
			game(Pedro, Conchita);
		}
	}
	
	public static void game(linkedListClass first, linkedListClass second)
	{
		linkedListClass<cards> table = new linkedListClass<cards>();
		int round = 0;
		
		while(round++ < 10)
		{
			System.out.println("\nRound " + round);
			System.out.println("first hand " + first);
			System.out.println("second hand " + second);
			
			table.add( 0, (cards)first.remove(0) );											// first player draws
			System.out.println("first player draws " + table.get(1));
			if(table.size() > 1 && ( table.get(1).getSuit() == table.get(2).getSuit() ) )	// comparing top card with previous
			{
				System.out.println("second player lost round");
				while(table.size() > 0)
					first.add(0, table.remove(0));
			}
			
			table.add( 0, (cards)second.remove(0) );										// second player draws
			System.out.println("second player draws " + table.get(1));
			if(table.size() > 1 && ( table.get(1).getSuit() == table.get(2).getSuit() ) )	// comparing top card with previous
			{
				System.out.println("first player lost round");
				while(table.size() > 0)
					second.add(0, table.remove(0));	
			}
			
			System.out.println("cards on the table " + table);
		}
		
		System.out.println("\n\nfirst hand " + first);			// find winner
		System.out.println("second hand " + second);
		if(first.size() < second.size())
			System.out.println("\nfirst player wins");
		else
			System.out.println("\nsecond player wins");
	}
}
