package cs1302.arcade.pacMan;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

enum Direction {UP, DOWN, LEFT, RIGHT}

public abstract class Actor extends ImageView {
	
	protected PacBoard board;
	protected Direction direction;
	protected Image sprite;
	
	public abstract void move(boolean override);
	
	public int getXTile() {
		return ((int) getX() + 14) / 8 - 1;
	}
	
	public int getYTile() {
		return ((int) getY() + 6) / 8;
	}
	
	public Direction getDirection() {
		return direction;
	}
	

}
