package cs1302.arcade;

import java.io.File;

import cs1302.arcade.numberGame.*;
import cs1302.arcade.tetris.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is the ArcadeApp class
 */
public class ArcadeApp extends Application {

	HBox arcadeMainFrame;
	HBox arcadeWelcomeTextLayer = new HBox();
	HBox arcadeChooseGameTextLayer = new HBox();
	VBox arcadeGameButtonContainer = new VBox(30);
	HBox arcadeGameButtonLayer = new HBox();
	HBox arcadeHighScoreButtonLayer = new HBox(10);
	Region arcadeWelcomeTextLeftPadding = new Region();
	Text arcadeWelcomeText = new Text("\n\nWelcome to Banana Stand's Arcade");
	Region arcadeWelcomeTextRightPadding = new Region();
	Region arcadeChooseGameTextLeftPadding = new Region();
	Text arcadeChooseGameText = new Text(
			"\n\nPlease choose one of the game below. Enjoy!!!\n\n\n\n\n\n\n\n\n\n");
	Region arcadeChooseGameTextRightPadding = new Region();
	Button numGameButton = new Button("2048 Game");
	Button tetrisGameButton = new Button("Tetris Game");
	Region arcadeGameButtonContainerLeftPadding = new Region();
	Region arcadeGameButtonContainerRightPadding = new Region();
	Region highScoreTableButtonContainerPadding = new Region();
	Button highScore2048TableButton = new Button("2048 High Score");
	Button highScoreTetrisTableButton = new Button("Tetris High Score");
	Text paddingText = new Text("\n\n\n\n\n\n\n\n\n");

	/**
	 * This is the start method
	 * 
	 * @param stage the main stage for the application
	 */
	@Override
	public void start(Stage stage) {
		// Main frame items initializations
		VBox vbox = new VBox();

		// Layers and containers initializations

		// Scene items initializations

		// Items customizations
		arcadeWelcomeText.setFont(Font.font("Verdana", 24));
		arcadeWelcomeText.setFill(Color.RED);
		numGameButton.setPrefWidth(150);
		tetrisGameButton.setPrefWidth(150);

		// Setting item sizing and spacing
		HBox.setHgrow(vbox, Priority.ALWAYS);
		HBox.setHgrow(arcadeWelcomeText, Priority.ALWAYS);
		HBox.setHgrow(arcadeWelcomeTextLeftPadding, Priority.ALWAYS);
		HBox.setHgrow(arcadeWelcomeTextRightPadding, Priority.ALWAYS);
		HBox.setHgrow(arcadeChooseGameTextLeftPadding, Priority.ALWAYS);
		HBox.setHgrow(arcadeChooseGameTextRightPadding, Priority.ALWAYS);
		HBox.setHgrow(arcadeGameButtonContainerLeftPadding, Priority.ALWAYS);
		HBox.setHgrow(arcadeGameButtonContainerRightPadding, Priority.ALWAYS);
		HBox.setHgrow(highScoreTableButtonContainerPadding, Priority.ALWAYS);

		start2(stage, vbox);

	} // start method

	/**
	 * this start method is an extension of original start method
	 * @param stage current stage
	 * @param vbox vbox frame
	 */
	private void start2(Stage stage, VBox vbox) {

		// Layer items assignments
		arcadeWelcomeTextLayer.getChildren().addAll(
				arcadeWelcomeTextLeftPadding, arcadeWelcomeText,
				arcadeWelcomeTextRightPadding);
		arcadeChooseGameTextLayer.getChildren().addAll(
				arcadeChooseGameTextLeftPadding, arcadeChooseGameText,
				arcadeChooseGameTextRightPadding);
		arcadeGameButtonLayer.getChildren().addAll(
				arcadeGameButtonContainerLeftPadding, arcadeGameButtonContainer,
				arcadeGameButtonContainerRightPadding);
		arcadeGameButtonContainer.getChildren().addAll(numGameButton,
				tetrisGameButton);
		arcadeHighScoreButtonLayer.getChildren().addAll(
				highScoreTableButtonContainerPadding, highScore2048TableButton,
				highScoreTetrisTableButton);

		// Setting the items placements
		vbox.getChildren().addAll(arcadeWelcomeTextLayer,
				arcadeChooseGameTextLayer, arcadeGameButtonLayer, paddingText,
				arcadeHighScoreButtonLayer);
		arcadeMainFrame = new HBox(vbox);
		start3(stage);
	}

	/**
	 * this is the extension of start2 method
	 * @param stage current stage
	 */
	private void start3(Stage stage) {

		// Setting numGameButton function
		numGameButton.setOnAction(e -> {
			new NumberGame();
		});

		// Setting tetrisGameButton function
		tetrisGameButton.setOnAction(e -> {
			new TetrisLauncher();
		});

		highScore2048TableButton.setOnAction(e -> {
			highScoreTable(
					new File(getClass().getResource("/2048/highScores.txt")
							.getPath().replaceAll("%20", " "))).show();
		});

		highScoreTetrisTableButton.setOnAction(e -> {
			highScoreTable(
					new File(getClass().getResource("/tetris/highScores.txt")
							.getPath().replaceAll("%20", " "))).show();
		});

		// Initialize and setting the scene
		Scene arcadeMainScene = new Scene(arcadeMainFrame);

		// Setting the stage
		stage.setScene(arcadeMainScene);
		stage.setHeight(700);
		stage.setWidth(600);
		stage.show();
	}

	/**
	 * This method displays the high score tables
	 * @param scoreFile the file being passed in
	 * @return a new stage to show the table of high scores
	 */
	private Stage highScoreTable(File scoreFile) {
		Score[] scores = ArcadeGame.generateScores(scoreFile);
		String[] s = new String[scores.length];
		for (int i = 0; i < s.length; i++) {
			s[i] = (i + 1) + ". " + scores[i].getScore() + " by "
					+ scores[i].getName();
		}
		Stage stage = new Stage();
		stage.setScene(new Scene(
				new VBox(new ListView(FXCollections.observableArrayList(s)))));
		stage.sizeToScene();
		return stage;
	}

} // ArcadeApp class
