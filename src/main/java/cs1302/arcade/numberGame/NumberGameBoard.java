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
	
	private boolean moveMade;
	
	public NumberGameBoard(int rows, int columns, NumberGame game) {
		playField = new NumberGameTile[rows][columns];
		this.game = game;
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField[i].length; j++) {
				playField[i][j] = new NumberGameTile(i, j, game);
				//top two rows are hidden, this statement ensures that
				getChildren().add(playField[i][j]);
			}
		}
	}
	
	public boolean up() {
		moveMade = false;
		for (int row = 1; row < playField.length; row++) {
			for (int col = 0; col < playField.length; col++) {
				if (getTile(row, col).isOccupied(null)) {
					for (int i = row; i > 0; i--) {
						NumberGameTile newTile = (NumberGameTile) getTile(i - 1, col);
						NumberGameTile oldTile = (NumberGameTile) getTile(i, col);
						if (move(newTile, oldTile)) {
							break;
						}
					} // for
				} // if
			} // for
		} // for
		clearCombo();
		return moveMade;
	}
	
	public boolean down() {
		moveMade = false;
		for (int row = playField.length - 2; row >= 0; row--) {
			for (int col = 0; col < playField.length; col++) {
				if (getTile(row, col).isOccupied(null)) {
					for (int i = row; i < playField.length - 1; i++) {
						NumberGameTile newTile = (NumberGameTile) getTile(i + 1, col);
						NumberGameTile oldTile = (NumberGameTile) getTile(i, col);
						if (move(newTile, oldTile)) {
							break;
						}
					} // for
				} // if
			} // for
		} // for
		clearCombo();
		return moveMade;
	} // down
	
	public boolean left() {
		moveMade = false;
		for (int col = 1; col < playField.length; col++) {
			for (int row = 0; row < playField.length; row++) {
				if (getTile(row, col).isOccupied(null)) {
					for (int i = col; i > 0; i--) {
						NumberGameTile newTile = (NumberGameTile) getTile(row, i - 1);
						NumberGameTile oldTile = (NumberGameTile) getTile(row, i);
						if (move(newTile, oldTile)) {
							break;
						}
					} // for
				} // if
			} // for
		} // for
		clearCombo();
		return moveMade;
	} // left
	
	public boolean right() {
		moveMade = false;
		for (int col = playField.length - 2; col >= 0; col--) {
			for (int row = 0; row < playField.length; row++) {
				if (getTile(row, col).isOccupied(null)) {
					for (int i = col; i < playField.length - 1; i++) {
						NumberGameTile newTile = (NumberGameTile) getTile(row, i + 1);
						NumberGameTile oldTile = (NumberGameTile) getTile(row, i);
						if (move(newTile, oldTile)) {
							break;
						}
					} // for
				} // if
			} // for
		} // for
		clearCombo();
		return moveMade;
	} // right
	
	private boolean move(NumberGameTile newTile, NumberGameTile oldTile) {
		if(!(newTile.isOccupied(null))) {
			newTile.setPiece(oldTile.getPiece());
			oldTile.clearPiece();
			moveMade = true;
		} else {
			if(newTile.getPiece().equals(oldTile.getPiece())  &&
					!newTile.hasCombined()) {
				newTile.setPiece(oldTile.getPiece() * 2);
				newTile.combine(true);
				game.updateScore(newTile.getPiece().intValue());
				oldTile.clearPiece();
				moveMade = true;
			} // if
			return true;
		} // if else
		return false;
	}
	
	public void clear() {
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField.length; j++) {
				getTile(i, j).clearPiece();
			}
		}
	}
	
	private void clearCombo() {
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField.length; j++) {
				((NumberGameTile) getTile(i, j)).combine(false);
			}
		}
	}
	
	public boolean isFull() {
		for (int y = 0; y < playField.length; y++) {
			for (int x = 1; x < playField.length; x++) {
				if (getTile(x, y).getPiece().equals(getTile(x - 1, y).getPiece())) {
					return false;
				}
			}
		}
		for (int x = 0; x < playField.length; x++) {
			for (int y = 1; y < playField.length; y++) {
				if (getTile(x, y).getPiece().equals(getTile(x, y - 1).getPiece())) {
					return false;
				}
			}
		}
		return true;
	}
}
