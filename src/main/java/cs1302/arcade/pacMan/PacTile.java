package cs1302.arcade.pacMan;

import cs1302.arcade.ArcadeGame;
import cs1302.arcade.Tile;
import javafx.scene.image.Image;

enum Piece {MAZE, DOT, ENERGIZER, PACMAN, GHOST}

public class PacTile extends Tile<Piece> {
	
	static final int xStart = 0;
	static final int yStart = 0;
	static final int size = 8;
	static final int offset = 0;
	final Image dot = new Image("/pacMan/dot.png");
	final Image ghost = new Image("/pacMan/ghost.png");

	public PacTile(int row, int column, Piece piece, PacMan game) {
		super(row, column, xStart, yStart, size, offset, game);
		setPiece(piece);
		update();
	}

	@Override
	public void update() {
		if (!isOccupied(null)) {
			return;
		}
		switch (currentPiece) {
		case DOT:
			setImage(dot);
			break;
		default:
			setImage(null);
		}
	}

}
