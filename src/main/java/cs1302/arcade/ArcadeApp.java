package cs1302.arcade;

import cs1302.arcade.numberGame.*;
import cs1302.arcade.tetris.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is the ArcadeApp
 */
public class ArcadeApp extends Application {
	
	ArcadeMenuBar arcadeMenuBar;
	HBox arcadeMainFrame;
	
	@Override
	public void start(Stage stage) {
		// Main frame items initializations
		VBox vbox = new VBox();
		arcadeMainFrame = new HBox(vbox);
		
		// Layers and containers initializations
		HBox arcadeMenuBarLayer = new HBox();
		HBox arcadeWelcomeTextLayer = new HBox();
		HBox arcadeChooseGameTextLayer = new HBox();
		VBox arcadeGameButtonContainer = new VBox(30);
		HBox arcadeGameButtonLayer = new HBox();
		
		// Scene items initializations
		arcadeMenuBar = new ArcadeMenuBar(this);
		Region arcadeWelcomeTextLeftPadding = new Region();
		Text arcadeWelcomeText = new Text("\n\nWelcome to Banana Stand's Arcade");
		Region arcadeWelcomeTextRightPadding = new Region();
		Region arcadeChooseGameTextLeftPadding = new Region();
		Text arcadeChooseGameText = new Text("\n\nPlease choose one of the game below. Enjoy!!!\n\n\n\n\n\n\n\n\n\n");
		Region arcadeChooseGameTextRightPadding = new Region();
		Button numGameButton = new Button("2048 Game");
		Button tetrisGameButton = new Button("Tetris Game");
		Region arcadeGameButtonContainerLeftPadding = new Region();
		Region arcadeGameButtonContainerRightPadding = new Region();
		
		// Items customizations
		arcadeWelcomeText.setFont(Font.font("Verdana", 24));
		arcadeWelcomeText.setFill(Color.RED);
		numGameButton.setPrefWidth(150);
		tetrisGameButton.setPrefWidth(150);

		// Setting item sizing and spacing
		HBox.setHgrow(vbox, Priority.ALWAYS);
		HBox.setHgrow(arcadeMenuBar, Priority.ALWAYS);
		HBox.setHgrow(arcadeWelcomeText, Priority.ALWAYS );
		HBox.setHgrow(arcadeWelcomeTextLeftPadding, Priority.ALWAYS);
		HBox.setHgrow(arcadeWelcomeTextRightPadding, Priority.ALWAYS);
		HBox.setHgrow(arcadeChooseGameTextLeftPadding, Priority.ALWAYS);
		HBox.setHgrow(arcadeChooseGameTextRightPadding, Priority.ALWAYS);
		HBox.setHgrow(arcadeGameButtonContainerLeftPadding, Priority.ALWAYS);
		HBox.setHgrow(arcadeGameButtonContainerRightPadding, Priority.ALWAYS);
		
		
		// Layer items assignments
		arcadeMenuBarLayer.getChildren().add(arcadeMenuBar);
		arcadeWelcomeTextLayer.getChildren().addAll(arcadeWelcomeTextLeftPadding, arcadeWelcomeText, arcadeWelcomeTextRightPadding);
		arcadeChooseGameTextLayer.getChildren().addAll(arcadeChooseGameTextLeftPadding, arcadeChooseGameText, arcadeChooseGameTextRightPadding);
		arcadeGameButtonLayer.getChildren().addAll(arcadeGameButtonContainerLeftPadding, arcadeGameButtonContainer, arcadeGameButtonContainerRightPadding);
		arcadeGameButtonContainer.getChildren().addAll(numGameButton, tetrisGameButton);
		
		// Setting the items placements
		vbox.getChildren().addAll(arcadeMenuBarLayer, arcadeWelcomeTextLayer, arcadeChooseGameTextLayer, arcadeGameButtonLayer);
		
		// Setting numGameButton function
		numGameButton.setOnAction(e -> {
			new NumberGame();
		});
		
		// Setting tetrisGameButton function
		tetrisGameButton.setOnAction(e -> {
			new TetrisLauncher();
		});

		// Initialize and setting the scene
		Scene arcadeMainScene = new Scene(arcadeMainFrame);
		
		// Setting the stage
		stage.setScene(arcadeMainScene);
		stage.setHeight(600);
		stage.setWidth(600);
		stage.show();
		
	} // start method

} // ArcadeApp class
