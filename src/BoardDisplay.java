import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class BoardDisplay extends JFrame {

	private Board board;

	private JTextField p1Score;
	private JTextField p2Score;

	static final int CARD_WIDTH = 150;
	static final int CARD_HEIGHT = 100;
	/**
	 * The size of one side of a single box of the key (they're squares).
	 */
	static final int KEY_SIZE = 150;

	public BoardDisplay(Board board, Player p1, Player p2) {
		this.board = board;

		p1Score = new JTextField(""+p1.getScore());
		p1Score.setBackground(p1.getColor());
		p1Score.setHorizontalAlignment(JTextField.CENTER);

		p2Score = new JTextField(""+p2.getScore());
		p2Score.setBackground(p2.getColor());
		p2Score.setHorizontalAlignment(JTextField.CENTER);

		addButtons(p1, p2);
		add(p1Score);
		add(p2Score);

		initializeGraphics();

	}

	/**
	 * Creates a button for every word on the Board, going row by row
	 * to fit the grid format
	 */
	private void addButtons(Player p1, Player p2) {
		for(int y=0; y<board.getWords().length; y++) {
			String[] row = board.getWords()[y];
			for(int x=0; x<row.length; x++) {
				JButton button = new JButton(board.getWords()[x][y]);

				//when the button is clicked
				Color color = board.getColorKey()[x][y];
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(button.getBackground() != p1.getColor() && button.getBackground() != p2.getColor()) {
							button.setBackground(color);
							if(color == p1.getColor()) {
								p1.lowerScore();
								p1Score.setText(""+p1.getScore());
							} else if(color == p2.getColor()) {
								p2.lowerScore();
								p2Score.setText(""+p2.getScore());
							}
						}
					}
				});

				add(button);
			}
		}
	}

	/**
	 * Initializes the frame.
	 */
	private void initializeGraphics(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(Board.HEIGHT+1, Board.WIDTH)); //+1 for the score board
		setSize(Board.WIDTH*CARD_WIDTH, (Board.HEIGHT+1)*CARD_HEIGHT);
		setVisible(true);

	}

}
