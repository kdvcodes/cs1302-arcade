/**
 * 
 */
package cs1302.arcade;

import cs1302.arcade.tetris.Tetrimino;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

/**
 * @author haile999
 *
 */
public abstract class Tile<T> extends ImageView{
	
	protected T currentPiece;
	protected WritableImage img;
	
	public Tile(int row, int column, int xStart, int yStart, int size, int offset) {
		setX(xStart + column * (size + offset));
		setY(yStart + row * (size + offset));
		img = new WritableImage(size, size);
		setImage(img);
	}

	public T getPiece() {
		return currentPiece;
	}
	
	public void setPiece(T t) {
		currentPiece = t;
		update();
	}
	
	public void clearPiece() {
		currentPiece = null;
		update();
	}

	public boolean isOccupied(T t) {
		if (currentPiece == null || currentPiece == t) {
			return false;
		}
		return true;
	}
	
	protected abstract void update();
}
