package cs1302.arcade.tetris;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is the Tetris game class
 *
 */
public class Tetris extends Stage implements Runnable {
	
	Board game;
	Tetrimino currentPiece;
	Random generator;
	int level;
	
	public Tetris() {
		super();
		VBox vbox = new VBox();
		HBox tetrisMainFrame = new HBox(vbox);
		
		Scene tetrisScene = new Scene(tetrisMainFrame);
		this.setScene(tetrisScene);
		this.setHeight(600);
		this.setWidth(450);
		this.show();
	} // Tetris constructor

	@Override
	public void run() {
		game = new Board();
		generator = new Random();
	} // run

}
