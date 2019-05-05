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
public abstract class ArcadeToolBar extends ToolBar {
	
	protected ArcadeGame game;
	
	public ArcadeToolBar(ArcadeGame game) {
		// super();
		this.game = game;
		
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
		
		newGame.setOnAction(this::newGame);
		options.setOnAction(this::options);
		help.setOnAction(this::options);
		
		exit.setOnAction(e -> {
			Platform.exit();
		});
	}
	
	protected abstract void newGame(ActionEvent e);
	
	protected abstract void options(ActionEvent e);
	
	protected abstract void help(ActionEvent e);
	
}
