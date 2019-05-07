package cs1302.arcade;

import javafx.scene.Group;

/**
 * Board represents a 2D array of Tiles, or game objects,
 * used by both 2048 and Tetris.
 */
public class Board extends Group {
	
	/**
	 * The underlying 2D array of Tiles
	 */
	protected Tile[][] playField;
	
	/**
	 * The {@code ArcadeGame} this Board belongs to.
	 */
	protected ArcadeGame game;
	
	/**
	 * Returns the {@code Tile} at the given row and column
	 * 
	 * @param row
	 * @param column
	 * @return the {@code Tile} at the given parameters
	 */
	public Tile getTile(int row, int column) {
		return playField[row][column];
	}
	
	/**
	 * Checks if the given row is completely full of {@code Tiles}.
	 * 
	 * @param row
	 * @return true if the row is full, false otherwise
	 */
	public boolean lineFull(int row) {
		for (int i = 0; i < playField[row].length; i++) {
			if (!getTile(row, i).isOccupied(null)) {
				return false;
			}
		}
		return true;
	}
}
