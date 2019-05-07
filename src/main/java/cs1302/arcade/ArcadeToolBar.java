package cs1302.arcade;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;

/**
 * This is the Menu Bar for ArcadeApp
 */
public class ArcadeToolBar extends ToolBar {
	
	public ArcadeToolBar(ArcadeGame game) {
		// super();
		
		// MenuBar items initializations
		// Menu fileMenu = new Menu("File");
		
		// MenuItems initializations
		Button newGame = new Button("New Game");
		Button options = new Button("Options");
		Button help = new Button("Help");
		Button exit = new Button("Exit");
		
		newGame.setFocusTraversable(false);
		options.setFocusTraversable(false);
		help.setFocusTraversable(false);
		exit.setFocusTraversable(false);
		
		// Adding items into File menu
		// fileMenu.getItems().add(exit);
		
		// Add Menu items into MenuBar
		getItems().addAll(newGame, options, help, exit);
		
		newGame.setOnAction(game::newGame);
		options.setOnAction(game::options);
		help.setOnAction(game::help);
		exit.setOnAction(game::exit);
		
	}
	
}
