package cs1302.arcade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

	/**
	 * The parent group of this Scene's nodes
	 */
	protected Group game;
	/**
	 * This game's (@code Board}
	 */
	protected Board board;
	/**
	 * The current score
	 */
	protected int score;
	/**
	 * Array of (@code Score} objects representing the current high scores
	 */
	protected Score[] highScores;
	/**
	 * Random number generator for this game.
	 */
	protected final Random generator = new Random();
	/**
	 * The file containing this game's high scores
	 */
	protected File scoreFile;
	/**
	 * FileWriter for altering the score file
	 */
	protected FileWriter scoreWriter;
	/**
	 * Stores if the game is waiting to exit (window close incoming)
	 */
	protected boolean finished;
	/**
	 * The background of this game
	 */
	protected Image background;

	/**
	 * Initializes some common variables for both games, and sets up the stage
	 * 
	 * @param menu the {@code ArcadeToolBar} to use
	 * @param n    the array of {@code Nodes} to add as children
	 */
	protected void start(ArcadeToolBar menu, Node... n) {
		score = 0;
		finished = false;
		game = new Group(new ImageView(background));
		game.getChildren().addAll(n);
		VBox vbox = new VBox(menu, game);
		Scene scene = new Scene(vbox);
		scene.setOnKeyPressed(this::move);
		setOnCloseRequest(this::exit);
		setScene(scene);
		sizeToScene();
		show();
	} // Tetris constructor

	/**
	 * Static method for generating a Score array from a score file
	 * 
	 * @param scoreFile
	 * @return an array of scores from the given File
	 */
	public static Score[] generateScores(File scoreFile) {
		Score[] highScores = new Score[0];
		try {
			Scanner scoreReader = new Scanner(scoreFile);
			scoreReader.useDelimiter("/");
			for (int i = 0; scoreReader.hasNext(); i++) {
				// increase array size by one
				highScores = Arrays.copyOf(highScores, highScores.length + 1);
				highScores[highScores.length - 1] = new Score(
						scoreReader.next(), scoreReader.nextInt());
			}
		} catch (FileNotFoundException e) {
		}
		return highScores;
	}

	/**
	 * Launches a dialog for name input
	 * 
	 * @param e
	 */
	public void submitScore(ActionEvent e) {
		TextInputDialog d = new TextInputDialog();
		d.setHeaderText("Enter your name:");
		((Button) d.getDialogPane().lookupButton(ButtonType.OK))
				.setOnAction((w) -> {
					highScore(d.getEditor().getText());
				});
		d.show();
	}

	/**
	 * Writes the current score to the high score file
	 * 
	 * @param name the name of the player
	 */
	protected void highScore(String name) {
		int i = 0;
		try {
			scoreWriter = new FileWriter(scoreFile, false);
			for (; i < highScores.length
					&& highScores[i].getScore() > score; i++) {
				scoreWriter.write(highScores[i].getName() + "/");
				scoreWriter.write(highScores[i].getScore() + "/");
			}
			// write the current score
			scoreWriter.write(name + "/" + score + "/");
			// write scores less than the current score
			for (; i < highScores.length; i++) {
				scoreWriter.write(highScores[i].getName() + "/");
				scoreWriter.write(highScores[i].getScore() + "/");
			}
			scoreWriter.close();
		} catch (IOException e) {
		}
		finished();
	}

	/**
	 * Saves the current score and launches a new game
	 * 
	 * @param e
	 */
	public abstract void newGame(ActionEvent e);

	/**
	 * Launches the options menu
	 * 
	 * @param e
	 */
	public abstract void options(ActionEvent e);

	/**
	 * Gives info on controls
	 * 
	 * @param e
	 */
	public abstract void help(ActionEvent e);

	/**
	 * @param e
	 */
	public abstract void exit(Event e);

	/**
	 * Restarts the game (if a new game is reuested), and ends this game
	 */
	protected abstract void finished();

	/**
	 * Controls player movement
	 * 
	 * @param ke
	 */
	protected abstract void move(KeyEvent ke);

	/**
	 * Updates the score variable and score text to reflect the current game
	 * state
	 * 
	 * @param i a function is applied to i to alter the score
	 */
	public abstract void updateScore(int i);

}
