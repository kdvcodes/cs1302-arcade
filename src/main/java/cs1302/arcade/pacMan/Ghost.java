package cs1302.arcade.pacMan;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ghost extends Actor {
	
	private int targetX;
	private int targetY;
	private final int scatterX;
	private final int scatterY;
	
	public Ghost(PacBoard board, Image sprite) {
		this.sprite = sprite;
		scatterX = 0;
		scatterY = 0;
		setX(109);
		setY(109);
		direction = Direction.LEFT;
		this.board = board;
		setImage(sprite);
	}
	
	public void move(boolean override) {
		switch (direction) {
		case LEFT:
			if (getX() != getXTile() * 8 - 3 || override) {
				setX(getX() - 1);
			}
			else {
				turn();
			}
			break;
		case RIGHT:
			if (getX() != getXTile() * 8 - 3 || override) {
				setX(getX() + 1);
			}
			else {
				turn();
			}
			break;
		case UP:
			if (getY() != getYTile() * 8 - 3 || override) {
				setY(getY() - 1);
			}
			else {
				turn();
			}
			break;
		case DOWN:
			if (getY() != getYTile() * 8 - 3 || override) {
				setY(getY() + 1);
			}
			else {
				turn();
			}
			break;
		}
		board.getTile(getYTile(), getXTile()).setPiece(Piece.GHOST);
	}
	
	private void turn() {
		Direction turn = direction;
		int distance = 10000000;
		if (board.getTile(getYTile() - 1, getXTile()).getPiece() != Piece.MAZE &&
				getYTile() != 14 &&
				!(getYTile() == 26 && (getXTile() == 12 || getXTile() == 15)) &&
				direction != Direction.DOWN) {
			int upDistance = (int) (Math.pow(getXTile() - targetX, 2) + Math.pow(getYTile() - 1 - targetY, 2));
			if (upDistance < distance) {
				turn = Direction.UP;
				distance = upDistance;
			}
		}
		if (board.getTile(getYTile(), getXTile() - 1).getPiece() != Piece.MAZE &&
				direction != Direction.RIGHT) {
			int leftDistance = (int) (Math.pow(getXTile() - 1 - targetX, 2) + Math.pow(getYTile() - targetY, 2));
			if (leftDistance < distance) {
				turn = Direction.LEFT;
				distance = leftDistance;
			}
		}
		if (board.getTile(getYTile() + 1, getXTile()).getPiece() != Piece.MAZE &&
				direction != Direction.UP) {
			int downDistance = (int) (Math.pow(getXTile() - targetX, 2) + Math.pow(getYTile() + 1 - targetY, 2));
			if (downDistance < distance) {
				turn = Direction.DOWN;
				distance = downDistance;
			}
		}
		if (board.getTile(getYTile(), getXTile() + 1).getPiece() != Piece.MAZE &&
				direction != Direction.LEFT) {
			int rightDistance = (int) (Math.pow(getXTile() + 1 - targetX, 2) + Math.pow(getYTile() - targetY, 2));
			if (rightDistance < distance) {
				turn = Direction.RIGHT;
				distance = rightDistance;
			}
		}
		direction = turn;
		move(true);
	}
	
	public void setTarget(int x, int y) {
		targetX = x;
		targetY = y;
	}

	public void reverse() {
		switch (direction) {
		case UP:
			direction = Direction.DOWN;
			break;
		case DOWN:
			direction = Direction.UP;
			break;
		case LEFT:
			direction = Direction.RIGHT;
			break;
		case RIGHT:
			direction = Direction.LEFT;
			break;
		}
	}
	

}
