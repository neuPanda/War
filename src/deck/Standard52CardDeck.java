package deck;

import java.util.Random;

public class Standard52CardDeck extends Deck {

	/**
	 * Default constructor
	 */
	public Standard52CardDeck() {
		init();
	}

	/**
	 * Use for testing purposes only
	 * @param mockGen  a seeded random generator
	 */
	public Standard52CardDeck(Random mockGen) {
		super(mockGen);
		init();
	}

	/**
	 * Builds the deck
	 * 52 cards
	 * 13 of each suit
	 * card values from 2 (inclusive) to 14 (inclusive)
	 */
	private void init() {
		for (Suit suit : Suit.values()) {
			for (int cardValue = 2; cardValue <= 14; cardValue++) {
				deck.add(new PlayingCard(cardValue, suit));
			}
		}
	}

}
