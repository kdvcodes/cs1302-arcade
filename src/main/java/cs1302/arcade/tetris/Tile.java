package cs1302.arcade.tetris;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends ImageView {
	
	private Tetrimino currentPiece;
	
	public Tile(int row, int column) {
		setImage(new Image("/tetris/tile3.png"));
		setX(194 + column * 16);
		setY(50 + row * 16);
	}
	
	public Tetrimino getPiece() {
		return currentPiece;
	}
	
	public void setPiece(Tetrimino t) {
		currentPiece = t;
	}
	
	public void clearPiece() {
		currentPiece = null;
	}
	
	public boolean isOccupied() {
		if (currentPiece == null) {
			return false;
		}
		return true;
	}

}
