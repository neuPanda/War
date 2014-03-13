package game;

import java.util.ArrayList;
import java.util.Collections;

import deck.Deck;
import deck.PlayingCard;
import deck.Standard52CardDeck;

public class War extends Game<WarPlayer> {

	private GameState state = GameState.NewGame;
	private ArrayList<PlayingCard> collateral = new ArrayList<>();
	private ArrayList<WarPlayer> roundActivePlayers;
	private ArrayList<PlayingCard> activeCards;

	/**
	 * Creates a game of War with the players given
	 * @param ArrayList<WarPlayer> players
	 */
	public War(ArrayList<WarPlayer> players) {
		super(players);
	}

	/**
	 * Retrieves a list of all cards that are active for comparison currently.
	 * @return ArrayList<PlayingCard>
	 */
	public ArrayList<PlayingCard> getActiveCards() {
		return activeCards;
	}

	/**
	 * Retrieves the current state of the game
	 * @return GameState
	 */
	public GameState getState() {
		return state;
	}

	/**
	 * Retrieves the current count of cards put into the field of play
	 * @return int
	 */
	public int getCollteralSize() {
		return collateral.size();
	}

	/**
	 * Takes a deck and deals cards evenly to the players 
	 * @param Deck deck
	 */
	private void dealCards(Deck deck) {
		while (deck.getCards().size() / getPlayers().size() > 0) {
			for (WarPlayer player : getPlayers()) {
				player.addCard(deck.drawCard());
			}
		}
	}

	/**
	 * Returns all active players
	 * @return ArrayList<WarPlayer>
	 */
	public ArrayList<WarPlayer> getActivePlayers() {
		return getActivePlayers(getPlayers());
	}

	/**
	 * Returns all active players in the list of given players
	 * @param ArrayList<WarPlayer> players
	 * @return ArrayList<WarPlayer>
	 */
	private ArrayList<WarPlayer> getActivePlayers(ArrayList<WarPlayer> players) {
		ArrayList<WarPlayer> activePlayers = new ArrayList<>();
		for (WarPlayer player : players) {
			if (player.hasCards())
				activePlayers.add(player);
		}
		return activePlayers;
	}

	/**
	 * builds a deck
	 * shuffles the deck
	 * deals the cards
	 */
	@Override
	protected void init() {
		Standard52CardDeck deck = new Standard52CardDeck();
		deck.shuffle();
		dealCards(deck);
	}

	/**
	 * if there are more than 1 players active in the game return true
	 * if there are 0 or 1 players active in the game return false
	 */
	@Override
	public boolean isActive() {
		return getActivePlayers().size() > 1;
	}

	/**
	 * Handles logical flow of game
	 */
	@Override
	public boolean play() {
		for (WarPlayer player : getPlayers()) {
			player.clearCurrentCard();
		}
		activeCards = new ArrayList<>();
		switch (state) {
		case MidRound:
			midRound();
		case StartRound:
			startRound();
			break;
		case EndRound:
			endRound();
			if (!isActive()) {
				state = GameState.GameOver;
				break;
			}
		case NewGame:
			state = GameState.StartRound;
			roundActivePlayers = getActivePlayers();
			break;
		default:
			break;
		}
		return false;
	}

	/**
	 * shuffles cards played during the round
	 * gives the cards played during the round to the player who won the round
	 * resets the cards played during the round 
	 */
	private void endRound() {
		Collections.shuffle(collateral);
		roundActivePlayers.get(0).addCard(collateral);
		collateral.clear();
		roundActivePlayers = getActivePlayers();
	}

	/**
	 * Flips cards
	 * Determines winning player(s)
	 * Adds active cards to collateral
	 * Determines next game state 
	 * 	Mid round if there is a tie
	 * 	End round if there is a victor 
	 */
	private void startRound() {
		flipCards();
		ArrayList<Integer> indices = analizeAttack(activeCards);
		roundActivePlayers = getRemainingPlayers(roundActivePlayers, indices);
		collateral.addAll(activeCards);
		if (roundActivePlayers.size() > 1) 
			state = GameState.MidRound;
		else 
			state = GameState.EndRound;
	}

	/**
	 * Adds bonus cards to collateral
	 */
	private void midRound() {
		if (!iDeclareWar(collateral, roundActivePlayers)) 
			roundActivePlayers = getActivePlayers(roundActivePlayers);
	}

	/**
	 * Gets the next card from each player
	 */
	private void flipCards() {
		for (WarPlayer player : roundActivePlayers) {
			activeCards.add(player.flipCard());
		}
	}

	/**
	 * Takes in the players from the battle and index of winning players
	 * Returns list of winning players
	 * @param ArrayList<WarPlayer> activePlayers
	 * @param ArrayList<Integer> indices
	 * @return ArrayList<WarPlayer>
	 */
	private ArrayList<WarPlayer> getRemainingPlayers(ArrayList<WarPlayer> activePlayers, ArrayList<Integer> indices) {
		ArrayList<WarPlayer> remainingPlayers = new ArrayList<>();
		for (Integer index : indices) {
			WarPlayer player = activePlayers.get(index);
			remainingPlayers.add(player);
		}
		return remainingPlayers;
	}

	/**
	 * Takes in currently active cards (attack)
	 * Returns the index of the max cards
	 * @param ArrayList<PlayingCard> attack
	 * @return ArrayList<Integer>
	 */
	private ArrayList<Integer> analizeAttack(ArrayList<PlayingCard> attack) {
		ArrayList<Integer> attackValues = getAttackValues(attack);
		Integer maxValue = Collections.max(attackValues);
		ArrayList<Integer> indices = indicesOfMaxCards(attackValues, maxValue);
		return indices;
	}

	/**
	 * Takes in a list of cards
	 * Returns a list of the values of the cards
	 * @param ArrayList<PlayingCard> attack
	 * @return ArrayList<Integer>
	 */
	private ArrayList<Integer> getAttackValues(ArrayList<PlayingCard> attack) {
		ArrayList<Integer> attackValues = new ArrayList<>();
		for (PlayingCard card : attack) {
			attackValues.add(card != null ? card.getValue() : 0);
		}
		return attackValues;
	}

	/**
	 * Takes in a list of values
	 * Returns the indices of the max values
	 * @param ArrayList<Integer> attackValues
	 * @param Integer maxValue
	 * @return ArrayList<Integer>
	 */
	private ArrayList<Integer> indicesOfMaxCards(ArrayList<Integer> attackValues, Integer maxValue) {
		ArrayList<Integer> indices = new ArrayList<>();
		while (Collections.frequency(attackValues, maxValue) > 0) {
			int index = attackValues.indexOf(maxValue);
			indices.add(index);
			attackValues.set(index, -1);
		}
		return indices;
	}

	/**
	 * While both players have cards, each player adds a card to collateral, max of 3
	 * if a player only has 1 card left, all players stop adding cards
	 * @param ArrayList<PlayingCard> collateral
	 * @param remainingPlayers
	 * @return ArrayList<WarPlayer> remainingPlayers
	 */
	private boolean iDeclareWar(ArrayList<PlayingCard> collateral, ArrayList<WarPlayer> remainingPlayers) {
		boolean value = true;
		for (int i = 0; i < 3; i++) {
			for (WarPlayer player : remainingPlayers) {
				value = player.hasCards();
				if (!value)
					break;
			}
			for (CardPlayer cardPlayer : remainingPlayers) {
				collateral.add(cardPlayer.playCard());
			}
		}
		return value;
	}
}