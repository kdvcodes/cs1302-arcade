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
import javafx.scene.input.KeyEvent;
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
	Timeline t;
	
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
		currentPiece = new Tetrimino(randomShape(), board);
		tetrisScene.setOnKeyPressed(this::move);
		this.setScene(tetrisScene);
		this.sizeToScene();
		this.show();
		t = new Timeline(new KeyFrame(Duration.millis(799), this::drop));
		t.setCycleCount(Timeline.INDEFINITE);
		t.play();
	} // Tetris constructor
	
	private void lock() {
		t.stop();
		for (int i = 0; i < rows; i++) {
			if (board.lineFull(i)) {
				board.clearLine(i);
			}
		}
		currentPiece = new Tetrimino(randomShape(), board);
		t.play();
	}
	
	private void drop(ActionEvent e) {
		if (!currentPiece.drop()) {
			lock();
		}
	}
	
	private void move(KeyEvent ke) {
		switch (ke.getCode()) {
		case RIGHT:
			currentPiece.right();
			break;
		case LEFT:
			currentPiece.left();
			break;
		case DOWN:
			drop(null);
			break;
		case Z:
			currentPiece.rotate(1);
			break;
		case X:
			currentPiece.rotate(-1);
			break;
		}
	}
	
	private Shape randomShape() {
		switch (generator.nextInt(7)) {
		case 0:
			return Shape.T;
		case 1:
			return Shape.J;
		case 2:
			return Shape.Z;
		case 3:
			return Shape.O;
		case 4:
			return Shape.S;
		case 5:
			return Shape.L;
		case 6:
			return Shape.I;
		}
		return null;
	}

}
