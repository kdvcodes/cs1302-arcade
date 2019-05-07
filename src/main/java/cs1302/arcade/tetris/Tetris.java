package cs1302.arcade.tetris;

import java.io.File;
import java.util.Arrays;

import cs1302.arcade.ArcadeGame;
import cs1302.arcade.ArcadeToolBar;
import cs1302.arcade.Score;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * This is the Tetris game class
 */
public class Tetris extends ArcadeGame {

	/**
	 * The current piece being controlled
	 */
	private Tetrimino currentPiece;
	private int level;
	private Timeline dropper;
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
	private final PixelReader statPic = new Image("/tetris/stats.png")
			.getPixelReader();
	private WritableImage stats;
	private ImageView statView;
	private final int rows = 22;
	private final int columns = 10;
	private int linesCleared;
	private int[] clearedRows;
	private MediaPlayer music;
	private AudioClip lock;
	private AudioClip clear;
	private AudioClip tetris;
	private AudioClip levelUp;
	private AudioClip select;
	private final Font NES = Font
			.loadFont(getClass().getResourceAsStream("/tetris/NES.ttf"), 16);
	private TetrisBoard nextBoard;
	private boolean paused;
	private boolean active;
	private boolean showGhost;
	private boolean playSound;
	private Stage options;

	/**
	 * Creates a new Tetris game
	 * 
	 * @param level      the starting level
	 * @param highScores the Score array of existing scores
	 */
	public Tetris(int level, Score[] highScores) {
		this.level = level;
		this.highScores = highScores;
		linesCleared = 0;
		active = true;
		paused = false;
		showGhost = true;
		playSound = true;
		scoreFile = new File(getClass().getResource("/tetris/highScores.txt")
				.getPath().replaceAll("%20", " "));
		board = new TetrisBoard(rows, columns, this);
		background = new Image("/tetris/background.png");
		stats = new WritableImage(statPic, 46, 208);
		statView = new ImageView();
		statView.setX(45);
		statView.setY(170);
		makeNext();
		statSetup();
		drawStats();
		start(new ArcadeToolBar(this), board, nextBoard, statView);
		textSetup();
		soundSetup();
		optionsSetup();
		setTitle("Tetris");
		currentPiece = new Tetrimino(randomShape(), board, showGhost);
		dropper = new Timeline(new KeyFrame(dropRate(), this::drop));
		dropper.setCycleCount(Timeline.INDEFINITE);
		dropper.play();
		if (playSound) {
			music.play();
		}
	} // Tetris constructor

	/**
	 * Sets up lots of the text in the scene
	 */
	private void textSetup() {
		scoreText = new Text(385, 110, String.format("Score\n%06d", score));
		highScoreText = new Text(385, 60,
				String.format("Top\n%06d", highScores[0].getScore()));
		levelText = new Text(385, 318, String.format("Level\n  %02d", level));
		linesClearedText = new Text(208, 46,
				String.format("Lines-%03d", linesCleared));
		Text title = new Text(47, 62, "Tetris");
		Text next = new Text(384, 207, "Next");
		Text stats = new Text(47, 140, "Stats");
		int xStart = 93;
		int yStart = 189;
		int space = 32;
		tText = new Text(xStart, yStart, String.format("%03d", tStat));
		jText = new Text(xStart, yStart + (space * 1),
				String.format("%03d", jStat));
		zText = new Text(xStart, yStart + (space * 2),
				String.format("%03d", zStat));
		oText = new Text(xStart, yStart + (space * 3),
				String.format("%03d", oStat));
		sText = new Text(xStart, yStart + (space * 4),
				String.format("%03d", sStat));
		lText = new Text(xStart, yStart + (space * 5),
				String.format("%03d", lStat));
		iText = new Text(xStart, yStart + (space * 6),
				String.format("%03d", iStat));
		formatText(NES, Color.WHITE, scoreText, highScoreText, levelText,
				linesClearedText, title, next, stats);
		formatText(NES, Color.rgb(0xd8, 0x28, 0x00), tText, jText, zText, oText,
				sText, lText, iText);
		game.getChildren().addAll(scoreText, highScoreText, levelText,
				linesClearedText, tText, jText, zText, oText, sText, lText,
				iText, title, next, stats);
	}

	/**
	 * Takes a array of Text and formats it according to the parameters
	 * 
	 * @param f the Font to use
	 * @param c the Color to use
	 * @param t the Text to format
	 */
	public static void formatText(Font f, Color c, Text... t) {
		for (int i = 0; i < t.length; i++) {
			t[i].setFont(f);
			t[i].setFill(c);
		}
	}

	/**
	 * Initialzes the statistic variables
	 */
	private void statSetup() {
		tStat = 0;
		jStat = 0;
		zStat = 0;
		oStat = 0;
		sStat = 0;
		lStat = 0;
		iStat = 0;
	}

	/**
	 * Sets up to options panel
	 */
	private void optionsSetup() {
		CheckBox ghost = new CheckBox("Enable ghost blocks");
		CheckBox sound = new CheckBox("Enable sound");
		ghost.setSelected(true);
		sound.setSelected(playSound);
		ghost.setOnAction((i) -> {
			if (ghost.isSelected())
				showGhost = true;
			else
				showGhost = false;
		});
		sound.setOnAction((i) -> {
			if (sound.isSelected())
				playSound = true;
			else
				playSound = false;
		});
		ListView lv = new ListView();
		lv.getItems().addAll(ghost, sound);
		lv.setPrefHeight(58);
		VBox v = new VBox(lv);
		options = new Stage();
		options.setOnCloseRequest(this::unpause);
		options.setScene(new Scene(v));
		options.sizeToScene();
	}

	/**
	 * Checks if sound is able to be initialzed
	 */
	private void soundSetup() {
		try {
			music = new MediaPlayer(new Media(
					getClass().getResource("/tetris/Music_1.wav").toString()));
			lock = new AudioClip(
					getClass().getResource("/tetris/lock.wav").toString());
			clear = new AudioClip(
					getClass().getResource("/tetris/clear.wav").toString());
			tetris = new AudioClip(
					getClass().getResource("/tetris/tetris.wav").toString());
			levelUp = new AudioClip(
					getClass().getResource("/tetris/level.wav").toString());
			select = new AudioClip(
					getClass().getResource("/tetris/select.wav").toString());
		} catch (MediaException e) {
			playSound = false;
		}
	}

	/**
	 * Draws the statistics images according to the current level color
	 */
	private void drawStats() {
		for (int x = 0; x < stats.getWidth(); x++) {
			for (int y = 0; y < stats.getHeight(); y++) {
				if (statPic.getArgb(x, y) == 0xFF2038ec) {
					stats.getPixelWriter().setArgb(x, y,
							Shape.getColor1(level));
				} else if (statPic.getArgb(x, y) == 0xFF3cbcfc) {
					stats.getPixelWriter().setArgb(x, y,
							Shape.getColor2(level));
				} else {
					stats.getPixelWriter().setArgb(x, y, statPic.getArgb(x, y));
				}
			}
		}
		statView.setImage(stats);
	}

	/**
	 * This method makes the next tetrimino
	 */
	private void makeNext() {
		Shape s = randomShape();
		nextBoard = s.nextBoard(this);
		if (s == Shape.I) {
			next = new Tetrimino(s, nextBoard, 2, 0);
			return;
		}
		next = new Tetrimino(s, nextBoard, 1, 0);
	}

	/**
	 * Locks the current piece into place
	 */
	private void lock() {
		dropper.stop();
		getScene().setOnKeyPressed(null);
		clearedRows = new int[0];
		for (int i = 0; i < rows; i++) {
			if (board.lineFull(i)) {
				clearedRows = Arrays.copyOf(clearedRows,
						clearedRows.length + 1);
				clearedRows[clearedRows.length - 1] = i;
			}
		}
		if (clearedRows.length != 0) {
			if (playSound) {
				if (clearedRows.length == 4) {
					tetris.play();
				} else {
					clear.play();
				}
			}
			((TetrisBoard) board).clearLine(clearedRows);
		} else {
			if (playSound) {
				lock.play();
			}
			newPiece();
		}
	}

	/**
	 * Creates a new Tetrimino
	 */
	public void newPiece() {
		updateScore(clearedRows.length);
		// check for level up
		if (linesCleared >= (level + 1) * 10) {
			levelUp();
		}
		increaseStats();
		currentPiece = new Tetrimino(next.getShape(), board, showGhost);
		game.getChildren().remove(nextBoard);
		makeNext();
		game.getChildren().add(nextBoard);
		if (active) {
			getScene().setOnKeyPressed(this::move);
			dropper.play();
		}
	}

	/**
	 * Increments the statistics
	 */
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

	/**
	 * Increments the current level
	 */
	private void levelUp() {
		level++;
		if (playSound) {
			levelUp.play();
		}
		levelText.setText(String.format("Level\n  %02d", level));
		drawStats();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				board.getTile(i, j).update();
			}
		}
		// update drop rate based on current level
		dropper.getKeyFrames()
				.replaceAll((k) -> new KeyFrame(dropRate(), this::drop));
	}

	/**
	 * Returns the current level
	 * 
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/*
	 * {@inheritDoc}
	 */
	public void updateScore(int i) {
		switch (i) {
		case 0:
			break;
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
		linesCleared += i;
		scoreText.setText(String.format("Score\n%06d", score));
		linesClearedText.setText(String.format("Lines-%03d", linesCleared));
		// check if top score is beaten
		if (highScores[0].getScore() < score) {
			highScoreText.setText(String.format("Top\n%06d", score));
		}
	}

	/**
	 * Drops the current piece and locks it if needed
	 * 
	 * @param e
	 * @return true if the piece locked, false otherise
	 */
	private boolean drop(ActionEvent e) {
		if (!currentPiece.drop()) {
			lock();
			return true;
		}
		return false;
	}

	/*
	 * {@inheritDoc}
	 */
	protected void move(KeyEvent ke) {
		if (paused && ke.getCode() == KeyCode.ESCAPE) {
			unpause(null);
		} else if (!paused && active) {
			switch (ke.getCode()) {
			case RIGHT:
				currentPiece.right();
				break;
			case LEFT:
				currentPiece.left();
				break;
			case DOWN:
				drop(null);
				score++;
				break;
			case SPACE:
				while (!drop(null)) {
					score++;
				}
				;
				break;
			case Z:
				currentPiece.rotate(1);
				break;
			case X:
				currentPiece.rotate(-1);
				break;
			case ESCAPE:
				pause();
				break;
			}
		}
	}

	/**
	 * Pauses the game
	 */
	private void pause() {
		paused = true;
		if (playSound) {
			select.play();
			music.pause();
		}
		dropper.pause();
	}

	/**
	 * Unpauses the game
	 * 
	 * @param w
	 */
	private void unpause(WindowEvent w) {
		paused = false;
		if (playSound) {
			select.play();
			music.play();
		}
		dropper.play();
	}

	/**
	 * Returns a random {@code Shape}
	 * 
	 * @return a random shape
	 */
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

	/**
	 * Returns the drop rate for this level
	 * 
	 * @return the drop rate for this level
	 */
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
		default:
			return moreDropRates();
		}

	}

	/**
	 * Table of drop rates for each level.
	 * 
	 * @return the drop rate
	 */
	private Duration moreDropRates() {
		switch (level) {
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

	/*
	 * {@inheritDoc}
	 */
	@Override
	public void newGame(ActionEvent e) {
		gameOver();
		submitScore(null);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public void options(ActionEvent e) {
		pause();
		options.show();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public void help(ActionEvent e) {
		pause();
		Alert helpAlert = new Alert(AlertType.INFORMATION);
		helpAlert.setContentText(
				"Use arrow keys to move pieces around. z and x for rotating the pieces. "
						+ "Space for hard drop. Esc to pause the game. Enjoy the game!");
		helpAlert.show();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public void exit(Event e) {
		gameOver();
		finished = true;
		submitScore(null);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	protected void finished() {
		if (!finished) {
			new TetrisLauncher();
		}
		close();
	}

	/**
	 * Stops various threads from updating the game
	 */
	public void gameOver() {
		active = false;
		dropper.stop();
		if (playSound) {
			music.stop();
		}
		getScene().setOnKeyPressed(null);
	}

}
