
package gui;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import ndevlin_blackjack.*;


/**
 * @author Nathan
 */
public class Gui extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// Height and width of window
	int WIDTH = 1280;
	int HEIGHT = 720;
	
	// Colors of various elements
	Color backgroundColor = new Color(40, 120, 20);
	Color colorButton = new Color(250, 250, 250);
	
	
	// Buttons
	JButton hitButton = new JButton();
	JButton stayButton = new JButton();
	JButton yesButton = new JButton();
	JButton noButton = new JButton();
	
	
	// Card grid positioning and Dimensions
	// Grid will be the area that the cards are displayed...
	int gridX = 50;
	int gridY = 50;
	int gridW = 900;
	int gridH = 400;
	
	// totals, hit/stay grid positions/dimensions
	int hsX = gridX + gridW + 50;
	int hsY = gridY;
	int hsW = 230;
	int hsH = 400;
	
	// "play again?" question grid
	int paX = hsX;
	int paY = hsY + hsH + 50;
	int paW = hsW;
	int paH = 200;
	
	// ArrayList that will hold all of the cards in the deck, player's cards, and dealer's cards
	ArrayList<Card> all_cards = new ArrayList<Card>();
	ArrayList<Card> player_cards = new ArrayList<Card>();
	ArrayList<Card> dealer_cards = new ArrayList<Card>();

	int cardSpacing = 10;
	// We want to have space for six cards across in our grid
	
	int cardEdgeSoften = 10;
	
	int cardTotalWidth = gridW/6;
	// ...and space for two cards in "height" on the playing grid
	int cardTotalHeight = gridH/2;
	int cardActualWidth = cardTotalWidth - 2*cardSpacing;
	int cardActualHeight = cardTotalHeight- 2*cardSpacing;
	
	// Fonts
	Font buttonFont = new Font("Times New Roman", Font.PLAIN, 30);
	Font cardFont = new Font("Times New Roman", Font.BOLD, 35);
	
	
	
	public Gui()
	{
		this.setSize(WIDTH+6, HEIGHT+29);
		this.setTitle("BlackJack ");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Board board = new Board();
		this.setContentPane(board);
		this.setLayout(null);
		
		
		// Hit button
		ActionHit aHit = new ActionHit();
		hitButton.addActionListener(aHit);
		
		// Set the location/color/font/text for the hit button
		hitButton.setBounds(hsX+55, hsY+40, 120, 80);
		hitButton.setBackground(Color.yellow);
		hitButton.setFont(buttonFont);
		hitButton.setText("HIT");
		
	 
		board.add(hitButton);
		
		
		
		// Stay button
		ActionStay aStay = new ActionStay();
		stayButton.addActionListener(aStay);
		// Settings for Stay button:
		stayButton.setBounds(hsX+55, hsY+280, 120, 80);
		stayButton.setBackground(Color.yellow);
		stayButton.setFont(buttonFont);
		stayButton.setText("STAY");
		
		board.add(stayButton);
		
		
		
		// Yes Button (to "play again?")
		ActionYes aYes = new ActionYes();
		yesButton.addActionListener(aYes);
		
		yesButton.setBounds(paX+10, paY+110, 100, 80);
		yesButton.setBackground(Color.yellow);
		yesButton.setFont(buttonFont);
		yesButton.setText("YES");
		
		board.add(yesButton);
		
		
		// No Button (to "play again?")
		ActionNo aNo = new ActionNo();
		noButton.addActionListener(aNo);
		
		noButton.setBounds(paX+120, paY+110, 100, 80);
		noButton.setBackground(Color.yellow);
		noButton.setFont(buttonFont);
		noButton.setText("NO");
		
		board.add(noButton);
		
		// Using a nested for-loop to add the suit and the value
			// for each of the cards in the ArrayList all_cards
		String suit = null;
		for(int k = 0; k < 4; k++) {
			if (k ==0) {
				suit = "Hearts";
			} else if (k == 1) {
				suit = "Diamonds";
			} else if (k == 2) {
				suit = "Spades";
			} else {
				suit = "Clubs";
			}
			for (int j = 2; j < 15; j++) {
				all_cards.add(new Card(j, suit));
			}
			
		}
		
	}
	
	
	public class Board extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		 
		public void paintComponent(Graphics graphic)
		{
			// Got background color to work
			graphic.setColor(backgroundColor);
			graphic.fillRect(0, 0, 1280, 720);
			
			// Temporary Grid Painting
			graphic.setColor(Color.black);
			graphic.drawRect(gridX, gridY, gridW, gridH);
			
			// Temporary Log Borders Painting
			graphic.drawRect(gridX, gridY+ gridH + 50, gridW, gridH);
			
			// temp totals, hit/stay buttons grid, and messages
			graphic.drawRect(hsX,  hsY,  hsW,  hsH);
			
			// temp "play again" grid
			graphic.drawRect(paX, paY, paW, paH);
			
			
			// make a for-loop to draw the grid spaces where each card will be (both our cards and dealer's cards)
			// we'll say six cards for now... seems very unlikely we'd ever need more than six for a given hand- unless we build-in splitting functionality later
			
			/* Hide card grid
			for (int i = 0; i < 6; i++) {
				graphic.drawRect(gridX+i*cardTotalWidth+cardSpacing, gridY+cardSpacing, cardActualWidth, cardActualHeight);
				// dealer cards (higher on the grid than our cards...specifically by "cardSpacing" amount)
				graphic.drawRect(gridX+i*cardTotalWidth+cardSpacing, gridY+cardSpacing+cardTotalHeight, cardActualWidth, cardActualHeight);
			}
			*/
			

			// Draw Cards
			int index = 0;
			for(Card c: all_cards)
			{
				graphic.setColor(Color.white);
				
				// Draw 2 rectangles to allow for round edges
				// 1st is short and squat
				graphic.fillRect(gridX+index*cardTotalWidth+cardSpacing, 
					gridY+cardSpacing+cardEdgeSoften,
					 cardActualWidth, cardActualHeight - 2*cardEdgeSoften);
				// Second is tall and skinny 
				graphic.fillRect(gridX+index*cardTotalWidth+cardSpacing+cardEdgeSoften, 
					gridY+cardSpacing, cardActualWidth - 2*cardEdgeSoften, 
					cardActualHeight);
					 
				// Original full-size rectangle:
				//graphic.fillRect(gridX+index*cardTotalWidth+cardSpacing, 
				//	gridY+cardSpacing, cardActualWidth, cardActualHeight);
					
				
				// Draw 4 circles to create round edges
				// Upper Left
				graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing, 
					gridY+cardSpacing, 2*cardEdgeSoften, 2*cardEdgeSoften);
				// Upper Right
				graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing+cardActualWidth-2*cardEdgeSoften, 
					gridY+cardSpacing, 2*cardEdgeSoften, 2*cardEdgeSoften);
				// Lower Left
				graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing, 
					gridY+cardSpacing+cardActualHeight-2*cardEdgeSoften,
					2*cardEdgeSoften, 2*cardEdgeSoften);
				// Lower Right
				graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing+cardActualWidth-2*cardEdgeSoften, 
					gridY+cardSpacing+cardActualHeight-2*cardEdgeSoften,
					 2*cardEdgeSoften, 2*cardEdgeSoften);
				
					
				graphic.setColor(Color.red);
				
				//Add code to setColor to Black if card suit is 
					// Spade or Club
				if (c.suit.equalsIgnoreCase("Spades")||c.suit.equalsIgnoreCase("Clubs")) {
					graphic.setColor(Color.black);
				}
				

				
				// Draw spades
				if(c.suit.equalsIgnoreCase("Spades")) {
					graphic.setColor(Color.black);
					// fill ovals for Spades
					graphic.fillOval(gridX+index*cardTotalWidth+40, gridY+85, 40, 40);
					graphic.fillOval(gridX+index*cardTotalWidth+40+30, gridY+85, 40, 40);
					// fill arc for Spades
					graphic.fillArc(gridX+index*cardTotalWidth+28, gridY+30, 90, 70, 230, 80);
					// fill rectangle (the little stem at the base of the spade)
					graphic.fillRect(gridX+index*cardTotalWidth+70, gridY+90, 10, 50);
					// Note that we don't use an image file and instead draw the image because the program would not be portable itself if it relied upon
						// an image file being present... it's easier to have the Java program draw the shape itself so we don't have to worry about
						// having the program call to an image file that may or may not be in the right directory, etc
				} else if(c.suit.equalsIgnoreCase("Hearts")) {
					// draw hearts
					graphic.setColor(Color.red);
					graphic.fillOval(gridX+index*cardTotalWidth+40, gridY+70, 40, 40);
					graphic.fillOval(gridX+index*cardTotalWidth+40+30, gridY+70, 40, 40);
					graphic.fillArc(gridX+index*cardTotalWidth+30, gridY+96, 90, 70, 50, 80);
				} else if(c.suit.equalsIgnoreCase("Diamonds")) {
					//Draw Diamonds
					graphic.setColor(Color.red);
					// fillPolygon will, as the name implies, fill an n-sided polygon given an array of x and y coordinates
					int x1,x2,x3,x4,y1,y2,y3,y4;
					x1 = 75 + gridX + index*cardTotalWidth;
					y1 = 60 + gridY;
					x2 = 50 + gridX + index*cardTotalWidth;
					y2 = 100 + gridY;
					x3 = 75+ gridX + index*cardTotalWidth;;
					y3 = 140 + gridY;
					x4 = 100+ gridX + index*cardTotalWidth;;
					y4 = 100 + gridY;
					int[] xPolyCoordinates = {x1, x2, x3, x4};
					int[] yPolyCoordinates = {y1, y2, y3, y4};
					graphic.fillPolygon(xPolyCoordinates, yPolyCoordinates, 4);
				}
				
				
				graphic.setFont(cardFont);
				graphic.drawString(c.symbol, 
					gridX+index*cardTotalWidth+cardSpacing*2, 
					gridY+cardActualHeight);
				
				if (c.symbol.equalsIgnoreCase("Spades")) {
					
				}
				
				index++;
				if(index > 5)
					break;
			}
			
			
			
		}  
		
	}
	
	
	public class ActionHit implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Hit button clicked.");
			
		}
		
	}
	
	
	public class ActionStay implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Stay button clicked.");
			
		}
		
	}
	
	
	public class ActionYes implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Yes button clicked.");
			
		}
		
	}
	
	
	public class ActionNo implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("No button clicked.");
			
		}
		
	}
	
	
	
}
