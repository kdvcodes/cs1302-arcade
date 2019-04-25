package cs1302.arcade.tetris;


import java.util.Random;

import cs1302.arcade.Board;
import javafx.scene.Group;

public class TetrisBoard extends Board {
	
	public TetrisBoard(int rows, int columns, Tetris game) {
		playField = new TetrisTile[rows][columns];
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
	
	private void dropTile(int row, int column) {
		getTile(row + 1, column).setPiece(getTile(row, column).getPiece());
		getTile(row, column).clearPiece();
	}
	
	public void clearLine(int row) {
		for (int i = 0; i < playField[row].length; i++) {
			getTile(row, i).clearPiece();
		}
		for (int i = row - 1; i >= 0; i--) {
			for (int j = 0; j < playField[i].length; j++) {
				if (getTile(i, j).isOccupied(null)) {
					dropTile(i, j);
				}
			}
		}
	}

}
