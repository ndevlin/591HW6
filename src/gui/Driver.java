package gui;


import ndevlin_blackjack.*;


/**
 * @author Nathan
 */
public class Driver implements Runnable
{
	
	Gui gui = new Gui();
	
	static Dealer theDealer = new Dealer();
	static Player thePlayer = new Player();
	
	static int handNumber = 1;
	static int input = 0;
	
	static boolean userExit = false;
	
	
	public static void main(String[] args) 
	{
		new Thread(new Driver()).start();
		
		
		theDealer.dealNewHand(thePlayer);
		
		thePlayer.getPlayersHand().calculateCurrentHandValue();
		theDealer.getDealerHand().calculateCurrentHandValue();
		
		

	}

	@Override
	public void run() 
	{
		while(true)
		{
			gui.repaint();
			
			
			
		}
		
	}

}
