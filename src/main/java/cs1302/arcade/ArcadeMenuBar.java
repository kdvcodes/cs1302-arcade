package cs1302.arcade;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * This is the Menu Bar for ArcadeApp
 */
public class ArcadeMenuBar extends MenuBar {
	ArcadeApp app;
	
	public ArcadeMenuBar(ArcadeApp app) {
		super();
		this.app = app;
		
		// MenuBar items initializations
		Menu fileMenu = new Menu("File");
		
		// MenuItems initializations
		MenuItem exit = new MenuItem("Exit");
		
		// Adding items into File menu
		fileMenu.getItems().add(exit);
		
		// Add Menu items into MenuBar
		this.getMenus().add(fileMenu);
		
		exit.setOnAction(e -> {
			Platform.exit();
		});
	}
}
