import java.awt.Graphics;

import javax.swing.JPanel;


public class DeckPanel extends JPanel{

	//Offset per card for paint
	private final int offset = 25;
		
	//Cards per row
	private final int rows = 26;
	
	//Starting x
	private final int start = 150;
		
	//Height of card
	private int height = 100;
	
	//Deck
	private Deck d;
	
	//Constructor
	public DeckPanel(Deck d)
	{
		this.d = d;
		
		//Update height
		if(d.getLength() > 0)
			height = d.getCards().get(0).getImage().getHeight();
		
		setLayout(null);
	}
	
	//Overriding paint
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//Starting point
		int x = start, y = 0;
		
		//Index
		int index = 0;
		
		//For each row
		for(int i = 0; i < d.getCards().size() / rows; i++)
		{
			//For each column
			for(int j = 0; j < rows; j++)
			{
				//Draw image
				System.out.println("Printing: " + index + " " + d.getCards().get(index).niceToString());
				d.getCards().get(index).show(g, x, y);
				
				//Increment x
				x += offset;
				
				//Increment index
				index++;
			}
			
			//Increment y, reset x
			y += height;
			x = start;
		}
		
		//Leftovers
		for(int i = ((d.getCards().size() / rows) * rows); i < d.getCards().size(); i++)
		{
			//Draw image
			System.out.println("Printing: " + index + " " + d.getCards().get(index).niceToString());
			d.getCards().get(index).show(g, x, y);
			
			//Increment x
			x += offset;
			
			//Increment index
			index++;
		}
	}
}
