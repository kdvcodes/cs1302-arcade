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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

enum Mode {CHASE, SCATTER, FRIGHTENED}

public class PacMan extends ArcadeGame {
	
	private final long framerate = 60;
	
	private Player pacMan;
	private Ghost blinky;
	private Ghost pinky;
	private Ghost inky;
	private Ghost clyde;
	
	private Mode mode;
	private int modeTimer;
	
	private KeyCode newDirection;
	
	private final int blinkyScatterX = 25;
	private final int blinkyScatterY = 0;
	private final int pinkyScatterX = 2;
	private final int pinkyScatterY = 0;
	private final int inkyScatterX = 27;
	private final int inkyScatterY = 35;
	private final int clydeScatterX = 0;
	private final int clydeScatterY = 35;
	
	private final Timeline gameLoop = 
			new Timeline(new KeyFrame(Duration.millis(1000 / framerate), this::play));
	
	public PacMan() {
		board = new PacBoard(this);
		background = new Image("/pacMan/background.png");
		mode = Mode.SCATTER;
		modeTimer = 0;
		blinky = new Ghost((PacBoard) board, new Image("/pacMan/blinky.png"));
		pinky = new Ghost((PacBoard) board, new Image("/pacMan/pinky.png"));
		inky = new Ghost((PacBoard) board, new Image("/pacMan/inky.png"));
		clyde = new Ghost((PacBoard) board, new Image("/pacMan/clyde.png"));
		pacMan = new Player((PacBoard) board);
		setScatterTargets();
		start(new ArcadeToolBar(this), board, blinky, pinky, inky, clyde, pacMan);
		getScene().setOnKeyReleased(ke -> {if (ke.getCode() == newDirection)
			newDirection = null;
		});
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
		updateMode();
		if (mode == Mode.CHASE) {
			setChaseTargets(pacMan.getXTile(), pacMan.getYTile());
		}
		blinky.move(false);
		pinky.move(false);
		inky.move(false);
		clyde.move(false);
		move();
		pacMan.move(false);
		modeTimer++;
	}
	
	private void updateMode() {
		if (modeTimer == 420 && mode == Mode.SCATTER) {
			mode = mode.CHASE;
			blinky.reverse();
			pinky.reverse();
			inky.reverse();
			clyde.reverse();
			modeTimer = 0;
		}
		if (modeTimer == 1200) {
			mode = mode.SCATTER;
			blinky.reverse();
			pinky.reverse();
			inky.reverse();
			clyde.reverse();
			setScatterTargets();
			modeTimer = 0;
		}
	}

	private void setChaseTargets(int x, int y) {
		blinky.setTarget(x, y);
		switch (pacMan.getDirection()) {
		case UP:
			pinky.setTarget(x - 4, y - 4);
			inky.setTarget(2 * (x - 4) - blinky.getXTile(), 2 * (y - 4) - blinky.getYTile());
			break;
		case DOWN:
			pinky.setTarget(x, y + 4);
			inky.setTarget(2 * (x) - blinky.getXTile(), 2 * (y + 4) - blinky.getYTile());
			break;
		case LEFT:
			pinky.setTarget(x - 4, y);
			inky.setTarget(2 * (x - 4) - blinky.getXTile(), 2 * (y) - blinky.getYTile());
			break;
		case RIGHT:
			pinky.setTarget(x + 4, y);
			inky.setTarget(2 * (x + 4) - blinky.getXTile(), 2 * (y) - blinky.getYTile());
			break;
		}
		if (Math.pow(clyde.getXTile() - x, 2) + Math.pow(clyde.getYTile() - y, 2) > 64) {
			clyde.setTarget(x, y);
		}
		else {
			clyde.setTarget(clydeScatterX, clydeScatterY);
		}
	}
	
	private void setScatterTargets() {
		blinky.setTarget(blinkyScatterX, blinkyScatterY);
		pinky.setTarget(pinkyScatterX, pinkyScatterY);
		inky.setTarget(inkyScatterX, inkyScatterY);
		clyde.setTarget(clydeScatterX, clydeScatterY);
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
			newDirection = KeyCode.UP;
			break;
		case DOWN:
			newDirection = KeyCode.DOWN;
			break;
		case LEFT:
			newDirection = KeyCode.LEFT;
			break;
		case RIGHT:
			newDirection = KeyCode.RIGHT;
			break;
		}
	}
	
	private void move() {
		if (newDirection == null) {
			return;
		}
		switch (newDirection) {
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
