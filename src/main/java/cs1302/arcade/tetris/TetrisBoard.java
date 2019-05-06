package cs1302.arcade.tetris;


import java.util.Random;

import cs1302.arcade.Board;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.util.Duration;

public class TetrisBoard extends Board {
	
	int[] clearedRows;
	Timeline clearAnim;
	Timeline curtain;
	int tileSweep;
	
	public TetrisBoard(int rows, int columns, Tetris game) {
		this.game = game;
		playField = new TetrisTile[rows][columns];
		clearAnim = new Timeline(new KeyFrame(Duration.millis(67), this::clear1));
		clearAnim.setCycleCount(6);
		clearAnim.setOnFinished(this::clear2);
		curtain = new Timeline(new KeyFrame(Duration.millis(67), this::drawCurtain));
		curtain.setCycleCount(playField.length);
		curtain.setOnFinished(game::submitScore);
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField[i].length; j++) {
				playField[i][j] = new TetrisTile(i, j, game);
				//top two rows are hidden, this statement ensures that
				if (i > 1) {
					getChildren().add(playField[i][j]);
				}
			}
		}
	}
	
	public TetrisBoard(int rows, int columns, Tetris game, int xStart, int yStart) {
		playField = new TetrisTile[rows][columns];
		this.game = game;
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField[i].length; j++) {
				playField[i][j] = new TetrisTile(i, j, xStart, yStart, game);
				getChildren().add(playField[i][j]);
			}
		}
	}
	
	private void dropTile(int row, int column) {
		getTile(row + 1, column).setPiece(getTile(row, column).getPiece());
		getTile(row, column).clearPiece();
	}
	
	public void clearLine(int row[]) {
		clearedRows = row;
		tileSweep = playField[0].length / 2;
		clearAnim.playFromStart();
	}
	
	private void clear1(ActionEvent e) {
		for (int j = 0; j < clearedRows.length; j++) {
			getTile(clearedRows[j], tileSweep).clearPiece();
			getTile(clearedRows[j], tileSweep * (-1) + 9).clearPiece();
		}
		if (tileSweep < playField[0].length - 1) {
			tileSweep++;
		}
	}
	
	private void clear2(ActionEvent e) {
		for (int i = 0; i < clearedRows.length; i++) {
			for (int j = 0; j < playField[clearedRows[i]].length; j++) {
				for (int k = clearedRows[i] - 1; k >= 0; k--) {
					if (getTile(k, j).isOccupied(null)) {
						dropTile(k, j);
					}
				}
			}
		}
		((Tetris) game).newPiece();
	}

	public void gameOver() {
		((Tetris) game).gameOver();
		tileSweep = 0;
		curtain.play();
	}
	
	private void drawCurtain(ActionEvent e) {
		for (int i = 0; i < playField[tileSweep].length; i++) {
			((TetrisTile) getTile(tileSweep, i)).curtain();
		}
		tileSweep++;
	}

}
