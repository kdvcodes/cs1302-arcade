package cs1302.arcade.tetris;

import java.util.Arrays;

import cs1302.arcade.ArcadeGame;
import cs1302.arcade.Board;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
	private Text highScoreText;
	private Text levelText;
	private Text linesClearedText;
	private Tetrimino next;
	
	private int tStat;
	private int jStat;
	private int zStat;
	private int oStat;
	private int sStat;
	private int lStat;
	private int iStat;
	
	private Text tText;
	private Text jText;
	private Text zText;
	private Text oText;
	private Text sText;
	private Text lText;
	private Text iText;

	private final PixelReader statPic = new Image("/tetris/stats.png").getPixelReader();
	private WritableImage stats;
	private ImageView statView;
	
	private final int rows = 22;
	private final int columns = 10;
	private int linesCleared;
	private int[] clearedRows;
	private final MediaPlayer music = new MediaPlayer(new Media(getClass().getResource("/tetris/Music_1.wav").toString()));
	private final AudioClip lock = new AudioClip(getClass().getResource("/tetris/lock.wav").toString());
	private final AudioClip clear = new AudioClip(getClass().getResource("/tetris/clear.wav").toString());
	private final AudioClip tetris = new AudioClip(getClass().getResource("/tetris/tetris.wav").toString());
	private final AudioClip levelUp = new AudioClip(getClass().getResource("/tetris/level.wav").toString());
	private final AudioClip select = new AudioClip(getClass().getResource("/tetris/select.wav").toString());
	private final Font NES = Font.loadFont(getClass().getResourceAsStream("/tetris/NES.ttf"), 16);
	private TetrisBoard nextBoard;
	private boolean paused;
	private boolean active;
	private TetrisLauncher tl;
	
	public Tetris(int level, TetrisLauncher tl) {
		active = true;
		this.level = level;
		this.tl = tl;
		linesCleared = 0;
		paused = false;
		board = new TetrisBoard(rows, columns, this);
		background = new Image("/tetris/background.png");
		stats = new WritableImage(statPic, 46, 208);
		statView = new ImageView();
		makeNext();
		statSetup();
		drawStats();
		statView.setX(45);
		statView.setY(170);
		newGame(new TetrisToolBar(this), board, nextBoard, statView);
		textSetup();
		setTitle("Tetris");
		currentPiece = new Tetrimino(randomShape(), board);
		t = new Timeline(new KeyFrame(dropRate(), this::drop));
		t.setCycleCount(Timeline.INDEFINITE);
		t.play();
		music.play();
	} // Tetris constructor
	
	private void textSetup() {
		scoreText = new Text(385, 110, String.format("Score\n%06d", score));
		scoreText.setFont(NES);
		scoreText.setFill(Color.WHITE);
		highScoreText = new Text(385, 60, String.format("Top\n%06d", highScore));
		highScoreText.setFont(NES);
		highScoreText.setFill(Color.WHITE);
		levelText = new Text(385, 318, String.format("Level\n  %02d", level));
		levelText.setFont(NES);
		levelText.setFill(Color.WHITE);
		linesClearedText = new Text(208, 46, String.format("Lines-%03d", linesCleared));
		linesClearedText.setFont(NES);
		linesClearedText.setFill(Color.WHITE);
		int xStart = 93;
		int yStart = 189;
		int space = 32;
		tText = new Text(xStart, yStart, String.format("%03d", tStat));
		tText.setFont(NES);
		tText.setFill(Color.rgb(0xd8, 0x28, 0x00));
		jText = new Text(xStart, yStart + (space * 1), String.format("%03d", jStat));
		jText.setFont(NES);
		jText.setFill(Color.rgb(0xd8, 0x28, 0x00));
		zText = new Text(xStart, yStart + (space * 2), String.format("%03d", zStat));
		zText.setFont(NES);
		zText.setFill(Color.rgb(0xd8, 0x28, 0x00));
		oText = new Text(xStart, yStart + (space * 3), String.format("%03d", oStat));
		oText.setFont(NES);
		oText.setFill(Color.rgb(0xd8, 0x28, 0x00));
		sText = new Text(xStart, yStart + (space * 4), String.format("%03d", sStat));
		sText.setFont(NES);
		sText.setFill(Color.rgb(0xd8, 0x28, 0x00));
		lText = new Text(xStart, yStart + (space * 5), String.format("%03d", lStat));
		lText.setFont(NES);
		lText.setFill(Color.rgb(0xd8, 0x28, 0x00));
		iText = new Text(xStart, yStart + (space * 6), String.format("%03d", iStat));
		iText.setFont(NES);
		iText.setFill(Color.rgb(0xd8, 0x28, 0x00));
		Text title = new Text(47, 62, "Tetris");
		title.setFont(NES);
		title.setFill(Color.WHITE);
		Text next = new Text(384, 207, "Next");
		next.setFont(NES);
		next.setFill(Color.WHITE);
		Text stats = new Text(47, 140, "Stats");
		stats.setFont(NES);
		stats.setFill(Color.WHITE);
		game.getChildren().addAll(scoreText, highScoreText, levelText, linesClearedText, tText, jText, zText, oText, sText, lText, iText, title, next, stats);
	}
	
	private void statSetup() {
		highScore = 10000;
		tStat = 0;
		jStat = 0;
		zStat = 0;
		oStat = 0;
		sStat = 0;
		lStat = 0;
		iStat = 0;
	}
	
	private void drawStats() {
		for (int x = 0; x < stats.getWidth(); x++) {
			for (int y = 0; y < stats.getHeight(); y++) {
				if (statPic.getArgb(x, y) == 0xFF2038ec) {
					stats.getPixelWriter().setArgb(x, y, Shape.getColor1(level));
				}
				else if (statPic.getArgb(x, y) == 0xFF3cbcfc) {
					stats.getPixelWriter().setArgb(x, y, Shape.getColor2(level));
				}
				else {
					stats.getPixelWriter().setArgb(x, y, statPic.getArgb(x, y));
				}
			}
		}
		statView.setImage(stats);
	}
	
	private void makeNext() {
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
		getScene().setOnKeyPressed(null);
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
		updateScore(clearedRows.length);
		if (linesCleared >= (level + 1) * 10) {
			levelUp();
		}
		increaseStats();
		currentPiece = new Tetrimino(next.getShape(), board);
		game.getChildren().remove(nextBoard);
		makeNext();
		game.getChildren().add(nextBoard);
		if (active) {
			getScene().setOnKeyPressed(this::move);
			t.play();
		}
	}
	
	private void increaseStats() {
		switch (next.getShape()) {
		case T:
			tStat++;
			tText.setText(String.format("%03d", tStat));
			break;
		case J:
			jStat++;
			jText.setText(String.format("%03d", jStat));
			break;
		case Z:
			zStat++;
			zText.setText(String.format("%03d", zStat));
			break;
		case O:
			oStat++;
			oText.setText(String.format("%03d", oStat));
			break;
		case S:
			sStat++;
			sText.setText(String.format("%03d", sStat));
			break;
		case L:
			lStat++;
			lText.setText(String.format("%03d", lStat));
			break;
		case I:
			iStat++;
			iText.setText(String.format("%03d", iStat));
			break;
		}
	}

	private void levelUp() {
		level++;
		levelUp.play();
		levelText.setText(String.format("Level\n  %02d", level));
		drawStats();
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
	
	public void updateScore(int i) {
		switch (i) {
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
		scoreText.setText(String.format("Score\n%06d", score));
		linesClearedText.setText(String.format("Lines-%03d", linesCleared));
		if (highScore < score) {
			highScoreText.setText(String.format("Top\n%06d", highScore));
		}
	}
	
	private boolean drop(ActionEvent e) {
		if (!currentPiece.drop()) {
			lock();
			return true;
		}
		return false;
	}
	
	protected void move(KeyEvent ke) {
		if (paused && ke.getCode() == KeyCode.ESCAPE) {
			paused = false;
			select.play();
			music.play();
			t.play();
		}
		else if (!paused && active) {
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
			case SPACE:
				while (!drop(null));
				break;
			case Z:
				currentPiece.rotate(1);
				break;
			case X:
				currentPiece.rotate(-1);
				break;
			case ESCAPE:
				paused = true;
				select.play();
				music.pause();
				t.pause();
				break;
			}
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
	
	@Override
	public void newGame() {
		gameOver();
		tl.show();
		close();
	}
	
	public void gameOver() {
		active = false;
		t.stop();
		music.stop();
		getScene().setOnKeyPressed(null);
	}

}
