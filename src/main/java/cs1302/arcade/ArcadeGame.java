package cs1302.arcade;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public abstract class ArcadeGame extends Stage {
	
	protected Group game;
	protected Board board;
	protected int score;
	
	public Image background;
	int rows;
	int columns;
	
	public void newGame() {
		//ask dr barnes about this, see old piazza post
		//board = new TetrisBoard(rows, columns);
		score = 0;
		game = new Group(new ImageView(background));
		game.getChildren().add(board);
		Scene scene = new Scene(game);
		scene.setOnKeyPressed(this::move);
		this.setScene(scene);
		this.sizeToScene();
		this.show();
	} // Tetris constructor
	
	protected abstract void move(KeyEvent ke);

}
