package cs1302.arcade.numberGame;

import cs1302.arcade.Board;

/**
 * This is the number game board class that holds most logics for the 2048 game
 */
public class NumberGameBoard extends Board {

	private boolean moveMade;

	/**
	 * This is the NumberGameBoard constructor that sets up the logic when the
	 * game starts
	 * 
	 * @param rows    sets up the dimension of the game
	 * @param columns used to set up the dimension of the game
	 * @param game    specifies the current game
	 */
	public NumberGameBoard(int rows, int columns, NumberGame game) {
		playField = new NumberGameTile[rows][columns];
		this.game = game;
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField[i].length; j++) {
				playField[i][j] = new NumberGameTile(i, j, game);
				// top two rows are hidden, this statement ensures that
				getChildren().add(playField[i][j]);
			}
		}
	}

	/**
	 * This is the method for the move up
	 * 
	 * @return true if a move has been made, false otherwise
	 */
	public boolean up() {
		moveMade = false;
		for (int row = 1; row < playField.length; row++) {
			for (int col = 0; col < playField.length; col++) {
				if (getTile(row, col).isOccupied(null)) {
					for (int i = row; i > 0; i--) {
						NumberGameTile newTile = (NumberGameTile) getTile(i - 1,
								col);
						NumberGameTile oldTile = (NumberGameTile) getTile(i,
								col);
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

	/**
	 * This is the method for the move down
	 * 
	 * @return true if a move has been made, false otherwise
	 */
	public boolean down() {
		moveMade = false;
		for (int row = playField.length - 2; row >= 0; row--) {
			for (int col = 0; col < playField.length; col++) {
				if (getTile(row, col).isOccupied(null)) {
					for (int i = row; i < playField.length - 1; i++) {
						NumberGameTile newTile = (NumberGameTile) getTile(i + 1,
								col);
						NumberGameTile oldTile = (NumberGameTile) getTile(i,
								col);
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

	/**
	 * This is the method for the move left
	 * 
	 * @return true if a move has been made, false otherwise
	 */
	public boolean left() {
		moveMade = false;
		for (int col = 1; col < playField.length; col++) {
			for (int row = 0; row < playField.length; row++) {
				if (getTile(row, col).isOccupied(null)) {
					for (int i = col; i > 0; i--) {
						NumberGameTile newTile = (NumberGameTile) getTile(row,
								i - 1);
						NumberGameTile oldTile = (NumberGameTile) getTile(row,
								i);
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

	/**
	 * This is the method for the move right
	 * 
	 * @return true if a move has been made, false otherwise
	 */
	public boolean right() {
		moveMade = false;
		for (int col = playField.length - 2; col >= 0; col--) {
			for (int row = 0; row < playField.length; row++) {
				if (getTile(row, col).isOccupied(null)) {
					for (int i = col; i < playField.length - 1; i++) {
						NumberGameTile newTile = (NumberGameTile) getTile(row,
								i + 1);
						NumberGameTile oldTile = (NumberGameTile) getTile(row,
								i);
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

	/**
	 * This method will define movements of the game 2048
	 * 
	 * @param newTile the new tile that is made if there is a move
	 * @param oldTile the old tile that will be cleared
	 * @return true if a move is made, false otherwise
	 */
	private boolean move(NumberGameTile newTile, NumberGameTile oldTile) {
		if (!(newTile.isOccupied(null))) {
			newTile.setPiece(oldTile.getPiece());
			oldTile.clearPiece();
			moveMade = true;
		} else {
			if (newTile.getPiece().equals(oldTile.getPiece())
					&& !newTile.hasCombined()) {
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

	/**
	 * this method will clear the piece that calls it
	 */
	public void clear() {
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField.length; j++) {
				getTile(i, j).clearPiece();
			}
		}
	}

	/**
	 * This method will clear the combination property of each tiles
	 */
	private void clearCombo() {
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField.length; j++) {
				((NumberGameTile) getTile(i, j)).combine(false);
			}
		}
	}

	/**
	 * this method will check if the grid is full
	 * 
	 * @return true if grid if full, false otherwise
	 */
	public boolean isFull() {
		for (int y = 0; y < playField.length; y++) {
			for (int x = 1; x < playField.length; x++) {
				if (getTile(x, y).getPiece()
						.equals(getTile(x - 1, y).getPiece())) {
					return false;
				}
			}
		}
		for (int x = 0; x < playField.length; x++) {
			for (int y = 1; y < playField.length; y++) {
				if (getTile(x, y).getPiece()
						.equals(getTile(x, y - 1).getPiece())) {
					return false;
				}
			}
		}
		return true;
	}
}
