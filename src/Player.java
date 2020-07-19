import java.awt.Color;

public class Player {

	private Color color;
	
	private int score;

	/**
	 * Initializes a player with the given color and starting score.
	 * @param color
	 * @param score
	 */
	public Player(Color color, int score) {
		this.color = color;
		this.score = score;
	}

	public void lowerScore() {
		score--;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getScore() {
		return score;
	}
	
	
}
