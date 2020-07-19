import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Board {
	
	static final int WIDTH = 5;
	static final int HEIGHT = 5;

	private String[][] words;
	/**
	 * Red is one team, blue is another. Light gray is neutral, and black is assassin.
	 */
	private Color[][] colorKey;
	
	private boolean[][] visibility;

	Random rand = new Random();

	/**
	 * Creates a random board with nothing visible
	 */
	public Board(Player p1, Player p2) {

		visibility = new boolean[WIDTH][HEIGHT];
		resetVisibility();
		
		words = new String[WIDTH][HEIGHT];
		randomizeWords();

		colorKey = new Color[WIDTH][HEIGHT];
		randomizeColors(p1, p2);
		
	}

	/**
	 * randomizes the words on the board
	 */
	private void randomizeWords() {
		ArrayList<String> wordsAdded = new ArrayList<String>();
		for(int i=0; i<words.length; i++) {
			String[] row = words[i];
			for(int j=0; j<row.length; j++) {
				String randomWord = Main.WORDS.get(rand.nextInt(Main.WORDS.size()-1));
				if(!wordsAdded.contains(randomWord)) {
					words[i][j] = randomWord;
					wordsAdded.add(words[i][j]);
				} else {
					if(j!=0) {
						j--;
					} else {
						i--;
						j = row.length-1;
					}
				}
			}
		}
	}

	/**
	 * Randomizes the colors on the board (the starting color has one more square).
	 */
	private void randomizeColors(Player p1, Player p2) {
		resetColors();
		
		randomizeColor(p1.getColor(), p1.getScore());

		randomizeColor(p2.getColor(), p2.getScore());
		
		//assassin
		randomizeColor(Color.black, 1);
		
		
		
	}

	/**
	 * Generates random locations for a color. Will only change color
	 * spaces that are neutral (light gray). The number of colors to generate is given.
	 * @param color
	 * @param num
	 */
	private void randomizeColor(Color color, int num) {
		for(int i=0; i<num; i++) {
			int x = rand.nextInt(5);
			int y = rand.nextInt(5);
			if(colorKey[x][y] == Color.lightGray) {
				colorKey[x][y] = color;
			} else {
				i--;
			}
		}
	}
	
	/**
	 * sets all the colors to light gray.
	 */
	private void resetColors() {
		for(int i=0; i<colorKey.length; i++) {
			for(int j=0; j<colorKey[0].length; j++) {
				colorKey[i][j] = Color.lightGray;
			}
		}
	}
	
	/**
	 * Sets the color visibility of all cards to false.
	 */
	private void resetVisibility() {
		for(int i=0; i<visibility.length; i++) {
			boolean[] row = visibility[i];
			for(int j=0; j<row.length; j++) {
				visibility[i][j] = false;
			}
		}
	}

	public String[][] getWords() {
		return words;
	}

	public boolean getVisibility(int x, int y) {
		return visibility[x][y];
	}

	public Color[][] getColorKey() {
		return colorKey;
	}

}
