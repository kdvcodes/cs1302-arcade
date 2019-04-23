package cs1302.arcade.tetris;

import cs1302.arcade.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TetrisTile extends Tile<Tetrimino> {
	
	static final int xStart = 192;
	static final int yStart = 50;
	static final int size = 16;
	final Image tile3 = new Image("/tetris/tile3.png");
	
	public TetrisTile(int row, int column) {
		super(row, column, xStart, yStart, size);
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
