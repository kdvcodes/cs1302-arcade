/**
 * 
 */
package cs1302.arcade.numberGame;

import cs1302.arcade.Tile;
import javafx.scene.image.Image;

/**
 * @author haile999
 *
 */
public class NumberGameTile extends Tile{
	
	static final int xStart = 60;
	static final int yStart = 330;
	static final int size = 134;
	final Image two = new Image("/2048/2.png");
	
	public NumberGameTile(int row, int column) {
		super(row, column, xStart, yStart, size);
		update();
	}

	@Override
	protected void update() {
		this.setImage(two);
	}
	
}
