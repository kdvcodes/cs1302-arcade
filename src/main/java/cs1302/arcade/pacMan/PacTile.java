package cs1302.arcade.pacMan;

import java.util.ArrayList;

import cs1302.arcade.ArcadeGame;
import cs1302.arcade.Tile;
import javafx.scene.image.Image;

enum Piece {MAZE, DOT, ENERGIZER, PACMAN, GHOST}

public class PacTile extends Tile<ArrayList<Piece>> {
	
	static final int xStart = 0;
	static final int yStart = 0;
	static final int size = 8;
	static final int offset = 0;
	final Image dot = new Image("/pacMan/dot.png");
	final Image energizer = new Image("/pacMan/energizer.png");

	public PacTile(int row, int column, Piece piece, PacMan game) {
		super(row, column, xStart, yStart, size, offset, game);
		currentPiece = new ArrayList();
		currentPiece.add(piece);
		update();
	}
	
	public void eat() {
		currentPiece.remove(Piece.DOT);
		currentPiece.remove(Piece.ENERGIZER);
		update();
	}
	
	public boolean isMaze() {
		if (currentPiece.contains(Piece.MAZE)) {
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		if (currentPiece.contains(Piece.DOT)) {
			setImage(dot);
		}
		else if (currentPiece.contains(Piece.ENERGIZER)) {
			setImage(energizer);
		}
		else {
			setImage(null);
		}
	}

}
