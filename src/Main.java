import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Main {

	public final static ArrayList<String> WORDS = loadCsvWords();
	
	public final static Color RED = new Color(255,102,102);
	public final static Color BLUE = new Color(102,178,255);

	public static void main(String[] args) {
		Player p1, p2;
		
		Random rand = new Random();
		boolean flip = rand.nextBoolean();
		
		//decide who starts
		if(flip) {
			p1 = new Player(RED, 9);
			p2 = new Player(BLUE, 8);
		} else {
			p1 = new Player(BLUE, 9);
			p2 = new Player(RED, 8);
		}

		System.out.print("Setting up board...");
		Board board = new Board(p1, p2);
		System.out.println("Done");
		
		createKeyImage(board);

		new BoardDisplay(board, p1, p2);

	}

	public static void createKeyImage(Board board) {
		Color[][] key = board.getColorKey();
		
		BufferedImage image = new BufferedImage(
				BoardDisplay.CARD_WIDTH*Board.WIDTH, 
				BoardDisplay.CARD_HEIGHT*Board.HEIGHT, 
				BufferedImage.TYPE_INT_RGB); 
		Graphics g = image.getGraphics();
		
		for(int x=0; x<key.length; x++) {
			Color[] column = key[x];
			for(int y=0; y<column.length; y++) {
				g.setColor(key[x][y]);
				g.fillRect(x*BoardDisplay.CARD_WIDTH, y*BoardDisplay.CARD_HEIGHT, 
						BoardDisplay.CARD_WIDTH, BoardDisplay.CARD_HEIGHT);
				g.setColor(Color.BLACK);
				g.drawString(board.getWords()[x][y], x*BoardDisplay.CARD_WIDTH+BoardDisplay.CARD_WIDTH/2, y*BoardDisplay.CARD_HEIGHT+BoardDisplay.CARD_HEIGHT/2);
			}
		}
		
		File outputFile = new File("files/key.jpg");
		try {
			ImageIO.write(image, "jpg", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> loadTxtWords() {

		System.out.print("Loading words...");

		ArrayList<String> words = new ArrayList<String>();

		Scanner scan;
		try {
			scan = new Scanner(new File("files/words"));
			while(scan.hasNextLine()) {
				words.add(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Done");

		return words;
	}
	
	public static ArrayList<String> loadCsvWords(){
		System.out.print("Loading CSV words...");

		ArrayList<String> words = new ArrayList<String>();

		Scanner scan;
		try {
			scan = new Scanner(new File("files/words.csv"));
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] lineWords = line.split(",");
				words.add(lineWords[0].toUpperCase());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Done");

		return words;
	}

}
