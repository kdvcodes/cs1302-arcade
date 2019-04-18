package cs1302.arcade.tetris;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This is the Tetris game class
 *
 */
public class Tetris extends Stage {
	
	Board game;
	Tetrimino currentPiece;
	Random generator;
	int level;
	
	private final int rows = 22;
	private final int columns = 10;
	
	private final int framerate = 60;
	
	public Tetris(int level) {
		game = new Board(rows, columns);
		generator = new Random();
		this.level = level;
	}
	
	public Tetris() {
		super();
		VBox vbox = new VBox();
		HBox tetrisMainFrame = new HBox(vbox);
		
		Scene tetrisScene = new Scene(tetrisMainFrame);
		this.setScene(tetrisScene);
		this.setHeight(600);
		this.setWidth(450);
		this.show();
		Timeline t = new Timeline(new KeyFrame(Duration.millis(1000 / framerate), this::update));
	} // Tetris constructor

	public void update(ActionEvent r) {
		for (int i = 0; i < rows; i++) {
			if (game.lineFull(i)) {
				game.clearLine(i);
			}
		}
	} // run

}
