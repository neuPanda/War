package game;

import java.util.ArrayList;

public abstract class Game<T extends Player> {

	private ArrayList<T> players;

	/**
	 * Creates a game with given players
	 * Follows the implementation specific initialization
	 * @param ArrayList<T> players
	 */
	public Game(ArrayList<T> players) {
		this.players = players;
		init();
	}

	/**
	 * Returns a list of players in the game
	 * @return players
	 */
	public ArrayList<T> getPlayers() {
		return players;
	}


	protected abstract void init();
	public abstract boolean isActive();
	public abstract boolean play();
} 