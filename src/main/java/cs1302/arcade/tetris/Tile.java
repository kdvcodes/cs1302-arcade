package cs1302.arcade.tetris;

public class Tile {
	
	private Tetrimino currentPiece;
	
	public boolean isOccupied() {
		if (currentPiece == null) {
			return false;
		}
		return true;
	}

}
