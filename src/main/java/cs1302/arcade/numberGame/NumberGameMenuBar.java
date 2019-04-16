package cs1302.arcade.numberGame;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * This is the NumberGameMenuBar class
 *
 */
public class NumberGameMenuBar extends MenuBar {
	
	NumberGame numGame;
	
	public NumberGameMenuBar(NumberGame numGame) {
		super();
		this.numGame = numGame;
		
		// MenuBar items initializations
		Menu fileMenu = new Menu("File");
			
		// MenuItems initializations
		MenuItem exit = new MenuItem("Exit 2040 Game");
			
		// Adding items into File menu
		fileMenu.getItems().add(exit);
			
		// Add Menu items into MenuBar
		this.getMenus().add(fileMenu);
			
		exit.setOnAction(e -> {
			this.numGame.close();
		});
	}
	
} // NumberGameMenuBar class
