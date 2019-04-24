package cs1302.arcade.tetris;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public enum Shape {
	
	T, J, Z, O, S, L, I;

	final static PixelReader tile1 = new Image("/tetris/tile1.png").getPixelReader();
	final static PixelReader tile2 = new Image("/tetris/tile2.png").getPixelReader();
	
	public PixelReader image() {
		switch (this) {
		case T:
		case O:
		case I:
			return tile1;
		}
		return tile2;
	}
}
