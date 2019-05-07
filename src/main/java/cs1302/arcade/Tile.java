/**
 * 
 */
package cs1302.arcade;

import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

/**
 * This class represents a game piece on a {@Board object}.
 *
 * @param <T> the type of game piece this {@code Tile} stores
 */
public abstract class Tile<T> extends ImageView {

	/**
	 * The piece this Tile holds at present
	 */
	protected T currentPiece;

	/**
	 * Represents the visual appearance of this tile
	 */
	protected WritableImage img;

	/**
	 * The {@code ArcadeGame} that holds this Tile.
	 */
	protected ArcadeGame game;

	/**
	 * Default consturtor, places Tile based on a variety of parameters
	 * 
	 * @param row    the row this Tile is contained in
	 * @param column the column this Tile is contained in
	 * @param xStart the x coordinate that the first Tile is at
	 * @param yStart the y coordinate that the first Tile is at
	 * @param size   the size of each Tile
	 * @param offset the distance between Tiles
	 * @param game   the {@code ArcadeGame} holding this Tile
	 */
	public Tile(int row, int column, int xStart, int yStart, int size,
			int offset, ArcadeGame game) {
		this.game = game;
		setX(xStart + column * (size + offset));
		setY(yStart + row * (size + offset));
		img = new WritableImage(size, size);
		setImage(img);
	}

	/**
	 * Returns the piece held by this Tile
	 * 
	 * @return currentPiece
	 */
	public T getPiece() {
		return currentPiece;
	}

	/**
	 * Sets the current piece and updates the image accordingly
	 * 
	 * @param t the new piece
	 */
	public void setPiece(T t) {
		currentPiece = t;
		update();
	}

	/**
	 * Clears out the current piece and updates the image
	 */
	public void clearPiece() {
		currentPiece = null;
		update();
	}

	/**
	 * Checks if this Tile is occupied by a piece other than the one it
	 * currently holds
	 * 
	 * @param t the piece that this Tile might hold
	 * @return true if this Tile is occuped, false otherwise
	 */
	public boolean isOccupied(T t) {
		if (currentPiece == null || currentPiece == t) {
			return false;
		}
		return true;
	}

	/**
	 * Updates the underlying image to reflect the Tile's current state
	 */
	public abstract void update();
}
