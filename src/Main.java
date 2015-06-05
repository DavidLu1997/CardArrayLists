import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Main class, ties everything together
public class Main {

	//Deck
	private static Deck deck;
	
	//Frame
	static JFrame frame;
	
	//DeckPanel
	static DeckPanel deckPane;
	
	//ComboBoxes

	static JComboBox location;
	static JComboBox rank;
	static JComboBox suit;
	
	//JButtons
	static JButton generateB;
	static JButton shuffleB;
	static JButton sortB;
	static JButton slowSortB;
	static JButton dealB;
	static JButton addB;
	static JButton removeB;
	static JButton findB;
	
	//Textfields
	static JTextField generate; 
	
	//Creates GUI
	public static void createGUI()
	{
		//Initialize frame
		frame = new JFrame("Deck Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
				
		//Initialize pane using BoxLayout
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());
		
		//Initialize deckPane
		deckPane = new DeckPanel(deck);
		
		//Initialize buttons pane
		JPanel buttons = new JPanel();
		
		//Generate
		generateB = new JButton("Generate");
		generateB.setActionCommand("generate");
		generateB.setMnemonic(KeyEvent.VK_G);
		generateB.addActionListener(deck);
		buttons.add(generateB);
		
		//TextField
		generate = new JTextField("52", 3);
		generate.addActionListener(deck);
		buttons.add(generate);
		
		//Shuffle
		shuffleB = new JButton("Shuffle");
		shuffleB.setActionCommand("shuffle");
		shuffleB.setMnemonic(KeyEvent.VK_S);
		shuffleB.addActionListener(deck);
		buttons.add(shuffleB);
		
		//Sort
		sortB = new JButton("Sort");
		sortB.setActionCommand("sort");
		sortB.setMnemonic(KeyEvent.VK_O);
		sortB.addActionListener(deck);
		buttons.add(sortB);
		
		//SlowSort
		slowSortB = new JButton("Slow Sort");
		slowSortB.setActionCommand("slowsort");
		slowSortB.setMnemonic(KeyEvent.VK_L);
		slowSortB.addActionListener(deck);
		buttons.add(slowSortB);
		
		//Deal
		dealB = new JButton("Deal");
		dealB.setActionCommand("deal");
		dealB.setMnemonic(KeyEvent.VK_D);
		dealB.addActionListener(deck);
		buttons.add(dealB);
		
		//ComboBox for location
		location = new JComboBox();
		
		//If deck is empty, disable
		if(deck.getLength() == 0)
		{
			location.setEnabled(false);
		}
		
		//Else
		else
		{
			location.removeAllItems();
			
			for(int i = 1; i <= deck.getLength(); i++)
			{
				location.addItem(i);
			}
				
			location.setEnabled(true);
			location.setSelectedIndex(0);
			location.addActionListener(deck);
		}
		buttons.add(location);
		
		//Add
		addB = new JButton("Add");
		addB.setActionCommand("add");
		addB.setMnemonic(KeyEvent.VK_A);
		addB.addActionListener(deck);
		buttons.add(addB);
		
		//Remove
		removeB = new JButton("Remove");
		removeB.setActionCommand("remove");
		removeB.setMnemonic(KeyEvent.VK_R);
		removeB.addActionListener(deck);
		buttons.add(removeB);
		
		//Find
		findB = new JButton("Find");
		findB.setActionCommand("find");
		findB.setMnemonic(KeyEvent.VK_F);
		findB.addActionListener(deck);
		buttons.add(findB);
		
		//ComboBox for rank
		rank = new JComboBox(deck.getCard(0).getRanks());
		rank.setSelectedIndex(0);
		rank.addActionListener(deck);
		buttons.add(rank);
		
		//JLabel for "of"
		JLabel of = new JLabel("of");
		buttons.add(of);
		
		//ComboBox for suit
		suit = new JComboBox(deck.getCard(0).getSuits());
		suit.setSelectedIndex(0);
		suit.addActionListener(deck);
		buttons.add(suit);
		
		//Add to pane
		pane.add(buttons, BorderLayout.PAGE_START);
		pane.add(deckPane, BorderLayout.CENTER);
		
		//Initialize frame
		frame.setContentPane(pane);
		frame.pack();
		frame.setSize(1000, 1000);
		frame.setVisible(true);
		frame.setLocationRelativeTo (null); 
	}
	
	//Updates JFrame
	public static void update()
	{
		//Update deal
		
		//If deck is empty, disable
		if(deck.getLength() == 0)
		{
			location.setEnabled(false);
			dealB.setEnabled(false);
			removeB.setEnabled(false);
			findB.setEnabled(false);
			shuffleB.setEnabled(false);
			sortB.setEnabled(false);
			slowSortB.setEnabled(false);
		}
		
		//Else, update location if isn't on reset
		else if(!findB.getText().equals("Reset"))
		{
			location.removeAllItems();
			
			for(int i = 1; i <= deck.getLength(); i++)
			{
				location.addItem(i);
			}
			
			location.setSelectedIndex(0);
			location.addActionListener(deck);
			
			//Enable combo boxes
			rank.setEnabled(true);
			suit.setEnabled(true);
			location.setEnabled(true);
			
			//Enable buttons
			generateB.setEnabled(true);
			shuffleB.setEnabled(true);
			sortB.setEnabled(true);
			slowSortB.setEnabled(true);
			dealB.setEnabled(true);
			addB.setEnabled(true);
			removeB.setEnabled(true);
			findB.setEnabled(true);
			
			//Enable textfield
			generate.setEnabled(true);
		}
		
		//Else disable everything but findB
		else
		{
			//Disable combo boxes
			rank.setEnabled(false);
			suit.setEnabled(false);
			location.setEnabled(false);
			
			//Disable buttons
			generateB.setEnabled(false);
			shuffleB.setEnabled(false);
			sortB.setEnabled(false);
			slowSortB.setEnabled(false);
			dealB.setEnabled(false);
			addB.setEnabled(false);
			removeB.setEnabled(false);
			
			//Disable textfield
			generate.setEnabled(false);
		}
		
		//New DeckPanel
		deckPane = new DeckPanel(deck);
		
		//Redraw frame
		frame.validate();
		frame.repaint();
	}
	
	public static void main(String[] args)
	{
		deck = new Deck();
		
		createGUI();
	}
}
