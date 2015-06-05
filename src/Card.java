import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Card Class, implements a card
public class Card {

	//Rank
	private int rank;
	
	//Suit
	private Suit suit;
	
	//Image
	private BufferedImage img;
	
	//Flipped
	private boolean flip;
	
	//Ranks
	private final String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	
	//Suits
	private final String[] suits = {"Diamonds", "Clubs", "Hearts", "Spades"};
	
	//Constructor
	public Card(int rank, Suit suit)
	{
		this.rank = rank;
		this.suit = suit;
		this.flip = false;
		
		System.out.println(""+this.rank+this.suit);
		
		loadImage();
	}
	
	//Constructor for integer suit
	public Card(int rank, int suit)
	{
		this.rank = rank;
		this.suit = Suit.SPADES;
		this.suit = this.suit.getSuit(suit);
		this.flip = false;
		
		System.out.println(""+this.rank+this.suit);
		
		loadImage();
	}
	
	//Getter for rank
	public int getRank()
	{
		return rank;
	}
	
	//Getter for suit
	public Suit getSuit()
	{
		return suit;
	}
	
	//Getter for ranks
	public String[] getRanks()
	{
		return ranks;
	}
	
	//Getter for suits
	public String[] getSuits()
	{
		return suits;
	}
	
	//Getter for image
	public BufferedImage getImage()
	{
		return img;
	}
	
	//Getter for flip
	public boolean getFlip()
	{
		return flip;
	}
	
	//Flip the card
	public void flip()
	{
		this.flip = !this.flip;
		
		//If card is flipped, load back image
		if(flip)
		{
			try
			{
				img = (BufferedImage)ImageIO.read(new File("src/cards/b.gif"));
			}
			catch(IOException e)
			{
				System.out.println("Invalid "+ "src/cards/b.gif");
			}
		}
		
		else
		{
			loadImage();
		}
	}
	
	//Draws card at x, y
	public void show(Graphics g, int x, int y)
	{
		g.drawImage(img, x, y, null);
	}
	
	//toString
	public String toString()
	{
		return rank + suit.toString();
	}
	
	//niceToString
	public String niceToString()
	{
		return rankToString() + " of " + suitToString();
	}
	
	//RankToString
	public String rankToString()
	{
		switch(rank)
		{
		case 1:
			return "Ace";
		case 2:
			return "Two";
		case 3:
			return "Three";
		case 4:
			return "Four";
		case 5:
			return "Five";
		case 6:
			return "Six";
		case 7:
			return "Seven";
		case 8:
			return "Eight";
		case 9:
			return "Nine";
		case 10:
			return "Ten";
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		}
		
		return null;
	}
	
	//Suit toString
	public String suitToString()
	{
		return suit.niceToString();
	}
	
	//Load image
	public void loadImage()
	{
		try
		{
			img = (BufferedImage)ImageIO.read(new File("src/cards/" + rank + suit.toString() + ".gif"));
		}
		catch(IOException e)
		{
			System.out.println("Invalid " + "src/cards/" + rank + suit.toString() + ".gif");
		}
	}
	
	//Tests for equals
	public boolean equals(Card c)
	{
		return c != null && getRank() == c.getRank() && getSuit() == c.getSuit();
	}
	
	//CompareTo, returns -1 if less, 1 if larger, and 0 if equal
	public int compareTo(Card c)
	{
		//If two cards are equal
		if(equals(c))
		{
			return 0;
		}
		
		//If rank is lower
		if(c.getRank() < getRank())
		{
			return -1;
		}
		
		//If rank is higher
		else if(c.getRank() > getRank())
		{
			return 1;
		}
		
		//If rank is equal, compare suit
		else
		{
			//If suit is less
			if(c.getSuit().getNumber() < getSuit().getNumber())
				return -1;
			//If suit is more
			else if(c.getSuit().getNumber() > getSuit().getNumber())
				return 1;
			
			//Otherwise equal
			else
				return 0;
		}
	}
}
