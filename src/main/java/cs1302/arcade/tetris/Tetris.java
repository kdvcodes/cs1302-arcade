package cs1302.arcade.tetris;

import java.io.File;
import java.util.Random;

import cs1302.arcade.ArcadeGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
public class Tetris extends ArcadeGame {
	
	Tetrimino currentPiece;
	int level;
	Timeline t;
	
	private final int rows = 22;
	private final int columns = 10;
	
	/*
	public Tetris(int level) {
		game = new TetrisBoard(rows, columns);
		generator = new Random();
		this.level = level;
	}
	*/
	
	public Tetris() {
		level = 1;
		board = new TetrisBoard(rows, columns, this);
		background = new Image("/tetris/background.png");
		newGame();
		currentPiece = new Tetrimino(randomShape(), board);
		t = new Timeline(new KeyFrame(dropRate(), this::drop));
		t.setCycleCount(Timeline.INDEFINITE);
		t.play();
	} // Tetris constructor
	
	private void lock() {
		t.stop();
		int cleared = 0;
		for (int i = 0; i < rows; i++) {
			if (board.lineFull(i)) {
				((TetrisBoard) board).clearLine(i);
				cleared++;
			}
		}
		updateScore(cleared);
		currentPiece = new Tetrimino(randomShape(), board);
		t.play();
	}
	
	public int getLevel() {
		return level;
	}
	
	private void updateScore(int linesCleared) {
		switch (linesCleared) {
		case 0:
			return;
		case 1:
			score += 40 * (level + 1);
			break;
		case 2:
			score += 100 * (level + 1);
			break;
		case 3:
			score += 300 * (level + 1);
			break;
		case 4:
			score += 1200 * (level + 1);
			break;
		}
	}
	
	private void drop(ActionEvent e) {
		if (!currentPiece.drop()) {
			lock();
		}
	}
	
	protected void move(KeyEvent ke) {
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
	
	private Duration dropRate() {
		switch (level) {
		case 0:
			return Duration.millis(799);
		case 1:
			return Duration.millis(715);
		case 2:
			return Duration.millis(632);
		case 3:
			return Duration.millis(549);
		case 4:
			return Duration.millis(466);
		case 5:
			return Duration.millis(383);
		case 6:
			return Duration.millis(300);
		case 7:
			return Duration.millis(216);
		case 8:
			return Duration.millis(133);
		case 9:
			return Duration.millis(100);
		case 10:
		case 11:
		case 12:
			return Duration.millis(83);
		case 13:
		case 14:
		case 15:
			return Duration.millis(67);
		case 16:
		case 17:
		case 18:
			return Duration.millis(50);
		case 19:
		case 20:
		case 21:
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
			return Duration.millis(33);
		default:
			return Duration.millis(17);
		}
		
	}

}
