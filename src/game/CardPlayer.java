package game;

import java.util.ArrayList;
import java.util.List;

import deck.PlayingCard;

public class CardPlayer extends Player {
	private ArrayList<PlayingCard> hand = new ArrayList<>();

	/**
	 * Creates a CardPlayer with the given name
	 * @param String name
	 */
	public CardPlayer(String name) {
		super(name);
	}

	/**
	 * If the hand has cards, remove the first card from the hand and return it
	 * If the hand does not have any cards, return null
	 * @return PlayingCard
	 */
	public PlayingCard playCard() {
		PlayingCard value = null;
		if (!hand.isEmpty())
			value = hand.remove(0);
		return value;
	}

	/**
	 * Adds a single playing card to the bottom of the hand
	 * @param PlayingCard card
	 */
	public void addCard(PlayingCard card) {
		hand.add(card);
	}

	/**
	 * Adds a list of cards to the bottom of the hand
	 * @param List<PlayingCard> cards
	 */
	public void addCard(List<PlayingCard> cards) {
		hand.addAll(cards);
	}

	/**
	 * Returns true if the hand has cards
	 * Returns false if the hand is empty
	 * @return boolean
	 */
	public boolean hasCards() {
		return !hand.isEmpty();
	}

	/**
	 * Returns the hand of cards
	 * @return ArrayList<PlayingCard>
	 */
	public ArrayList<PlayingCard> getHand(){
		return hand;
	}
	
	/**
	 * Returns the size of the hand
	 * @return int
	 */
	public int getHandSize(){
		return hand.size();
	}

}