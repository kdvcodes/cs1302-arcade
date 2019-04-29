package cs1302.arcade.tetris;

import cs1302.arcade.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class TetrisTile extends Tile<Tetrimino> {
	
	static final int xStart = 192;
	static final int yStart = 50;
	static final int size = 16;
	static final int offset = 0;
	
	public TetrisTile(int row, int column, Tetris game) {
		super(row, column, xStart, yStart, size, offset, game);
	}
	
	public void update() {
		if (currentPiece == null) {
			setImage(null);
			return;
		}
		Shape s = currentPiece.getShape();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (s.image().getArgb(x, y) == 0xFFec1c24) {
					img.getPixelWriter().setArgb(x, y, s.getColor(((Tetris) game).getLevel()));
				}
				else {
					img.getPixelWriter().setArgb(x, y, s.image().getArgb(x, y));
				}
			}
		}
		setImage(img);
	}

	private int getColor() {
		return 0xFF633dfd;
	}
}
