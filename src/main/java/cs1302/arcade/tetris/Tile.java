package cs1302.arcade.tetris;

public class Tile {
	
	private Tetrimino currentPiece;
	
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
