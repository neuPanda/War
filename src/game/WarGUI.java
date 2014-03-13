package game;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import deck.PlayingCard;

@SuppressWarnings("serial")
public class WarGUI extends JFrame {
	JButton nextRoundButton = new NextRoundButton();
	JTextPane StackDisplay = new JTextPane();
	private War war;

	public WarGUI() {
		super("War");
		setupDisplay();
		add(nextRoundButton);
		add(StackDisplay);
		war = new War(setupPlayers(getPlayerCount()));
		setVisible(true);

	}

	/**
	 * Sets up the Display
	 */
	private void setupDisplay() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 600);
		setResizable(false);
		setLayout(new GridLayout());
	}

	/**
	 *  Creates a display that requests player count and returns the result
	 * @return int
	 */
	private static int getPlayerCount() {

		String inputString = JOptionPane.showInputDialog("How many players? (2-4)");
		int input;
		try {
			input = Integer.parseInt(inputString);
			if (input < 2 || input > 4) 
				input = getPlayerCount();
		} catch (NumberFormatException e) {
			input = getPlayerCount();
		}
		return input;
	}

	/**
	 * Sets up as many player players as identified by the input
	 * @param playerCount
	 * @return ArrayList<WarPlayer>
	 */
	private ArrayList<WarPlayer> setupPlayers(int playerCount) {
		ArrayList<WarPlayer> players = createPlayers(playerCount);
		createPlayerDisplays(players);
		return players;
	}

	/**
	 * Creates a player displays for each given player
	 * @param ArrayList<WarPlayer> players
	 */
	private void createPlayerDisplays(ArrayList<WarPlayer> players) {
		for (WarPlayer player : players) {
			JPanel playerDispalay = new PlayerDisplay(player);
			this.add(playerDispalay);
		}
	}

	/**
	 * Creates a list of players 
	 * @param int playerCount
	 * @return ArrayList<WarPlayer>
	 */
	private ArrayList<WarPlayer> createPlayers(int playerCount) {
		ArrayList<WarPlayer> players = new ArrayList<>();
		for (int i = 1; i <= playerCount; i++) {
			players.add(new WarPlayer(JOptionPane.showInputDialog("Display name for player" + i)));
		}
		return players;
	}

	/**
	 * Used to represent player data
	 */
	private class PlayerDisplay extends JPanel {

		JTextPane playerNameDisplay = new JTextPane();
		JTextPane playerScoreDisplay = new JTextPane();
		JTextPane playerCardDisplay = new JTextPane();
		WarPlayer player;

		/**
		 * Creates a display for the given player
		 * @param WarPlayer player
		 */
		public PlayerDisplay(WarPlayer player) {
			this.player = player;
			this.add(playerNameDisplay);
			this.add(playerScoreDisplay);
			this.add(playerCardDisplay);

			playerNameDisplay.setText("Player Name: " + player.getName());
			playerCardDisplay.setText("Current Card: " + player.getCurrentCard());
		}

		@Override
		public void paint(Graphics g) {
			playerScoreDisplay.setText("Player Score: " + player.getHandSize());
			PlayingCard currentCard = player.getCurrentCard();
			playerCardDisplay.setText("Current Card: " + (currentCard == null ? "" : currentCard));
			super.paint(g);
		}

	}

	private class NextRoundButton extends JButton implements ActionListener {

		/**
		 * Creates a button used for playing the game
		 */
		public NextRoundButton() {
			super("Start Game");
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			next();
			changeButtonText();
		}

		/**
		 * based on the game state, changes the display of the button
		 */
		private void changeButtonText() {
			switch (war.getState()) {
			case StartRound:
				this.setText("Flip Cards");
				break;
			case MidRound:
				this.setText("I Declare War");
				break;
			case EndRound:
				this.setText("Next Round");
				break;
			case GameOver:
				this.setText("Game Over");
				nextRoundButton.setEnabled(false);
				ArrayList<WarPlayer> players = war.getActivePlayers();
				if (players.size() == 1)
					this.setText("Winner is " + players.get(0).getName());
				else if (players.size() == 0) 
					this.setText("Game is a Draw");
				break;
			default:
				nextRoundButton.setEnabled(false);
				this.setText("ERROR");
				break;
			}
		}

	}

	/**
	 * calls the plays the game and updates the display accordingly
	 */
	private void next() {
		war.play();
		StackDisplay.setText("Battlefield size: " + war.getCollteralSize());
		if (!war.isActive()) 
			nextRoundButton.setEnabled(false);
		this.paintAll(getGraphics());
	}

	
	public static void main(String[] args) {
		new WarGUI();
	}
}
