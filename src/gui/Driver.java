package gui;


import ndevlin_blackjack.*;


/**
 * @author Nathan
 */
public class Driver implements Runnable
{
	
	Gui gui = new Gui();
	
	
	public static void main(String[] args) 
	{
		new Thread(new Driver()).start();

	}

	@Override
	public void run() 
	{
		
		
	}

}
