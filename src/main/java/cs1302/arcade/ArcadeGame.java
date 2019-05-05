package cs1302.arcade;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class ArcadeGame extends Stage {
	
	protected Group game;
	protected Board board;
	protected int score;
	protected int highScore;
	protected final Random generator = new Random();
	
	public Image background;
	
	protected void newGame(ArcadeToolBar menu, Node... n) {
		//ask dr barnes about this, see old piazza post
		//board = new TetrisBoard(rows, columns);
		score = 0;
		game = new Group(new ImageView(background));
		game.getChildren().addAll(n);
		VBox vbox = new VBox(menu, game);
		Scene scene = new Scene(vbox);
		scene.setOnKeyPressed(this::move);
		//scene.getStylesheets().add(getClass().getResource("/tetris/tetris.css").toExternalForm());
		this.setScene(scene);
		this.sizeToScene();
		this.show();
	} // Tetris constructor
	
	public abstract void newGame();
	protected abstract void move(KeyEvent ke);
	public abstract void updateScore(int i);

}
