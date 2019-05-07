package cs1302.arcade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import cs1302.arcade.tetris.TetrisLauncher;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class ArcadeGame extends Stage {
	
	protected Group game;
	protected Board board;
	protected int score;
	protected Score[] highScores;
	protected final Random generator = new Random();
	protected File scoreFile;
	protected FileWriter scoreWriter;
	
	protected Image background;
	
	protected void start(ArcadeToolBar menu, Node... n) {
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
	
	public static Score[] generateScores(File scoreFile) {
		Score[] highScores = new Score[0];
		try {
			Scanner scoreReader = new Scanner(scoreFile);
			scoreReader.useDelimiter("/");
			for (int i = 0; scoreReader.hasNext(); i++) {
				highScores = Arrays.copyOf(highScores, highScores.length + 1);
				highScores[highScores.length - 1] = new Score(scoreReader.next(), scoreReader.nextInt());
			}
		} catch (FileNotFoundException e) {System.out.println("File gone");}
		return highScores;
	}
	
	public void submitScore(ActionEvent e) {
		TextInputDialog d = new TextInputDialog();
		d.setHeaderText("Enter your name:");
		((Button) d.getDialogPane().lookupButton(ButtonType.OK)).setOnAction((w) -> {highScore(d.getEditor().getText());});
		d.show();
	}

	protected void highScore(String name) {
		int i = 0;
		try {
			scoreWriter = new FileWriter(scoreFile, false);
			for (; i < highScores.length && highScores[i].getScore() > score; i++) {
				scoreWriter.write(highScores[i].getName() + "/");
				scoreWriter.write(highScores[i].getScore() + "/");
			}
			scoreWriter.write(name + "/" + score + "/");
			for (; i < highScores.length; i++) {
				scoreWriter.write(highScores[i].getName() + "/");
				scoreWriter.write(highScores[i].getScore() + "/");
			}
			scoreWriter.close();
		} catch (IOException e) {}
		new TetrisLauncher();
		close();
	}
	
	public abstract void newGame(ActionEvent e);
	
	public abstract void options(ActionEvent e);
	
	public abstract void help(ActionEvent e);
	
	protected abstract void move(KeyEvent ke);
	
	public abstract void updateScore(int i);

}
