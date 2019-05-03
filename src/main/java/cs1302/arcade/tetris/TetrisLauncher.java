package cs1302.arcade.tetris;

import cs1302.arcade.ArcadeGame;
import cs1302.arcade.Board;
import cs1302.arcade.Tile;
import cs1302.arcade.numberGame.NumberGameTile;
import javafx.scene.Group;
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
	private Text[] levels;
	private int selectedX;
	private int selectedY;
	
	private final int rows = 2;
	private final int columns = 5;
	private static final int xStart = 112;
	private static final int yStart = 175;
	private static final int size = 32;
	
	private final Font NES = Font.loadFont(getClass().getResourceAsStream("/tetris/NES.ttf"), 16);
	
	private final Image background = new Image("/tetris/levelSelect.png");
	
	public TetrisLauncher() {
		levels = new Text[10];
		selectedX = 0;
		selectedY = 0;
		g = new Group(new ImageView(background));
		board = new LauncherBoard();
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
		g.getChildren().addAll(title, level);
	}

	private void move(KeyEvent ke) {
		switch (ke.getCode()) {
		case ENTER:
			new Tetris(Integer.valueOf(((Text) board.getSelected().getPiece()).getText()), this);
			close();
			break;
		case DOWN:
			board.setSelected(selectedX, selectedY + 1);
			break;
		case UP:
			board.setSelected(selectedX, selectedY - 1);
			break;
		}
			
	}
	
	//Board and tile are only used for conveince in placing the level numbers
	//The grouping abilities and image display are not used
	class LauncherBoard extends Board {
		
		private Tile selected;

		public LauncherBoard() {
			playField = new LauncherTile[rows][columns];
			for (int i = 0; i < playField.length; i++) {
				for (int j = 0; j < playField[i].length; j++) {
					playField[i][j] = new LauncherTile(i, j);
				}
			}
			selected = playField[0][0];
			selected.update();
		}
		
		public Tile getSelected() {
			return selected;
		}

		public void setSelected(int x, int y) {
			try {
				getTile(x, y).update();
				selected.update();
				selected = getTile(x, y);
			} catch (ArrayIndexOutOfBoundsException e) {
				
			}
		}
		
	}
	
	class LauncherTile extends Tile<Text> {

		public LauncherTile(int row, int column) {
			super(row, column, xStart, yStart, size, 0, null);
			Text t = new Text(getX(), getY(), String.valueOf(row * 5 + column));
			t.setFont(NES);
			t.setFill(Color.rgb(0xf8, 0x38, 0x00));
			t.setUnderline(true);
			setPiece(t);
			g.getChildren().add(t);
			levels[row * 5 + column] = t;
		}

		@Override
		public void update() {
			getPiece().setUnderline(!getPiece().isUnderline());
		}
		
	}

}
