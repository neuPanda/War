package game;

import deck.PlayingCard;

public class WarPlayer extends CardPlayer {

	private PlayingCard current;

	/**
	 * Creates a WarPlayer with the given name
	 * @param String name
	 */
	public WarPlayer(String name) {
		super(name);
	}
	
	/**
	 * sets the current card to the card just played
	 * returns the current card
	 * @return PlayingCard
	 */
	public PlayingCard flipCard(){
		current = playCard();
		return getCurrentCard();
	}

	/**
	 * returns the current card
	 * @return PlayingCard
	 */
	public PlayingCard getCurrentCard() {
		return current;
	}

	/**
	 * sets currentCard to null
	 */
	public void clearCurrentCard() {
		current= null;
	}
}
