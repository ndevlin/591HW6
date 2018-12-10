/**
 *  Written by Nathan Devlin for CIS 592 HW3
 *  
 * The Hand class represents a hand of cards. It contains an ArrayList of Card objects
 */

package ndevlin_blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Nathan
 */
public class Hand
{
	ArrayList<Card> theHand;
	
	private boolean isBusted;
	
	// Constructors
	
	/**
	 * Default Constructor
	 * Accepts no Parameters
	 */
	public Hand()
	{
		//12 Cards is the largest possible hand
		theHand = new ArrayList<Card>(12);
		isBusted = false;
	}
	
	
	/**
	 * Constructor;
	 * Accepts an ArrayList of Card objects as parameter
	 */
	public Hand(ArrayList<Card> cardsIn)
	{
		theHand = new ArrayList<Card>(12);
		theHand = cardsIn;
		if(calculateCurrentHandValue() > 21)
		{
			isBusted = true;
		}
		else
		{
			isBusted = false;
		}
		
	}


	// Getters
	
	/**Returns the theHand ArrayList<Card> */
	public ArrayList<Card> getTheHand() {
		return theHand;
	}
	
	
	/**
	 * Accepts an integer index as parameter
	 * Returns the Card at that index
	 */
	public Card getCardAtIndex(int index)
	{
		return theHand.get(index);
	}
	
	/** Returns the size of the Hand */
	public int getSize()
	{
		return theHand.size();
	}


	/** Returns the isBusted boolean */
	public boolean isBusted() {
		return isBusted;
	}

	
	/** Returns the calculated value of the hand */
	public int calculateCurrentHandValue()
	{
		int result = 0;
		for(int i = 0; i < theHand.size(); i++)
		{
			result += theHand.get(i).getValue();
		}
		
		if(result <= 21)
		{
			isBusted = false;
			return result;
		}
		
		if(result > 21 && getNumberFullElevenAces() < 1)
		{
			isBusted = true;
			return result;
		}
		
		if(result > 21 && getNumberFullElevenAces() > 0)
		{
			changeAcesToGetUnderBust();
		}
		
		result = 0;
		for(int i = 0; i < theHand.size(); i++)
		{
			result += theHand.get(i).getValue();
		}
		
		if(result > 21)
		{
			isBusted = true;
		}
		
		return -1; // if we reach here, there's been an error
	}
	
	
	/**
	 * Accepts no parameters, Returns the number of unconverted Aces
	 * Converts Aces' value from 11 to 1 as needed to get under 22
	 */
	public int changeAcesToGetUnderBust()
	{	
		
		if(getNumberFullElevenAces() == 0)
		{
			return 0;
		}
		
		int value = 0;
		for(int i = 0; i < theHand.size(); i++)
		{
			value += theHand.get(i).getValue();
		}
		
		int iterator = 0;
		while(getNumberFullElevenAces() > 0 && value > 21)
		{
			if(getCardAtIndex(iterator).isAce())
			{
				aceToOneAtIndex(iterator);
			}
			iterator++;
			
			value = 0;
			for(int i = 0; i < theHand.size(); i++)
			{
				value += theHand.get(i).getValue();
			}
		}
		
		return getNumberFullElevenAces();
	}
	
	
	/** Returns the number of Aces in the hand */
	public int getNumberOfAces()
	{
		int result = 0;
		for(int i = 0; i < theHand.size(); i++)
		{
			if(theHand.get(i).isAce())
			{
				result++;
			}
		}
		
		return result;
	}
	
	/** Returns the number of 11-valued Aces in the hand */
	public int getNumberFullElevenAces()
	{
		int numFullElevenAces = 0;
	
		for(int i = 0; i < getSize(); i++)
		{
			if(getCardAtIndex(i).getValue() == 11)
			{
				numFullElevenAces++;
			}
		}
		
		return numFullElevenAces;
	}
	
	
	// Mutators
	
	/**
	 * Accepts a Card as parameter, returns nothing
	 * Adds the Card to the hand, calculates if the hand is busted
	 */
	public void addCard(Card cardIn)
	{
		theHand.add(cardIn);
		if(calculateCurrentHandValue() > 21)
		{
			isBusted = true;
		}
	}
	
	
	/**
	 * Accepts a Deck as parameter, returns nothing
	 * Puts the hand into the discard pile
	 */
	public void discardHand(Deck theDeck)
	{
		while(theHand.size() > 0)
		{
			Card theCard = theHand.remove(0);
			if(theCard.isAce())
			{
				theCard.aceToEleven();
			}
			theDeck.addToDiscard(theCard);
		}
	}
	
	
	/**
	 * Accepts an index of an ArrayList, returns nothing.
	 * Sets the value of the ace at that position to 1
	 */
	public void aceToOneAtIndex(int index)
	{
		if(theHand.get(index).isAce())
		{
			theHand.set(index, theHand.get(index).aceToOne());
		}
	}

}


