package cs1302.arcade.tetris;

public class Board {
	
	private Tile[][] playField;
	
	private final int numberOfRows = 22;
	private final int numberOfColumns = 10;
	
	public Board() {
		playField = new Tile[numberOfColumns][numberOfRows];
		for (int i = 0; i < playField.length; i++;) {
			for (int j = 0; j < playField[i].length; j++) {
				playField[i][j] = new Tile();
			}
		}
	}
	
	public Tile getTile(int row, int column) {
		return playField[row][column];
	}
	
	public Tile[][] getPlayField() {
		return playField;
	}

}
