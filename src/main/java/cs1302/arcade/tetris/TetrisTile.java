package cs1302.arcade.tetris;

import cs1302.arcade.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TetrisTile extends Tile<Tetrimino> {
	
	final Image tile3 = new Image("/tetris/tile3.png");
	
	public TetrisTile(int row, int column) {
		setX(192 + column * 16);
		setY(50 + row * 16);
	}
	
	protected void update() {
		if (currentPiece == null) {
			setImage(null);
			return;
		}
		switch (currentPiece.getShape()) {
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
}
