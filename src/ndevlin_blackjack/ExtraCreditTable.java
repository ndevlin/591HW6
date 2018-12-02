/**
 *  Written by Nathan Devlin for CIS 592 HW3
 *  
 *  This is the Extra Credit version of the Driver class of the project.
 * 
 *  It has a computer version of the human player, and implements a strategy to 
 *  maximize its number of wins as elaborated upon in the Extra Credit txt
 * 
 */

package ndevlin_blackjack;

import java.util.Scanner;

/**
 * @author Nathan
 */
public class ExtraCreditTable
{

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		Dealer theDealer = new Dealer();
		Player thePlayer = new Player();
		
		int dealerSupposedValue = 0;
		
		int handNumber = 1;
		int numberOfTimesToPlay = 0;
		
		
		int input = 0;
		
		System.out.println(" ");
		System.out.println("Welcome to BlackJack!");
		System.out.println(" ");
		
		
		System.out.println("How many hands would you like to play? ");
		numberOfTimesToPlay = scan.nextInt();
		
		
		
		while(handNumber <= numberOfTimesToPlay)
		{
			
			System.out.println("Hand #" + handNumber + ":");
			System.out.println(" ");
			
			theDealer.dealNewHand(thePlayer);
			
			thePlayer.getPlayersHand().calculateCurrentHandValue();
			theDealer.getDealerHand().calculateCurrentHandValue();
			
			System.out.println("Dealer's cards: ");
			System.out.println("      ?      ");
			System.out.println(theDealer.getVisibleCard().getName());
			
			System.out.println(" ");
			System.out.println("Your cards: ");
			for(int i = 0; i < thePlayer.getPlayersHand().getSize(); i++)
			{
				System.out.println(thePlayer.getPlayersHand().getCardAtIndex(i).getName());
			}
			System.out.println(" ");
			
			System.out.println("What would you like to do? ");
			
			
			
			
			////////// Strategy Algorithm
			dealerSupposedValue = theDealer.getVisibleCard().getValue() + 10;
			
			if(thePlayer.getPlayersHand().calculateCurrentHandValue() <= 11)
			{
				input = 1;	// hit
			}
			else
			{
				if(dealerSupposedValue == 15 || dealerSupposedValue == 16)
				{
					input = 0; // stay
				}
				else if (dealerSupposedValue == 21)
				{
					if(thePlayer.getPlayersHand().calculateCurrentHandValue() < 20)
					{
						input = 1; // hit
					}
					else
					{
						input = 0; // stay
					}
				}
				else if(dealerSupposedValue > 16 && dealerSupposedValue < 21)
				{
					if(thePlayer.getPlayersHand().calculateCurrentHandValue() < 17)
					{
						input = 1; // hit
					}
					else
					{
						input = 0;  // stay
					}
				}
				else if(dealerSupposedValue < 15)
				{
					if(thePlayer.getPlayersHand().calculateCurrentHandValue() < dealerSupposedValue)
					{
						input = 1; // hit
					}
					else
					{
						input = 0;  // stay
					}
				}
				else
				{
					input = 0;  // stay
				}
			}
			
			
			while(input == 1)
			{
				System.out.println("Hit ");
				
				if(thePlayer.getPlayersHand().calculateCurrentHandValue() <= 21)
				{
					System.out.println(theDealer.playerHit(thePlayer).getName());
				}
				if(thePlayer.getPlayersHand().calculateCurrentHandValue() > 21)
				{
					input = -1;		//To indicate a bust
				}
				else
				{
					System.out.println("What would you like to do? ");
					
					dealerSupposedValue = theDealer.getVisibleCard().getValue() + 10;
					
					if(thePlayer.getPlayersHand().calculateCurrentHandValue() <= 11)
					{
						input = 1;	// hit
					}
					else
					{
						if(dealerSupposedValue == 15 || dealerSupposedValue == 16)
						{
							input = 0; // stay
						}
						else if (dealerSupposedValue == 21)
						{
							if(thePlayer.getPlayersHand().calculateCurrentHandValue() < 20)
							{
								input = 1; // hit
							}
							else
							{
								input = 0; // stay
							}
						}
						else if(dealerSupposedValue > 16 && dealerSupposedValue < 21)
						{
							if(thePlayer.getPlayersHand().calculateCurrentHandValue() < 17)
							{
								input = 1; // hit
							}
							else
							{
								input = 0;  // stay
							}
						}
						else if(dealerSupposedValue < 15)
						{
							if(thePlayer.getPlayersHand().calculateCurrentHandValue() < dealerSupposedValue)
							{
								input = 1; // hit
							}
							else
							{
								input = 0;  // stay
							}
						}
						else
						{
							input = 0;  // stay
						}
					}
				}
			}
			
			if(input == -1)
			{
				System.out.println(" ");
				System.out.println("Bust. ");
				System.out.println(" ");
			}
			if(input == 0)
			{
				System.out.println("Stay");
			}
			theDealer.dealersTurn(thePlayer);
			System.out.println("The Dealer's Cards: ");
			for(int i = 0; i < theDealer.getDealerHand().getSize(); i++)
			{
				System.out.print(theDealer.getDealerHand().getCardAtIndex(i).getName());
				System.out.print("  ");
			}
			theDealer.getDealerHand().calculateCurrentHandValue();
			System.out.println("   Value: " + theDealer.getDealerHand().calculateCurrentHandValue());
			
			
			System.out.println("Your Cards: ");
			for(int i = 0; i < thePlayer.getPlayersHand().getSize(); i++)
			{
				System.out.print(thePlayer.getPlayersHand().getCardAtIndex(i).getName());
				System.out.print("  ");
			}
			thePlayer.getPlayersHand().calculateCurrentHandValue();
			System.out.println("  Value: " + thePlayer.getPlayersHand().calculateCurrentHandValue());
			System.out.println(" ");
			
			if(theDealer.resultOfLastRound() == 0)
			{
				System.out.println("Push.  ");
			}
			else if(theDealer.resultOfLastRound() == 1)
			{
				System.out.println("Player wins! ");
			}
			else if(theDealer.resultOfLastRound() == 2)
			{
				System.out.println("Dealer wins");
			}
			else if(theDealer.resultOfLastRound() == -1)
			{
				System.out.println(" ");
			}
			System.out.println(" ");
			
			System.out.println("Current win record: ");
			System.out.println("Player: " + thePlayer.getCurrentWins() +
					"    Dealer: " + theDealer.getDealerScore());
			
			System.out.println(" ");
			
			
			System.out.println(" ");
			
			handNumber++;
			
			System.out.println("  ");
		}
		
		System.out.println("  ");
		System.out.println("Total Hands played: " + (handNumber - 1));
		
		System.out.println("Total win record: ");
		System.out.println("Player: " + theDealer.getPlayerScore() +
				"    Dealer: " + theDealer.getDealerScore() +
				"    Push: " + (handNumber - 1 - theDealer.getPlayerScore() - theDealer.getDealerScore()));
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Thank you for playing! ");
		System.out.println(" ");
		
		
		scan.close();
	}

}


