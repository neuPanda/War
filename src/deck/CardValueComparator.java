package deck;

import java.util.Comparator;

public class CardValueComparator implements Comparator<PlayingCard> {
	
	/**
	 * Use to compare PlayingCards on their value
	 */
	public int compare(PlayingCard o1, PlayingCard o2) {
		return (o1.getValue() < o2.getValue()) ? -1 :
			(o1.getValue() == o2.getValue())? 0 :
				1;
	}
}
