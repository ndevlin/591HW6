package ndevlin_blackjack;


public class Currency {

	private BillSize type;
	private MoneyValues value;
	
	//constructing assigning bill type and value
	public Currency(BillSize type, MoneyValues value) {
		this.type=type;
		this.value=value;
	}
	
	//printing string values and bill type for each currency
	public String toString() {
		return this.type.toString() + "-" + this.value.toString();
	}
	
	public MoneyValues getMoneyValues() {
		return this.value;
	}
	
	
}
