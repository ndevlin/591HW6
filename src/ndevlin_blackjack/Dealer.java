/**
 *  Written by Nathan Devlin for CIS 592 HW3
 *  
 * The Dealer class represents a dealer. It has a hand and plays accordingly. It also deals the cards.
 */

package ndevlin_blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;


//COMPLETED interface for betting and floormanager, implemented interface through their classes, and then created instnaces as objects.
//TODO 1. Where to call the functions (i.e. where are the rounds being played? where is the winner total being tallied?)
//TODO 2. End game when player or dealer runs out of money. Specifically, don't prompt player / dealer to play game again. 
/**
 * @author Nathan
 */
public class Dealer
{
	private Deck theDeck;
	
	private int dealerScore;
	private int playerScore;
	
	/** 0 for Push,  1 for Player win,  2 for Dealer win */
	private int lastRoundResult;
	
	private Hand dealerHand;
	
	private Card visibleCard;
	private Betting bet;

	
/*	//minimum amount a player can bet per turn
	private final static int Bet_Minimum = 5;

	private final static int DealerBalance = 500;*/
	
	
	/**
	 * Constructor for Dealer object
	 * Accepts no parameters
	 */
	public Dealer()
	{
		theDeck = new Deck();
		dealerScore = 0;
		playerScore = 0;
		lastRoundResult = 0;
		bet = new Betting(500);
		
		dealerHand = new Hand();
	}

	
	// Getters
	
	public Betting getBet() {
		return bet;
	}


	public void setBet(Betting bet) {
		this.bet = bet;
	}


	/** Returns theDeck Deck */
	public Deck getTheDeck() {
		return theDeck;
	}
	
	/** Returns the number of times the dealer has won, dealerScore */
	public int getDealerScore() {
		return dealerScore;
	}
	
	/**
	 * This method tests that the dealer's score is initialized to zero.
	 */
	@Test
	public void dealerScoreTest() {
  		Dealer testDealer = new Dealer();
  		testDealer.getDealerScore();
  		assertEquals(0, testDealer.getDealerScore());
	}
	
	/**
	 * This method tests that the player's score is initialized to zero.
	 */
	@Test
	public void playerScoreTest() {
  		Dealer testDealer = new Dealer();
  		testDealer.getDealerScore();
  		assertEquals(0, testDealer.getPlayerScore());
	}
	
	/**
	 * This method tests that the default for result of last round is zero.
	 */
	@Test
	public void lastRoundTest() {
  		Dealer testDealer = new Dealer();
  		testDealer.resultOfLastRound();
  		assertEquals(0, testDealer.resultOfLastRound());
	}

	/** Returns the number of times the player has won, playerScore */
	public int getPlayerScore() {
		return playerScore;
	}

	/**
	 * Returns the result of the most recent round;
	 * 0 = push		1 = Player won		2 = Dealer won
	 */
	public int resultOfLastRound() {
		return lastRoundResult;
	}

	/** Returns the Hand dealerHand */
	public Hand getDealerHand() {
		return dealerHand;
	}


	/** Returns the Card visibleCard */
	public Card getVisibleCard() {
		return visibleCard;
	}
	
	
	// Mutators
	
	/**
	 * Accepts Deck as parameter, returns nothing
	 * Puts the playersHand in the discard pile
	 */
	public void discardHand()
	{
		dealerHand.discardHand(theDeck);
	}
	
	
	/**
	 * Accepts a Player object, returns nothing
	 * Deals new hands to the dealer and the player
	 */
	public void dealNewHand(Player thePlayer)
	{
		discardHand();
		thePlayer.discardHand(theDeck);
		
		ArrayList<Card> dealerList = new ArrayList<Card>(2);
		ArrayList<Card> playerList = new ArrayList<Card>(2);
		
		visibleCard = theDeck.drawCard();
		
		dealerList.add(visibleCard);
		dealerList.add(theDeck.drawCard());
		playerList.add(theDeck.drawCard());
		playerList.add(theDeck.drawCard());
		
		dealerHand = new Hand(dealerList);
		Hand playerHandIn = new Hand(playerList);
		
		thePlayer.newHand(theDeck, playerHandIn);
	}
	
	
	/**
	 * Accepts no parameters, returns nothing
	 * Adds a card from the deck to the Dealer's hand
	 */
	public void dealerHit()
	{
		dealerHand.addCard(theDeck.drawCard());
	}
	
	/**
	 * Method tests that when addCard() is called, it correctly adds a card to the hand
	 */
	@Test
	public void testAddCard() {
		Deck testDeck = new Deck();
		Hand testDealerHand = new Hand();
		testDealerHand.addCard(testDeck.drawCard());
		assertEquals(1, testDealerHand.getSize());
	}
	
	/**
	 * Method tests that when dealerHit() is called, it correctly adds a card to the hand
	 */
	@Test
	public void testDealerHit() {
		Dealer testDealer = new Dealer();
		testDealer.dealerHit();
		assertEquals(1, testDealer.getDealerHand().getSize());
	}
	
	
	/**
	 * Accepts a Player as parameter, returns the current value of the hand as an int
	 * Adds a card from the deck to the player's hand
	 */
	public Card playerHit(Player thePlayer)
	{
		Card drawnCard = theDeck.drawCard();
		thePlayer.playersHand.addCard(drawnCard);
		
		return drawnCard;
	}
	
	
	/**
	 * Returns 0 for a push, 1 if the player wins, and 2 if the dealer wins
	 */
	public int dealersTurn(Player thePlayer)
	{
		lastRoundResult = 0;
		
		dealerHand.calculateCurrentHandValue();
		thePlayer.getPlayersHand().calculateCurrentHandValue();
		//getting bet and updating
		thePlayer.getBet().placeBet();
		
		if(thePlayer.getPlayersHand().calculateCurrentHandValue() > 21)
		{
			dealerScore++;
			lastRoundResult = 2;
			return 2;
		}
		
		while(dealerHand.calculateCurrentHandValue() < 17)
		{
			dealerHit();
		}
		
		dealerHand.calculateCurrentHandValue();
		
		if(dealerHand.calculateCurrentHandValue() <= 21)
		{
			// Push
			if(dealerHand.calculateCurrentHandValue() == thePlayer.getPlayersHand().calculateCurrentHandValue())
			{
				lastRoundResult = 0;
				return 0;
			}
			
			// Dealer wins
			if(dealerHand.calculateCurrentHandValue() > thePlayer.getPlayersHand().calculateCurrentHandValue())
			{
				dealerScore++;
				lastRoundResult = 2;
				return 2;
			}
			
			// Player wins
			if(dealerHand.calculateCurrentHandValue() < thePlayer.getPlayersHand().calculateCurrentHandValue())
			{
				playerScore++;
				thePlayer.addWin();
				lastRoundResult = 1;
				return 1;
			}
			
		}
		
		//Dealer busts, has no more unconverted Aces
		if(dealerHand.getNumberFullElevenAces() < 1 && dealerHand.calculateCurrentHandValue() > 21)
		{
			playerScore++;
			thePlayer.addWin();
			lastRoundResult = 1;
			return 1;
		}
		
		return 0;
	}
}




