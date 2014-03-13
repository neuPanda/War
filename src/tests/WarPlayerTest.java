package tests;

import static org.junit.Assert.*;
import game.WarPlayer;

import org.junit.Before;
import org.junit.Test;

import deck.PlayingCard;
import deck.Suit;

public class WarPlayerTest {

	private WarPlayer player;
	private PlayingCard card;

	/**
	 * Creates a new player with name "name" before each test
	 * Creates and adds the card 1 of clubs to player
	 */
	@Before
	public void setUp() throws Exception {
		player = new WarPlayer("name");
		card = new PlayingCard(1, Suit.Club);
		player.addCard(card);
	}

	/**
	 * Validates flip card returns the card
	 * Validates flip card removed the card from the players hand
	 */
	@Test
	public void testFlipCard() {
		assertEquals(card, player.flipCard());
		assertFalse(player.hasCards());
	}

	/**
	 * Validates that get current card returns null if there is no current card
	 * Flips the first card
	 * Validates that get current card returns the card if there is a current card
	 */
	@Test
	public void testGetCurrentCard() {
		assertNull(player.getCurrentCard());
		player.flipCard();
		assertEquals(card, player.getCurrentCard());
	}

}
