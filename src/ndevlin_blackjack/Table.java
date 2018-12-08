/**
 *  Written by Nathan Devlin for CIS 592 HW3
 *  
 * This is the Driver class of the project, Table, since everything happens in(on)it.
 * This is the non-extra credit version which allows a human player to choose themselves
 * what actions they would like to take
 */

package ndevlin_blackjack;

import java.util.Scanner;

/**
 * @author Nathan
 */
public class Table
{

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

		Dealer theDealer = new Dealer();
		Player thePlayer = new Player();
		
		int handNumber = 1;
		int input = 0;
		
		boolean userExit = false;
		
		System.out.println(" ");
		System.out.println("Welcome to BlackJack!");
		System.out.println(" ");
		
		while(userExit != true)
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
			
			System.out.println("What would you like to do? 0 to stay, 1 to hit: ");
			input = scan.nextInt();
			while(input != 0 && input != 1)
			{
				System.out.println("Please enter 0 or 1: ");
				input = scan.nextInt();
			}
			
			while(input == 1)
			{
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
					System.out.println("What would you like to do? 0 to stay, 1 to hit: ");
					input = scan.nextInt();
					while(input != 0 && input != 1)
					{
						System.out.println("Please enter 0 or 1: ");
						input = scan.nextInt();
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
				System.out.println("Player wins!");
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
			
			System.out.println("Would you like to play another hand? 1 to play, any other to exit");
			input = scan.nextInt();
			if(input == 1)
			{
				userExit = false;
			}
			else
			{
				userExit = true;
			}
			System.out.println("  ");
			System.out.println("  ");
		}
		
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Thank you for playing! ");
		System.out.println(" ");
		
		scan.close();
		
	}

}




