package cs1302.arcade.tetris;

enum Shape {
	T, J, Z, O, S, L, I
}

public class Tetrimino {
	
	private Shape shape;
	private int[][] coordinates;
	private Board board;
	
	private final int initialX = 5;
	private final int initialY = 2;
	
	public Tetrimino(Shape shape, Board board;) {
		this.shape = shape;
		this.boardl = board;
		x = initialX;
		y = initialY;
		switch (shape) {
			case T:
				
		}
	}

}
