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
public class NumberGameTile extends Tile<Integer>{
	
	static final int xStart = 60;
	static final int yStart = 330;
	static final int size = 114;
	static final int offset = 20;
	final Image two = new Image("/2048/2.png");
	final Image four = new Image("/2048/4.png");
	final Image eight = new Image("/2048/8.png");
	
	public NumberGameTile(int row, int column, NumberGame game) {
		super(row, column, xStart, yStart, size, offset, game);
		update();
	}

	@Override
	public void update() {
		if(currentPiece == null) {
			setImage(null);
			return;
		}
		
		switch(currentPiece) {
		case 2:
			setImage(two);
			break;
		case 4:
			setImage(four);
			break;
		case 8:
			setImage(eight);
			break;
		} //switch
	}
	
}
