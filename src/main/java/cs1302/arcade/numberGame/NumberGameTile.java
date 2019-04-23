/**
 * 
 */
package cs1302.arcade.numberGame;

import cs1302.arcade.Tile;

/**
 * @author haile999
 *
 */
public class NumberGameTile extends Tile{
	
	public NumberGameTile(int row, int column) {
		setX(192 + column * 16);
		setY(50 + row * 16);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}
	
}
