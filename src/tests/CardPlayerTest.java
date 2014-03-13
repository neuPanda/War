package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import game.CardPlayer;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import deck.PlayingCard;
import deck.Suit;

public class CardPlayerTest {


	private CardPlayer player;

	/**
	 * Sets player to new card player by the name of "name" before each test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		player = new CardPlayer("name");
	}
	
	/**
	 * Verifies the player returns null with no cards in hand 
	 * Creates and adds 2 cards to the players hand
	 * Verifies that play card plays the first card
	 * Verifies that the card returns matches the card put in
	 * Verifies that the card played is removed from the players hand
	 */
	@Test
	public void testPlayCard() {
		assertNull(player.playCard());
		PlayingCard card = new PlayingCard(1, Suit.Spade);
		player.addCard(card);
		PlayingCard card2 = new PlayingCard(1, Suit.Heart);
		player.addCard(card2);
		assertEquals(card, player.playCard());
		assertEquals(card2, player.playCard());
	}

	/**
	 * Creates and adds a card to the players hand
	 * Verifies the player has cards
	 * Verifies that the card returns matches the card put in
	 */
	@Test
	public void testAddCardPlayingCard() {
		PlayingCard card = new PlayingCard(1, Suit.Spade);
		player.addCard(card);
		assertTrue(player.hasCards());
		assertEquals(card, player.playCard());
	}

	/**
	 * Creates a list of cards and adds them to players hand
	 * Verifies the player has cards
	 * Verifies that cards are returned in the order they were put into the queue, (first in, first out)
	 */
	@Test
	public void testAddCardArrayListOfPlayingCard() {
		ArrayList<PlayingCard> cards = new ArrayList<>();
		PlayingCard card = new PlayingCard(1, Suit.Spade);
		cards.add(card);
		cards.add(new PlayingCard(1, Suit.Heart));
		player.addCard(cards);
		assertEquals(card, player.playCard());
	}

	/**
	 * Validates that has cards returns false if the player doesn't have any cards
	 * Creates and adds a card to the players hand
	 * Validates that has cards returns true if the player has any cards
	 */
	@Test
	public void testHasCards() {
		assertFalse(player.hasCards());
		PlayingCard card = new PlayingCard(1, Suit.Spade);
		player.addCard(card);
		assertTrue(player.hasCards());
	}

	/**
	 * Creates and adds a list of cards to player
	 * Validates that when get hand is called, the players hand is returned
	 */
	@Test
	public void testGetHand() {
		PlayingCard card = new PlayingCard(1, Suit.Spade);
		player.addCard(card);
		ArrayList<PlayingCard> expected = new ArrayList<>();
		expected.add(card);
		assertEquals(expected, player.getHand());
	}
	
	/**
	 * Creates and adds a card to the players hand
	 * Validates that the size of the players hand is equal to the number of cards added
	 */
	@Test
	public void testGetHandSize() {
		PlayingCard card = new PlayingCard(1, Suit.Spade);
		player.addCard(card);
		assertEquals(player.getHandSize(), 1);
	}
}
