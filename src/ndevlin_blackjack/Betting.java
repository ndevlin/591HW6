package ndevlin_blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * 
 * @author Hasan
 *Interface to establish behavior of dealer and player placing bets. 
 *List of abstract methods to be applied.
 */

public class Betting implements Betting_Interface {

	private int bet;
	public int balance;
	
	public Betting(int balance) {
		this.balance=balance;
	}
	
	public void placeBet () {
		//scanner to get bet amount
		int betAmount = 50;
		bet = betAmount;
		balance = balance-bet;
		if(balance<0) {
			//TODO not able to play again
			System.out.print("out of money");
		}
	}
	
	public int getBet () {
		return bet;
	}

	public void addBalance(int totalTableMoney) {
		balance = balance + totalTableMoney;
	}


}


