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
			right();
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
		for (int row = 1; row < gameSize; row++) {
			for (int col = 0; col < gameSize; col++) {
				if (board.getTile(row, col).isOccupied(null)) {
					for (int i = row; i > 0; i--) {
						if(!(board.getTile(i - 1 , col).isOccupied(null))) {
							board.getTile(i - 1, col).setPiece(board.getTile(i, col).getPiece());
							board.getTile(i, col).clearPiece();
						} else {
							if(board.getTile(i - 1, col).getPiece() == board.getTile(i, col).getPiece()) {
								board.getTile(i - 1, col).setPiece(((int) board.getTile(i, col).getPiece()) * 2);
								board.getTile(i, col).clearPiece();
								break;
							} // if
						} // if else
					} // for
				} // if
			} // for
		} // for
	}
	
	private void down() {
		for (int row = gameSize - 2; row >= 0; row--) {
			for (int col = 0; col < gameSize; col++) {
				if (board.getTile(row, col).isOccupied(null)) {
					for (int i = row; i < gameSize - 1; i++) {
						if(!(board.getTile(i + 1 , col).isOccupied(null))) {
							board.getTile(i + 1, col).setPiece(board.getTile(i, col).getPiece());
							board.getTile(i, col).clearPiece();
						} else {
							if(board.getTile(i + 1, col).getPiece() == board.getTile(i, col).getPiece()) {
								board.getTile(i + 1, col).setPiece(((int) board.getTile(i, col).getPiece()) * 2);
								board.getTile(i, col).clearPiece();
								break;
							} // if
						} // if else
					} // for
				} // if
			} // for
		} // for
	} // down
	
	private void left() {
		for (int col = 1; col < gameSize; col++) {
			for (int row = 0; row < gameSize; row++) {
				if (board.getTile(row, col).isOccupied(null)) {
					for (int i = col; i > 0; i--) {
						if(!(board.getTile(row , i - 1).isOccupied(null))) {
							board.getTile(row, i - 1).setPiece(board.getTile(row, i).getPiece());
							board.getTile(row, i).clearPiece();
						} else {
							if(board.getTile(row, i - 1).getPiece() == board.getTile(row, i).getPiece()) {
								board.getTile(row, i - 1).setPiece(((int) board.getTile(row, i).getPiece()) * 2);
								board.getTile(row, i).clearPiece();
								break;
							} // if
						} // if else
					} // for
				} // if
			} // for
		} // for
	} // left
	
	private void right() {
		for (int col = gameSize - 2; col >= 0; col--) {
			for (int row = 0; row < gameSize; row++) {
				if (board.getTile(row, col).isOccupied(null)) {
					for (int i = col; i < gameSize - 1; i++) {
						NumberGameTile newTile = (NumberGameTile) board.getTile(row, i + 1);
						if(!(newTile.isOccupied(null))) {
							newTile.setPiece((Integer) board.getTile(row, i).getPiece());
							board.getTile(row, i).clearPiece();
						} else {
							if(newTile.getPiece() == board.getTile(row, i).getPiece() &&
									!newTile.hasCombined()) {
								newTile.setPiece(((int) board.getTile(row, i).getPiece()) * 2);
								newTile.combine(true);
								board.getTile(row, i).clearPiece();
								break;
							} // if
						} // if else
					} // for
				} // if
			} // for
		} // for
		clearCombo();
	} // right
	
	private void clearCombo() {
		for (int i = 0; i < gameSize; i++) {
			for (int j = 0; j < gameSize; j++) {
				((NumberGameTile) board.getTile(i, j)).combine(false);
			}
		}
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
