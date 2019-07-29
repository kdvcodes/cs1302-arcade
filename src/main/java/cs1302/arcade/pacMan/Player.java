package cs1302.arcade.pacMan;

import javafx.scene.image.Image;

public class Player extends Actor {

	public Player(PacBoard board) {
		sprite = new Image("/pacMan/pacMan.png");
		setX(109);
		setY(109 + 96);
		direction = Direction.LEFT;
		this.board = board;
		setImage(sprite);
	}

	@Override
	public void move(boolean override) {
		switch (direction) {
		case UP:
			if (board.getTile(getYTile() - 1, getXTile()).getPiece() != Piece.MAZE) {
				setY(getY() - 1);
				if (getX() < getXTile() * 8 - 3) {
					setX(getX() + 1);
				}
				if (getX() > getXTile() * 8 - 3) {
					setX(getX() - 1);
				}
			}
			else if (getY() != getYTile() * 8 - 3) {
				setY(getY() - 1);
			}
			break;
		case DOWN:
			if (board.getTile(getYTile() + 1, getXTile()).getPiece() != Piece.MAZE) {
				setY(getY() + 1);
				if (getX() < getXTile() * 8 - 3) {
					setX(getX() + 1);
				}
				if (getX() > getXTile() * 8 - 3) {
					setX(getX() - 1);
				}
			}
			else if (getY() != getYTile() * 8 - 3) {
				setY(getY() + 1);
			}
			break;
		case LEFT:
			if (board.getTile(getYTile(), getXTile() - 1).getPiece() != Piece.MAZE) {
				setX(getX() - 1);
				if (getY() < getYTile() * 8 - 3) {
					setY(getY() + 1);
				}
				if (getY() > getYTile() * 8 - 3) {
					setY(getY() - 1);
				}
			}
			else if (getX() != getXTile() * 8 - 3) {
				setX(getX() - 1);
			}
			break;
		case RIGHT:
			if (board.getTile(getYTile(), getXTile() + 1).getPiece() != Piece.MAZE) {
				setX(getX() + 1);
				if (getY() < getYTile() * 8 - 3) {
					setY(getY() + 1);
				}
				if (getY() > getYTile() * 8 - 3) {
					setY(getY() - 1);
				}
			}
			else if (getX() != getXTile() * 8 - 3) {
				setX(getX() + 1);
			}
			break;
		}
	}
	
	public void setDirection(Direction d) {
		switch (d) {
		case UP:
			if (board.getTile(getYTile() - 1, getXTile()).getPiece() == Piece.MAZE) {
				return;
			}
			break;
		case DOWN:
			if (board.getTile(getYTile() + 1, getXTile()).getPiece() == Piece.MAZE) {
				return;
			}
			break;
		case LEFT:
			if (board.getTile(getYTile(), getXTile() - 1).getPiece() == Piece.MAZE) {
				return;
			}
			break;
		case RIGHT:
			if (board.getTile(getYTile(), getXTile() + 1).getPiece() == Piece.MAZE) {
				return;
			}
			break;
		}
		direction = d;
	}

}
