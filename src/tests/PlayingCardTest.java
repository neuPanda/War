package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import deck.PlayingCard;
import deck.Suit;

public class PlayingCardTest {

	private PlayingCard card1;
	private PlayingCard card2;
	private PlayingCard card3;

	/**
	 * Creates 2 identical cards before each test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		card1 = new PlayingCard(1, Suit.Spade);
		card2 = new PlayingCard(1, Suit.Spade);
		card3 = new PlayingCard(2, Suit.Spade);
	}

	/**
	 * Validates get value returns the value of the card
	 */
	@Test
	public void testGetValue() {
		assertEquals(1, card1.getValue());
	}

	/**
	 * Validates get suit returns the suit of the card
	 */
	@Test
	public void testGetSuit() {
		assertEquals(Suit.Spade, card1.getSuit());
	}

	/**
	 * Validates that the hash codes for 2 identical cards are the same
	 */
	@Test
	public void testHashCode() {
		assertEquals(card1.hashCode(), card2.hashCode());
	}

	/**
	 * Validates that when 2 cards are identical then the equals will return true
	 * Validates that when 2 cards are not identical then the equals will return false
	 */
	@Test
	public void testEqualsObject() {
		assertEquals(card1, card2);
		assertNotEquals(card1, card3);
	}
	
	/**
	 * Validates the string output of the card will be "(card value) of (card suit)s"
	 */
	@Test
	public void testToString(){
		assertEquals("1 of Spades", card1.toString());
	}

}
