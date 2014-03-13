package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import deck.PlayingCard;
import deck.Standard52CardDeck;

public class DeckTest {

	private Standard52CardDeck deck1;

	/**
	 * Creates a deck with a seeded random generator before each test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		deck1 = new Standard52CardDeck(new Random(2));
	}

	/**
	 * Creates a deck to compare against
	 * Asserts that the decks are the same before shuffling
	 * Shuffles the deck
	 * Asserts that the decks are different after shuffling
	 */
	@Test
	public void testShuffle() {
		Standard52CardDeck deck2 = new Standard52CardDeck();
		assertEquals(deck1.getCards(), deck2.getCards());
		deck1.shuffle();
		assertNotEquals(deck1.getCards(), deck2.getCards());
	}

	/**
	 * Gets the first card from the deck
	 * Validates that draw card is equal to the first card
	 * Empties the Deck
	 * Validates that when there are no cards remaining null is returned
	 */
	@Test
	public void testdrawCard() {
		PlayingCard card = deck1.getCards().get(0);
		assertEquals(deck1.drawCard(), card);
		emptyDeck1();
		assertNull(deck1.drawCard());
	}
	
	/**
	 * Validates that has cards returns true when it has cards
	 * Empties the deck
	 * Validates that has cards returns false when it does not contain any cards
	 */
	@Test
	public void testhasCards() {
		assertTrue(deck1.hasCards());
		emptyDeck1();
		assertFalse(deck1.hasCards());
	}

	/**
	 * Removes all cards from deck1
	 */
	private void emptyDeck1() {
		while (deck1.getCards().size() > 0) {
			deck1.drawCard();
		}
	}

}
