package tests;

import static org.junit.Assert.*;
import game.Player;
import game.WarPlayer;

import org.junit.Test;

public class PlayerTest {

	/**
	 * Creates a string for the players name
	 * Creates a WarPlayer as a Player
	 * Validates that get name returns the players name
	 */
	@Test
	public void testGetPlayerName() {
		String name = "playerName";
		Player player = new WarPlayer(name);
		assertEquals(name, player.getName());
	}

}
