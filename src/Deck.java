import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

//Deck class, stores card objects
public class Deck implements ActionListener{

	//Cards array
	private ArrayList<Card> cards;
	
	//Default Constructor
	public Deck()
	{
		cards = new ArrayList<Card>();
		
		//For each card
		for(int i = 1; i <= 4; i++)
		{
			for(int j = 1; j <= 13; j++)
			{
				cards.add(new Card(j, i));
			}
		}
	}
	
	//Cards Constructor
	public Deck(int n)
	{
		cards = new ArrayList<Card>();
		
		//Index
		int index = 0;
		
		//While index is still valid
		while(index < n)
		{
			//For each card
			for(int i = 1; i <= 4 && index < n; i++)
			{
				for(int j = 1; j <= 13 && index < n; j++)
				{
					cards.add(new Card(i, j));
					index++;
				}
			}
		}
	}
	
	//Getter for cards
	public ArrayList<Card> getCards()
	{
		return cards;
	}
	
	//Getter for size
	public int getLength()
	{
		return cards.size();
	}
	
	//Getter for card
	public Card getCard(int n)
	{
		//Check for valid
		if(n<cards.size())
			return cards.get(n);
		
		//Null otherwise
		return null;
	}
	
	//Displays the deck
	public void show()
	{
		//Text
		for(int i = 0; i < cards.size(); i++)
		{
			System.out.println(cards.get(i).niceToString());
		}
		
		//Redraw frame
		Main.update();
	}
	
	//AddCard, adds a card at the end
	public void addCard(Card c)
	{
		cards.add(c);
		
		System.out.println(c + " has been added at " + cards.size());
	}
	
	//AddCard, adds a card at a given location
	public void addCard(Card c, int n)
	{
		cards.add(n, c);
		
		System.out.println(c + " has been added at " + n);
	}
	
	//RemoveCard, removes the card, returning the card or null
	public Card removeCard(Card c)
	{
		//Go through arraylist
		for(int i = 0; i < cards.size(); i++)
		{
			//If card found
			if(cards.get(i).equals(c))
			{
				//Remove card
				Card removed = cards.get(i);
				
				cards.remove(i);
				
				return removed;
			}
		}
		
		//If card not found
		return null;
	}
	
	//Removes a card at a given location, returning the card or null
	public Card removeCard(int n)
	{
		//If index is within bounds, remove card
		if(n < cards.size())
		{
			Card removed = cards.get(n);
			
			cards.remove(n);
			
			return removed;
		}
		
		return null;
	}
	
	//Search method, returns locations of occurrences of c
	public int[] search(Card c)
	{
		int[] locations = new int[0];
		
		//Go through array
		for(int i = 0; i < cards.size(); i++)
		{
			//If c is equal
			if(c.equals(cards.get(i)))
			{
				//Create temp
				int[] temp = new int[locations.length + 1];
				
				//Assign to temp
				for(int j = 0; j < locations.length; j++)
				{
					temp[j] = locations[j];
				}
				
				//Assign i
				temp[locations.length] = i;
				
				//Resize locations
				locations = temp;
			}
		}
		
		return locations;
	}
	
	//FlipCards, flips cards given an array of indexes
	public void flipCards(int[] indexes)
	{
		//Flips each card
		for(int i = 0; i < indexes.length; i++)
		{
			cards.get(indexes[i]).flip();
		}
	}
	
	//Shuffle, shuffles the deck using Fisher-Yates Shuffle
	public void shuffle()
	{
		for(int i = cards.size() - 1; i >= 0; i--)
		{
			int temp = (int)(Math.random() * (i + 1));
			
			Card temp1 = cards.get(temp);
			cards.set(temp, cards.get(i));
			cards.set(i, temp1);
		}
	}
	
	//Sort, calls quicksort
	public void sort()
	{
		quicksort(0, cards.size()-1);
	}
	
	//SlowSort, calls insertionsort
	public void slowSort()
	{
		insertionSort();
	}
	
	//Quicksort, sorts via quicksort
	private void quicksort(int left, int right)
	{
		//As long as the list has more than 1 element
		if(left < right)
		{
			//Using middle as pivot
			int pivotNewIndex = partition(left, right, left+(right-left)/2);
			
			//Sort everything smaller
			quicksort(left, pivotNewIndex - 1);
			
			//Sort everything larger
			quicksort(pivotNewIndex + 1, right);
		}
	}
	
	//Partition function for quicksort
	private int partition(int left, int right, int pivotIndex)
	{
		//Get value of pivot
		Card pivotValue = cards.get(pivotIndex);
		
		//Swap array[pivotIndex] and array[right]
		Card temp = cards.get(pivotIndex);
		cards.set(pivotIndex, cards.get(right));
		cards.set(right, temp);
		
		//Store index
		int storeIndex = left;
		
		//From left to right
		for(int i = left; i < right; i++)
		{
			//Compare array[i] with pivot value
			if(cards.get(i).compareTo(pivotValue) <= 0)
			{
				//Swap with store index
				temp = cards.get(i);
				cards.set(i, cards.get(storeIndex));
				cards.set(storeIndex, temp);
				
				//Increment storeindex
				storeIndex++;
			}
		}
		
		//Swap store index and right
		temp = cards.get(storeIndex);
		cards.set(storeIndex, cards.get(right));
		cards.set(right, temp);
		
		//Return store index
		return storeIndex;
	}
	
	//Insertionsort, sorts via insertion sort
	private void insertionSort()
	{
		//Hole and value
		Card value;
		int hole;

		//Go through array
		for(int i = 0; i < cards.size(); i++)
		{
			//Update value and whole
			value = cards.get(i);
			hole = i;

			//Shift leftwards until value is bigger than preceding
			while(hole > 0 && value.compareTo(cards.get(hole-1)) < 0)
			{
				cards.set(hole, cards.get(hole-1));
				hole--;
			}

			//Put into hole
			cards.set(hole, value);
		}
	}

	//ActionListener overriding
	public void actionPerformed(ActionEvent e)
	{
		int rank, suit, size;
		
		switch(e.getActionCommand())
		{
		//Generate
		case "generate":
			
			//If text isn't null
			if(Main.generate.getText() != null)
			{
				size = Integer.parseInt(Main.generate.getText());
				
				//If size is valid, assign new deck
				if(size > 0)
				{
					cards = new ArrayList<Card>();
					
					//Index
					int index = 0;
					
					//While index is still valid
					while(index < size)
					{
						//For each card
						for(int i = 1; i <= 4 && index < size; i++)
						{
							for(int j = 1; j <= 13 && index < size; j++)
							{
								cards.add(new Card(j, i));
								index++;
							}
						}
					}
				}
			}
			
			//Update frame
			Main.update();
			
		break;
		
		//Shuffles
		case "shuffle":
			
			shuffle();
			
			//Update frame
			Main.update();
			
			break;
			
		//Sorts
		case "sort":
			
			sort();
			
			//Update frame
			Main.update();
			
			break;
			
		case "slowsort":
			
			insertionSort();
			
			//Update frame
			Main.update();
			
			break;
			
		//Deals
		case "deal":
			
			removeCard((int)Main.location.getSelectedItem()-1);
			
			//Update frame
			Main.update();
			
			break;
			
		//Adds
		case "add":
			
			//Get card
			rank = Main.rank.getSelectedIndex() + 1;
			suit = Main.suit.getSelectedIndex() + 1;
			
			addCard(new Card(rank, suit));
			
			//Update frame
			Main.update();
			
			break;
			
		//Removes
		case "remove":
			
			//Get card
			rank = Main.rank.getSelectedIndex() + 1;
			suit = Main.suit.getSelectedIndex() + 1;
			
			removeCard(new Card(rank, suit));
			
			//Update frame
			Main.update();
			
			break;
			
		//Finds and flips
		case "find":
			
			//Get card
			rank = Main.rank.getSelectedIndex() + 1;
			suit = Main.suit.getSelectedIndex() + 1;
			
			//Search for cards
			flipCards(search(new Card(rank, suit)));
			
			//Set button to reset
			Main.findB.setText("Reset");
			Main.findB.setActionCommand("reset");
			Main.findB.setMnemonic(KeyEvent.VK_R);
			
			//Update frame
			Main.update();
			
			break;
			
		case "reset":
			//Get card
			rank = Main.rank.getSelectedIndex() + 1;
			suit = Main.suit.getSelectedIndex() + 1;
			
			//Unflip the cards
			flipCards(search(new Card(rank, suit)));
			
			//Set button to find
			Main.findB.setText("Find");
			Main.findB.setActionCommand("find");
			Main.findB.setMnemonic(KeyEvent.VK_F);
			
			//Update frame
			Main.update();
			
			break;
		}
	}
}
