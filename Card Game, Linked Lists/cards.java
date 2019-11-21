
public class cards 
{
	int value, suit;
	
	public cards()						
	{
		// 
	}
	
	public cards( int suit, int value )
	{
		this.suit = suit;
		this.value = value;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
	
	public int getSuit()
	{
		return suit;
	}
	
	public void setSuit(int suit)
	{
		this.suit = suit;
	}
	
	@Override
	public String toString()
	{
		String stringSuit = "";
		
		if(suit == 1)
			stringSuit = "h";
		else if(suit == 2)
			stringSuit = "s";
		else if(suit == 3)
			stringSuit = "c";
		else
			stringSuit = "d";
		
		return "( " + stringSuit + ", " + value + " )";
	}
}
