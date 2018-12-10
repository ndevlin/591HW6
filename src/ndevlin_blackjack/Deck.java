/**
 *  Written by Nathan Devlin for CIS 592 HW3
 *  
 * The Deck class represents a deck of cards. It contains ArrayLists of Card objects
 */

package ndevlin_blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Nathan
 */
public class Deck
{
	/** Holds a deck of cards from 1 to 52, in order */
	private ArrayList<Card> initialDeck;
	
	/** the current Deck, may hold fewer than 52 cards */
	private ArrayList<Card> currentDeck;
	
	
	/** holds the discarded cards	 */
	ArrayList<Card> discardPile;


	/** 
	 * Constructor, takes no arguments
	 *  Initializes the ArrayLists and shuffles the deck 
	 */
	public Deck()
	{
		initialDeck = new ArrayList<Card>(52);
		currentDeck = new ArrayList<Card>(52);
		discardPile = new ArrayList<Card>(52);
		
		for(int i = 0; i < 52; i++)
		{
			Card currentCard = new Card(i);
			initialDeck.add(currentCard);
		}
		
		currentDeck = initialDeck;
		
		Collections.shuffle(currentDeck);
	}
	
	
	// Getters and Setters
	
	/** returns the initialDeck ArrayList of Card objects */
	public ArrayList<Card> getInitialDeck() {
		return initialDeck;
	}

	/** returns the currentDeck ArrayList of Card objects */
	public ArrayList<Card> getCurrentDeck() {
		return currentDeck;
	}

	/** returns the discardPile ArrayList of Card objects */
	public ArrayList<Card> getDiscardPile() {
		return discardPile;
	}
	
	
	/** returns the currentNumCardsInDeck variable */
	public int getCurrentNumCardsInDeck() {
		return currentDeck.size();
	}
	
	
	// Mutators
	
	/**
	 * Removes a Card object from currentDeck and returns it
	 * Accepts no parameters
	 */
	Card drawCard()
	{
		if(getCurrentNumCardsInDeck() < 1)
		{
			reshuffle();
		}
		return currentDeck.remove(currentDeck.size() - 1);
	}
	
	
	/**
	 * Accepts a Card object toAdd as parameter
	 * appends it to discardPile
	 */
	void addToDiscard(Card toAdd)
	{
		discardPile.add(toAdd);
	}
	
	
	/**
	 * Reshuffles the deck
	 * Void and accepts no parameters
	 * Moves discardPile to currentDeck and shuffles it, clears discardPile
	 */
	void reshuffle()
	{
		//currentDeck = discardPile
		currentDeck.clear();
		for(int i = 0; i < discardPile.size(); i++)
		{
			currentDeck.add(discardPile.get(i));
		}
		
		Collections.shuffle(currentDeck);
		discardPile.clear();
		
		System.out.println("  ");
		System.out.println("**Shuffling**");
		System.out.println("  ");
	}
	
}


