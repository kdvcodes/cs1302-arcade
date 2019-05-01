/**
 * 
 */
package cs1302.arcade.numberGame;

import cs1302.arcade.Board;

/**
 * @author haile999
 *
 */
public class NumberGameBoard extends Board {
	
	public NumberGameBoard(int rows, int columns, NumberGame game) {
		playField = new NumberGameTile[rows][columns];
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField[i].length; j++) {
				playField[i][j] = new NumberGameTile(i, j, game);
				//top two rows are hidden, this statement ensures that
				getChildren().add(playField[i][j]);
			}
		}
	}
	
	public void up() {
		for (int row = 1; row < playField.length; row++) {
			for (int col = 0; col < playField.length; col++) {
				if (getTile(row, col).isOccupied(null)) {
					for (int i = row; i > 0; i--) {
						NumberGameTile newTile = (NumberGameTile) getTile(i - 1, col);
						NumberGameTile oldTile = (NumberGameTile) getTile(i, col);
						if(!(newTile.isOccupied(null))) {
							newTile.setPiece((Integer) oldTile.getPiece());
							oldTile.clearPiece();
						} else {
							if(newTile.getPiece() == oldTile.getPiece() &&
									!((NumberGameTile) oldTile).hasCombined()) {
								newTile.setPiece(((int) oldTile.getPiece()) * 2);
								newTile.combine(true);
								oldTile.clearPiece();
								break;
							} // if
						} // if else
					} // for
				} // if
			} // for
		} // for
		clearCombo();
	}
	
	public void down() {
		for (int row = playField.length - 2; row >= 0; row--) {
			for (int col = 0; col < playField.length; col++) {
				if (getTile(row, col).isOccupied(null)) {
					for (int i = row; i < playField.length - 1; i++) {
						NumberGameTile newTile = (NumberGameTile) getTile(i + 1, col);
						NumberGameTile oldTile = (NumberGameTile) getTile(i, col);
						if(!(newTile.isOccupied(null))) {
							newTile.setPiece((Integer) oldTile.getPiece());
							oldTile.clearPiece();
						} else {
							if(newTile.getPiece() == oldTile.getPiece() &&
									!((NumberGameTile) oldTile).hasCombined()) {
								newTile.setPiece(((int) oldTile.getPiece()) * 2);
								newTile.combine(true);
								oldTile.clearPiece();
								break;
							} // if
						} // if else
					} // for
				} // if
			} // for
		} // for
		clearCombo();
	} // down
	
	public void left() {
		for (int col = 1; col < playField.length; col++) {
			for (int row = 0; row < playField.length; row++) {
				if (getTile(row, col).isOccupied(null)) {
					for (int i = col; i > 0; i--) {
						NumberGameTile newTile = (NumberGameTile) getTile(row, i - 1);
						NumberGameTile oldTile = (NumberGameTile) getTile(row, i);
						if(!(newTile.isOccupied(null))) {
							newTile.setPiece((Integer) oldTile.getPiece());
							oldTile.clearPiece();
						} else {
							if(newTile.getPiece() == oldTile.getPiece() &&
									!((NumberGameTile) oldTile).hasCombined()) {
								newTile.setPiece(((int) oldTile.getPiece()) * 2);
								newTile.combine(true);
								oldTile.clearPiece();
								break;
							} // if
						} // if else
					} // for
				} // if
			} // for
		} // for
		clearCombo();
	} // left
	
	public void right() {
		for (int col = playField.length - 2; col >= 0; col--) {
			for (int row = 0; row < playField.length; row++) {
				if (getTile(row, col).isOccupied(null)) {
					for (int i = col; i < playField.length - 1; i++) {
						NumberGameTile newTile = (NumberGameTile) getTile(row, i + 1);
						NumberGameTile oldTile = (NumberGameTile) getTile(row, i);
						if(!(newTile.isOccupied(null))) {
							newTile.setPiece((Integer) oldTile.getPiece());
							oldTile.clearPiece();
						} else {
							if(newTile.getPiece() == oldTile.getPiece() &&
									!((NumberGameTile) oldTile).hasCombined()) {
								newTile.setPiece(((int) oldTile.getPiece()) * 2);
								newTile.combine(true);
								oldTile.clearPiece();
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
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField.length; j++) {
				((NumberGameTile) getTile(i, j)).combine(false);
			}
		}
	}
}
