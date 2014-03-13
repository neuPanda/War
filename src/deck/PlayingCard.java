package deck;

public class PlayingCard {

	private int Value;
	private Suit Suit;

	/**
	 * Returns the cards value
	 * @return int
	 */
	public int getValue() {
		return Value;
	}

	/**
	 * Returns the cards Suit
	 * @return Suit
	 */
	public Suit getSuit() {
		return Suit;
	}

	/**
	 * Create a card of given value and given suit
	 * @param int value
	 * @param Suit suit
	 */
	public PlayingCard(int value, Suit suit) {
		Value = value;
		Suit = suit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Suit == null) ? 0 : Suit.hashCode());
		result = prime * result + Value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayingCard other = (PlayingCard) obj;
		if (Suit != other.Suit)
			return false;
		if (Value != other.Value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Value+ " of " + Suit+"s";
	}
	
	

}
