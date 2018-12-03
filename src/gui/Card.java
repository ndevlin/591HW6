package gui;

public class Card {
	
	int card_value;
	String suit;
	boolean card_used = false;
	String symbol;
	String name;
	
	public Card(int card_value, String suit) {
		this.card_value = card_value;
		this.suit = suit;
		
		if(card_value < 11) {
			symbol = Integer.toString(card_value);
			name = Integer.toString(card_value);
		} else if(card_value == 11) {
			symbol = "J";
			name = "Jack";
		} else if (card_value == 12) {
			symbol = "Q";
			name = "Queen";
		} else if (card_value == 13) {
			symbol = "K";
			name = "King";
		} else {
			symbol = "A";
			name = "Ace";
		}
		
		System.out.println("Card: "+name+" of "+suit+" was created.");
	}

}
