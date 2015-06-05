
public enum Suit {
DIAMONDS(1), CLUBS(2), HEARTS(3), SPADES(4);

	//Number
	private int number;
	
	//Constructor
	Suit(int number)
	{
		this.number = number;
	}
	
	//toString
	public String toString()
	{
		switch(this)
		{
		case CLUBS:
			return "C";
		case DIAMONDS:
			return "D";
		case HEARTS:
			return "H";
		case SPADES:
			return "S";
		}
		
		return null;
	}
	
	//niceToString
	public String niceToString()
	{
		switch(this)
		{
		case CLUBS:
			return "Clubs";
		case DIAMONDS:
			return "Diamonds";
		case HEARTS:
			return "Hearts";
		case SPADES:
			return "Spades";
		}
		
		return null;
	}
	
	//GetNumber
	public int getNumber()
	{
		return number;
	}
	
	//GetSuit
	public Suit getSuit(int n)
	{
		switch(n)
		{
		case 1:
			return Suit.DIAMONDS;
		case 2:
			return Suit.CLUBS;
		case 3:
			return Suit.HEARTS;
		case 4:
			return Suit.SPADES;
		}
		
		return null;
	}
}
