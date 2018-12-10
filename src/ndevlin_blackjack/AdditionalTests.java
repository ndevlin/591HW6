package ndevlin_blackjack;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * This class is meant to test additional methods beyond the tests that have been written inside the other classes themselves.
 *
 */
public class AdditionalTests {

		/**
		 * This method tests that the default bet is 50.
		 */
		@Test
		public void testDefaultPlaceBet() {
			Betting testBetting = new Betting(100);
			testBetting.placeBet();
			assertEquals(50, testBetting.getBet());
		}
		
		/**
		 * Tests that the default balance is 100.
		 */
		@Test
		public void testDefaultBalance() {
			Betting testBetting = new Betting(100);
			assertEquals(100, testBetting.balance);
		}
		
		/**
		 * Tests that the addBalance() method works correctly
		 */
		@Test
		public void testAdjustedBalance() {
			Betting testBetting = new Betting(100);
			testBetting.addBalance(50);
			assertEquals(150, testBetting.balance);
		}
		
		/**
		 * Tests that the deck is correctly initialized to have 52 cards.
		 */
		@Test
		public void testInitialDeckSize() {
			Deck testDeck = new Deck();
			assertEquals(52, testDeck.getCurrentNumCardsInDeck());
		}
		
		/**
		 * Tests that the discard pile is initially zero.
		 */
		@Test
		public void testDiscardSize() {
			Deck testDeck = new Deck();
			assertEquals(0, testDeck.discardPile.size());
			
		}
		
		/**
		 * Tests that "current deck" variable is initially 52.
		 */
		@Test
		public void testCurrentDeckSize() {
			Deck testDeck = new Deck();
			assertEquals(52, testDeck.getCurrentDeck().size());
			
		}
		
		/**
		 * Tests functionality of adding to discard pile.
		 */
		@Test
		public void testAddToDiscard() {
			Deck testDeck = new Deck();
			Card newCard = new Card(9);
			testDeck.discardPile.add(newCard);
			assertEquals(1, testDeck.getDiscardPile().size());
		}
		
		/**
		 * Tests that "current deck" is updated after drawing a card.
		 */
		@Test
		public void testCurrentAfterDraw() {
			Deck testDeck = new Deck();
			testDeck.drawCard();
			assertEquals(51, testDeck.getCurrentDeck().size());
		}
		
		/**
		 * Tests that the reshuffle() method correctly clears the discard pile.
		 */
		@Test
		public void testShufflingClearsDiscard() {
			Deck testDeck = new Deck();
			Card newCard = new Card(9);
			testDeck.discardPile.add(newCard);
			testDeck.reshuffle();
			assertEquals(0, testDeck.getDiscardPile().size());
		}
}
