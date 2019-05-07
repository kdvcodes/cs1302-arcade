package cs1302.arcade.tetris;

import cs1302.arcade.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

/**
 * This is the tetristile class
 */
public class TetrisTile extends Tile<Tetrimino> {
	
	static final int xStart = 192;
	static final int yStart = 50;
	static final int size = 16;
	static final int offset = 0;
	final PixelReader curtain = new Image("/tetris/curtain.png").getPixelReader();
	
	/**
	 * This is the default constructor for the tetristile class
	 * @param row row val
	 * @param column col val
	 * @param game the current game
	 */
	public TetrisTile(int row, int column, Tetris game) {
		super(row, column, xStart, yStart, size, offset, game);
	}

	/**
	 * This is the constructor with x and y start
	 * @param row row val
	 * @param column col val
	 * @param xStart xStart val
	 * @param yStart yStart val
	 * @param game current game
	 */
	public TetrisTile(int row, int column, int xStart, int yStart, Tetris game) {
		super(row, column, xStart, yStart, size, offset, game);
	}
	
	/**
	 * This method will update the tetris tile
	 */
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
	
	/**
	 * 
	 */
	public void curtain() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (curtain.getArgb(x, y) == 0xFF3f48cc) {
					img.getPixelWriter().setArgb(x, y, Shape.getColor2(((Tetris) game).getLevel()));
				}
				if (curtain.getArgb(x, y) == 0xFFec1c24) {
					img.getPixelWriter().setArgb(x, y, Shape.getColor1(((Tetris) game).getLevel()));
				}
				else {
					img.getPixelWriter().setArgb(x, y, curtain.getArgb(x, y));
				}
			}
		}
		setImage(img);
	}
}
