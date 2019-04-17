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

	@Override
	public void run() {
		game = new Board();
		generator = new Random();
	}

}
