package cs1302.arcade.numberGame;

import java.io.File;

import cs1302.arcade.ArcadeGame;
import cs1302.arcade.ArcadeToolBar;
import cs1302.arcade.Tile;
import cs1302.arcade.tetris.TetrisBoard;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;

/**
 * This is the NumberGame class for the game of 2448
 *
 */
public class NumberGame extends ArcadeGame{

	final int gameSize = 4;
	private Text scoreText;
	private Text highScoreText;
	/* NumberGameMenuBar numberGameMenuBar;
	NumberGameInfoBar numberGameInfoBar;
	NumberGameMainContent numberGameMainContent; */
	final Timeline expand = new Timeline(new KeyFrame(Duration.millis(1000/60)));
	
	public NumberGame() {
		scoreFile = new File(getClass().getResource("/2048/highScores.txt").getPath().replaceAll("%20", " "));
		board = new NumberGameBoard(gameSize, gameSize, this);
		background = new Image("/2048/background.png");
		scoreText = new Text(370, 110, String.format("%05d", score));
		scoreText.setFill(Color.WHITE);
		scoreText.setFont(Font.font(32));
		highScores = generateScores(scoreFile);
		highScoreText = new Text(480, 110, String.format("%05d", highScores[0].getScore()));
		highScoreText.setFill(Color.WHITE);
		highScoreText.setFont(Font.font(32));
		start(new ArcadeToolBar(this), board, scoreText, highScoreText);
		expand.setCycleCount(1);
		newGame(null);
	}

	@Override
	protected void move(KeyEvent ke) {
		switch(ke.getCode()) {
		case UP:
			((NumberGameBoard) board).up();
			newPiece();
			break;
		case DOWN:
			((NumberGameBoard) board).down();
			newPiece();
			break;
		case LEFT:
			((NumberGameBoard) board).left();
			newPiece();
			break;
		case RIGHT:
			((NumberGameBoard) board).right();
			newPiece();
			break;
		}
		
	}
	
	private void newPiece() {
		NumberGameTile t = randomTile();
		t.setPiece(randomNumGenerator());
		t.setFitHeight(0);
		t.setFitWidth(0);
		expand.getKeyFrames().removeAll();
		expand.getKeyFrames().add(new KeyFrame(Duration.millis(200), new KeyValue(t.fitHeightProperty(), t.size)));
		expand.getKeyFrames().add(new KeyFrame(Duration.millis(200), new KeyValue(t.fitWidthProperty(), t.size)));
		expand.play();
	}
	
	private NumberGameTile randomTile() {
		Tile t = board.getTile(generator.nextInt(gameSize), generator.nextInt(gameSize));
		
		if(t.isOccupied(null)) {
			return randomTile();
		} // if
		
		return (NumberGameTile) t;
	}
	
	private Integer randomNumGenerator() {
		return generator.nextDouble() < 0.9 ? 2 : 4;
	}
	
	public void updateScore(int i) {
		score += i;
		scoreText.setText(String.format("%05d", score));
		if (highScores[0].getScore() < score) {
			highScoreText.setText(String.format("%05d", score));
		}
	} // updatScore

	@Override
	public void newGame(ActionEvent e) {
		((NumberGameBoard) board).clear();
		newPiece();
		newPiece();
	}

	@Override
	public void options(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void help(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	public NumberGame() {
		// Main frame items initializations
		VBox vbox = new VBox();
		HBox numberGameMainFrame = new HBox(vbox);
		
		// Layers and containers initializations
		HBox numberGameMenuBarLayer = new HBox();
		HBox numberGameInfoLayer = new HBox();
		HBox numberGameMainContentLayer = new HBox();
		
		// Scene items initializations
		numberGameMenuBar = new NumberGameMenuBar(this);
		numberGameInfoBar = new NumberGameInfoBar(this);
		numberGameMainContent = new NumberGameMainContent(this, 400, 400);
		
		// Items customizations
		
		// Setting items sizing and spacing
		HBox.setHgrow(vbox, Priority.ALWAYS);
		HBox.setHgrow(numberGameMenuBar, Priority.ALWAYS);
		
		// Layer items assignments
		numberGameMenuBarLayer.getChildren().addAll(numberGameMenuBar);
		numberGameInfoLayer.getChildren().addAll(numberGameInfoBar);
		numberGameMainContentLayer.getChildren().addAll(numberGameMainContent);
		
		// Set actions
		numberGameMainContentLayer.setOnKeyPressed(numberGameMainContent.createKeyHandler());
		
		// Setting the items placements
		vbox.getChildren().addAll(numberGameMenuBarLayer, numberGameInfoLayer, numberGameMainContentLayer);
		
		Scene testScene = new Scene(numberGameMainFrame);
		this.setScene(testScene);
		this.setMaxHeight(600);
		this.setMaxWidth(600);
		this.show();
		
		numberGameMainContentLayer.requestFocus();
	}
	*/
}
