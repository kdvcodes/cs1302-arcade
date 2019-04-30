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
}
