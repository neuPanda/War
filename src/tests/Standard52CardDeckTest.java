package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import deck.Standard52CardDeck;

public class Standard52CardDeckTest {
	
	/**
	 * creates a standard 52 card deck
	 * validates that it creates 52 cards
	 */
	@Test
	public void testStandard52CardDeck() {
		Standard52CardDeck deck = new Standard52CardDeck();
		assertEquals(52, deck.getCards().size());
	}

}
