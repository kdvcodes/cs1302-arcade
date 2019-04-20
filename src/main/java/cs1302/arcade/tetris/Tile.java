package cs1302.arcade.tetris;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends ImageView {
	
	private Tetrimino currentPiece;

	final Image tile3 = new Image("/tetris/tile3.png");
	
	public Tile(int row, int column) {
		setX(192 + column * 16);
		setY(50 + row * 16);
	}
	
	public Tetrimino getPiece() {
		return currentPiece;
	}
	
	public void setPiece(Tetrimino t) {
		currentPiece = t;
		switch (t.getShape()) {
		case I:
		case J:
		case L:
		case O:
		case S:
		case T:
		case Z:
			setImage(tile3);
		}
	}
	
	public void clearPiece() {
		currentPiece = null;
		setImage(null);
	}
	
	public boolean isOccupied(Tetrimino t) {
		if (currentPiece == null || currentPiece == t) {
			return false;
		}
		return true;
	}

}
