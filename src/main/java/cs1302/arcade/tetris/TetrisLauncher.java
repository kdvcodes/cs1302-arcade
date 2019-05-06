package cs1302.arcade.tetris;

import java.io.File;

import cs1302.arcade.ArcadeGame;
import cs1302.arcade.Board;
import cs1302.arcade.Score;
import cs1302.arcade.Tile;
import cs1302.arcade.numberGame.NumberGameTile;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TetrisLauncher extends Stage {
	
	private Group g;
	private LauncherBoard board;
	private int selectedX;
	private int selectedY;
	private Score[] highScores;
	private final File scoreFile = new File(getClass().getResource("/tetris/highScores.txt").getPath().replaceAll("%20", " "));
	private Text names;
	private Text scores;
	private Text levels;
	
	private final int rows = 2;
	private final int columns = 5;
	
	private final Font NES = Font.loadFont(getClass().getResourceAsStream("/tetris/NES.ttf"), 16);
	
	private final Image background = new Image("/tetris/levelSelect.png");
	
	public TetrisLauncher() {
		selectedX = 0;
		selectedY = 0;
		highScores = ArcadeGame.generateScores(scoreFile);
		g = new Group(new ImageView(background));
		board = new LauncherBoard();
		g.getChildren().add(board);
		textSetup();
		Scene scene = new Scene(g);
		scene.setOnKeyPressed(this::move);
		this.setScene(scene);
		this.sizeToScene();
		this.show();
	}
	
	private void textSetup() {
		Text title = new Text(207, 47, "Tetris");
		title.setFont(NES);
		title.setFill(Color.WHITE);
		Text level = new Text(144, 127, "Level");
		level.setFont(NES);
		level.setFill(Color.WHITE);
		Text info = new Text(160, 286, "Name  Score  LV");
		info.setFont(NES);
		info.setFill(Color.WHITE);
		Text rank = new Text(115, 320, "1\n2\n3");
		rank.setLineSpacing(15);
		rank.setFont(NES);
		rank.setFill(Color.WHITE);
		drawScores();
		g.getChildren().addAll(title, level, info, rank, names, scores);
	}
	
	public void drawScores() {
		names = new Text(150, 320, highScores[0].getName() + "\n" + highScores[1].getName() + "\n" + highScores[2].getName());
		names.setLineSpacing(15);
		names.setFont(NES);
		names.setFill(Color.WHITE);
		scores = new Text(255, 320, String.format("%06d\n%06d\n%06d", highScores[0].getScore(), highScores[1].getScore(), highScores[2].getScore()));
		scores.setLineSpacing(15);
		scores.setFont(NES);
		scores.setFill(Color.WHITE);
	}

	private void move(KeyEvent ke) {
		switch (ke.getCode()) {
		case ENTER:
			new Tetris(Integer.valueOf(((Text) board.getSelected().getPiece()).getText()), highScores, this);
			close();
			break;
		case RIGHT:
			if (board.setSelected(selectedX, selectedY + 1)) {
				selectedY++;
			}
			break;
		case LEFT:
			if (board.setSelected(selectedX, selectedY - 1)) {
				selectedY--;
			}
			break;
		case UP:
			if (board.setSelected(selectedX - 1, selectedY)) {
				selectedX--;
			}
			break;
		case DOWN:
			if (board.setSelected(selectedX + 1, selectedY)) {
				selectedX++;
			}
			break;
		}
	}
	
	class LauncherBoard extends Board {
		
		private LauncherTile selected;

		public LauncherBoard() {
			playField = new LauncherTile[rows][columns];
			for (int i = 0; i < playField.length; i++) {
				for (int j = 0; j < playField[i].length; j++) {
					playField[i][j] = new LauncherTile(i, j);
					getChildren().add(playField[i][j]);
					getChildren().add((Text) playField[i][j].getPiece());
				}
			}
			selected = (LauncherTile) playField[0][0];
			selected.select(true);
		}
		
		public Tile getSelected() {
			return selected;
		}

		public boolean setSelected(int x, int y) {
			try {
				((LauncherTile) getTile(x, y)).select(true);
				selected.select(false);
				selected = (LauncherTile) getTile(x, y);
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
			return true;
		}
		
	}
	
	class LauncherTile extends Tile<Text> {

		private static final int xStart = 107;
		private static final int yStart = 153;
		private static final int size = 28;
		private static final int offset = 4;
		private boolean isSelected;
		private final Image selector = new Image("/tetris/selector.png");

		public LauncherTile(int row, int column) {
			super(row, column, xStart, yStart, size, offset, null);
			isSelected = false;
			Text t = new Text(getX() + 5, getY() + 22, String.valueOf(row * 5 + column));
			t.setFont(NES);
			t.setFill(Color.rgb(0xf8, 0x38, 0x00));
			setPiece(t);
		}
		
		public void select(boolean select) {
			isSelected = select;
			update();
		}
		
		@Override
		public void update() {
			if (isSelected) {
				setImage(selector);
			}
			else {
				setImage(null);
			}
		}
		
	}

}
