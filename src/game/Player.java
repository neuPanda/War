package game;

public abstract class Player {

	private String name;

	/**
	 * Creates a Player with the given name
	 * @param String name
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * Gets the players name
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
}
