package cs1302.arcade.tetris;

import java.util.Random;

public class Board {
	
	private Tile[][] playField;
	
	public Board(int rows, int columns) {
		playField = new Tile[rows][columns];
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField[i].length; j++) {
				playField[i][j] = new Tile();
			}
		}
	}
	
	public void newTetrimino(Shape shape) {
		Tetrimino t = new Tetrimino(shape);
	}
	
	public Tile getTile(int row, int column) {
		return playField[row][column];
	}
	
	public void dropTetriminio(Tetrimino t) {
		
	}
	
	private void dropTile(int row, int column) {
		getTile(row + 1, column).setPiece(getTile(row, column).getPiece());
		getTile(row, column).clearPiece();
	}
	
	public boolean lineFull(int row) {
		for (int i = 0; i < playField[row].length; i++) {
			if (!getTile(row, i).isOccupied()) {
				return false;
			}
		}
		return true;
	}
	
	public void clearLine(int row) {
		for (int i = 0; i < playField[row].length; i++) {
			getTile(row, i).clearPiece();
		}
		for (int i = row - 1; i >= 0; i--) {
			for (int j = 0; j < playField[i].length; i++) {
				if (getTile(i, j).isOccupied()) {
					dropTile(i, j);
				}
			}
		}
	}

}
