package cs1302.arcade.tetris;

import java.io.File;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This is the Tetris game class
 *
 */
public class Tetris extends Stage {
	
	Group game;
	Board board;
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
		board = new Board(rows, columns);
		game = new Group(new ImageView("/tetris/background.png"));
		game.getChildren().add(board);
		generator = new Random();
		Scene tetrisScene = new Scene(game);
		this.setScene(tetrisScene);
		this.sizeToScene();
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
