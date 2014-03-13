package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import game.Game;
import game.War;
import game.WarPlayer;

import org.junit.Test;

public class GameTest {
	
	/**
	 * Creates a list of players
	 * Adds a player by the name of "name" to the list
	 * Creates a new WarGame as Game with the list of payers
	 * Validates that the list of players is returned from get players
	 */
	@Test
	public void testGetPlayers() {
		ArrayList<WarPlayer> players = new ArrayList<>();
		players.add(new WarPlayer("name"));
		Game<WarPlayer> game = new War(players);
		assertEquals(players, game.getPlayers());;
	}

}
