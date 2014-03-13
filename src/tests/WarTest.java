package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import game.GameState;
import game.War;
import game.WarPlayer;

import org.junit.Before;
import org.junit.Test;

import deck.PlayingCard;
import deck.Suit;

public class WarTest {
	War war;
	ArrayList<WarPlayer> players = new ArrayList<>();

	/**
	 * Before each test
	 * Create a list of players
	 * Create a war game with the list of players
	 * Clear all players hands
	 * Create an list of cards
	 * Add cards to each player
	 * Add create and add a low card to the first player
	 * Add create and add a high card to the second player
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		WarPlayer player1 = new WarPlayer("player1");
		WarPlayer player2 = new WarPlayer("player2");

		players.add(player1);
		players.add(player2);

		war = new War(players);

		clearPlayersHand(player1);
		clearPlayersHand(player2);

		ArrayList<PlayingCard> cards = new ArrayList<>();
		cards.add(new PlayingCard(1, Suit.Spade));
		cards.add(new PlayingCard(2, Suit.Spade));
		cards.add(new PlayingCard(3, Suit.Spade));
		cards.add(new PlayingCard(4, Suit.Spade));

		ArrayList<PlayingCard> hand1 = new ArrayList<>();
		ArrayList<PlayingCard> hand2 = new ArrayList<>();

		hand1.addAll(cards);
		hand1.add(new PlayingCard(5, Suit.Spade));

		hand2.addAll(cards);
		hand2.add(new PlayingCard(6, Suit.Spade));

		player1.addCard(hand1);
		player2.addCard(hand2);
	}

	/**
	 * Removes all cards from a given players hand
	 * @param WarPlayer player
	 */
	private void clearPlayersHand(WarPlayer player) {
		while (player.hasCards()) {
			player.playCard();
		}
	}

	/**
	 * Validates that war is active while there are multiple players
	 * Validates that was is not active while there is only 1 player remaining
	 */
	@Test
	public void testIsActive() {
		assertTrue(war.isActive());
		clearPlayersHand(players.get(0));
		assertFalse(war.isActive());
	}

	/**
	 * Validates that the game starts in the NewGame state
	 * Validates that the round starts in StartRound state
	 * Validates that if there is a tie the state will be changed to MidRound
	 * Validates that when the round has been resolved the state will be changed to EndRound
	 * Validates that when the game is over it will result in an GameOver state
	 */
	@Test
	public void testPlay() {
		assertEquals(GameState.NewGame, war.getState());
		war.play();
		assertEquals(GameState.StartRound, war.getState());
		war.play();
		assertEquals(GameState.MidRound, war.getState());
		war.play();
		assertEquals(GameState.EndRound, war.getState());
		war.play();
		assertEquals(GameState.GameOver, war.getState());

	}

	/**
	 * Validates that when the round starts there are no active cards
	 * Validates that when players flip cards, they are added to the list of active cards
	 * Validates that cards are removed from active cards before the next flip
	 * Validates that all active cards are removed at the end of the round
	 */
	@Test
	public void testGetActiveCards() {
		war.play();
		assertEquals(0, war.getActiveCards().size());
		war.play();
		assertEquals(2, war.getActiveCards().size());
		war.play();
		assertEquals(2, war.getActiveCards().size());
		war.play();
		assertEquals(0, war.getActiveCards().size());
	}

	/**
	 * Validates get game state returns the current state of the game
	 */
	@Test
	public void testGetState() {
		assertEquals(GameState.NewGame, war.getState());
	}

	/**
	 * Validated collateral is empty at the start of the round
	 * Validates active cards are added to collateral
	 * Validates that in the case of a tie bonus cards are added to collateral and then the next set of active cards
	 * Validates that all cards are removed from the collateral at the end of the round
	 */
	@Test
	public void testGetCollteralSize() {
		war.play();
		assertEquals(0, war.getCollteralSize());
		war.play();
		assertEquals(2, war.getCollteralSize());
		war.play();
		assertEquals(10, war.getCollteralSize());
		war.play();
		assertEquals(0, war.getCollteralSize());
	}

	/**
	 * Validates that both players are returned if they have cards
	 * Removes all cards from a players hand
	 * Validates that only the second player is returned because the first is out of cards
	 */
	@Test
	public void testGetActivePlayers() {
		assertEquals(players, war.getActivePlayers());
		clearPlayersHand(war.getActivePlayers().get(0));
		assertEquals(players.subList(1, 2), war.getActivePlayers());
	}
}
