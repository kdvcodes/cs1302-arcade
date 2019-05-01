package cs1302.arcade.tetris;

import java.util.Arrays;
import cs1302.arcade.ArcadeGame;
import cs1302.arcade.Board;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * This is the Tetris game class
 *
 */
public class Tetris extends ArcadeGame {
	
	Tetrimino currentPiece;
	int level;
	Timeline t;
	private Text scoreText;
	private Tetrimino next;
	
	private final int rows = 22;
	private final int columns = 10;
	private int linesCleared;
	private int[] clearedRows;
	private final AudioClip music = new AudioClip(getClass().getResource("/tetris/Music_1.wav").toString());
	private final AudioClip lock = new AudioClip(getClass().getResource("/tetris/lock.wav").toString());
	private final AudioClip clear = new AudioClip(getClass().getResource("/tetris/clear.wav").toString());
	private final AudioClip tetris = new AudioClip(getClass().getResource("/tetris/tetris.wav").toString());
	private final AudioClip levelUp = new AudioClip(getClass().getResource("/tetris/level.wav").toString());
	private Board nextBoard;
	/*
	public Tetris(int level) {
		game = new TetrisBoard(rows, columns);
		generator = new Random();
		this.level = level;
	}
	*/
	
	public Tetris() {
		level = 0;
		board = new TetrisBoard(rows, columns, this);
		background = new Image("/tetris/background.png");
		scoreText = new Text(380, 140, "0");
		scoreText.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/ARCADECLASSIC.ttf"), 36));
		scoreText.setFill(Color.WHITE);
		next();
		newGame(board, scoreText, nextBoard);
		currentPiece = new Tetrimino(randomShape(), board);
		t = new Timeline(new KeyFrame(dropRate(), this::drop));
		t.setCycleCount(Timeline.INDEFINITE);
		t.play();
		music.play();
	} // Tetris constructor
	
	private void next() {
		Shape s = randomShape();
		nextBoard = s.nextBoard(this);
		if (s == Shape.I) {
			next = new Tetrimino(s, nextBoard, 2, 0);
			return;
		}
		next = new Tetrimino(s, nextBoard, 1, 0);
	}

	private void lock() {
		t.stop();
		clearedRows = new int[0];
		for (int i = 0; i < rows; i++) {
			if (board.lineFull(i)) {
				clearedRows = Arrays.copyOf(clearedRows, clearedRows.length + 1);
				clearedRows[clearedRows.length - 1] = i;
			}
		}
		if (clearedRows.length != 0) {
			if (clearedRows.length == 4) {
				tetris.play();
			}
			else {
				clear.play();
			}
			((TetrisBoard) board).clearLine(clearedRows);
		}
		else {
			lock.play();
			newPiece();
		}
	}
	
	public void newPiece() {
		updateScore();
		if (linesCleared >= (level + 1) * 10) {
			levelUp();
		}
		currentPiece = new Tetrimino(next.getShape(), board);
		game.getChildren().remove(nextBoard);
		next();
		game.getChildren().add(nextBoard);
		t.play();
	}
	
	private void levelUp() {
		level++;
		levelUp.play();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				board.getTile(i, j).update();
			}
		}
		t.getKeyFrames().replaceAll((k) -> new KeyFrame(dropRate(), this::drop));
	}
	
	public int getLevel() {
		return level;
	}
	
	private void updateScore() {
		switch (clearedRows.length) {
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
		linesCleared += clearedRows.length;
		scoreText.setText(String.valueOf(score));
	}
	
	private void drop(ActionEvent e) {
		if (!currentPiece.drop() && t.getStatus() == Status.RUNNING) {
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
	
	public void gameOver() {
		t.stop();
		music.stop();
		getScene().setOnKeyPressed(null);
	}

}
