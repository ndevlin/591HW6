package gui;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ndevlin_blackjack.*;

/**
 *@Group
 * Using blackjack backend to create a gui of the game.
 */
public class Gui extends JFrame
{
	/**
	 * Jlabels for parts of the game, including cards and betting amounts.
	 */
	private static final long serialVersionUID = 1L;

    JLabel Title = new JLabel("Welcome to BlackJack", JLabel.CENTER);
    JLabel PlayerCards = new JLabel("Player Cards", JLabel.CENTER);
    JLabel DealerCards = new JLabel("Dealer Cards", JLabel.CENTER);
    int bet=50;
    JLabel betAmount = new JLabel("", JLabel.CENTER);
    JLabel dealerBetAmount = new JLabel("", JLabel.CENTER);
    JLabel winnings = new JLabel("", JLabel.CENTER);
    JLabel losings = new JLabel("", JLabel.CENTER);
    JLabel danger = new JLabel("Gambling is dangerous", JLabel.CENTER);

	String messageToDisplay = "";
	String yourMoney = "Your money: ";
	int moneyAmount = 1000;
	int pot = 0;
	JTextField betToDisplay = new JTextField();

	/**
	 * 	Height and width of window
	 */
	int WIDTH = 1280;
	int HEIGHT = 720;

	/**
	 * Colors of various elements
	 */
	Color backgroundColor = new Color(163, 205, 96);
	Color colorButton = new Color(250, 250, 250);

	/**
	 * 	Game phases booleans
	 */
	boolean isHitOrStay = true;
	boolean isDealerTurn = false;
	boolean playAgain = false;
	boolean betMoney = false;

	String play_moreQ = "Play Again?";

	/**
	 * Creation of game buttons
	 */
	JButton hitButton = new JButton();
	JButton stayButton = new JButton();
	JButton yesButton = new JButton();
	JButton betButton = new JButton();
	JButton noButton = new JButton();

	/**
	 * Card grid position and dimensions.
	 */
	int gridX = 50;
	int gridY = 50;
	int gridW = 900;
	int gridH = 400;

	/**
	 * hit/stay grid positions/dimensions
	 */
	int hsX = gridX + gridW + 50;
	int hsY = gridY;
	int hsW = 230;
	int hsH = 400;

	/**
	 * "play again?" question grid
	 */
	int paX = hsX;
	int paY = hsY + hsH + 50;
	int paW = hsW;
	int paH = 200;

	/**
	 * ArrayList that will hold all of the cards in the deck, player's cards, and dealer's cards
	 */
	//ArrayList<ndevlin_blackjack.Card> all_cards = new ArrayList<ndevlin_blackjack.Card>();
	ArrayList<ndevlin_blackjack.Card> player_cards = new ArrayList<ndevlin_blackjack.Card>();
	ArrayList<ndevlin_blackjack.Card> dealer_cards = new ArrayList<ndevlin_blackjack.Card>();
	ndevlin_blackjack.Card visibleDealerCard;

	/**
	 *We want to have space for six cards across in our grid & and space for two cards in "height" on the playing grid

	 */
	int cardSpacing = 10;

	int cardEdgeSoften = 10;

	int cardTotalWidth = gridW/6;

	int cardTotalHeight = gridH/2;
	int cardActualWidth = cardTotalWidth - 2*cardSpacing;
	int cardActualHeight = cardTotalHeight- 2*cardSpacing;
	
	boolean isFirstHand = true;

	/**
	 * Standardizing font usage
	 */
	Font buttonFont = new Font("Times New Roman", Font.PLAIN, 30);
	Font cardFont = new Font("Times New Roman", Font.BOLD, 35);
	Font questionFont = new Font("Times New Roman", Font.PLAIN, 35);

	/**
	 * Creating gui board, setting buttons, and overall layout
	 */
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


		/**
		 * Implementing hit button to the board
		 */
		ActionHit aHit = new ActionHit();
		hitButton.addActionListener(aHit);

		/**
		 * Set the location/color/font/text for the hit button
		 */
		hitButton.setBounds(hsX+55, hsY+40, 120, 80);
		hitButton.setBackground(Color.yellow);
		hitButton.setFont(buttonFont);
		hitButton.setText("HIT");


		board.add(hitButton);

		/**
		 * Adding BlackJack title
		 */
	   Title.setSize(1200,40);
	   Title.setForeground(Color.BLACK);
	   Title.setFont(new Font("Serif", Font.BOLD, 24));
		board.add(Title);

		/**
		 * Adding bet amount jlabel to the board
		 */
		betAmount.setSize(2250,1020);
		betAmount.setForeground(Color.WHITE);
		betAmount.setFont(new Font("Serif", Font.BOLD, 20));
		betAmount.setVisible(false);
		board.add(betAmount);

		/**
		 * Adding dealer bet amount jlabel to the board
		 */
		dealerBetAmount.setSize(2235,1060);
		dealerBetAmount.setForeground(Color.WHITE);
		dealerBetAmount.setFont(new Font("Serif", Font.BOLD, 20));
		dealerBetAmount.setVisible(false);
		board.add(dealerBetAmount);

		/**
		 * adding winnings jlabel to the board
		 */
		winnings.setSize(2235,1100);
		winnings.setForeground(Color.GREEN);
		winnings.setFont(new Font("Serif", Font.BOLD, 20));
		winnings.setVisible(false);
		board.add(winnings);

		/**
		 * adding losings jlabel to the board
		 */
		losings.setSize(2235,100);
		losings.setForeground(Color.RED);
		losings.setFont(new Font("Serif", Font.BOLD, 20));
		losings.setVisible(false);
		//board.add(losings);
		danger.setSize(2235,1060);
		danger.setForeground(Color.RED);
		danger.setFont(new Font("Serif", Font.BOLD, 20));
		danger.setVisible(false);
		board.add(danger);

		/**
		 * Player and Dealer Cards label
		 */
		PlayerCards.setSize(220,70);
		PlayerCards.setForeground(Color.white);
		board.add(PlayerCards);

		DealerCards.setSize(220,930);
		DealerCards.setForeground(Color.white);
		board.add(DealerCards);


		/**
		 * Add bet $50 button
		 */
		ActionBet aBet = new ActionBet();
		betButton.addActionListener(aBet);
		

		/**
		 * Set the location/color/font/text for the bet button
		 */
		betButton.setBounds(hsX+40, hsY+160, 160, 80);
		betButton.setBackground(Color.MAGENTA);
		betButton.setFont(buttonFont);
		betButton.setText("BET $50");

		board.add(betButton);

		/**
		 * Add stay button to the board
		 */
		ActionStay aStay = new ActionStay();
		stayButton.addActionListener(aStay);
		stayButton.setBounds(hsX+55, hsY+280, 120, 80);
		stayButton.setBackground(Color.yellow);
		stayButton.setFont(buttonFont);
		stayButton.setText("STAY");

		board.add(stayButton);

		/**
		 * yes Button (to "play again?") added to th board
		 */
		ActionYes aYes = new ActionYes();
		yesButton.addActionListener(aYes);

		yesButton.setBounds(paX+10, paY+110, 100, 80);
		yesButton.setBackground(Color.yellow);
		yesButton.setFont(buttonFont);
		yesButton.setText("YES");

		board.add(yesButton);

		/**
		 * No Button (to "play again?") added to the board
		 */
		ActionNo aNo = new ActionNo();
		noButton.addActionListener(aNo);

		noButton.setBounds(paX+120, paY+110, 100, 80);
		noButton.setBackground(Color.yellow);
		noButton.setFont(buttonFont);
		noButton.setText("NO");

		board.add(noButton);
		betButton.setVisible(true);
	}

	/**
	 *
	 * @Group
	 * board extending jpanel to add graphics for color, borders, grid, dealer and player cards, and point values
	 *
	 */
	public class Board extends JPanel
	{

		private static final long serialVersionUID = 1L;


		/**
		 * Draw the table and cards
		 */
		public void paintComponent(Graphics graphic)
		{
			if(isFirstHand)
			{
				betButton.setVisible(true);
				isFirstHand = false;
			}
			
			/**
			 * background color
			 */
			graphic.setColor(backgroundColor);
			graphic.fillRect(0, 0, 1280, 720);

			/**
			 * temporary grid painting
			 */
			graphic.setColor(Color.black);
			graphic.drawRect(gridX, gridY, gridW, gridH);

			/**
			 * temporary log borders painting
			 */
			graphic.drawRect(gridX, gridY+ gridH + 50, gridW, gridH);

			/**
			 * temp totals, hit/stay buttons grid, and messages
			 */
			graphic.drawRect(hsX,  hsY,  hsW,  hsH);

			/**
			 * temp "play again" grid
			 */
			graphic.drawRect(paX, paY, paW, paH);


			/**
			 * Play Again question
			 */
			graphic.setFont(questionFont);
			if(playAgain == true)
			{
				//graphic.drawString(play_moreQ, paX +26, paY +100);
				graphic.setFont(cardFont);
				graphic.setColor(Color.white);
				graphic.drawString("Player's Points: "+Integer.toString(Driver.thePlayer.getPlayersHand().calculateCurrentHandValue()), hsX - 500, hsY + 490);
				
			/**
			 * Display the point totals for player
			 */
			} else if (isHitOrStay == true) {
				graphic.setFont(cardFont);
				graphic.setColor(Color.white);
				graphic.drawString("Player's Points: "+Integer.toString(Driver.thePlayer.getPlayersHand().calculateCurrentHandValue()), hsX - 500, hsY + 490);
			} else if (isDealerTurn == true) {
				graphic.setFont(cardFont);
				graphic.setColor(Color.white);
				graphic.drawString("Player's Points: "+Integer.toString(Driver.thePlayer.getPlayersHand().calculateCurrentHandValue()), hsX - 500, hsY + 490);
			}


			// make a for-loop to draw the grid spaces where each card will be (both our cards and dealer's cards)
			// we'll say six cards for now... seems very unlikely we'd ever need more than six for a given hand- unless we build-in splitting functionality later

			/* Hide card grid
			for (int i = 0; i < 6; i++) {
				graphic.drawRect(gridX+i*cardTotalWidth+cardSpacing, gridY+cardSpacing, cardActualWidth, cardActualHeight);
				// dealer cards (higher on the grid than our cards...specifically by "cardSpacing" amount)
				graphic.drawRect(gridX+i*cardTotalWidth+cardSpacing, gridY+cardSpacing+cardTotalHeight, cardActualWidth, cardActualHeight);
			}
			*/


			// Update cards to be displayed

			/**
			 * Update player's hand
			 */
			Driver.thePlayer.getPlayersHand().calculateCurrentHandValue();
			player_cards = Driver.thePlayer.getPlayersHand().getTheHand();

			/**
			 * Updated Dealer's cards
			 */
			Driver.theDealer.getDealerHand().calculateCurrentHandValue();
			dealer_cards = Driver.theDealer.getDealerHand().getTheHand();
			visibleDealerCard = Driver.theDealer.getVisibleCard();

			/**
			 * Draw/Paint Player Cards
			 */
			int index = 0;
			for(ndevlin_blackjack.Card c: player_cards)
			{
				graphic.setColor(Color.white);

				/**
				 * Draw 2 rectangles to allow for round edges
				 */

				graphic.fillRect(gridX+index*cardTotalWidth+cardSpacing,
					gridY+cardSpacing+cardEdgeSoften,
					 cardActualWidth, cardActualHeight - 2*cardEdgeSoften);

				graphic.fillRect(gridX+index*cardTotalWidth+cardSpacing+cardEdgeSoften,
					gridY+cardSpacing, cardActualWidth - 2*cardEdgeSoften,
					cardActualHeight);

				// Original full-size rectangle:
				//graphic.fillRect(gridX+index*cardTotalWidth+cardSpacing,
				//	gridY+cardSpacing, cardActualWidth, cardActualHeight);


				/**
				 * Draw 4 circles to create round edges
				 */
				/**
				 * Upper Left
				 */
				graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing,
					gridY+cardSpacing, 2*cardEdgeSoften, 2*cardEdgeSoften);
				/**
				 * Upper Right
				 */
				graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing+cardActualWidth-2*cardEdgeSoften,
					gridY+cardSpacing, 2*cardEdgeSoften, 2*cardEdgeSoften);
				/**
				 * Lower Left
				 */
				graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing,
					gridY+cardSpacing+cardActualHeight-2*cardEdgeSoften,
					2*cardEdgeSoften, 2*cardEdgeSoften);
				/**
				 * Lower Right
				 */
				graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing+cardActualWidth-2*cardEdgeSoften,
					gridY+cardSpacing+cardActualHeight-2*cardEdgeSoften,
					 2*cardEdgeSoften, 2*cardEdgeSoften);


				graphic.setColor(Color.red);

				/**
				 * code to setColor to Black if card suit is
				 */
					/**
					 * Spade or Club
					 */
				if (c.getName().contains("Spades")||c.getName().contains("Clubs")) {
					graphic.setColor(Color.black);
				}


				/**
				 * Draw/Paint spades
				 */
				if(c.getName().contains("Spades")) {
					graphic.setColor(Color.black);
					/**
					 * fill ovals for Spades
					 */
					graphic.fillOval(gridX+index*cardTotalWidth+40, gridY+85, 40, 40);
					graphic.fillOval(gridX+index*cardTotalWidth+40+30, gridY+85, 40, 40);
					/**
					 * fill arc for Spades
					 */
					graphic.fillArc(gridX+index*cardTotalWidth+28, gridY+30, 90, 70, 230, 80);
					/**
					 * fill rectangle (the little stem at the base of the spade)
					 */
					graphic.fillRect(gridX+index*cardTotalWidth+70, gridY+90, 10, 50);
					/**
					 * Note that we don't use an image file and instead draw the image because the program would not be portable itself if it relied upon
					 */
						/**
						 * an image file being present... it's easier to have the Java program draw the shape itself so we don't have to worry about
						 */
						/**
						 * having the program call to an image file that may or may not be in the right directory, etc
						 */
				} else if(c.getName().contains("Hearts"))
				{
					/**
					 * Draw hearts/Paint
					 */
					graphic.setColor(Color.red);
					graphic.fillOval(gridX+index*cardTotalWidth+40, gridY+70, 40, 40);
					graphic.fillOval(gridX+index*cardTotalWidth+40+30, gridY+70, 40, 40);
					graphic.fillArc(gridX+index*cardTotalWidth+30, gridY+96, 90, 70, 50, 80);
				} else if(c.getName().contains("Diamonds"))
				{
					/**
					 * Draw/Paint Diamonds
					 */
					graphic.setColor(Color.red);
					/**
					 * fillPolygon will, as the name implies, fill an n-sided polygon given an array of x and y coordinates
					 */
					int x1,x2,x3,x4,y1,y2,y3,y4;
					x1 = 75 + gridX + index*cardTotalWidth;
					y1 = 60 + gridY;
					x2 = 50 + gridX + index*cardTotalWidth;
					y2 = 100 + gridY;
					x3 = 75+ gridX + index*cardTotalWidth;
					y3 = 140 + gridY;
					x4 = 100+ gridX + index*cardTotalWidth;
					y4 = 100 + gridY;
					int[] xPolyCoordinates = {x1, x2, x3, x4};
					int[] yPolyCoordinates = {y1, y2, y3, y4};
					graphic.fillPolygon(xPolyCoordinates, yPolyCoordinates, 4);
				} else {
					/**
					 * Draw clubs by simply removing the arc from the spades drawing and replacing it with a third oval
					 */
					/**
					 * fill ovals for Clubs
					 */
					graphic.setColor(Color.black);
					graphic.fillOval(gridX+index*cardTotalWidth+35, gridY+85, 40, 40);
					graphic.fillOval(gridX+index*cardTotalWidth+40+35, gridY+85, 40, 40);
					/**
					 * Third oval (instead of arc that's seen in Spades)
					 */
					graphic.fillOval(gridX+index*cardTotalWidth+55, gridY+55, 40, 40);

					/**
					 * fill rectangle (the little stem at the base of the spade)
					 */
					graphic.fillRect(gridX+index*cardTotalWidth+70, gridY+90, 10, 50);
				}


				graphic.setFont(cardFont);
				graphic.drawString(c.getSymbol(),
					gridX+index*cardTotalWidth+cardSpacing*2,
					gridY+cardActualHeight);


				index++;
				if(index > 5)
					break;
			}

			/**
			 * Draw single visible dealer card
			 */
			{

				graphic.setColor(Color.white);

				/**
				 * Draw 2 rectangles to allow for round edges
				 */
				/**
				 * 1st is short and squat
				 */
				graphic.fillRect(gridX+cardSpacing,
					gridY+cardTotalHeight+cardSpacing+cardEdgeSoften,
					 cardActualWidth, cardActualHeight - 2*cardEdgeSoften);
				/**
				 * Second is tall and skinny
				 */
				graphic.fillRect(gridX+cardSpacing+cardEdgeSoften,
					gridY+cardTotalHeight+cardSpacing, cardActualWidth - 2*cardEdgeSoften,
					cardActualHeight);

				/**
				 * Draw 4 circles to create round edges
				 */
				/**
				 * Upper Left
				 */
				graphic.fillOval(gridX+cardSpacing,
					gridY+cardTotalHeight+cardSpacing, 2*cardEdgeSoften, 2*cardEdgeSoften);
				/**
				 * Upper Right
				 */
				graphic.fillOval(gridX+cardSpacing+cardActualWidth-2*cardEdgeSoften,
					gridY+cardTotalHeight+cardSpacing, 2*cardEdgeSoften, 2*cardEdgeSoften);
				/**
				 * Lower Left
				 */
				graphic.fillOval(gridX+cardSpacing,
					gridY+cardTotalHeight+cardSpacing+cardActualHeight-2*cardEdgeSoften,
					2*cardEdgeSoften, 2*cardEdgeSoften);
				/**
				 * Lower Right
				 */
				graphic.fillOval(gridX+cardSpacing+cardActualWidth-2*cardEdgeSoften,
					gridY+cardTotalHeight+cardSpacing+cardActualHeight-2*cardEdgeSoften,
					 2*cardEdgeSoften, 2*cardEdgeSoften);


				graphic.setColor(Color.red);

				/**
				 * Add code to setColor to Black if card suit is
				 */
					/**
					 * Spade or Club
					 */
				if (visibleDealerCard.getName().contains("Spades")||
						visibleDealerCard.getName().contains("Clubs")) {
					graphic.setColor(Color.black);
				}

				/**
				 * Draw/Paint spades
				 */
				if(visibleDealerCard.getName().contains("Spades")) {
					graphic.setColor(Color.black);
					/**
					 * fill ovals for Spades
					 */
					graphic.fillOval(gridX+40,
							gridY+cardTotalHeight+85, 40, 40);
					graphic.fillOval(gridX+40+30,
							gridY+cardTotalHeight+85, 40, 40);
					/**
					 * fill arc for Spades
					 */
					graphic.fillArc(gridX+28,
							gridY+cardTotalHeight+30, 90, 70, 230, 80);
					/**
					 * fill rectangle (the little stem at the base of the spade)
					 */
					graphic.fillRect(gridX+70,
							gridY+cardTotalHeight+90, 10, 50);
					/**
					 * Note that we don't use an image file and instead draw the image because the program would not be portable itself if it relied upon
					 */
						/**
						 * an image file being present... it's easier to have the Java program draw the shape itself so we don't have to worry about
						 */
						/**
						 * having the program call to an image file that may or may not be in the right directory, etc
						 */
				} else if(visibleDealerCard.getName().contains("Hearts"))
				{
					/**
					 * Draw hearts/Paint
					 */
					graphic.setColor(Color.red);
					graphic.fillOval(gridX+40,
							gridY+cardTotalHeight+70, 40, 40);
					graphic.fillOval(gridX+40+30,
							gridY+cardTotalHeight+70, 40, 40);
					graphic.fillArc(gridX+30,
							gridY+cardTotalHeight+96, 90, 70, 50, 80);
				} else if(visibleDealerCard.getName().contains("Diamonds"))
				{
					/**
					 * Draw/Paint Diamonds
					 */
					graphic.setColor(Color.red);
					/**
					 * fillPolygon will, as the name implies, fill an n-sided polygon given an array of x and y coordinates
					 */
					int x1,x2,x3,x4,y1,y2,y3,y4;
					x1 = 75 + gridX;
					y1 = 60 + gridY+cardTotalHeight;
					x2 = 50 + gridX;
					y2 = 100 + gridY+cardTotalHeight;
					x3 = 75+ gridX;
					y3 = 140 + gridY+cardTotalHeight;
					x4 = 100+ gridX;
					y4 = 100 + gridY+cardTotalHeight;
					int[] xPolyCoordinates = {x1, x2, x3, x4};
					int[] yPolyCoordinates = {y1, y2, y3, y4};
					graphic.fillPolygon(xPolyCoordinates, yPolyCoordinates, 4);
				} else {
					/**
					 * Draw clubs by simply removing the arc from the spades drawing and replacing it with a third oval
					 */
					/**
					 * fill ovals for Clubs
					 */
					graphic.setColor(Color.black);
					graphic.fillOval(gridX+35,
							gridY+cardTotalHeight+85, 40, 40);
					graphic.fillOval(gridX+40+35,
							gridY+cardTotalHeight+85, 40, 40);
					/**
					 * Third oval (instead of arc that's seen in Spades)
					 */
					graphic.fillOval(gridX+55,
							gridY+cardTotalHeight+55, 40, 40);

					/**
					 * fill rectangle (the little stem at the base of the spade)
					 */
					graphic.fillRect(gridX+70,
							gridY+cardTotalHeight+90, 10, 50);
				}


				graphic.setFont(cardFont);
				graphic.drawString(visibleDealerCard.getSymbol(),
					gridX+cardSpacing*2,
					gridY+cardTotalHeight+cardActualHeight);


			}


			/**
			 * Draw Dealer face-down card
			 */
			index = 1;
			{
				graphic.setColor(Color.gray);

				/**
				 * Draw 2 rectangles to allow for round edges
				 */
				/**
				 * 1st is short and squat
				 */
				graphic.fillRect(gridX+index*cardTotalWidth+cardSpacing,
					gridY+cardTotalHeight+cardSpacing+cardEdgeSoften,
					 cardActualWidth, cardActualHeight - 2*cardEdgeSoften);
				/**
				 * Second is tall and skinny
				 */
				graphic.fillRect(gridX+index*cardTotalWidth+cardSpacing+cardEdgeSoften,
					gridY+cardTotalHeight+cardSpacing, cardActualWidth - 2*cardEdgeSoften,
					cardActualHeight);


				/**
				 * Draw 4 circles to create round edges
				 */
				/**
				 * Upper Left
				 */
				graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing,
					gridY+cardTotalHeight+cardSpacing, 2*cardEdgeSoften, 2*cardEdgeSoften);
				/**
				 * Upper Right
				 */
				graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing+cardActualWidth-2*cardEdgeSoften,
					gridY+cardTotalHeight+cardSpacing, 2*cardEdgeSoften, 2*cardEdgeSoften);
				/**
				 * Lower Left
				 */
				graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing,
					gridY+cardTotalHeight+cardSpacing+cardActualHeight-2*cardEdgeSoften,
					2*cardEdgeSoften, 2*cardEdgeSoften);
				/**
				 * Lower Right
				 */
				graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing+cardActualWidth-2*cardEdgeSoften,
					gridY+cardTotalHeight+cardSpacing+cardActualHeight-2*cardEdgeSoften,
					 2*cardEdgeSoften, 2*cardEdgeSoften);

			}

			/**
			 * Draw/ Paint Dealer Cards
			 */
			if(isDealerTurn == true)
			{
				index = 0;
				for(ndevlin_blackjack.Card c: dealer_cards)
				{
					graphic.setColor(Color.white);

					/**
					 * Draw 2 rectangles to allow for round edges
					 */
					/**
					 * 1st is short and squat
					 */
					graphic.fillRect(gridX+index*cardTotalWidth+cardSpacing,
						gridY+cardTotalHeight+cardSpacing+cardEdgeSoften,
						 cardActualWidth, cardActualHeight - 2*cardEdgeSoften);
					/**
					 * Second is tall and skinny
					 */
					graphic.fillRect(gridX+index*cardTotalWidth+cardSpacing+cardEdgeSoften,
						gridY+cardTotalHeight+cardSpacing, cardActualWidth - 2*cardEdgeSoften,
						cardActualHeight);


					/**
					 * Draw 4 circles to create round edges
					 */
					/**
					 * Upper Left
					 */
					graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing,
						gridY+cardTotalHeight+cardSpacing, 2*cardEdgeSoften, 2*cardEdgeSoften);
					/**
					 * Upper Right
					 */
					graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing+cardActualWidth-2*cardEdgeSoften,
						gridY+cardTotalHeight+cardSpacing, 2*cardEdgeSoften, 2*cardEdgeSoften);
					/**
					 * Lower Left
					 */
					graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing,
						gridY+cardTotalHeight+cardSpacing+cardActualHeight-2*cardEdgeSoften,
						2*cardEdgeSoften, 2*cardEdgeSoften);
					/**
					 * Lower Right
					 */
					graphic.fillOval(gridX+index*cardTotalWidth+cardSpacing+cardActualWidth-2*cardEdgeSoften,
						gridY+cardTotalHeight+cardSpacing+cardActualHeight-2*cardEdgeSoften,
						 2*cardEdgeSoften, 2*cardEdgeSoften);

					graphic.setColor(Color.red);

					/**
					 * Add code to setColor to Black if card suit is
					 */
						/**
						 * Spade or Club
						 */
					if (c.getName().contains("Spades")||c.getName().contains("Clubs")) {
						graphic.setColor(Color.black);
					}

					/**
					 * Draw/Paint spades
					 */
					if(c.getName().contains("Spades")) {
						graphic.setColor(Color.black);
						/**
						 * fill ovals for Spades
						 */
						graphic.fillOval(gridX+index*cardTotalWidth+40,
								gridY+cardTotalHeight+85, 40, 40);
						graphic.fillOval(gridX+index*cardTotalWidth+40+30,
								gridY+cardTotalHeight+85, 40, 40);
						/**
						 * fill arc for Spades
						 */
						graphic.fillArc(gridX+index*cardTotalWidth+28,
								gridY+cardTotalHeight+30, 90, 70, 230, 80);
						/**
						 * fill rectangle (the little stem at the base of the spade)
						 */
						graphic.fillRect(gridX+index*cardTotalWidth+70,
								gridY+cardTotalHeight+90, 10, 50);

					} else if(c.getName().contains("Hearts"))
					{
						/**
						 * Draw hearts/Paint
						 */
						graphic.setColor(Color.red);
						graphic.fillOval(gridX+index*cardTotalWidth+40,
								gridY+cardTotalHeight+70, 40, 40);
						graphic.fillOval(gridX+index*cardTotalWidth+40+30,
								gridY+cardTotalHeight+70, 40, 40);
						graphic.fillArc(gridX+index*cardTotalWidth+30,
								gridY+cardTotalHeight+96, 90, 70, 50, 80);
					} else if(c.getName().contains("Diamonds"))
					{
						/**
						 * Draw/Paint Diamonds
						 */
						graphic.setColor(Color.red);
						/**
						 * fillPolygon will, as the name implies, fill an n-sided polygon given an array of x and y coordinates
						 */
						int x1,x2,x3,x4,y1,y2,y3,y4;
						x1 = 75 + gridX + index*cardTotalWidth;
						y1 = 60 + gridY+cardTotalHeight;
						x2 = 50 + gridX + index*cardTotalWidth;
						y2 = 100 + gridY+cardTotalHeight;
						x3 = 75+ gridX + index*cardTotalWidth;
						y3 = 140 + gridY+cardTotalHeight;
						x4 = 100+ gridX + index*cardTotalWidth;
						y4 = 100 + gridY+cardTotalHeight;
						int[] xPolyCoordinates = {x1, x2, x3, x4};
						int[] yPolyCoordinates = {y1, y2, y3, y4};
						graphic.fillPolygon(xPolyCoordinates, yPolyCoordinates, 4);
					} else {
						/**
						 * Draw clubs by simply removing the arc from the spades drawing and replacing it with a third oval
						 */
						/**
						 * fill ovals for Clubs
						 */
						graphic.setColor(Color.black);
						graphic.fillOval(gridX+index*cardTotalWidth+35,
								gridY+cardTotalHeight+85, 40, 40);
						graphic.fillOval(gridX+index*cardTotalWidth+40+35,
								gridY+cardTotalHeight+85, 40, 40);
						/**
						 * Third oval (instead of arc that's seen in Spades)
						 */
						graphic.fillOval(gridX+index*cardTotalWidth+55,
								gridY+cardTotalHeight+55, 40, 40);

						/**
						 * fill rectangle (the little stem at the base of the spade)
						 */
						graphic.fillRect(gridX+index*cardTotalWidth+70,
								gridY+cardTotalHeight+90, 10, 50);
					}

					graphic.setFont(cardFont);
					graphic.drawString(c.getSymbol(),
						gridX+index*cardTotalWidth+cardSpacing*2,
						gridY+cardTotalHeight+cardActualHeight);

					index++;
					if(index > 5)
						break;
				}
			}

			graphic.setColor(Color.black);
			graphic.drawString(messageToDisplay, 100, 550);
			graphic.drawString("Your Money: " + moneyAmount, 150, 650);
			graphic.drawString("Pot: " + pot, 500, 650);
			graphic.drawString(play_moreQ, 1020, 550);
			//graphic.drawString(betToDisplay, 300, 500);
		}
	}

	/**
	 * Refreshes the state of the game: Player, dealer, or ask if new game
	 */
		public void refresher()
		{
			if(isHitOrStay == true)
			{
				hitButton.setVisible(true);
				stayButton.setVisible(true);
				//yesButton.setVisible(false);
				//noButton.setVisible(false);

			}
			/*
			else if(isDealerTurn == true)
			{
				hitButton.setVisible(false);
				stayButton.setVisible(false);
				betButton.setVisible(false);
				yesButton.setVisible(true);
				noButton.setVisible(true);

			}
			*/
		}

		/**
		 *  Runs the back-end dealer logic to hit or stay
		 */
		public void dealerHitOrStay()
		{
			Driver.theDealer.dealersTurn(Driver.thePlayer);
			Driver.theDealer.getDealerHand().calculateCurrentHandValue();
		}

	/**
	 * Executes when player clicks Hit button
	 * @author Group
	 *
	 */
	public class ActionHit implements ActionListener
	{

		@Override
		/**
		 * Gets hand value when hit action implemented
		 */
		public void actionPerformed(ActionEvent e)
		{
			betButton.setVisible(false);
			
			System.out.println("Hit button clicked.");

			if(Driver.thePlayer.getPlayersHand().calculateCurrentHandValue() <= 21)
			{
				Driver.theDealer.playerHit(Driver.thePlayer).getName();
			}
			if(Driver.thePlayer.getPlayersHand().calculateCurrentHandValue() > 21)
			{
				//Player busted

			}

		}

	}

	/**
	 * Executes when player clicks Bet button
	 * @author Group
	 *
	 */
		public class ActionBet implements ActionListener
		{

			@Override
			/**
			 * shows bet amount when bet button clicked
			 */
			public void actionPerformed(ActionEvent e)
			{
				//betToDisplay = "You bet $50. Dealer bet $50";
				if(e.getSource() == betButton) {
					int newBet=50;
					bet = newBet;
					betButton.setVisible(false);
					dealerBetAmount.setVisible(true);
					//delete from here if fup
/*					if(stayButton.isSelected()) {
						betButton.setEnabled(false);
					}*/
					Driver.theDealer.dealNewHand(Driver.thePlayer);
					if(moneyAmount >= 50)
					{
						moneyAmount-=50;
						pot += 100;
					}
					else
					{
						messageToDisplay = "You're Broke.";
					}
					
				}

				System.out.println("You bet $50. Gambling is dangerous.");
			}
		}

	/**
	 * Executes when player clicks Stay button
	 * @author Group
	 *
	 */
	public class ActionStay implements ActionListener
	{

		@Override
		/**
		 * Ends game to declare winner or asks for push
		 */
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Stay button clicked.");
			isHitOrStay = false;
			isDealerTurn = true;
			dealerHitOrStay();
			betButton.setVisible(false);

			Driver.thePlayer.getPlayersHand().calculateCurrentHandValue();

			/**
			 * Updated Dealer's cards
			 */
			Driver.theDealer.getDealerHand().calculateCurrentHandValue();

			if(Driver.theDealer.resultOfLastRound() == 0)
			{
				messageToDisplay = "Push.   ";
				moneyAmount += pot;
				pot = 0;

			}
			else if(Driver.theDealer.resultOfLastRound() == 1)
			{
				messageToDisplay = "You Won!  ";
				//play_moreQ.setVisible(true);
				betAmount.setVisible(false);
				dealerBetAmount.setVisible(false);
				if(betButton.getModel().isEnabled()) {
					winnings.setVisible(true);
				}
				moneyAmount += pot;
				pot = 0;


			}
			else if(Driver.theDealer.resultOfLastRound() == 2)
			{
				messageToDisplay = "You Lost";

				betAmount.setVisible(false);
				dealerBetAmount.setVisible(false);
				danger.setVisible(true);
				if(betButton.getModel().isEnabled()) {
					losings.setVisible(true);
					danger.setVisible(false);
					pot = 0;
				}
			}


		}

	}

	/**
	 * Executes when player clicks Yes button
	 * @author Group
	 *
	 */
	public class ActionYes implements ActionListener
	{

		@Override
		/**
		 * resets board for new round when "play again" yes button clicked
		 */
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Yes button clicked.");
			playAgain = true;
			betButton.setVisible(true);
			isDealerTurn = false;
			betAmount.setVisible(false);
			dealerBetAmount.setVisible(false);
			losings.setVisible(false);
			winnings.setVisible(false);
			danger.setVisible(false);
			messageToDisplay = "Make a Bet!";
			betButton.setVisible(true);
			

		}



	}

	/**
	 * Executes when player clicks No button
	 * @author Group
	 *
	 */
	public class ActionNo implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{

			System.out.println("No button clicked.");
			System.exit(0);
		}

	}
}





