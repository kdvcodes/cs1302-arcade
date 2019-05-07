package cs1302.arcade.numberGame;

import cs1302.arcade.Tile;
import javafx.scene.image.Image;

/**
 * This is the number game tile class
 *
 */
public class NumberGameTile extends Tile<Integer> {

	static final int xStart = 60;
	static final int yStart = 330;
	static final int size = 114;
	static final int offset = 20;
	private boolean hasCombined;
	final Image two = new Image("/2048/2.png");
	final Image four = new Image("/2048/4.png");
	final Image eight = new Image("/2048/8.png");
	final Image sixteen = new Image("/2048/16.png");
	final Image thirtyTwo = new Image("/2048/32.png");
	final Image sixtyFour = new Image("/2048/64.png");
	final Image oneTwoEight = new Image("/2048/128.png");
	final Image twoFiveSix = new Image("/2048/256.png");
	final Image fiveTwelve = new Image("/2048/512.png");
	final Image tenTwoFour = new Image("/2048/1024.png");
	final Image twentyFourtyEight = new Image("/2048/2048.png");

	/**
	 * this is the number game tile constructor for the game
	 * 
	 * @param row    sets up the position for the tile
	 * @param column sets up the position for the tile
	 * @param game   the game the tile is in
	 */
	public NumberGameTile(int row, int column, NumberGame game) {
		super(row, column, xStart, yStart, size, offset, game);
		hasCombined = false;
		update();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		if (currentPiece == null) {
			setImage(null);
			return;
		}

		switch (currentPiece) {
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
		} // switch
	}

	/**
	 * this method will check if the tile has combined or not
	 * 
	 * @return true if has combined, false otherwise
	 */
	public boolean hasCombined() {
		return hasCombined;
	}

	/**
	 * this method will set the combination property for the tile
	 * 
	 * @param b the specified boolean value to be set
	 */
	public void combine(boolean b) {
		hasCombined = b;
	}

}
