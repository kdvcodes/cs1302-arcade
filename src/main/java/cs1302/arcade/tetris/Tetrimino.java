package cs1302.arcade.tetris;

import cs1302.arcade.Board;
import cs1302.arcade.Tile;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaException;

public class Tetrimino {
	
	private Shape shape;
	private int rotation;
	private TetrisBoard board;
	private TetrisTile[] coordinates;
	private TetrisTile[] ghost;
	private int x;
	private int y;
	private final int initialX = 5;
	private final int initialY = 2;
	private AudioClip move;
	private AudioClip rotate;
	private final Image ghostTile = new Image("/tetris/tile0.png");
	private boolean playSound;
	
	public Tetrimino(Shape shape, Board board, boolean showGhost) {
		this.board = (TetrisBoard) board;
		this.shape = shape;
		coordinates = new TetrisTile[4];
		soundSetup();
		if (showGhost) {
			ghost = new TetrisTile[4];
		}
		rotation = 2;
		update(true, initialX, initialY, 2);
	}
	
	public Tetrimino(Shape shape, Board board, int x, int y) {
		this.board = (TetrisBoard) board;
		this.shape = shape;
		coordinates = new TetrisTile[4];
		rotation = 2;
		update(true, x, y, 2);
	}
	
	private void soundSetup() {
		try {
			move = new AudioClip(getClass().getResource("/tetris/move.wav").toString());
			rotate = new AudioClip(getClass().getResource("/tetris/rotate.wav").toString());
			playSound = true;
		} catch (MediaException e) {
			playSound = false;
		}
	}
	
	private boolean update(boolean newPiece, int x, int y, int rotation) {
		if (canMove(x, y, rotation)) {
			if (!newPiece) {
				empty();
			}
			this.x = x;
			this.y = y;
			this.rotation = rotation;
			if (ghost != null) {
				drawGhost();
			}
			coordinates = shape.newCoordinates(x, y, rotation, board);
			fill();
			return true;
		}
		else {
			if (newPiece) {
				board.gameOver();
				return true;
			}
		}
		return false;
	}
	
	private void drawGhost() {
		int i = y;
		while (canMove(x, i + 1, rotation)) {
			i++;
		}
		ghost = shape.newCoordinates(x, i, rotation, board);
		for (int j = 0; j < 4; j++) {
			ghost[j].setImage(ghostTile);
		}
	}
	
	private void empty() {
		for (int i = 0; i < 4; i++) {
			coordinates[i].clearPiece();
			if (ghost != null) {
				ghost[i].clearPiece();
			}
		}
	}
	
	private void fill() {
		for (int i = 0; i < 4; i++) {
			coordinates[i].setPiece(this);
		}
	}
	
	private boolean canMove(int x, int y, int rotation) {
		try {
			TetrisTile[] c = shape.newCoordinates(x, y, rotation, board);
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
		if (shape == Shape.O) {
			return;
		}
		int rotation = this.rotation + direction;
		switch (shape) {
		case T:
		case J:
		case L:
			if (rotation == -1) {
				rotation = 3;
			}
			if (rotation == 4) {
				rotation = 0;
			}
			break;
		default:
			if (rotation == 0) {
				rotation = 2;
			}
			if (rotation == 3) {
				rotation = 1;
			}
			break;
		}
		if (update(false, x, y, rotation) && playSound) {
			rotate.play();
		}
	}
		
	public boolean drop() {
		if (!update(false, x, y + 1, rotation)) {
			return false;
		}
		return true;
	}
	
	public void left() {
		if (update(false, x - 1, y, rotation) && playSound) {
			move.play();
		}
	}
	
	public void right() {
		if (update(false, x + 1, y, rotation) && playSound) {
			move.play();
		}
	}
	
	public Shape getShape() {
		return shape;
	}

}
