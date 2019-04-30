package cs1302.arcade;

import javafx.scene.Group;

/**
 * @author haile999
 *
 */
public class Board extends Group {
	
	protected Tile[][] playField;
	protected ArcadeGame game;
	
	public Tile getTile(int row, int column) {
		return playField[row][column];
	}
	
	public boolean lineFull(int row) {
		for (int i = 0; i < playField[row].length; i++) {
			if (!getTile(row, i).isOccupied(null)) {
				return false;
			}
		}
		return true;
	}
}
