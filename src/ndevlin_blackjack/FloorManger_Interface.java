package ndevlin_blackjack;
/**
 * 
 * @author Hasan
 *Interface that models the floor manager. Keeps track of bets for players. 
 */

//TO DO -- need to create a FloorManager variable of type FloorManager_Interface

public interface FloorManger_Interface {

	public void betsPlaced (Player player, Hand hand, int betAmount);
	
	public int getBetAmount (Hand hand);
	
	public int betWinner (Player player, Dealer dealer, Hand hand);
	
	public int initialBets (Player player, int betAmount);
}
