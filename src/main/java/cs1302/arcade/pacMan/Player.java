package cs1302.arcade.pacMan;

import javafx.scene.Node;
import javafx.scene.image.Image;

public class Player extends Actor {
	
	private final Image[] sprites = {
			new Image("/pacMan/pacMan0.png"),
			new Image("/pacMan/pacMan.png"),
			new Image("/pacMan/pacMan2.png"),
			new Image("/pacMan/pacMan.png")
	};
	
	private int currentSprite;
	
	//limits eating to 30 fps
	private boolean spriteCounter;

	public Player(PacBoard board, PacMan game) {
		setX(106);
		setY(109 + 96);
		direction = Direction.LEFT;
		this.board = board;
		this.game = game;
		currentSprite = 3;
		spriteCounter = true;
		changeSprite();
	}

	@Override
	public void move(boolean override) {
		PacTile currentTile = getCurrentTile();
		switch (direction) {
		case UP:
			if (!((PacTile) board.getTile(getYTile() - 1, getXTile())).isMaze()) {
				setY(getY() - 1);
				changeSprite();
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
			if (!((PacTile) board.getTile(getYTile() + 1, getXTile())).isMaze()) {
				setY(getY() + 1);
				changeSprite();
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
			if (!((PacTile) board.getTile(getYTile(), getXTile() - 1)).isMaze()) {
				setX(getX() - 1);
				changeSprite();
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
			if (!((PacTile) board.getTile(getYTile(), getXTile() + 1)).isMaze()) {
				setX(getX() + 1);
				changeSprite();
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
		checkNewTile(currentTile);
	}
	
	protected void checkNewTile(PacTile t) {
		if (t != getCurrentTile()) {
			t.getPiece().remove(Piece.PACMAN);
			getCurrentTile().getPiece().add(Piece.PACMAN);
		}
		//pacman uses stupid code for checking dots. not my idea
		double[][] dotCoordinates = {{getY() + 7, getX() + 8}, {getY() + 7, getX() + 4},
				{getY() + 10, getX() + 7}, {getY() + 4, getX() + 7}};
		for (int i = 0; i < 4; i++) {
			PacTile p = ((PacTile) board.getTile((int) dotCoordinates[i][0] / 8,
					(int) dotCoordinates[i][1] / 8));
			if (p.getPiece().contains(Piece.DOT)) {
				p.eat();
				game.justEaten();
			}
		}
		
	}
	
	public void setDirection(Direction d) {
		switch (d) {
		case UP:
			if (((PacTile) board.getTile(getYTile() - 1, getXTile())).isMaze()) {
				return;
			}
			setRotate(90);
			break;
		case DOWN:
			if (((PacTile) board.getTile(getYTile() + 1, getXTile())).isMaze()) {
				return;
			}
			setRotate(270);
			break;
		case LEFT:
			if (((PacTile) board.getTile(getYTile(), getXTile() - 1)).isMaze()) {
				return;
			}
			setRotate(0);
			break;
		case RIGHT:
			if (((PacTile) board.getTile(getYTile(), getXTile() + 1)).isMaze()) {
				return;
			}
			setRotate(180);
			break;
		}
		direction = d;
	}
	
	public void changeSprite() {
		if (spriteCounter) {
			currentSprite++;
			setImage(sprites[currentSprite % 4]);
		}
		spriteCounter = !spriteCounter;
	}

}
