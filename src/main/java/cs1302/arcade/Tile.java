/**
 * 
 */
package cs1302.arcade;

import cs1302.arcade.tetris.Tetrimino;
import javafx.scene.image.ImageView;

/**
 * @author haile999
 *
 */
public abstract class Tile<T> extends ImageView{
	
	protected T currentPiece;
	
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
