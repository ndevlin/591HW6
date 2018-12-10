/**
 *  Written by Nathan Devlin for CIS 592 HW3
 *  
 * The Card class represents a single card: has a cardNumber corresponding to the 
 * card and a value corresponding to its BlackJack value
 */

package ndevlin_blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * @author Nathan
 */
public class Card
{
	/**
	 * cardNumber indicates the specific card, given by:
	 * 
	 * 0: Ace of Spades		1: Ace of Diamonds		2:Ace of Clubs		3:Ace of Hearts
	 * 4: 2 of Spades		5: 2 of Diamonds		6: 2 of Clubs		7: 2 of Hearts
	 * 8: 3 of Spades		9: 3 of Diamonds		10: 3 of Clubs		11: 3 of Hearts
	 *			... 
	 * 48: King of Spades	49: King of Diamonds	50: King of Clubs	51: King of Hearts
	 *
	 *	etc., where it is a Spade if cardNumber % 4 = 0,
	 *			a Diamond if cardNumber % 4 = 1,
	 *			a Club if cardNumber % 4 = 2,
	 *			and a Heart if cardNumber % 4 = 3,
	 *	and where if it is a number card, its number is (int)(cardNumber / 4) + 1.
	 *  The face cards are
	 *  Jack: 40-43
	 *  Queen: 44-47
	 *  King: 48-51
	 */
	private int cardNumber;
	
	/** 
	 * value equals its number if a number card, 10 if a face card,
	 * and either 1 or 11 if an Ace 
	 * */
	private int value;
	
	private boolean isAce;
	
	
	// Changed for GUI implementation
	private String symbol;
	
	
	// Constructor
	
	public Card(int cardNumIn)
	{
		cardNumber = cardNumIn;
		
		if(cardNumIn > 39)
		{
			value = 10;
		}
		else
		{
			value = (int)(cardNumber / 4) + 1;
		}
		
		if(cardNumber < 4)
		{
			isAce = true;
			value = 11;
		}
		else
		{
			isAce = false;
		}
		
		
		
		if(39 < cardNumber && cardNumber < 44)
		{
			symbol = "J";
		}
		else if(43 < cardNumber && cardNumber < 48)
		{
			symbol = "Q";
		}
		else if(47 < cardNumber && cardNumber < 52)
		{
			symbol = "K";
		}
		else if(cardNumber < 4)
		{
			symbol = "A";
		}
		else
		{
			symbol = Integer.toString(value);
		}
		
		
	}

	
	// Getters and Setters
	
	/** returns the symbol variable */
	public String getSymbol() {
		return symbol;
	}
		
	
	/** returns the cardNumber variable */
	public int getCardNumber() {
		return cardNumber;
	}

	/** sets cardNumber to the input value */
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	/** returns the value variable */
	public int getValue() {
		return value;
	}

	/** returns whether the card is an ace */
	public boolean isAce() {
		return isAce;
	}

	
	/** Returns the cards representation as a string */
	public String getName()
	{
		String result = "";
		if(cardNumber >= 48)
		{
			result = "King";
		}
		else if(44 <= cardNumber && cardNumber < 48)
		{
			result = "Queen";
		}
		else if(40 <= cardNumber && cardNumber < 44)
		{
			result = "Jack";
		}
		else if(cardNumber < 4)
		{
			result = "Ace";
		}
		else
		{
			result = String.valueOf(value);
		}
		
		result += " of ";
		
		if((cardNumber % 4) == 0)
		{
			result += "Spades";
		}
		else if((cardNumber % 4) == 1)
		{
			result += "Diamonds";
		}
		else if((cardNumber % 4) == 2)
		{
			result += "Clubs";
		}
		else
		{
			result += "Hearts";
		}

		
		return result;
	}
	
	
	/**  Sets the value of the card to 1, then returns itself */
	public Card aceToOne()
	{
		if(isAce())
		{
			value = 1;
		}
		
		return this;
	}
	
	
	/** Sets the value of the card to 11, then returns itself */
	public Card aceToEleven()
	{
		if(isAce())
		{
			value = 11;
		}
		
		return this;
	}
	
}

