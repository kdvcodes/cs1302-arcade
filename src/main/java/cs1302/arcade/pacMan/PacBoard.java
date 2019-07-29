package cs1302.arcade.pacMan;

import cs1302.arcade.Board;
import cs1302.arcade.tetris.TetrisTile;

public class PacBoard extends Board {
	
	private final Piece[][] layout = 
		{
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.ENERGIZER, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.ENERGIZER, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, null, null, null, null, null, null, null, null, null, null, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, null, null, null, null, null, null, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{null, null, null, null, null, null, Piece.DOT, null, null, null, Piece.MAZE, null, null, null, null, null, null, Piece.MAZE, null, null, null, Piece.DOT, null, null, null, null, null, null},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, null, null, null, null, null, null, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, null, null, null, null, null, null, null, null, null, null, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, null, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.ENERGIZER, Piece.DOT, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, null, null, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.DOT, Piece.ENERGIZER, Piece.MAZE},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.DOT, Piece.MAZE},
			{Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE, Piece.MAZE},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
		};
			
	public PacBoard(PacMan game) {
		this.game = game;
		playField = new PacTile[36][28];
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField[i].length; j++) {
				playField[i][j] = new PacTile(i, j, layout[i][j], game);
				getChildren().add(playField[i][j]);
			}
		}
	}

}
