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
	private boolean hasCombined;
	final Image two = new Image("/2048/2.jpg");
	final Image four = new Image("/2048/4.jpg");
	final Image eight = new Image("/2048/8.jpg");
	final Image sixteen = new Image("/2048/16.jpg");
	final Image thirtyTwo = new Image("/2048/32.jpg");
	final Image sixtyFour = new Image("/2048/64.jpg");
	final Image oneTwoEight = new Image("/2048/128.jpg");
	final Image twoFiveSix = new Image("/2048/256.jpg");
	final Image fiveTwelve = new Image("/2048/512.jpg");
	final Image tenTwoFour = new Image("/2048/1024.jpg");
	final Image twentyFourtyEight = new Image("/2048/2048.jpg");
	
	public NumberGameTile(int row, int column, NumberGame game) {
		super(row, column, xStart, yStart, size, offset, game);
		hasCombined = false;
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
		case 16:
			setImage(sixteen);
			break;
		case 32:
			setImage(thirtyTwo);
			break;
		case 64:
			setImage(sixtyFour);
			break;
		case 128:
			setImage(oneTwoEight);
			break;
		case 256:
			setImage(twoFiveSix);
			break;
		case 512:
			setImage(fiveTwelve);
			break;
		case 1024:
			setImage(tenTwoFour);
			break;
		case 2048:
			setImage(twentyFourtyEight);
			break;
		} //switch
	}
	
	public boolean hasCombined() {
		return hasCombined;
	}
	
	public void combine(boolean b) {
		hasCombined = b;
	}
	
}
