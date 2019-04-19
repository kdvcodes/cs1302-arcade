package cs1302.arcade.tetris;

enum Rotation {
	UP, RIGHT, DOWN, LEFT
}

public class Tetrimino {
	
	private Shape shape;
	private int rotation;
	private Board board;
	private Tile[] coordinates;
	private int x;
	private int y;
	private boolean isActive;
	
	private final int initialX = 5;
	private final int initialY = 2;
	
	public Tetrimino(Shape shape, Board board) {
		this.board = board;
		this.shape = shape;
		coordinates = new Tile[4];
		isActive = true;
		rotation = 2;
		update(true, initialX, initialY, 2);
	}
	
	private Tile[] newCoordinates(int x, int y, int rotation) {
		Tile[] c = new Tile[4];
		c[0] = board.getTile(y, x);
		switch (shape) {
		case T:
			switch (rotation) {
			case 0:
				c[1] = board.getTile(y, x - 1);
				c[2] = board.getTile(y, x + 1);
				c[3] = board.getTile(y - 1, x);
				break;
			case 1:
				c[1] = board.getTile(y, x + 1);
				c[2] = board.getTile(y + 1, x);
				c[3] = board.getTile(y - 1, x);
				break;
			case 2:
				c[1] = board.getTile(y, x - 1);
				c[2] = board.getTile(y, x + 1);
				c[3] = board.getTile(y + 1, x);
				break;
			case 3:
				c[1] = board.getTile(y, x - 1);
				c[2] = board.getTile(y + 1, x);
				c[3] = board.getTile(y - 1, x);
				break;
			}
			break;
		}
		return c;
	}
	
	private boolean update(boolean newPiece, int x, int y, int rotation) {
		if (canMove(x, y, rotation)) {
			if (!newPiece) {
				empty();
			}
			this.x = x;
			this.y = y;
			this.rotation = rotation;
			coordinates = newCoordinates(x, y, rotation);
			fill();
			return true;
		}
		else {
			if (newPiece) {
				//game over :(
			}
		}
		return false;
	}
	
	private void empty() {
		for (int i = 0; i < 4; i++) {
			coordinates[i].clearPiece();
		}
	}
	
	private void fill() {
		for (int i = 0; i < 4; i++) {
			coordinates[i].setPiece(this);
		}
	}
	
	private boolean canMove(int x, int y, int rotation) {
		try {
			Tile[] c = newCoordinates(x, y, rotation);
			for (int i = 0; i < 4; i++) {
				if (c[i].isOccupied(this)) {
					return false;
				}
			}
			return true;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public void rotate(int direction) {
		switch (shape) {
		case T:
		case J:
		case L:
			rotation += direction;
			if (rotation == -1) {
				rotation = 3;
			}
			if (rotation == 4) {
				rotation = 0;
			}
			break;
		}
		update(false);
	}
		
	public boolean drop() {
		if (!update(false, x, y + 1, rotation)) {
			return false;
		}
		return true;
	}
	
	public void left() {
		update(false, x - 1, y, rotation);
	}
	
	public void right() {
		update(false, x + 1, y, rotation);
	}
	
	public Shape getShape() {
		return shape;
	}

}
