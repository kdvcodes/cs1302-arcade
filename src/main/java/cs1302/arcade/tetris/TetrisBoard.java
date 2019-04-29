package cs1302.arcade.tetris;


import java.util.Random;

import cs1302.arcade.Board;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.util.Duration;

public class TetrisBoard extends Board {
	
	int[] row;
	Timeline t;
	int tileSweep;
	
	public TetrisBoard(int rows, int columns, Tetris game) {
		playField = new TetrisTile[rows][columns];
		t = new Timeline(new KeyFrame(Duration.millis(500), this::clear1));
		t.setCycleCount(5);
		t.setOnFinished(this::clear2);
		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField[i].length; j++) {
				playField[i][j] = new TetrisTile(i, j, game);
				//top two rows are hidden, this statement ensures that
				if (i > 1) {
					getChildren().add(playField[i][j]);
				}
			}
		}
	}
	
	private void dropTile(int row, int column) {
		getTile(row + 1, column).setPiece(getTile(row, column).getPiece());
		getTile(row, column).clearPiece();
	}
	
	public void clearLine(int row[]) {
		this.row = row;
		tileSweep = playField[0].length / 2;
		t.playFromStart();
	}
	
	private void clear1(ActionEvent e) {
		for (int j = 0; j < row.length; j++) {
			getTile(row[j], tileSweep).clearPiece();
			getTile(row[j], tileSweep).update();
			getTile(row[j], tileSweep * (-1) + 9).clearPiece();
			getTile(row[j], tileSweep * (-1) + 9).update();
		}
		tileSweep++;
	}
	
	private void clear2(ActionEvent e) {
		for (int i = row[row.length - 1]; i >= 0; i--) {
			for (int j = 0; j < playField[i].length; j++) {
				if (getTile(i, j).isOccupied(null)) {
					dropTile(i, j);
				}
			}
		}
	}

}
