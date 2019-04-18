package cs1302.arcade.tetris;

import java.util.Random;

/**
 * This is the Tetris game class
 *
 */
public class Tetris implements Runnable {
	
	Board game;
	Tetrimino currentPiece;
	Random generator;
	int level;
	
	private final int rows = 22;
	private final int columns = 10;
	
	public Tetris(int level) {
		game = new Board(rows, columns);
		generator = new Random();
		this.level = level;
	}

	@Override
	public void run() {
		for (int i = 0; i < rows; i++) {
			if (game.lineFull(i)) {
				game.clearLine(i);
			}
		}
	}

}
