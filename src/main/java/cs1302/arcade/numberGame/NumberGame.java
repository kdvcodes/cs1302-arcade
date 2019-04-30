package cs1302.arcade.numberGame;

import cs1302.arcade.ArcadeGame;
import cs1302.arcade.Tile;
import cs1302.arcade.tetris.TetrisBoard;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is the NumberGame class for the game of 2448
 *
 */
public class NumberGame extends ArcadeGame{

	final int gameSize = 4;
	NumberGameMenuBar numberGameMenuBar;
	NumberGameInfoBar numberGameInfoBar;
	NumberGameMainContent numberGameMainContent;
	
	public NumberGame() {
		board = new NumberGameBoard(gameSize, gameSize, this);
		background = new Image("/2048/background.png");
		newGame(board);
		randomTile().setPiece(randomNumGenerator());
		randomTile().setPiece(randomNumGenerator());
	}

	@Override
	protected void move(KeyEvent ke) {
		switch(ke.getCode()) {
		case UP:
			up();
			randomTile().setPiece(randomNumGenerator());
			break;
		case DOWN:
			down();
			randomTile().setPiece(randomNumGenerator());
			break;
		case LEFT:
			left();
			randomTile().setPiece(randomNumGenerator());
			break;
		case RIGHT:
			right();
			randomTile().setPiece(randomNumGenerator());
			break;
		}
		
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
	
	private void up() {
		for (int row = gameSize - 1; row > 0; row--) {
			for (int col = 0; col < gameSize; col++) {
				if(!(board.getTile(row - 1 , col).isOccupied(null))) {
					board.getTile(row - 1, col).setPiece(board.getTile(row, col).getPiece());
					board.getTile(row, col).clearPiece();
				} else {
					if(board.getTile(row - 1, col).getPiece() == board.getTile(row, col).getPiece()) {
						board.getTile(row - 1, col).setPiece(((int) board.getTile(row, col).getPiece()) * 2);
						board.getTile(row, col).clearPiece();
					} // if
				} // if else
			} // for
		} // for
	} // up
	
	private void down() {
		for (int row = 0; row < gameSize - 1; row++) {
			for (int col = 0; col < gameSize; col++) {
				if(!(board.getTile(row + 1 , col).isOccupied(null))) {
					board.getTile(row + 1, col).setPiece(board.getTile(row, col).getPiece());
					board.getTile(row, col).clearPiece();
				} else {
					if(board.getTile(row + 1, col).getPiece() == board.getTile(row, col).getPiece()) {
						board.getTile(row + 1, col).setPiece(((int) board.getTile(row, col).getPiece()) * 2);
						board.getTile(row, col).clearPiece();
					} // if
				} // if else
			} // for
		} // for
	} // down
	
	private void left() {
		for (int col = gameSize - 1; col > 0; col--) {
			for (int row = 0; row < gameSize; row++) {
				if(!(board.getTile(row , col - 1).isOccupied(null))) {
					board.getTile(row, col - 1).setPiece(board.getTile(row, col).getPiece());
					board.getTile(row, col).clearPiece();
				} else {
					if(board.getTile(row, col - 1).getPiece() == board.getTile(row, col).getPiece()) {
						board.getTile(row, col - 1).setPiece(((int) board.getTile(row, col).getPiece()) * 2);
						board.getTile(row, col).clearPiece();
					} // if
				}
			} // for
		} // for
	} // left
	
	private void right() {
		for (int col = 0; col < gameSize - 1; col++) {
			for (int row = 0; row < gameSize; row++) {
				if(!(board.getTile(row , col + 1).isOccupied(null))) {
					board.getTile(row, col + 1).setPiece(board.getTile(row, col).getPiece());
					board.getTile(row, col).clearPiece();
				} else {
					if(board.getTile(row, col + 1).getPiece() == board.getTile(row, col).getPiece()) {
						board.getTile(row, col + 1).setPiece(((int) board.getTile(row, col).getPiece()) * 2);
						board.getTile(row, col).clearPiece();
					} // if
				}
			} // for
		} // for
	} // right
	
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
