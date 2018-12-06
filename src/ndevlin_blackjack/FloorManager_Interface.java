package ndevlin_blackjack;
/**
 * 
 * @author Hasan
 *Interface that models the floor manager. Keeps track of bets for players. 
 */

//TO DO -- need to create a FloorManager variable of type FloorManager_Interface

public interface FloorManager_Interface {

	//takes bets from both players and places them in an instance variable
	public void betsPlaced (Player player, Dealer dealer);
		
	public void moneyToWinner (Betting bet);
	
}
