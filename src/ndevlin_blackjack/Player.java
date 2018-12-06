/**
 *  Written by Nathan Devlin for CIS 592 HW3
 *  
 * The Player class represents the player. It chooses whether to hit or stay
 */

package ndevlin_blackjack;

/**
 * @author Nathan
 */
public class Player
{
	private Betting bet;
	
	public void setBet(Betting bet) {
		this.bet = bet;
	}


	Hand playersHand;
	
	private int currentWins;
	
	private boolean staying;
	

	/**
	 * Constructor
	 * Accepts no parameters
	 */
	public Player()
	{
		playersHand = new Hand();
		currentWins = 0;
		staying = false;
		bet = new Betting(500);
	}
	
	
	// Getters
	
	/** Returns the playersHand Hand */
	public Hand getPlayersHand() {
		return playersHand;
	}

	/** Returns the currentWins value */
	public int getCurrentWins() {
		return currentWins;
	}
	
	/** Returns the staying boolean */
	public boolean getWhetherIsStaying() {
		return staying;
	}

	/** Sets the staying boolean */
	public void isStaying(boolean willStay) {
		this.staying = willStay;
	}
	
	
	//Mutators
	
	/**
	 * Accepts Deck as parameter, returns nothing
	 * Puts the playersHand in the discard pile
	 */
	public void discardHand(Deck theDeck)
	{
		playersHand.discardHand(theDeck);
	}
	
	
	/** Returns the currentWins variable after incrementing it by one */
	public int addWin()
	{
		currentWins++;
		return currentWins;
	}

	
	/**
	 * Accepts the Deck and a Hand as argument, returns nothing
	 * Discards the old hand and sets playersHand to be theNewHand
	 */
	public void newHand(Deck theDeck, Hand theNewHand)
	{
		this.discardHand(theDeck);
		playersHand = theNewHand;
		staying = false;
	}


	public Betting getBet() {
		// TODO Auto-generated method stub
		return bet;
	}
	
}


