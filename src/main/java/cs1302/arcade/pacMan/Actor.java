package cs1302.arcade.pacMan;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

enum Direction {UP, DOWN, LEFT, RIGHT}

public abstract class Actor extends ImageView {
	
	protected PacBoard board;
	protected PacMan game;
	protected Direction direction;
	protected Image[] sprites;
	
	public abstract void move(boolean override);
	
	public PacTile getCurrentTile() {
		return (PacTile) board.getTile(getYTile(), getXTile());
	}
	
	public int getXTile() {
		return ((int) getX() + 7) / 8;
	}
	
	public int getYTile() {
		return ((int) getY() + 7) / 8;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	protected abstract void checkNewTile(PacTile t);
	
	protected abstract void changeSprite();
	

}
