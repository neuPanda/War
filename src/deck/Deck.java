package deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Deck {
	protected ArrayList<PlayingCard> deck = new ArrayList<>();
	private Random gen = new Random();

	/**
	 * Use for testing purposes only
	 * @param Random mockGen (a seeded random generator)
	 */
	protected Deck(Random mockGen) {
		gen = mockGen;
	}
	/**
	 * Default constructor
	 */
	protected Deck() {
	}

	/**
	 * Shuffle the cards using the Collections API shuffle passing in the decks random generator
	 */
	public void shuffle() {
		Collections.shuffle(deck, gen);
	}

	/**
	 * Returns the deck of cards
	 * @return ArrayList<PlayingCard>
	 */
	public ArrayList<PlayingCard> getCards() {
		return deck;
	}

	/**
	 * If the deck has cards, remove the first card from the deck and return it
	 * If the deck does not have any cards, return null
	 * @return PlayingCard
	 */
	public PlayingCard drawCard() {
		PlayingCard card = null;
		if (this.hasCards())
			card = deck.remove(0);
		return card;
	}

	/**
	 * Returns true if the deck has cards
	 * Returns false if the deck is empty
	 * @return boolean
	 */
	public boolean hasCards() {
		return !deck.isEmpty();
	}

}
