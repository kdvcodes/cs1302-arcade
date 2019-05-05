package cs1302.arcade.tetris;

import cs1302.arcade.ArcadeApp;
import cs1302.arcade.ArcadeGame;
import cs1302.arcade.ArcadeToolBar;
import javafx.event.ActionEvent;

public class TetrisToolBar extends ArcadeToolBar {

	public TetrisToolBar(Tetris game) {
		super(game);
	}

	@Override
	protected void newGame(ActionEvent e) {
		game.newGame();
	}

	@Override
	protected void options(ActionEvent e) {
		System.out.print("b");
		
	}

	@Override
	protected void help(ActionEvent e) {
		System.out.print("b");
		
	}

}
