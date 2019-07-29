package cs1302.arcade.pacMan;

import java.io.File;

import cs1302.arcade.ArcadeGame;
import cs1302.arcade.ArcadeToolBar;
import cs1302.arcade.tetris.Tetrimino;
import cs1302.arcade.tetris.TetrisBoard;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

public class PacMan extends ArcadeGame {
	
	private final long framerate = 60;
	
	private Player pacMan;
	private Ghost blinky;
	private Ghost pinky;
	
	private final Timeline gameLoop = 
			new Timeline(new KeyFrame(Duration.millis(1000 / framerate), this::play));
	
	public PacMan() {
		board = new PacBoard(this);
		background = new Image("/pacMan/background.png");
		blinky = new Ghost((PacBoard) board, new Image("/pacMan/blinky.png"));
		pinky = new Ghost((PacBoard) board, new Image("/pacMan/pinky.png"));
		pacMan = new Player((PacBoard) board);
		start(new ArcadeToolBar(this), board, blinky, pinky, pacMan);
		Scale scale = new Scale(2, 2);
		scale.setPivotX(0);
		scale.setPivotY(0);
		game.getTransforms().setAll(scale);
		setWidth(getWidth() * 2);
		setHeight(getHeight() * 2);
		setTitle("Pac-Man");
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		gameLoop.play();
	}
	
	private void play(ActionEvent e) {
		setTargets(pacMan.getXTile(), pacMan.getYTile());
		blinky.move(false);
		pinky.move(false);
		pacMan.move(false);
	}
	
	private void setTargets(int x, int y) {
		blinky.setTarget(x, y);
		switch (pacMan.getDirection()) {
		case UP:
			pinky.setTarget(x - 4, y - 4);
			break;
		case DOWN:
			pinky.setTarget(x, y + 4);
			break;
		case LEFT:
			pinky.setTarget(x - 4, y);
			break;
		case RIGHT:
			pinky.setTarget(x + 4, y);
			break;
		}
	}

	@Override
	public void newGame(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void options(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void help(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Event e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void finished() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void move(KeyEvent ke) {
		switch (ke.getCode()) {
		case UP:
			pacMan.setDirection(Direction.UP);
			break;
		case DOWN:
			pacMan.setDirection(Direction.DOWN);
			break;
		case LEFT:
			pacMan.setDirection(Direction.LEFT);
			break;
		case RIGHT:
			pacMan.setDirection(Direction.RIGHT);
			break;
		}
	}

	@Override
	public void updateScore(int i) {
		// TODO Auto-generated method stub

	}

}
